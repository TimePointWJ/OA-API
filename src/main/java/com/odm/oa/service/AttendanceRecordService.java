package com.odm.oa.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.odm.oa.entity.AttendanceRecord;
import com.odm.oa.entity.AttendanceRecordExcelImport;
import com.odm.oa.entity.ForgetToClockOutApply;
import com.odm.oa.entity.Leaves;
import com.odm.oa.entity.LegworkApply;
import com.odm.oa.entity.OnBusinessApply;
import com.odm.oa.entity.Staff;
import com.odm.oa.mapper.AttendanceRecordExcelImportMapper;
import com.odm.oa.mapper.AttendanceRecordMapper;
import com.odm.oa.mapper.ForgetToClockOutApplyMapper;
import com.odm.oa.mapper.LeavesMapper;
import com.odm.oa.mapper.LegworkApplyMapper;
import com.odm.oa.mapper.OnBusinessApplyMapper;
import com.odm.oa.mapper.StaffMapper;
import com.odm.oa.model.request.AttendanceRecordExcelImportRequest;
import com.odm.oa.model.request.AttendanceRecordRequest;
import com.odm.oa.utils.Constants;

@Service
public class AttendanceRecordService {
	
	private static Logger logger = LogManager.getLogger(AttendanceRecordService.class);

	@Autowired
	private AttendanceRecordMapper attendanceRecordMapper;
	//考勤记录表格导入
	@Autowired
	private AttendanceRecordExcelImportMapper attendanceRecordExcelImportMapper;
	//员工信息
	@Autowired
	private StaffMapper staffMapper;
	//请假信息
	@Autowired
	private LeavesMapper leavesMapper;
	//出差申请
	@Autowired
	private OnBusinessApplyMapper onBusinessApplyMapper;
	//忘打卡申请
	@Autowired
	private ForgetToClockOutApplyMapper forgetToClockOutApplyMapper;
	//外勤申请
	@Autowired
	private LegworkApplyMapper legworkApplyMapper;

	/**
	 * 分页查询
	 * @param request
	 * @return
	 */
    public PageInfo<AttendanceRecord> getPage(AttendanceRecordRequest request) {
    	logger.info("method: getList param：" + JSONObject.toJSONString(request));
    	if(request.getPageNum() == null){
    		request.setPageNum(Constants.PAGE_NUM);
    	}
    	if(request.getPageSize() == null){
    		request.setPageSize(Constants.PAGE_SIZE);
    	}
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<AttendanceRecord> list = attendanceRecordMapper.pageList(request);
        PageInfo<AttendanceRecord> pageInfo = new PageInfo<AttendanceRecord>(list);
        logger.info("method: getList result：" + JSONObject.toJSONString(pageInfo));
        return pageInfo;
    }

