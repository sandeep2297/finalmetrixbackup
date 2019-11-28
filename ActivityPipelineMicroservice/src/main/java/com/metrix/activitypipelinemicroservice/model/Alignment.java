package com.metrix.activitypipelinemicroservice.model;

public class Alignment {

    private String targetName;
    private String targetURL;
    private String targetDescription;
    private String targetFramework;
    private String targetCode;

    public Alignment() {
    }

    public Alignment(String targetName, String targetURL, String targetDescription, String targetFramework, String targetCode) {
        this.targetName = targetName;
        this.targetURL = targetURL;
        this.targetDescription = targetDescription;
        this.targetFramework = targetFramework;
        this.targetCode = targetCode;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public String getTargetURL() {
        return targetURL;
    }

    public void setTargetURL(String targetURL) {
        this.targetURL = targetURL;
    }

    public String getTargetDescription() {
        return targetDescription;
    }

    public void setTargetDescription(String targetDescription) {
        this.targetDescription = targetDescription;
    }

    public String getTargetFramework() {
        return targetFramework;
    }

    public void setTargetFramework(String targetFramework) {
        this.targetFramework = targetFramework;
    }

    public String getTargetCode() {
        return targetCode;
    }

    public void setTargetCode(String targetCode) {
        this.targetCode = targetCode;
    }

    @Override
    public String toString() {
        return "Alignment{" +
                "targetName='" + targetName + '\'' +
                ", targetURL='" + targetURL + '\'' +
                ", targetDescription='" + targetDescription + '\'' +
                ", targetFramework='" + targetFramework + '\'' +
                ", targetCode='" + targetCode + '\'' +
                '}';
    }
}
