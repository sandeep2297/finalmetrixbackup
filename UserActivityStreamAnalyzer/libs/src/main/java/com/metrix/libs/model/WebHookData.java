package com.metrix.libs.model;
import java.util.HashMap;

public class WebHookData {

    HashMap<String ,Object> body;
    HashMap<String,String > header;
    String issuerId;
    String pipelineId;

    public WebHookData()
    {}

    public WebHookData(HashMap<String, Object> body, HashMap<String, String> header, String issuerId, String pipelineId) {
        this.body = body;
        this.header = header;
        this.issuerId = issuerId;
        this.pipelineId = pipelineId;
    }

    public HashMap<String, Object> getBody() {
        return body;
    }

    public void setBody(HashMap<String, Object> body) {
        this.body = body;
    }

    public HashMap<String, String> getHeader() {
        return header;
    }

    public void setHeader(HashMap<String, String> header) {
        this.header = header;
    }

    public String getIssuerId() {
        return issuerId;
    }

    public void setIssuerId(String issuerId) {
        this.issuerId = issuerId;
    }

    public String getPipelineId() {
        return pipelineId;
    }

    public void setPipelineId(String pipelineId) {
        this.pipelineId = pipelineId;
    }

    @Override
    public String toString() {
        return "WebHookData{" +
                "body=" + body +
                ", header=" + header +
                ", issuerId='" + issuerId + '\'' +
                ", pipelineId='" + pipelineId + '\'' +
                '}';
    }
}
