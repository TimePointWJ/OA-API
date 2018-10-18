package com.odm.oa.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.odm.oa.auth.JwtTokenUtil;
import com.odm.oa.entity.AddressPrivate;
import com.odm.oa.entity.ex.AddressPrivateEx;
import com.odm.oa.entity.ex.StaffEx;
import com.odm.oa.mapper.AddressPrivateMapper;
import com.odm.oa.mapper.StaffMapper;
import com.odm.oa.model.request.StaffPagination;
import com.odm.oa.model.response.BaseResponse;
import com.odm.oa.utils.Constants;

@Service
public class AddressService {
	@Autowired
	private StaffMapper staffMapper;
	@Autowired
	private AddressPrivateMapper addressPrivateMapper;
	/**
	 * 查询员工信息列表
	 */
	public PageInfo<StaffEx> paginationPublic(StaffPagination request) {
		PageHelper.startPage(request.getPageNum(), request.getPageSize());
		// 获取员工列表信息
		List<StaffEx> list = staffMapper.paginationPublic(request);
		PageInfo<StaffEx> pageInfo = new PageInfo<StaffEx>(list);
		return pageInfo;
	}
	/**
	 * 查询员工信息列表
	 */
	public PageInfo<StaffEx> paginationPrivate(StaffPagination request) {
		PageHelper.startPage(request.getPageNum(), request.getPageSize());
		// 获取员工列表信息
		List<StaffEx> list = staffMapper.paginationPrivate(request);
		PageInfo<StaffEx> pageInfo = new PageInfo<StaffEx>(list);
		return pageInfo;
	}
	/**
	 * 查询员工信息列表
	 */
	@Transactional(rollbackFor = Exception.class)
	public void addToPrivate(AddressPrivateEx request,BaseResponse<String> response) {
		request.setDelFlg(Constants.DEL_FLG);
		request.setVersion(Constants.VERSION);
		request.setCreateId(JwtTokenUtil.getUserIdFromContext());
		request.setCreateTime(new Date());
		addressPrivateMapper.insert(request);
		response.setStatusCode(Constants.RESPONSE_STATUS_CODE_SUCCESS);
	}
	/**
	 * 查询员工信息列表
	 */
	@Transactional(rollbackFor = Exception.class)
	public void moveFormPrivate(AddressPrivateEx request,BaseResponse<String> response) {
		// 根据id查询
		AddressPrivate ap=addressPrivateMapper.selectByPrimaryKey(request.getId());
		ap.setDelFlg(Constants.IS_DELETE);
		ap.setVersion(ap.getVersion()+1);
		ap.setUpdateId(JwtTokenUtil.getUserIdFromContext());
		ap.setUpdateTime(new Date());
		addressPrivateMapper.updateByPrimaryKey(ap);
		response.setStatusCode(Constants.RESPONSE_STATUS_CODE_SUCCESS);
	}
}
