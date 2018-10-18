package com.odm.oa.utils;

import java.util.HashMap;
import java.util.Map;
/**
 * <p>Title: Constants.java</p>
 * <p>Description: 常量类</p>
 * <p>Company: </p>
 * @author HJR
 * @date 2017年12月8日上午22:51:09
 */
public class Constants{
	
	 private Constants() {
		    throw new IllegalStateException("Constants class");
	 }
	 
	/**
	 * 业务操作成功
	 */
	public static final Integer RESPONSE_STATUS_CODE_SUCCESS = 200;
	
	/**
	 * 业务操作成功消息
	 */
	public static final String RESPONSE_STATUS_MSG_SUCCESS = "success";
	/**
	 * 业务操作失败消息
	 */
	public static final String RESPONSE_STATUS_MSG_ERROR = "error";
	
    /**
     * 业务操作失败
     */
    public static final Integer RESPONSE_STATUS_CODE_FAILED = 201;
    
    /**
     * 参数错误
     */
    public static final Integer RESPONSE_STATUS_CODE_FAILED_202 = 202;
    /**
     * 用户未通过审核
     */
    public static final Integer RESPONSE_STATUS_CODE_FAILED_203 = 203;
	/**
	 * 用户未通过审核
	 */
	public static final String STAFF_NO_CHECK_STATUS = "用户未通过审核";
    
    /**
     * 参数错误消息
     */
    public static final String
			ERROR_MSG_202 = "参数错误";
    
    /**
     * 后台系统异常
     */
    public static final Integer RESPONSE_STATUS_CODE_FAILED_999 = 999;
    
    /**
     * 后台系统异常消息
     */
    public static final String ERROR_MSG_999 = "后台系统异常";

	public static final String SESSION_SECURITY_CODE = "sessionSecCode";
	public static final String SESSION_USER = "sessionUser";
	public static final String SESSION_ROLE_RIGHTS = "sessionRoleRights";
	public static final String SESSION_MENULIST = "menuList";			//当前菜单
	public static final String SESSION_ALLMENULIST = "allmenuList";		//全部菜单
	public static final String SESSION_USERROL = "USERROL";				//用户对象
	public static final String SESSION_USERNAME = "USERNAME";			//用户名
	public static final String LOGIN = "/login";						//登录地址
	
	public static final String SUCCESS = "success";
	public static final String ERROR = "error";
	
	// 下拉列表Map
	public static final String COMBO_BOX_TYPE_DEPT = "deptMap";//部门
	public static final String COMBO_BOX_TYPE_USER = "userMap";//用户
	public static final String COMBO_BOX_TYPE_MODULE = "moduleMap";//菜单
	public static final String COMBO_BOX_TYPE_ROLE = "roleMap";//角色
	public static final String COMBO_BOX_TYPE_DICTIONARY = "dictionaryMap";//数据字典
	
	// 下拉列表数据类型(0:String类型型  1:对象类型)
	public static final String COMBO_BOX_MAP_TYPE_0 = "0";
	public static final String COMBO_BOX_MAP_TYPE_1 = "1";
	//删除标识
	public static final boolean DEL_FLG = false;
	public static final Boolean IS_DELETE = true;
	//版本号
	public static final int VERSION = 1;
	
	public static final String  ACCESS_TOKEN_KBN_PARENT = "1";//家长端token
	public static final String ACCESS_TOKEN_KBN_TEACHER = "2";//校务端token
	
//	public static final String APPID_PARENT = "wx66c4e2c76a15ae56";//家长端appid  test
	public static final String APPID_PARENT = "wx326173030da9ae8b";//家长端appid
//	public static final String APPID_TEACHER = "wxa87d954fd1f2eac5";//校务端appid test
	public static final String APPID_TEACHER = "wxb7ad5f9f9421dd9b";//校务端appid

//	public static final String SECRET_PARENT = "1dd6c07c712c56eb24562da82d6ea5b2";//家长端secret  test
	public static final String SECRET_PARENT = "ae56ff5b88effc182193c8a786777d47";//家长端secret
//	public static final String SECRET_TEACHER = "da8cab6cc02cd3e910d117abdf66a4be";//校务端secret test
	public static final String SECRET_TEACHER = "73bace143248a8f7e50596f5e638aa3f";//校务端secret 
	
