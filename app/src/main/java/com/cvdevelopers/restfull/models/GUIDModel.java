package com.cvdevelopers.restfull.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by CamiloVega on 2/20/17.
 */

public class GUIDModel extends ApiResponseModel {

    public static final String GUID_FIELD = "guid";

    @JsonProperty(GUID_FIELD)
    private String guid;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

}
