package com.cvdevelopers.restfull.models;

import android.text.format.DateUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;

/**
 * Base class for models used in the service layer.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class ApiResponseModel implements Serializable {
    /**
     * ObjectMapper for api models; should be used anywhere a tilt model is marshalled or unmarshalled.
     */
    public static final ObjectMapper OBJECT_MAPPER;

    static {
        // only include fields annotated with JsonProperty.
        OBJECT_MAPPER = new ObjectMapper()
                .disable(MapperFeature.AUTO_DETECT_CREATORS)
                .disable(MapperFeature.AUTO_DETECT_GETTERS)
                .disable(MapperFeature.AUTO_DETECT_SETTERS)
                .disable(MapperFeature.AUTO_DETECT_FIELDS)
                .disable(MapperFeature.AUTO_DETECT_IS_GETTERS);
    }
}
