package com.odm.oa.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.odm.oa.auth.JwtTokenUtil;
import com.odm.oa.entity.*;
import com.odm.oa.entity.ex.NoticeEx;
import com.odm.oa.entity.ex.ResponseEx;
import com.odm.oa.mapper.*;
import com.odm.oa.model.request.*;
import com.odm.oa.model.response.*;
import com.odm.oa.utils.Constants;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class NoticeService {
    private static Logger logger = LogManager.getLogger(StaffService.class);
    @Value("${filePath}") private String filePath;
    @Autowired
    private NoticeMapper noticeMapper;
    @Autowired
    private NoticeTypeMapper noticeTypeMapper;
    @Autowired
    private NoticeRecordMapper noticeRecordMapper;
    @Autowired
    private FileMapper fileMapper;
    @Autowired
    private StaffMapper staffMapper;
    @Autowired
    private NoticeResponseMapper noticeResponseMapper;
    public Long addNotice(SubmitNoticeRequest request){//添加公告
        Notice notice=new Notice();
        notice.setContent(request.getContent());
        logger.info(notice.getContent());
        notice.setTitle(request.getTitle());
        notice.setType(request.getType());
        notice.setReviewIdOne(request.getReviewId()[0]);
        if(request.getReviewId().length==2){
            notice.setReviewIdTwo(request.getReviewId()[1]);
        }
        notice.setStatus(request.getStatus());
        notice.setDelFlg(false);
        notice.setReviewFlg(false);
        notice.setCreateId(JwtTokenUtil.getUserIdFromContext());
        notice.setCreateTime(new Date());
        notice.setVersion(1);
        noticeMapper.insert(notice);
        addNoticeRecord(request.getReleaseStaff(),notice.getId());
        logger.info(notice.getId());
        logger.info(request.getFileUrl());
        if(request.getId()!=null){//将草稿删除
            noticeMapper.deleteById(request.getId());
        }
        for(String url:request.getFileUrl()){
            com.odm.oa.entity.File file=fileMapper.selectByUrl(url);
            file.setNoticeId(notice.getId());
            fileMapper.updateByPrimaryKey(file);
        }
        return notice.getId();
    }
    public void addNoticeRecord(Long[] releaseStaff,Long noticeId){//添加公告记录
        logger.info(noticeId);
        List<NoticeRecord> noticeRecords=noticeRecordMapper.selectByNoticeId(noticeId);
        if(noticeRecords!=null){
            for(NoticeRecord nr:noticeRecords){//先删除通知记录
                nr.setUpdateTime(new Date());
                nr.setUpdateId(JwtTokenUtil.getUserIdFromContext());
                nr.setDelFlg(true);
                noticeRecordMapper.updateByPrimaryKey(nr);
            }
        }
        NoticeRecord noticeRecord=new NoticeRecord();
        noticeRecord.setNoticeId(noticeId);
        noticeRecord.setCreateId(JwtTokenUtil.getUserIdFromContext());
        noticeRecord.setReadStatus(false);
        noticeRecord.setVersion(1);
        noticeRecord.setDelFlg(false);
        for(int i=0;i<releaseStaff.length;i++){
            noticeRecord.setUserId(releaseStaff[i]);
            noticeRecord.setCreateTime(new Date());
            noticeRecordMapper.insert(noticeRecord);
        }
    }
    public PageInfo<NoticeEx> pagination(NoticePagination request){//分页查看公告
        request.setReviewId(staffMapper.getIdByStaffNo(JwtTokenUtil.getUserIdFromContext()));
        PageHelper.startPage(request.getPageNum(),request.getPageSize());
        List<NoticeEx> list=noticeMapper.pagination(request);
        Long staffId=staffMapper.getIdByStaffNo(JwtTokenUtil.getUserIdFromContext());
        for(NoticeEx ne:list){//判断当前审核人是否已审核
            if(ne.getReviewIdOne()!=null&&ne.getReviewIdOne().equals(staffId)&&ne.getReviewOneStatus()==1){
                ne.setReviewPeopleFlg(true);
            }
            if(ne.getReviewIdTwo()!=null&&ne.getReviewIdTwo().equals(staffId)&&ne.getReviewTwoStatus()==1){
                ne.setReviewPeopleFlg(true);
            }
        }
        PageInfo<NoticeEx> pageInfo=new PageInfo<NoticeEx>(list);
        return pageInfo;
    }
    public void deleteNotice(Long[] request){//删除公告
        for(Long id:request){
            Notice notice=noticeMapper.selectByPrimaryKey(id);
            if(notice!=null){
                notice.setDelFlg(true);
                notice.setUpdateId(JwtTokenUtil.getUserIdFromContext());
                notice.setUpdateTime(new Date());
                notice.setVersion(notice.getVersion()==null?2:notice.getVersion()+1);
                noticeMapper.updateByPrimaryKey(notice);
            }
        }
    }
    public LookOneNoticeResponse lookOneNotice(NoticeDetialRequest request){//查看公告详情
        addReadStatus(request);
        LookOneNoticeResponse lookOneNoticeResponse=new LookOneNoticeResponse();
        lookOneNoticeResponse.setNoticeEx(noticeMapper.lookOneNotice(request.getNoticeId()));
        List<ResponseFile> file=fileMapper.getFileByNotice(request.getNoticeId());
        lookOneNoticeResponse.setFile(file);
        return lookOneNoticeResponse;
    }
    public void addReadStatus(NoticeDetialRequest request){//添加阅读状态
        NoticeRecord noticeRecord=noticeRecordMapper.selectByNoticeIdStaffNo(request);
        if(null!=noticeRecord&&!noticeRecord.getReadStatus()){
            logger.info("添加阅读状态");
            noticeRecord.setReadStatus(true);
            noticeRecord.setVersion(noticeRecord.getVersion()+1);
            noticeRecord.setUpdateId(JwtTokenUtil.getUserIdFromContext());
            noticeRecord.setUpdateTime(new Date());
            noticeRecord.setReadTime(noticeRecord.getReadTime()==null?new Date():noticeRecord.getReadTime());//阅读时间
            noticeRecordMapper.updateByPrimaryKey(noticeRecord);
        }
    }
    public List<PersonNotice> getPersonalNotice(String request){//查看首页个人公告列表
        List<PersonNotice> personNotices=new ArrayList<>();
        //分别取收到公告、草稿公告、已发布公告
        List<NoticeEx> personalNotice=noticeMapper.getPersonalNotice(request);
        List<NoticeEx> savedNotice=noticeMapper.getSavedNotice(request);
        List<NoticeEx> releasedNotice=noticeMapper.getReleasedNotice(request);
        List<NoticeEx> toReviewNotice=noticeMapper.getToReviewNotice(request);
        if(personalNotice.size()>0){//收到的公告
            PersonNotice personNotice=new PersonNotice();
            personNotice.setName("收到公告");
            personNotice.setNotice(personalNotice);
            personNotices.add(personNotice);
        }
        if(savedNotice.size()>0){//草稿的公告
            PersonNotice personNotice1=new PersonNotice();
            personNotice1.setName("草稿公告");
            personNotice1.setNotice(savedNotice);
            personNotices.add(personNotice1);
        }
        if(releasedNotice.size()>0){//已发布的公告
            PersonNotice personNotice2=new PersonNotice();
            personNotice2.setName("已发布公告");
            personNotice2.setNotice(releasedNotice);
            personNotices.add(personNotice2);
        }
        if(toReviewNotice.size()>0){//已发布的公告
            PersonNotice personNotice3=new PersonNotice();
            personNotice3.setName("待审核公告");
            personNotice3.setNotice(toReviewNotice);
            personNotices.add(personNotice3);
        }
        return personNotices;
    }
    public void writeToService(MultipartFile[] multfilel,BaseResponse<List<String>> response){
        try {
            List<String> urls=new ArrayList<>();
            logger.info(multfilel);
            for(MultipartFile multfile :multfilel){
                boolean exitFlg=false;
                logger.info("==写入服务器开始==");
                logger.info(multfile.getOriginalFilename());
                FileInputStream fis=(FileInputStream) multfile.getInputStream();
                String pathName=multfile.getOriginalFilename();
                int index=pathName.indexOf('.');
                String fileName=pathName;
                //pathName=filePath+pathName.substring(0,index)+new SimpleDateFormat("HHmmss").format(new Date())+pathName.substring(index);
                pathName=filePath+"/~$~"+new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+"/"+fileName;
                logger.info("写去服务器文件路径》》"+pathName);
                File file=new File(pathName);
                if(!file.getParentFile().exists()){
                    file.getParentFile().mkdirs();//创建目录
                }
                if(file.exists()){
                    exitFlg=true;
                }else{
                    file.createNewFile();
                }
                FileOutputStream fos=new FileOutputStream(file);//指定文件输出数据
                int data=-1;
                while((data=fis.read())!=-1)
                {
                    fos.write(data);//往要输出文件写数据
                }
                fos.close();
                fis.close();
                if(!exitFlg){//文件存在不添加进数据库
                    com.odm.oa.entity.File inserFile=new com.odm.oa.entity.File();
                    inserFile.setFileName(fileName);
                    inserFile.setFileUrl(pathName);
                    inserFile.setDelFlg(false);
                    inserFile.setVersion(1);
                    inserFile.setCreateId(JwtTokenUtil.getUserIdFromContext());
                    inserFile.setCreateTime(new Date());
                    fileMapper.insert(inserFile);
                }
                urls.add(pathName);
            }
            /*logger.info("==写入服务器开始==");
            FileInputStream fis=(FileInputStream) multfile.getInputStream();
            String pathName=multfile.getOriginalFilename();
            int index=pathName.indexOf('.');
            String fileName=pathName;
            //pathName=filePath+pathName.substring(0,index)+new SimpleDateFormat("HHmmss").format(new Date())+pathName.substring(index);
            pathName=filePath+"~$~"+new SimpleDateFormat("HHmmss").format(new Date())+fileName;
            logger.info("写去服务器文件路径》》"+pathName);
            File file=new File(pathName);
            if(!file.exists()){
                file.createNewFile();
            }
            FileOutputStream fos=new FileOutputStream(file);//指定文件输出数据
            int data=-1;
            while((data=fis.read())!=-1)
            {
                fos.write(data);//往要输出文件写数据
            }
            fos.close();
            fis.close();
            com.odm.oa.entity.File inserFile=new com.odm.oa.entity.File();
            inserFile.setFileName(fileName);
            inserFile.setFileUrl(pathName);
            inserFile.setDelFlg(false);
            inserFile.setVersion(1);
            inserFile.setCreateId(JwtTokenUtil.getUserIdFromContext());
            inserFile.setCreateTime(new Date());
            fileMapper.insert(inserFile);*/
            response.setStatusCode(Constants.RESPONSE_STATUS_CODE_SUCCESS);
            response.setResponseData(urls);
            logger.info("==写入成功==filePath>>"+urls);
        } catch (IOException e) {
            logger.info("==catch，写入失败==");
            logger.error(e);
            response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
            response.setStatusMsg("上传失败!");
            e.printStackTrace();
        }
    }
    public void reviewNotice(Long[] id){//审核公告
        Notice notice=noticeMapper.selectByPrimaryKey(id[0]);
        logger.info(notice.getReviewTwoStatus());
        logger.info(notice.getStatus());
        Long staffId=staffMapper.getIdByStaffNo(JwtTokenUtil.getUserIdFromContext());
        if(notice.getReviewIdOne().equals(staffId)){//审核人1审核
            if(notice.getReviewTwoStatus()==1||notice.getReviewIdTwo()==null){
                notice.setReviewFlg(true);
                notice.setStatus(3);
            }
            notice.setReviewOneStatus(1);
            notice.setUpdateTime(new Date());
            notice.setUpdateId(JwtTokenUtil.getUserIdFromContext());
            notice.setVersion(notice.getVersion()==null?2:notice.getVersion()+1);
            noticeMapper.updateByPrimaryKey(notice);
        }
        if(notice.getReviewIdTwo()!=null&&notice.getReviewIdTwo().equals(staffId)){//审核人2审核
            if(notice.getReviewOneStatus()==1){
                notice.setReviewFlg(true);
                notice.setStatus(3);
            }
            notice.setReviewTwoStatus(1);
            notice.setUpdateTime(new Date());
            notice.setUpdateId(JwtTokenUtil.getUserIdFromContext());
            notice.setVersion(notice.getVersion()==null?2:notice.getVersion()+1);
            noticeMapper.updateByPrimaryKey(notice);
        }
        /*if(notice!=null){
            notice.setReviewFlg(true);
            notice.setStatus(3);
            notice.setReviewId(staffMapper.getIdByStaffNo(JwtTokenUtil.getUserIdFromContext()));
            notice.setUpdateTime(new Date());
            notice.setUpdateId(JwtTokenUtil.getUserIdFromContext());
            notice.setVersion(notice.getVersion()==null?2:notice.getVersion()+1);
            noticeMapper.updateByPrimaryKey(notice);
        }*/
    }
    public List<Staff> listAuthor(){
        return staffMapper.listAuthor();
    }//发布者一览
    public void addResponse(SubmitResponseRequest request){//添加回复
        NoticeResponse noticeResponse =new NoticeResponse();
        noticeResponse.setContent(request.getContent());
        noticeResponse.setResponseToId(request.getResponseToId());
        noticeResponse.setCreateId(JwtTokenUtil.getUserIdFromContext());
        noticeResponse.setCreateTime(new Date());
        noticeResponse.setDelFlg(false);
        noticeResponse.setVersion(1);
        noticeResponseMapper.insert(noticeResponse);
    }
    public void deleteResponse(Long[] request){//删除回复
        for(Long id:request){
            NoticeResponse noticeResponse=noticeResponseMapper.selectByPrimaryKey(id);
            if(noticeResponse!=null){
                noticeResponse.setDelFlg(true);
                noticeResponse.setUpdateId(JwtTokenUtil.getUserIdFromContext());
                noticeResponse.setUpdateTime(new Date());
                noticeResponse.setVersion(noticeResponse.getVersion()+1);
                noticeResponseMapper.updateByPrimaryKey(noticeResponse);
            }
        }
    }
    public PageInfo<ResponseEx> pageResponse(NoticeResponsePagination request){//分页查看公告
        PageHelper.startPage(request.getPageNum(),request.getPageSize());
        List<ResponseEx> list=noticeResponseMapper.pagination(request);
        PageInfo<ResponseEx> pageInfo=new PageInfo<ResponseEx>(list);
        return pageInfo;
    }
    public List<Staff> listReviewPerson(){//获取可以审核的清单
        return staffMapper.listReviewPerson();
    }
    public SubmitNoticeRequest getEditData(Long id){//获取公告信息（编辑用）
        SubmitNoticeRequest submitNoticeRequest=noticeMapper.getEditData(id);
        Long[] releaseStaff=noticeMapper.getEditDataStaff(id);
        Notice notice=noticeMapper.selectByPrimaryKey(id);
        List<Long> reviewId=new ArrayList<>();
        reviewId.add(notice.getReviewIdOne());
        if(notice.getReviewIdTwo()!=null){
            reviewId.add(notice.getReviewIdTwo());
        }
        Long[] ids=new Long[reviewId.size()];
        reviewId.toArray(ids);
        submitNoticeRequest.setReviewId(ids);
        submitNoticeRequest.setReleaseStaff(releaseStaff);
        return submitNoticeRequest;
    }
    public ChartCount getNoticeCount(){//获取图形数据
        ChartCount chartCount=new ChartCount();
        List<PieChartCount> pieChartCounts=new ArrayList<>();
        List<NoticeType> noticeType=noticeTypeMapper.selectAll();
        for(NoticeType nt:noticeType){//获取饼图数据
            Long number=noticeMapper.getPieChartCount(JwtTokenUtil.getUserIdFromContext(),nt.getId());
            logger.info(number);
            if(number>0){//该类别有数据
                PieChartCount pieChartCount=new PieChartCount();
                pieChartCount.setCountNumber(number);
                pieChartCount.setTypeId(nt.getId());
                pieChartCount.setTypeName(nt.getName());
                pieChartCounts.add(pieChartCount);
            }
        }
        chartCount.setPieChartCount(pieChartCounts);
        return chartCount;
    }
}
