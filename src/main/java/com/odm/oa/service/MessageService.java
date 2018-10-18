package com.odm.oa.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.odm.oa.auth.JwtTokenUtil;
import com.odm.oa.entity.Message;
import com.odm.oa.entity.ex.MessageEx;
import com.odm.oa.mapper.MessageMapper;
import com.odm.oa.model.request.MessagePagination;
import com.odm.oa.model.response.BaseResponse;
import com.odm.oa.utils.Constants;

@Service
public class MessageService {
	@Autowired
	private MessageMapper messageMapper;
	/**
	 * 查询信息列表
	 */
	public PageInfo<MessageEx> pagination(MessagePagination request) {
		PageHelper.startPage(request.getPageNum(), request.getPageSize());
		// 获取员工列表信息
		List<MessageEx> list = messageMapper.pagination(request);
		//获取总页数
		long total=((Page)list).getTotal();
		//如果是点击消息按钮，并且查询结果大于一页，重新查询最后一页
		if(request.getClickMessageFlg()!=null&&"1".equals(request.getClickMessageFlg())&&total>request.getPageSize()){
			request.setPageNum((int)total/request.getPageSize()+1);
			PageHelper.startPage(request.getPageNum(), request.getPageSize());
			list = messageMapper.pagination(request);
		}
		//已经查看，修改消息状态
		alreadyRead(list,request);
		PageInfo<MessageEx> pageInfo = new PageInfo<MessageEx>(list);
		return pageInfo;
	}
	/**
	 * 已经查看，修改状态
	 */
	@Transactional(rollbackFor = Exception.class)
	public void alreadyRead(List<MessageEx> list,MessagePagination request){
		for(MessageEx mEx:list){
			//状态为没看多的，并且当前用户是接收者的
			if(Constants.NO_READ.equals(mEx.getReadFlg())&&request.getUserId()==mEx.getReceiveId()){
				mEx.setReadFlg(Constants.ALREADY_READ);
				mEx.setVersion(mEx.getVersion()+1);
				mEx.setUpdateId(JwtTokenUtil.getUserIdFromContext());
				mEx.setUpdateTime(new Date());
				messageMapper.updateByPrimaryKey(mEx);
				//用户前端显示
				mEx.setReadFlg(Constants.NO_READ);
			}
		}
	}
	/**
	 * 发送消息
	 */
	@Transactional(rollbackFor = Exception.class)
	public void sendMessage(MessageEx request,BaseResponse<String> response){
		request.setReadFlg(Constants.NO_READ);
		request.setSendDelFlg(Constants.DEL_FLG);
		request.setReceiveDelFlg(Constants.DEL_FLG);
		request.setDelFlg(Constants.DEL_FLG);
		request.setVersion(Constants.VERSION);
		request.setCreateId(JwtTokenUtil.getUserIdFromContext());
		request.setCreateTime(new Date());
		messageMapper.insert(request);
        response.setStatusCode(Constants.RESPONSE_STATUS_CODE_SUCCESS);
	}
	/**
	 * 删除此条消息
	 */
	@Transactional(rollbackFor = Exception.class)
	public void deleteMessage(MessageEx request,BaseResponse<String> response){
		//如果发送者是我的话，sendDelFlg设置为1，否则，receiveDelFlg设置为1
		if(request.getSendName()!=null&&Constants.SEND_MY_NAME.equals(request.getSendName())){
			request.setSendDelFlg(Constants.IS_DELETE);
			request.setVersion(request.getVersion()+1);
			request.setUpdateId(JwtTokenUtil.getUserIdFromContext());
			request.setUpdateTime(new Date());
		}else{
			//如果当前用户是接收者的话，删除的时候，传过来的readFlg为0，
			request.setReadFlg(Constants.ALREADY_READ);
			request.setReceiveDelFlg(Constants.IS_DELETE);
			request.setVersion(request.getVersion()+1);
			request.setUpdateId(JwtTokenUtil.getUserIdFromContext());
			request.setUpdateTime(new Date());
		}
		messageMapper.updateByPrimaryKey(request);
        response.setStatusCode(Constants.RESPONSE_STATUS_CODE_SUCCESS);
	}
	/**
	 * 删除全部消息
	 */
	@Transactional(rollbackFor = Exception.class)
	public void deleteAllMessage(MessageEx request,BaseResponse<String> response){
		//根据sendId 和receiveId，查询出所有的聊天记录
		List<Message> messages=messageMapper.selectBySendIdAndReceiveId(request);
		for(Message m:messages){
			//request的sendId是当前用户的id，如果和查询的sendId相等，那么表示是发送者，否则就是接收者
			if(m.getSendId()!=null&&request.getSendId()!=null&&m.getSendId()==request.getSendId()){
				m.setSendDelFlg(Constants.IS_DELETE);
				m.setVersion(m.getVersion()+1);
				m.setUpdateId(JwtTokenUtil.getUserIdFromContext());
				m.setUpdateTime(new Date());
			}else{
				m.setReceiveDelFlg(Constants.IS_DELETE);
				m.setVersion(m.getVersion()+1);
				m.setUpdateId(JwtTokenUtil.getUserIdFromContext());
				m.setUpdateTime(new Date());
			}
			messageMapper.updateByPrimaryKey(m);
		}
		response.setStatusCode(Constants.RESPONSE_STATUS_CODE_SUCCESS);
	}
}
