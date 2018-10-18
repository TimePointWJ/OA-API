package com.odm.oa.model.request;

public class SubmitResponseRequest {
    private String content;
    private Long responseToId;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getResponseToId() {
        return responseToId;
    }

    public void setResponseToId(Long responseToId) {
        this.responseToId = responseToId;
    }
}
