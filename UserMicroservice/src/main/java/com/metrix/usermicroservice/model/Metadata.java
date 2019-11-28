package com.metrix.usermicroservice.model;

import io.swagger.annotations.ApiModelProperty;

public class Metadata {

    @ApiModelProperty(notes = "Tags")
    private String tags;

    public Metadata(String tags) {
        super();
        this.tags = tags;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
