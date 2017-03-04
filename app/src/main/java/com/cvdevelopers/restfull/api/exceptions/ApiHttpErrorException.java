package com.cvdevelopers.restfull.api.exceptions;

import com.cvdevelopers.restfull.models.ApiResponseModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;

/**
 * An error resulting from a request to Api that receives a response
 * with a http status in the error range; >= 300.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiHttpErrorException extends ApiException {

    public static final String ERROR_MESSAGE_FIELD = "error";
    public static final String ERROR_ID_FIELD = "error_id";

    private HttpStatus code;

    @JsonProperty(ERROR_MESSAGE_FIELD)
    private String errorMessage;

    @JsonProperty(ERROR_ID_FIELD)
    private String errorId;

    public ApiHttpErrorException(HttpClientErrorException ex) {
        super(ex);
        this.code = ex.getStatusCode();
        try {
            ApiResponseModel.OBJECT_MAPPER.readerForUpdating(this).readValue(ex.getResponseBodyAsString());
        } catch (IOException e) {
            // nothing to do; there probably was no body.
        }
    }

    public HttpStatus getCode() {
        return code;
    }

    public void setCode(HttpStatus code) {
        this.code = code;
    }

    public String getErrorId() {
        return errorId;
    }

    public void setErrorId(String errorId) {
        this.errorId = errorId;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
