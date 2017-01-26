package com.cvdevelopers.restfull.api;

import com.cvdevelopers.restfull.models.Response;

import org.androidannotations.rest.spring.annotations.Get;
import org.androidannotations.rest.spring.annotations.Rest;
import org.androidannotations.rest.spring.api.RestClientHeaders;
import org.androidannotations.rest.spring.api.RestClientRootUrl;
import org.androidannotations.rest.spring.api.RestClientSupport;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import static com.cvdevelopers.restfull.api.RestManager.ROOT_URL;

/**
 * Created by Camilo Vega on 1/25/17.
 */

@Rest(rootUrl = ROOT_URL, converters = {MappingJackson2HttpMessageConverter.class})
public interface RestClient extends RestClientHeaders, RestClientRootUrl, RestClientSupport {

    @Get("/ping")
    Response pingServer();

}