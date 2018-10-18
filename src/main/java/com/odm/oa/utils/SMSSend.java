package com.odm.oa.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class SMSSend {
	
	private static Logger logger = LogManager.getLogger(SMSSend.class);
	
	private String veryCode = "9miehvwas8dv";
	private String user = "JSM42120";
	private String pwd = "sm6amqft";
	private String smsURL = "http://112.74.76.186:8030/service/httpService/httpInterface.do";
	
	public static final String MSGTYPE_1 = "1"; // 普通短信
	public static final String MSGTYPE_2 = "2"; // 模版短信
	
	public static final String JSM_1 = "JSM42120-0001"; // 模版ID
														// 您的验证码标准模板为：@1@，请尽快验证！
	public static final String JSM_2 = "JSM42120-0002"; // 模版ID
														// 【安学宝】您申请微信安学宝校务端绑定，验证码是@1@。请于5分钟内输入，请勿泄漏。如非本人操作，请致电安学宝客服人员！
	public static final String JSM_3 = "JSM42120-0003"; // 模版ID
														// 【安学宝】您运营的@1@的@2@终端机器出现问题，请及时维修！
	
	//http://112.74.76.186:8030/service/httpService/httpInterface.do
	//?method=sendMsg&username=JSM40001&password=123456&veryCode=453245&mobile=15951977097
	// &content=@1@=李先生,@2@=您好,@3@=928371&msgtype=2&tempid=JSM4001-000&code=gbk
	
	// 普通短信
	public String sendSMS(String mobile,String content,String sendtime){
		StringBuilder param = new StringBuilder();
		param.append("&username=").append(user);
		param.append("&password=").append(pwd);
		param.append("&veryCode=").append(veryCode);
		param.append("&mobile=").append(mobile);
		param.append("&content=").append(replaceTo(content));
		param.append("&msgtype=").append(MSGTYPE_1);
		param.append("&code=").append("UTF-8");
		if(!StringUtils.isEmpty(sendtime))
			param.append("&sendtime=").append(sendtime);
		StringEntity entity = new StringEntity(param.toString(),"UTF-8");
		entity.setContentType("application/x-www-form-urlencoded");
		entity.setContentEncoding("UTF-8");
		return executSend(smsURL + "?method=sendMsg",entity);
	}
	
	/**
	 * 模版短信
	 * 
	 * @param mobile
	 *            发送手机
	 * @param content
	 *            封装后的发送内容
	 * @param tmplId
	 *            模版ID
	 * @return
	 */
	public String sendTmplSMS(String mobile,String content,String tmplId){
		StringBuilder param = new StringBuilder();
		param.append("&username=").append(user);
		param.append("&password=").append(pwd);
		param.append("&veryCode=").append(veryCode);
		param.append("&mobile=").append(mobile);
		param.append("&content=").append(content);
		param.append("&msgtype=").append(MSGTYPE_2);
		param.append("&tempid=").append(tmplId);// 模板编号
		param.append("&code=").append("UTF-8");
		StringEntity entity = new StringEntity(param.toString(),"UTF-8");
		entity.setContentType("application/x-www-form-urlencoded");
		entity.setContentEncoding("UTF-8");
		return executSend(smsURL + "?method=sendMsg",entity);
	}
	
	/**
	 * 模版短信,进行发送内容参数封装
	 * 
	 * @param mobile
	 *            手机号码
	 * @param tmplId
	 *            模版ID
	 * @param args
	 *            发送内容
	 * @return
	 */
	public String sedTmplJSM(String mobile,String tmplId,String... args){
		if(args == null || args.length <= 0) return null;
		StringBuilder content = new StringBuilder("@1@=");
		try {
			if(JSM_1.equals(tmplId) && args.length == 1)
				content.append(replaceTo(args[0]));
			else if(JSM_2.equals(tmplId) && args.length == 1) 
				content.append(replaceTo(args[0]));
			else if(JSM_3.equals(tmplId) && args.length == 2){
				content.append(replaceTo(args[0]));
				content.append(",").append("@2@=");
				content.append(replaceTo(args[1]));
			}else{
				for (int i = 0; i < args.length; i++) {
					if(i == 0) content.append(replaceTo(args[i]));
					else{
						content.append(",").append("@").append((i + 1) + "@=");
						content.append(replaceTo(args[i]));
					}
				}
			}
			
			return sendTmplSMS(mobile, content.toString(), tmplId);
		} catch (Exception e) {
			logger.error("send error : " + e.getMessage());
			return null;
		}
	}
	
	// 查询余额
	public String getBalance(){
		StringBuilder param = new StringBuilder();
		param.append("&username").append(user);
		param.append("&password").append(pwd);
		param.append("&veryCode").append(veryCode);
		StringEntity entity = new StringEntity(param.toString(),"UTF-8");
		entity.setContentType("application/x-www-form-urlencoded");
		entity.setContentEncoding("UTF-8");
		return executSend(smsURL + "?method=getAmount",entity);
	}
	
	public String executSend(String url,StringEntity entity){
		String xml = "";
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost post = new HttpPost(url);
		try {
			post.setEntity(entity);
			HttpResponse result = httpClient.execute(post);
			if(result.getStatusLine().getStatusCode() == 200){
				xml = EntityUtils.toString(result.getEntity());
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return xml;
	}
	
	public String getURLEncode(String code,String charset){
		try {
			return URLEncoder.encode(code,charset);
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage());
		}
		return "";
	}
	
	public Element parseXml(String xml){
		if(StringUtils.isEmpty(xml)) return null;
		try {
			return DocumentHelper.parseText(xml).getRootElement();
		} catch (DocumentException e) {
			logger.error("parse XML error: ",e);
		}
		return null;
	}
	
	public Map<String, String> getNodeResult(Element node){
		Map<String, String> map = new HashMap<String,String>();
		if(node == null)
			return map;
		try {
			Iterator<?> iterator = node.elementIterator();  
	        while (iterator.hasNext())  
	        {  
	            Element element = (Element) iterator.next();  
	            map.put(element.getName(), element.getText());  
	        } 
		} catch (Exception e) {
			logger.error(e);
		}
		return map;
	}
	
	@SuppressWarnings("unchecked")
	public String getXMLStatus(String xml){
		Element ele = parseXml(xml);
		if(ele == null) return null;
		Iterator<Element> iterator = ele.elementIterator();
		if(iterator.hasNext())
			return getNodeResult(iterator.next()).get("status");
		return null;
	}
	
	public String replaceTo(String content){
		return content.replace("+", "%2B").replace(" ", "%20")
				.replace("/", "%2F").replace("?", "%3F")
				.replace("%", "%25").replace("#", "%23")
				.replace("&", "%26").replace("=", "%3D");
	}

	public static void main(String[] args) {
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
				+ "<sms><mt>"
				+ "<status>0</status>" // 状态码,0:提交成功 ,100:发送失败
				+ "<msgid>a2477f9b171d4edeab682ef36fea750d</msgid>" // 消息ID
				+ "</mt></sms>";
		SMSSend sms = new SMSSend();
		String status = sms.sedTmplJSM("xxx",JSM_1,CommonUtil.getRandoms(6));
		String status1 = sms.sendTmplSMS("xxx", "@1@=苏州分区,@2@=C校区32号", JSM_3);
		String status2 = sms.sendSMS("xxx", "【江苏美圣】您的验证码为" + CommonUtil.getRandoms(6) + ",请尽快验证!", null);
		System.out.println(status);
		System.out.println(status1);
		System.out.println(status2);
		System.out.println(sms.getXMLStatus(xml));
	}
	
}
