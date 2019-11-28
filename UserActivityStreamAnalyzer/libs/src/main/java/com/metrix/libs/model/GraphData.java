package com.metrix.libs.model;

public class GraphData {

    private String issuerProfileId;
    private int totalcount;
    private int passcount;
    private int failcount;

    public GraphData(String issuerProfileId, int totalcount, int passcount, int failcount) {
        this.issuerProfileId = issuerProfileId;
        this.totalcount = totalcount;
        this.passcount = passcount;
        this.failcount = failcount;
    }

    public GraphData() {
        super();
    }

    public String getIssuerProfileId() {
        return issuerProfileId;
    }

    public int getPasscount() {
        return passcount;
    }

    public int getFailcount() {
        return failcount;
    }

    public void setIssuerProfileId(String issuerProfileId) {
        this.issuerProfileId = issuerProfileId;
    }

    public void setPasscount(int passcount) {
        this.passcount = passcount;
    }

    public void setFailcount(int failcount) {
        this.failcount = failcount;
    }

    public int getTotalcount() {
        return totalcount;
    }

    public void setTotalcount(int totalcount) {
        this.totalcount = totalcount;
    }
}
