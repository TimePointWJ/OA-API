package com.odm.oa.service;

import java.util.ArrayList;
import java.util.List;

import com.odm.oa.entity.*;
import com.odm.oa.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.odm.oa.model.request.AreaPagination;
import com.odm.oa.model.response.BaseResponse;
import com.odm.oa.model.response.DictionaryListResponse;
import com.odm.oa.utils.Constants;
@Service
public class CommonService {
    @Autowired
    private DictionaryMapper dictionaryMapper;
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private PositionMapper positionMapper;
    @Autowired
    private AreaMapper areaMapper;
    @Autowired
    private ProjectMapper projectMapper;
	@Autowired
	private StaffMapper staffMapper;
	/**
	 * 根据数据字典类型获取数据字典数据列表
	 * 
	 * @param param
	 * @return deleteCount
	 */
	public BaseResponse<List<DictionaryListResponse>> getDictionaryList(String codeType) {
		BaseResponse<List<DictionaryListResponse>> result = new BaseResponse<>();
		//返回list
		List<DictionaryListResponse> listDic=new ArrayList<>();
		List<Dictionary> dictionary = new ArrayList<>();
		try {
			// 查询职务信息
			dictionary = dictionaryMapper.selectByCodeType(codeType);
			for (Dictionary dictionary2 : dictionary) {
				DictionaryListResponse dic=new DictionaryListResponse();
				dic.setKey(dictionary2.getCodeKey());
				dic.setValue(dictionary2.getValue());
				listDic.add(dic);
			}
			result.setStatusCode(Constants.RESPONSE_STATUS_CODE_SUCCESS);
		} catch (Exception e) {
			result.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
		}
		result.setResponseData(listDic);
		return result;
	}
	
	/**
	 * 获取所有部门信息
	 * @return
	 */
	public List<Department> getDepartmentList() {
		return departmentMapper.selectAll();
	}
	/**
	 * 获取所有项目息
	 * @return
	 */
	public List<Project> getProjectList() {
		return projectMapper.selectAll();
	}
	
	/**
	 * 获取所有职位信息
	 */
	public List<Position> getPositionList(){
		return positionMapper.selectAll();
	}
	/**
     * 获取所有地域列表
     */
    public List<Area> areaList(AreaPagination areaPagination) {
        return areaMapper.areaList(areaPagination);
    }
	/**
	 * 获取所有员工列表
	 */
	public List<Staff> staffList() {
		return staffMapper.selectAll();
	}
}
