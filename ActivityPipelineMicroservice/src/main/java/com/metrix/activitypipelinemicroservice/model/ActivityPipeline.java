package com.metrix.activitypipelinemicroservice.model;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Document(collection = "ActivityPipeline")
public class ActivityPipeline {
    @Id
    private String Id;
    @Indexed(unique = true)
    @ApiModelProperty(notes = "Activity Pipeline Id")
    private String activityPipelineId;
    @ApiModelProperty(notes = "Issuer Profile Id")
    private String issuerProfileId;
    @ApiModelProperty(notes = "Pipeline Name")
    private String pipelineName;
    @ApiModelProperty(notes = "Pipeline Description")
    private String pipelineDescription;
    @ApiModelProperty(notes = "Transformer")
    private String transformer;
    @ApiModelProperty(notes = "End Point Url")
    private String endpointUrl;
    @ApiModelProperty(notes = "Client End Point Url")
    private String clientEndpointUrl;
    @ApiModelProperty(notes = "Pipeline Status")
    private PipelineStatus pipelineStatus;
    @ApiModelProperty(notes = "End Point Token")
    private String endpointToken;
    @ApiModelProperty(notes = "headers")
    private ArrayList<Headers> headers;
    @ApiModelProperty(notes = "Created By")
    private String createdBy;
    @ApiModelProperty(notes = "Created On")
    private LocalDateTime createdOn;
    @ApiModelProperty(notes = "Updated On")
    private LocalDateTime updatedOn;
    @ApiModelProperty(notes = "Updated By")
    private String updatedBy;


    public ActivityPipeline() {
    }

    public ActivityPipeline(String id, String activityPipelineId, String issuerProfileId, String pipelineName, String pipelineDescription, String transformer, String endpointUrl, String clientEndpointUrl, PipelineStatus pipelineStatus, String endpointToken, ArrayList<Headers> headers, String createdBy, LocalDateTime createdOn, LocalDateTime updatedOn, String updatedBy) {
        Id = id;
        this.activityPipelineId = activityPipelineId;
        this.issuerProfileId = issuerProfileId;
        this.pipelineName = pipelineName;
        this.pipelineDescription = pipelineDescription;
        this.transformer = transformer;
        this.endpointUrl = endpointUrl;
        this.clientEndpointUrl = clientEndpointUrl;
        this.pipelineStatus = pipelineStatus;
        this.endpointToken = endpointToken;
        this.headers = headers;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
        this.updatedBy = updatedBy;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getActivityPipelineId() {
        return activityPipelineId;
    }

    public void setActivityPipelineId(String activityPipelineId) {
        this.activityPipelineId = activityPipelineId;
    }

    public String getIssuerProfileId() {
        return issuerProfileId;
    }

    public void setIssuerProfileId(String issuerProfileId) {
        this.issuerProfileId = issuerProfileId;
    }

    public String getPipelineName() {
        return pipelineName;
    }

    public void setPipelineName(String pipelineName) {
        this.pipelineName = pipelineName;
    }

    public String getPipelineDescription() {
        return pipelineDescription;
    }

    public void setPipelineDescription(String pipelineDescription) {
        this.pipelineDescription = pipelineDescription;
    }

    public String getTransformer() {
        return transformer;
    }

    public void setTransformer(String transformer) {
        this.transformer = transformer;
    }

    public String getEndpointUrl() {
        return endpointUrl;
    }

    public void setEndpointUrl(String endpointUrl) {
        this.endpointUrl = endpointUrl;
    }

    public String getClientEndpointUrl() {
        return clientEndpointUrl;
    }

    public void setClientEndpointUrl(String clientEndpointUrl) {
        this.clientEndpointUrl = clientEndpointUrl;
    }

    public PipelineStatus getPipelineStatus() {
        return pipelineStatus;
    }

    public void setPipelineStatus(PipelineStatus pipelineStatus) {
        this.pipelineStatus = pipelineStatus;
    }

    public String getEndpointToken() {
        return endpointToken;
    }

    public void setEndpointToken(String endpointToken) {
        this.endpointToken = endpointToken;
    }

    public ArrayList<Headers> getHeaders() {
        return headers;
    }

    public void setHeaders(ArrayList<Headers> headers) {
        this.headers = headers;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }


    @Override
    public String toString() {
        return "ActivityPipeline{" +
                "Id='" + Id + '\'' +
                ", activityPipelineId='" + activityPipelineId + '\'' +
                ", issuerProfileId='" + issuerProfileId + '\'' +
                ", pipelineName='" + pipelineName + '\'' +
                ", pipelineDescription='" + pipelineDescription + '\'' +
                ", transformer='" + transformer + '\'' +
                ", endpointUrl='" + endpointUrl + '\'' +
                ", clientEndpointUrl='" + clientEndpointUrl + '\'' +
                ", pipelineStatus=" + pipelineStatus +
                ", endpointToken='" + endpointToken + '\'' +
                ", headers=" + headers +
                ", createdBy='" + createdBy + '\'' +
                ", createdOn=" + createdOn +
                ", updatedOn=" + updatedOn +
                ", updatedBy='" + updatedBy + '\'' +
                '}';
    }
}
