package com.odm.oa.model.request;

public class SubmitNoticeRequest {
    private Long id;
    private String title;
    private String content;
    private Long[] releaseStaff;
    private String[] fileUrl;
    private Long type;
    private Long[] reviewId;
    private int status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Long[] getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long[] reviewId) {
        this.reviewId = reviewId;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public String[] getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String[] fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long[] getReleaseStaff() {
        return releaseStaff;
    }

    public void setReleaseStaff(Long[] releaseStaff) {
        this.releaseStaff = releaseStaff;
    }
}
