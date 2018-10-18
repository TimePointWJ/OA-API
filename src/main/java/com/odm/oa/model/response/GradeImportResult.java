package com.odm.oa.model.response;

public class GradeImportResult {
    private String gradeName;
    private String schoolName;
    private String gradeHeadmasterName;
    private String result;

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getGradeHeadmasterName() {
        return gradeHeadmasterName;
    }

    public void setGradeHeadmasterName(String gradeHeadmasterName) {
        this.gradeHeadmasterName = gradeHeadmasterName;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
