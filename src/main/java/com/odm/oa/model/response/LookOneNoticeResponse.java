package com.odm.oa.model.response;

import com.odm.oa.entity.NoticeResponse;
import com.odm.oa.entity.ex.NoticeEx;
import com.odm.oa.entity.ResponseFile;
import com.odm.oa.entity.ex.ResponseEx;

import java.util.List;

public class LookOneNoticeResponse {
    private NoticeEx noticeEx;
    private List<ResponseFile> file;

    public List<ResponseFile> getFile() {
        return file;
    }

    public void setFile(List<ResponseFile> file) {
        this.file = file;
    }

    public NoticeEx getNoticeEx() {
        return noticeEx;
    }

    public void setNoticeEx(NoticeEx noticeEx) {
        this.noticeEx = noticeEx;
    }

}
