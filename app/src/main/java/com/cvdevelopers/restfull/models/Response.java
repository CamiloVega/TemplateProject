package com.cvdevelopers.restfull.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Camilo Vega on 1/26/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Response {

    @JsonProperty("response")
    protected String response;

    public Response() {
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}

