package com.odm.oa.service;

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
import com.odm.oa.auth.JwtTokenUtil;
import com.odm.oa.entity.Department;
import com.odm.oa.entity.Goods;
import com.odm.oa.entity.ex.GoodsEx_WJ;
import com.odm.oa.mapper.GoodsMapper;
import com.odm.oa.mapper.ModuleMapper;
import com.odm.oa.mapper.ModuleRightMapper;
import com.odm.oa.model.request.DepartmentRequest;
import com.odm.oa.model.request.GoodsPagination_WJ;
import com.odm.oa.model.request.GoodsRequest_WJ;
import com.odm.oa.model.response.BaseResponse;
import com.odm.oa.utils.Constants;

@Service
public class GoodsService_WJ {
	private static Logger logger = LogManager.getLogger(GoodsService_WJ.class);
	@Autowired
	private GoodsMapper goodsMapper;
	@Autowired
    private ModuleMapper moduleMapper;
    
    @Autowired
    private ModuleRightMapper moduleRightMapper;
    /**
	 * 查询商品列表
	 */
	public PageInfo<GoodsEx_WJ> pagination(GoodsPagination_WJ request) {
		PageHelper.startPage(request.getPageNum(), request.getPageSize());
		// 获取员工列表信息
		List<GoodsEx_WJ> list = goodsMapper.pagination(request);
		PageInfo<GoodsEx_WJ> pageInfo = new PageInfo<GoodsEx_WJ>(list);
		return pageInfo;
	}

	/**
	 * 查询所有商品的信息
	 * 
	 * @param padinatioBase
	 * @return pageInfo
	 */
	public  BaseResponse<PageInfo<GoodsEx_WJ>> selectRoleList(GoodsPagination_WJ goodsPagination) {
        PageHelper.startPage(goodsPagination.getPageNum(), goodsPagination.getPageSize());
		// 方法返回对象初始化
		BaseResponse<PageInfo<GoodsEx_WJ>> result = new BaseResponse<>();
		PageInfo<GoodsEx_WJ> pageInfo = new PageInfo<>();
		
    	logger.info("method: selectRoleList param：" + JSONObject.toJSONString(goodsPagination));
    	try {
    		// 翻页信息获取
    		PageHelper.startPage(goodsPagination.getPageNum(), goodsPagination.getPageSize());
    		// 根据查询条件查询角色信息
    		List<GoodsEx_WJ> list = goodsMapper.selectRoleList(goodsPagination);
//    		list.get(0).getGoodstypes();
    		logger.info("method: selectRoleList result：" + JSONObject.toJSONString(list));
    		// 将查询结果设置到返回对象中
    		pageInfo.setList(list);
    		pageInfo = new PageInfo<>(list);
		} catch (Exception e) {
	    	logger.error("查询所有后台角色的信息异常", e);
		}
    	result.setResponseData(pageInfo);
		return result;
	}
	/**
	 * 查询商品的详细信息
	 * 
	 * @param systemRoleEx
	 * @return
	 */
	public BaseResponse<Goods> roleDetail(GoodsEx_WJ goodsEx) {
		logger.info("method: roleDetail param：" + JSONObject.toJSONString(goodsEx));
		// 方法返回对象初始化
		BaseResponse<Goods> result = new BaseResponse<>();
		try {
			// 根据查询条件查询角色信息
			Goods detail = goodsMapper.selectByPrimaryKey(goodsEx.getGoodsId());
			logger.info("method: roleDetail result：" + JSONObject.toJSONString(detail));
			if(detail == null){
				logger.warn("查询后台角色的详细信息为空");
	        	result.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
			}else{
				result.setResponseData(detail);
	        	result.setStatusCode(Constants.RESPONSE_STATUS_CODE_SUCCESS);				
			}
			
		} catch (Exception e) {
			logger.error("查询后台角色的详细信息异常", e);
        	result.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
		}
		// 将查询结果设置到返回对象中
		return result;
	}
	/**
	 * 添加sp
	 */
	@Transactional(rollbackFor = Exception.class)
	public void insertGoods(Goods request, BaseResponse<String> response) {
		
			response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
			response.setStatusMsg(Constants.Goods_NAME_ALREADY_EXIST);
		
			request.setDelFlg(Constants.DEL_FLG);
			request.setVersion(Constants.VERSION);
			request.setCreateId(JwtTokenUtil.getUserIdFromContext());// 新员工自己添加的
			request.setCreateTime(new Date());
			goodsMapper.insert(request);
			// 添加成功
			response.setStatusCode(Constants.RESPONSE_STATUS_CODE_SUCCESS);
		
	}

