package com.odm.oa.model.request;

public class NoticeResponsePagination extends PaginationBase{
    private Long noticeId;

    public Long getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Long noticeId) {
        this.noticeId = noticeId;
    }
}