	//微信公众号模板消息推送
	public static final String GET_WECHAT_TOKEN_URL_PARENT = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + APPID_PARENT + "&secret=" + SECRET_PARENT;
	public static final String GET_WECHAT_TOKEN_URL_TEACHER = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + APPID_TEACHER + "&secret=" + SECRET_TEACHER;
	public static String ACCESS_TOKEN_TEACHER = "";
	public static String ACCESS_TOKEN_PARENT = "";
	public static final String PUSH_URL_REQUEST = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";
	public static final String TOUSER = "touser";
	public static final String TEMPLATE_ID = "template_id";
	public static final String LINK_URL = "url";

	public static final String VALUE = "value";
	public static final String COLOR = "color";
	
//	public static final String SERVER_IP = "118.31.73.225";	
	public static final String SERVER_IP = "anxuebao.one-dream.com.cn";
	
	// 家长端推送详情URL
	public static final String PARENT_URL_DETAIL_BASE_PRE = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + APPID_PARENT + "&redirect_uri=";
	public static final String PARENT_URL_DETAIL_BASE_END = "&response_type=code&scope=snsapi_base";
	public static final String PARENT_ATTENDANCE_NOTICE_URL = "http://" + SERVER_IP + "/h5-anxuebao-parents/html/query_entry_exit_records_detail_ts.html?";
	public static final String PARENT_HOMEWORK_REMINDING_URL = "http://" + SERVER_IP + "/h5-anxuebao-parents/html/use_homework_detail_ts.html?";
	
	// 校务端推送详情URL
	public static final String TEACHER_URL_DETAIL_BASE_PRE = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + APPID_TEACHER + "&redirect_uri=";
	public static final String TEACHER_URL_DETAIL_BASE_END = "&response_type=code&scope=snsapi_base";
	
	/************************************ 家长端 模板ID start********************************************/  
	//考勤通知
	public static final String TEMPLATE_ID_ATTENDANCE_NOTICE = "5vgeMiyhrRTMfToQJZHbMjqLaHQCJ6AeK2z5dzETF80"; 
//	public static final String TEMPLATE_ID_ATTENDANCE_NOTICE = "s0TLyop5t-wbTTMsNmhUZLUBbv2imut8wGZeMAoX7G8"; //test
	//作业提醒
	public static final String TEMPLATE_ID_HOMEWORK_REMINDING = "m2aeIbOUqkvdmrsz8ZCLTj-BfuG9u0SB0xWxOXJ4K4g";
//	public static final String TEMPLATE_ID_HOMEWORK_REMINDING = "s0dJ006-mDH7uJ4MOB1rzXR_4Lz1qPbStHA1VO87TH4"; //test
	//家长会议通知
	public static final String TEMPLATE_ID_NOTICE_OF_THE_PARENTS_MEETING= "feDX17MoKouOIyfTsGbkQrghUniEBOC5b50i407iymk";
//	public static final String TEMPLATE_ID_NOTICE_OF_THE_PARENTS_MEETING= "oplqsfM987RsozM9Mq0NFNF6ELCfD4KXJjLqOt5z-bQ";//test

	/************************************ 家长端 模板ID end********************************************/  

	/************************************ 校务端 模板ID start********************************************/  
	//班级考勤通知
	public static final String TEMPLATE_ID_CLASS_ATTENDANCE_NOTICE = "PJ-wQuA6iQBQY6P2_xY1_5HWxhZenXj54bxE1-z4Stc";
//	public static final String TEMPLATE_ID_CLASS_ATTENDANCE_NOTICE = "zb4GDbVBnoreFKAvfewq-aWCPaueGeOziCqZmyqTseo";//test
	//请假审批
	public static final String TEMPLATE_ID_LEAVE_FOR_EXAMINATION_AND_APPROVAL = "gla3xEUIF4vZhrTzAjfr6qHy1fQdGB9GtKyVhX-5BHE";
//	public static final String TEMPLATE_ID_LEAVE_FOR_EXAMINATION_AND_APPROVAL = "MenrB24gHalMRKRxUYpUPtsHzHH1Nhgz064Z45puU_w";//test
	
	
	public static final String LEAVE_DETAIL_URL = "http://" + SERVER_IP + "/h5-anxuebao-teacher/html/leave_shenpi.html?";
	