	/**
	 * 修改sp
	 */
	@Transactional(rollbackFor = Exception.class)
	public void updateGoods(GoodsRequest_WJ request, BaseResponse<String> response) {
		
			
			request.setVersion(request.getVersion() + 1);
			request.setUpdateId(JwtTokenUtil.getUserIdFromContext());
			request.setUpdateTime(new Date());
			goodsMapper.updateByPrimaryKey(request);
			// 修改成功
			response.setStatusCode(Constants.RESPONSE_STATUS_CODE_SUCCESS);
		
	}

	/**
	 * 删除sp
	 */
	@Transactional(rollbackFor = Exception.class)
	public void deleteGoods(Long[] ids) {
		for (Long id : ids) {
			Goods goods = goodsMapper.selectByPrimaryKey(id);
			goods.setDelFlg(Constants.IS_DELETE);
			goods.setVersion(goods.getVersion() + 1);
			goods.setUpdateId(JwtTokenUtil.getUserIdFromContext());
			goods.setUpdateTime(new Date());
			goodsMapper.updateByPrimaryKey(goods);
		}
	}
//	/**
//	 * 修改商品信息
//	 */
//	@Transactional(rollbackFor = Exception.class)
//	public void updateGoods(GoodsRequest_WJ request, BaseResponse<String> response) {
//			response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
//			response.setStatusMsg(Constants.MOBILE_PHONE_ALREADY_EXIST);
//				response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
//				response.setStatusMsg(Constants.STAFF_NO_ALREADY_EXIST);
//				request.setUpdateId(JwtTokenUtil.getUserIdFromContext());
//				request.setUpdateTime(new Date());
//				goodsMapper.updateByPrimaryKey(request);
//				response.setStatusCode(Constants.RESPONSE_STATUS_CODE_SUCCESS);
//	}
//	/**
//	 * 删除商品信息
//	 */
//	@Transactional(rollbackFor = Exception.class)
//	public void deleteGoods(Long[] ids) {
//		for (Long id : ids) {
//			Goods goods = goodsMapper.selectByPrimaryKey(id);
//			goods.setDelFlg(Constants.IS_DELETE);
//			goods.setVersion(goods.getVersion() + 1);
//			goods.setUpdateId(JwtTokenUtil.getUserIdFromContext());
//			goods.setUpdateTime(new Date());
//			goodsMapper.updateByPrimaryKey(goods);
//		}
//	}
//	/**
//	 * 删除sp
//	 */
//	@Transactional(rollbackFor = Exception.class)
//	public void deleteGoods(Long[] ids) {
//		for (Long id : ids) {
//			Goods goods = goodsMapper.selectByPrimaryKey(id);
//			goods.setDelFlg(Constants.IS_DELETE);
//			goods.setVersion(goods.getVersion() + 1);
//			goods.setUpdateId(JwtTokenUtil.getUserIdFromContext());
//			goods.setUpdateTime(new Date());
//			goodsMapper.updateByPrimaryKey(goods);
//		}
//	}
    /**
     * 批量删除
     * @param Id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Integer delList(Long[] Id) {
        int count = 0;
        for(Long id : Id){
            Goods goods = goodsMapper.selectByPrimaryKey(id);
            if(goods != null){
            	goods.setVersion((goods.getVersion() == null ? 0 : goods.getVersion()) +1);
            	goods.setUpdateTime(new Date());
            	goods.setDelFlg(Constants.IS_DELETE);
            	goodsMapper.updateByPrimaryKey(goods);
                count++;
            }
        }
        return count;
    }
  
    public PageInfo<GoodsEx_WJ> selectByConditions(GoodsPagination_WJ request){
		PageHelper.startPage(request.getPageNum(), request.getPageSize());
		List<GoodsEx_WJ> list = goodsMapper.selectAllByConditions(request);
		PageInfo<GoodsEx_WJ> pageInfo = new PageInfo<GoodsEx_WJ>(list);
		return pageInfo;
	}
	public BaseResponse<List<Goods>> getGoods(){
		BaseResponse<List<Goods>> response=new BaseResponse<>();
		response.setResponseData(goodsMapper.selectAll());
		return response;
	}
//	public boolean checkGoodsName(String insertOrUpdateFlg, GoodsRequest_WJ request) {
//		Goods goods = goodsMapper.selectByName(request.getName());
//		if (Constants.INSERT_FLG.equals(insertOrUpdateFlg)) {
//			// 如果是添加，只要存在相同就不行，
//			if (goods != null) {
//				return true;
//			}
//		} else if (Constants.UPDATE_FLG.equals(insertOrUpdateFlg)) {
//			// 如果是修改，那就除了自己以外，如果查询的dept不是他本身，就是别人的重复
//			if (goods != null && goods.getGoodsId() != request.getId()) {
//				return true;
//			}
//		}
//		return false;
//	}
}