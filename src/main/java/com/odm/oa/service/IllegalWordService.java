package com.odm.oa.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.odm.oa.auth.JwtTokenUtil;
import com.odm.oa.entity.IllegalWord;
import com.odm.oa.entity.Staff;
import com.odm.oa.entity.ex.IllegalWordEx;
import com.odm.oa.mapper.IllegalWordMapper;
import com.odm.oa.mapper.StaffMapper;
import com.odm.oa.model.request.IllegalWordPagination;
import com.odm.oa.model.request.SubmitIllegalWordRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

@Service
public class IllegalWordService {
    private static Logger logger = LogManager.getLogger(IllegalWordService.class);
    @Autowired
    private IllegalWordMapper illegalWordMapper;
    @Autowired
    private StaffMapper staffMapper;
    public PageInfo<IllegalWordEx> pagination(IllegalWordPagination request){//分页查询
        PageHelper.startPage(request.getPageNum(),request.getPageSize());
        List<IllegalWordEx> list= illegalWordMapper.pagination(request);
        PageInfo<IllegalWordEx> pageInfo=new PageInfo<IllegalWordEx>(list);
        return pageInfo;
    }
    public String addIllegalWord(SubmitIllegalWordRequest request){//添加
        if(illegalWordMapper.selectByWord(request.getWord())!=null){
            return "该关键字已存在!";
        }else{
            IllegalWord illegalWord=new IllegalWord();
            illegalWord.setWord(request.getWord());
            illegalWord.setCreateId(JwtTokenUtil.getUserIdFromContext());
            illegalWord.setCreateTime(new Date());
            illegalWord.setDelFlg(false);
            illegalWord.setVersion(1);
            illegalWordMapper.insert(illegalWord);
            return null;
        }
    }
    public String editIllegalWord(SubmitIllegalWordRequest request){//编辑
        IllegalWord illegalWord=illegalWordMapper.selectByPrimaryKey(request.getId());
        if(illegalWord!=null){
            IllegalWord illegalWord1=illegalWordMapper.selectByWord(request.getWord());
            if(illegalWord1!=null&&!illegalWord.getId().equals(illegalWord1.getId())){
                return "该关键字已存在";
            }else{
                illegalWord.setWord(request.getWord());
                illegalWord.setVersion(illegalWord.getVersion()+1);
                illegalWord.setUpdateTime(new Date());
                illegalWord.setUpdateId(JwtTokenUtil.getUserIdFromContext());
                illegalWordMapper.updateByPrimaryKey(illegalWord);
                return null;
            }
        }else{
            return "该关键字不存在!";
        }
    }

    public void deleteIllegalWord(Long[] request) {//删除
        for (Long id : request) {
            IllegalWord illegalWord = illegalWordMapper.selectByPrimaryKey(id);
            if (illegalWord != null) {
                illegalWord.setVersion(illegalWord.getVersion() + 1);
                illegalWord.setDelFlg(true);
                illegalWord.setUpdateId(JwtTokenUtil.getUserIdFromContext());
                illegalWord.setUpdateTime(new Date());
                illegalWordMapper.updateByPrimaryKey(illegalWord);
            }
        }
    }
    public List<Staff> listPerson(){
        return staffMapper.listIllegalWordPerson();
    }
    public Set<String> listIllegalWord(){
        return illegalWordMapper.listIllegalWord();
    }
    public List<String> getIllegalWord( String content){//检索非法字符
        logger.info(JSONObject.toJSONString(content));
        Set<String> illegalList=listIllegalWord();
        List<String> illegalWord=new ArrayList<>();
        for(String illegalTemp:illegalList){
            logger.info(content.contains(illegalTemp));
            if(content.contains(illegalTemp)){
                illegalWord.add(illegalTemp);
            }
        }
        return illegalWord;
    }
}