	public static final String EXIT_RECORDS_DETAIL_URL = "http://" + SERVER_IP + "/h5-anxuebao-teacher/html/query_entry_exit_records_ts.html";
	/************************************ 校务端 模板ID end********************************************/  
	
	/**
	 * 付款方式key  1按月付、2按学期付 3按学年付、4一次性付
	 */
	public static final String PAY_TYPE_KEY_1 = "1";
	public static final String PAY_TYPE_KEY_2 = "2";
	public static final String PAY_TYPE_KEY_3 = "3";
	public static final String PAY_TYPE_KEY_4 = "4";
	
	/**
	 * 付款方式value  1按月付、2按学期付 3按学年付、4一次性付
	 */
	public static final String PAY_TYPE_VALUE_1 = "按月付";
	public static final String PAY_TYPE_VALUE_2 = "按学期付";
	public static final String PAY_TYPE_VALUE_3 = "按学年付";
	public static final String PAY_TYPE_VALUE_4 = "一次性付";
	

	/**
	 * 订单状态 0：等待支付 1：支付完成 2：支付失败 3：订单作废
	 */
	public static final String ORDER_STATUS_0 = "0";
	public static final String ORDER_STATUS_1 = "1";
	public static final String ORDER_STATUS_2 = "2";
	public static final String ORDER_STATUS_3 = "3";
	
	/**
	 * 地区区分1：省 2：市 3：区
	 */
	public static final String AREA_KBN_1 = "1";
	public static final String AREA_KBN_2 = "2";
	public static final String AREA_KBN_3 = "3";


	public static final String BIND_SUCCESS = "操作成功！";

	public static final String BIND_ERROR = "操作失败！";
	
	public static final String ZERO = "0";
	
	public static final String BLANK = "";
	public static final String STRING_0 = "0";
	public static final String STRING_1 = "1";
	public static final String STRING_2 = "2";
	public static final String STRING_3 = "3";
	public static final String STRING_4 = "4";
	public static final String STRING_5 = "5";
	public static final String STRING_6 = "6";
	public static final String STRING_7 = "7";
	public static final String STRING_8 = "8";
	public static final String STRING_9 = "9";
	public static final String STRING_10 = "10";
	public static final String STRING_11 = "11";
	public static final String STRING_12 = "12";
	public static final String STRING_13 = "13";
	public static final String STRING_14 = "14";
	public static final String STRING_15 = "15";
	public static final String STRING_16 = "16";
	public static final String STRING_17 = "17";
	public static final String STRING_18 = "18";
	public static final String STRING_19 = "19";
	public static final String STRING_20 = "20";
	public static final String STRING_22 = "22";
	
	public static final String STRING_01 = "01";	

	public static final String STRING_POINT = ".";
	//学校作息时间格式
	public static final String TIME_FORMAT_SCHOOL_WORK = "HH:mm";
	//学生打卡记录，进出标识
	public static final String IDENTIFY_RECORD_IO_SCHOOL_IN = "101";//进校
	public static final String IDENTIFY_RECORD_IO_SCHOOL_OUT = "102";//出校
	public static final String IDENTIFY_RECORD_IO_SCHOOL_UNKNOWN = "103";//进出校门，未知
	public static final String IDENTIFY_RECORD_IO_DORM_IN = "201";//进宿舍
	public static final String IDENTIFY_RECORD_IO_DORM_OUT = "202";//出宿舍
	public static final String IDENTIFY_RECORD_IO_DORM__UNKNOWN = "203";//进出宿舍，未知

	//设备段时间格式
	public static final String CLIENT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";//进出宿舍，未知