    /**
     * 批量导入
     * @param request
     * @return
     */
    @Transactional(rollbackFor=Exception.class)
	public List<AttendanceRecordExcelImportRequest> excelImport(AttendanceRecordExcelImport[] request) {
		
    	List<AttendanceRecordExcelImportRequest> result = new ArrayList<>();//返回的结果
		//卡号判断是否是同一人
		String cardNum = Constants.BLANK;
		//本次导入集合
		List<AttendanceRecordExcelImport> attendanceRecordExcelImportList = new ArrayList<AttendanceRecordExcelImport>();
		for(int i=0;i<request.length;i++){
			//Excel内容
			AttendanceRecordExcelImport aer = request[i];//如果有错返回
			AttendanceRecordExcelImportRequest excelImportRequest = new AttendanceRecordExcelImportRequest();
			//如果员工工号没有则返回
			if(aer.getStaffno() == null || "".equals(aer.getStaffno())){
				excelImportRequest.setResult("员工工号不能为空");
				copyAttendanceRecord(aer,excelImportRequest);
				result.add(excelImportRequest);
				continue;
			}else{
				//判断员工是否存在
				Staff staff = new Staff();
				staff.setStaffNo(aer.getStaffno());
				Staff sta = staffMapper.selectStaffByStaffNo(staff);
				if(sta == null){
					excelImportRequest.setResult("此员工不存在");
					copyAttendanceRecord(aer,excelImportRequest);
					result.add(excelImportRequest);
					continue;
				}
			}
			if(!cardNum.equals(aer.getCardname())){
				attendanceRecordExcelImportList.add(aer);
			}
			cardNum = aer.getCardname();
			if(aer != null){
				//保存Excel内容
				attendanceRecordExcelImportMapper.insert(aer);
			}
		}
		//获取本次导入的月份
		String time = "";
		if(request != null && request.length>0){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			time = sdf.format(request[0].getTime());
		}
		for(AttendanceRecordExcelImport attendanceRecordExcelImport : attendanceRecordExcelImportList){
			//找出此卡号本月的打卡记录
			//attendanceRecordExcelImportMapper.selectAttendanceRecordExcelList();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime ldt = LocalDateTime.parse(time, formatter);
			for(int j=0;j<GetMonthDayCount(ldt.getYear(),ldt.getMonth().getValue());j++){
				//保存考勤记录表
				AttendanceRecord record = new AttendanceRecord();
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String ri = "";
				if((""+j).length() == 1){
					ri = "0"+j;
				}
				//获取当前日
				Date myDate = null;
				try {
					myDate = dateFormat.parse(ldt.getYear()+"-"+ldt.getMonth().getValue()+"-"+ri);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				//获取上班时间记录
				AttendanceRecordExcelImport  start = 
						attendanceRecordExcelImportMapper.selectAttendanceRecordExcel(
								attendanceRecordExcelImport.getStaffno(),myDate,"min");
				//获取下班时间记录
				AttendanceRecordExcelImport  end = attendanceRecordExcelImportMapper.selectAttendanceRecordExcel(attendanceRecordExcelImport.getStaffno(),myDate,"max");
				//没有请假
				boolean isLeaveType = false;
				//没有出差
				boolean isOnBusiness = false;
				//没有忘打卡
				boolean isForgetToClockOut = false;
				//没有外勤
				boolean isLegwork = false;
				//有打卡记录
				boolean isWorkTime = false;
				record.setPersonId(attendanceRecordExcelImport.getStaffno());//人员编号
				record.setAttendanceDate(myDate);//考勤日期
				if(start != null || end != null){
					record.setWorktime(start.getTime());//上班时间
					record.setClosingtime(end.getTime());//下班时间
					record.setIswork(true);//出勤
				}else{
					record.setIswork(false);//没出勤
					isWorkTime = true;//没有打卡记录
					//查看此人员在这天有没有请假
					Leaves leaves = leavesMapper.selectIsLeaveType(attendanceRecordExcelImport.getStaffno(),myDate);
					if(leaves != null){
						record.setLeaveType(leaves.getId());
						isLeaveType = true;//有请假
					}
				}
				DateFormat df = new SimpleDateFormat("HH:mm:ss");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				//是否迟到
				if(start != null){
					LocalDateTime ldt1 = LocalDateTime.parse(sdf.format(start.getTime()), formatter);
					
					Date dt1 = null;
					try {
						dt1 = df.parse(ldt1.getHour() +":"+ldt1.getMinute() +":"+ldt1.getSecond());
					} catch (ParseException e) {
						e.printStackTrace();
					}//将字符串转换为date类型  
		            Date dt2 = null;
					try {
						dt2 = df.parse(Constants.WORK_TIME);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					if(dt1 != null && dt2 != null){
			            if(dt1.getTime() > dt2.getTime())//比较时间大小,如果dt1大于dt2  则迟到
			            {  
			            	record.setLate(true);//迟到
			            }else{
			            	record.setLate(false);//未迟到
			            }
					}
				}
				//是否早退
				if(end != null){
					LocalDateTime ldt2 = LocalDateTime.parse(sdf.format(end.getTime()), formatter);
					
					Date dt1 = null;
					try {
						dt1 = df.parse(ldt2.getHour() +":"+ldt2.getMinute() +":"+ldt2.getSecond());
					} catch (ParseException e) {
						e.printStackTrace();
					}//将字符串转换为date类型  
		            Date dt2 = null;
					try {
						dt2 = df.parse(Constants.CLOSING_TIME);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					if(dt1 != null && dt2 != null){
			            if(dt1.getTime() < dt2.getTime())//比较时间大小,如果dt1大于dt2  则迟到
			            {  
			            	record.setLate(true);//早退
			            }else{
			            	record.setLate(false);//未早退
			            }
					}
				}else{
					record.setLeaveEarly(true);//早退
				}
				//是否出差
				OnBusinessApply onBusinessApply = onBusinessApplyMapper.selectIsOnBusiness(attendanceRecordExcelImport.getStaffno(),myDate);
				if(onBusinessApply != null){
					//出差
					record.setOnBusiness(onBusinessApply.getId());
					isOnBusiness = true;
				}
				//是否忘打卡
				ForgetToClockOutApply forgetToClockOutApply = forgetToClockOutApplyMapper.selectIsForget(attendanceRecordExcelImport.getStaffno(),myDate);
				if(forgetToClockOutApply != null){
					//忘打卡
					record.setForgetToClockOut(forgetToClockOutApply.getId());
					isForgetToClockOut = true;
				}
				//是否外勤
				LegworkApply legworkApply = legworkApplyMapper.selectLegworkApply(attendanceRecordExcelImport.getStaffno(),myDate);
				if(legworkApply != null){
					//外勤
					record.setLegwork(legworkApply.getId());
					isLegwork = true;
				}
				//是否旷职(没有请假 没有出差 没有忘打卡 没有外勤 则旷职)
				if(!isLeaveType && !isOnBusiness && !isForgetToClockOut && !isLegwork && !isWorkTime){
					record.setAbsenteeism(true);
				}
				attendanceRecordMapper.insert(record);
			}
		}
		return result;
	}
    private void copyAttendanceRecord(AttendanceRecordExcelImport aer1,
    		AttendanceRecordExcelImportRequest aer2){
    	aer2.setStaffno(aer1.getStaffno());//员工工号
    	aer2.setCardname(aer1.getCardname());//卡号
    	aer2.setName(aer1.getName());//姓名
    	aer2.setDepartmentid(aer1.getDepartmentid());//部门名称
    	aer2.setTime(aer1.getTime());//时间
    	aer2.setSite(aer1.getSite());//地点
    	aer2.setState(aer1.getState());//状态
    	aer2.setDetection(aer1.getDetection());//通过检测
    }
  //获取月份天数  
    int GetMonthDayCount(int year,int month)  
    {  
        switch(month)  
        {  
            case 1:  
            case 3:  
            case 5:  
            case 7:  
            case 8:  
            case 10:  
            case 12:  
                {  
                    return 31;  
                }  
            case 4:  
            case 6:  
            case 9:  
            case 11:  
                {  
                    return 30;  
                }  
            case 2:  
                {  
                    if(year%4==0)  
                    {  
                        return 28;  
                    }  
                    else  
                    {  
                        if(year%100==0)  
                        {  
                            return 29;  
                        }  
                        else  
                        {  
                            if(year%400==0)  
                            {  
                                return 28;  
                            }  
                            else  
                            {  
                                return 29;  
                            }  
                        }  
                    }  
                }  
            default:  
                {  
                    return 0;  
                }  
        }  
    }  
}
