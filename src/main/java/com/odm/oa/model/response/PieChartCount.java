package com.odm.oa.model.response;

public class PieChartCount {
    private Long typeId;
    private String typeName;
    private Long countNumber;

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Long getCountNumber() {
        return countNumber;
    }

    public void setCountNumber(Long countNumber) {
        this.countNumber = countNumber;
    }
}
