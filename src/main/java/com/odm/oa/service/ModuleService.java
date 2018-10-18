package com.odm.oa.service;
//package com.zs.axb.service;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.util.StringUtils;
//
//import com.zs.axb.entity.Module;
//
//@Service
//public class ModuleService extends BasicService<Module>{
//	
//	@Autowired etpTModuleDao;
//	
//	@Autowired
//	private EtpTRightInfoDao etpTRightInfoDao;
//	
//	// 删除标识（Y/N） deleteFlag
//	private static final String DELETE_FLAG_N = "N";
//	
//	/**
//	 *  添加菜单
//	 */
//	public int insert(EtpTModuleEx param){
//		String moduleId = etpTModuleDao.save(param);
//		int insertCount = 0;
//		if(!StringUtils.isEmpty(String.valueOf(moduleId))){
//			// 添加菜单权限
//			EtpTRightInfoEx rightInfoEx = new EtpTRightInfoEx();
//			rightInfoEx.setModuleId(String.valueOf(moduleId));
//			rightInfoEx.setHasRight("1");
//			// 创建者、创建时间、更新者、更新时间设置
//			rightInfoEx.setCreateTime(DateUtil.getTime());
//			rightInfoEx.setCreateUser(param.getCreateUser());
//			rightInfoEx.setUpdateUser(param.getUpdateUser());
//			rightInfoEx.setUpdateTime(DateUtil.getTime());
//			// 删除标识
//			rightInfoEx.setDeleteFlag(DELETE_FLAG_N);
//			insertCount = etpTRightInfoDao.insert(rightInfoEx);
//		}
//		return insertCount;
//	}
//	
//	/**
//	 *  更新菜单信息
//	 */
//	public int update(EtpTModuleEx param){
//		return etpTModuleDao.updateByPrimaryKey(param);
//	}
//
//	/**
//	 *  删除菜单
//	 */
//	public int delete(String param){
//		
//		int deleteCount = etpTModuleDao.deleteByPrimaryKey(param);
//		
//		if(deleteCount > 0 ){
//			// 删除菜单权限
//			List<String> deleteList = new ArrayList<>();
//			deleteList.add(param);
//			etpTRightInfoDao.deleteList(deleteList);
//		}
//		
//		return deleteCount;
//	}
//
//	/**
//	 *  批量删除菜单
//	 */
//	public int deleteList(List<String> param){
//		
//		int deleteCount = etpTModuleDao.deleteList(param);
//		
//		if(deleteCount > 0 ){
//			// 删除菜单权限
//			etpTRightInfoDao.deleteList(param);
//		}
//		
//		return deleteCount;
//	}
//	
//	/**
//	 *  菜单一览查询
//	 */
//	public Map<String, Object> selectModuleList(EtpTModuleEx param){
//		Map<String, Object> resultMap = new HashMap<>();
//		// 根据查询条件查询符合条件的数据总数
//		Integer resultCount = etpTModuleDao.selectModuleListCount(param);
//		// 如果数据总数大于0,根据分页信息继续取数据的详细信息
//		if((resultCount != null) && resultCount > 0){
//			resultMap.put("resultCount", resultCount);
//			// 根据分页信息继续取数据的详细信息
//			List<EtpTModuleEx> resultList = etpTModuleDao.selectModuleList(param);
//			// 如果取到数据详细信息,放到返回的map中
//			if(!resultList.isEmpty()){
//				resultMap.put("resultList", resultList);
//			}
//		}
//		return resultMap;
//	}
//	
//	/**
//	 *  通过主key查询一条菜单数据
//	 */
//	public EtpTModuleEx selectByPrimaryKey(String param){
//		return etpTModuleDao.selectByPrimaryKey(param);
//	}
//	
//	/**
//	 *  查询菜单下拉列表
//	 */
//	public Map<String, String> selectMenuList(){
//		Map<String,String> resultMap = new HashMap<>();
//		List<EtpTModuleEx> selectMenuList = etpTModuleDao.selectMenuList();
//		for (EtpTModuleEx etpTModuleEx : selectMenuList) {
//			resultMap.put(etpTModuleEx.getModuleId(), etpTModuleEx.getModuleName());
//		}
//		return resultMap;
//	}
//
//	/**
//	 * 查询菜单下可操作菜单列表
//	 * 
//	 * @return
//	 */
//	public List<EtpTModuleEx> selectOneMenuSubModules(String param) {
//		return etpTModuleDao.selectOneMenuSubModules(param);
//	}
//	
//}