	/**************************数据字典类型  CODE_TYPE**********************************/
	public static final String DIC_1000010 = "1000010";//删除标识
	public static final String DIC_1000020 = "1000020";//代理人类型
	public static final String DIC_1000030 = "1000030";//付款方式
	public static final String DIC_1000040 = "1000040";//订单状态
	public static final String DIC_1000050 = "1000050";//账户状态
	public static final String DIC_1000060 = "1000060";//支付状态
	public static final String DIC_1000070 = "1000070";//地区区分
	public static final String DIC_1000080 = "1000080";//微信账号绑定状态
	public static final String DIC_1000090 = "1000090";//在职状态
	public static final String DIC_1000100 = "1000100";//职务属性
	public static final String DIC_1000110 = "1000110";//通知模板类型
	public static final String DIC_1000120 = "1000120";//通知发送标识
	public static final String DIC_1000130 = "1000130";//是否已读
	public static final String DIC_1000140 = "1000140";//性别
	public static final String DIC_1000150 = "1000150";//住宿标识
	public static final String DIC_1000160 = "1000160";//毕业标识
	public static final String DIC_1000170 = "1000170";//付款方式
	public static final String DIC_1000180 = "1000180";//付款状态
	public static final String DIC_1000190 = "1000190";//账户开通状态
	public static final String DIC_1000200 = "1000200";//学生绑定状态
	public static final String DIC_1000210 = "1000210";//请假状态
	public static final String DIC_1000220 = "1000220";//作业状态
	public static final String DIC_1000230 = "1000230";//打卡区分
	public static final String DIC_1000240 = "1000240";//宿舍区分
	public static final String DIC_1000250 = "1000250";//按时离开宿舍标识
	public static final String DIC_1000260 = "1000260";//按时返回宿舍标识
	public static final String DIC_1000270 = "1000270";//进出区分
	public static final String DIC_1000280 = "1000280";//掌纹区分
	public static final String DIC_1000290 = "1000290";//服务状态
	public static final String DIC_1000300 = "1000300";//终端状态
	public static final String DIC_1000310 = "1000310";//终端工作模式
	public static final String DIC_1000320 = "1000320";//掌静脉匹配策略
	public static final String DIC_1000330 = "1000330";//终端使用类型
	public static final String DIC_1000340 = "1000340";//终端即时执行操作
	public static final String DIC_1000350 = "1000350";//账号区分
	public static final String DIC_1000360 = "1000360";//菜单标识
	public static final String DIC_1000370 = "1000370";//权限区分
	public static final String DIC_1000380 = "1000380";//进出区分
	public static final String DIC_1000410 = "1000410";//微信通知模版
	
	//用户默认密码
	public static final String DEFAULT_USER_PASSWORD="123456";
	// 1：代理人 2：学校教职工 3：系统管理员
	public static final String ACCOUNT_TYPE_AGENT="1";
	public static final String ACCOUNT_TYPE_SCHOOL_STAFF="2";
	public static final String ACCOUNT_TYPE_SYSTEM_ADMIN="3";
	public static final String KEY_AGENT="agent";
	public static final String KEY_SCHOOL_STAFF="schoolStaff";
	public static final String KEY_SYSTEM_ADMIN="admin";

	public final static Map<Integer, String> GET_WEEK = new HashMap<Integer, String>() {
		{
			put(1, "周一");
			put(2, "周二");
			put(3, "周三");
			put(4, "周四");
			put(5, "周五");
			put(6, "周六");
			put(7, "周日");
		}
	};
	//默认值
	public static final Integer PAGE_NUM = 1;
	public static final Integer PAGE_SIZE = 10;

	public static final int VERSION_01 = 1;
	public static final String NO_CKECK = "1";
	public static final String IS_CKECK = "0";

	//上班时间
	public static final String WORK_TIME = "09:00:00";
	//下班时间
	public static final String CLOSING_TIME = "18:00:00";
	//手机已存在
	public static final String MOBILE_PHONE_ALREADY_EXIST="手机号码已存在！";
	public static final String STAFF_NO_ALREADY_EXIST="员工工号已存在！";
	public static final String DEPT_NAME_ALREADY_EXIST="部门名称已存在！";
	public static final String PROJECT_NAME_ALREADY_EXIST="项目名称已存在！";
	public static final String Goods_NAME_ALREADY_EXIST="商品名称已存在！";
	//insertOrUpdateFlg
	public static final String INSERT_FLG="insert";
	public static final String UPDATE_FLG="update";
	//修改过密码
	public static final String UPDATE_PASSWORD_FLG="1";
	
	//部门经理
	public static final Long DEPT_MANAGER=3l;
	//部门副经理
	public static final Long DEPT_ASSISTANT_MANAGER=4l;
	//未读
	public static final String NO_READ="0";
	//已读
	public static final String ALREADY_READ="1";
	//发送失败
	public static final String FAIL_TO_SEND="发送失败";
	//发送者为自己的
	public static final String SEND_MY_NAME="我";
}
