package com.odm.oa.model.request;

public class IllegalWordPagination extends PaginationBase{
    private String word;
    private Long createId;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Long getCreateId() {
        return createId;
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
    }
}
