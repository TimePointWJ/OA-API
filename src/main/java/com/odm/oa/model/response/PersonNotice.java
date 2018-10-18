package com.odm.oa.model.response;

import com.odm.oa.entity.Notice;
import com.odm.oa.entity.ex.NoticeEx;

import java.util.List;

public class PersonNotice {
    private List<NoticeEx> notice;
    private String name;

    public List<NoticeEx> getNotice() {
        return notice;
    }

    public void setNotice(List<NoticeEx> notice) {
        this.notice = notice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
