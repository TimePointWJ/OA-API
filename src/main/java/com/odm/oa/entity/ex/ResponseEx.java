package com.odm.oa.entity.ex;

import com.odm.oa.entity.NoticeResponse;

public class ResponseEx extends NoticeResponse{
    private String releaseTime;

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }
}
