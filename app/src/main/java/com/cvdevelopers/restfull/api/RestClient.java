package com.cvdevelopers.restfull.api;

import com.cvdevelopers.restfull.models.RentalItem;
import com.cvdevelopers.restfull.models.RentalItems;
import com.cvdevelopers.restfull.models.Response;

import org.androidannotations.rest.spring.annotations.Body;
import org.androidannotations.rest.spring.annotations.Get;
import org.androidannotations.rest.spring.annotations.Post;
import org.androidannotations.rest.spring.annotations.Put;
import org.androidannotations.rest.spring.annotations.Rest;
import org.androidannotations.rest.spring.api.RestClientHeaders;
import org.androidannotations.rest.spring.api.RestClientRootUrl;
import org.androidannotations.rest.spring.api.RestClientSupport;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import static com.cvdevelopers.restfull.api.RestManager.ROOT_URL;

/**
 * Created by Camilo Vega on 1/25/17.
 */

@Rest(rootUrl = ROOT_URL, converters = {MappingJackson2HttpMessageConverter.class} , interceptors = {RestLogger.class})
public interface RestClient extends RestClientHeaders, RestClientRootUrl, RestClientSupport {

    @Get("/ping")
    ResponseEntity<Response> pingServer();

    @Get("/api/availableRentalItems")
    RentalItems getRentalItems();

    @Get("/api/oneRentalItem")
    RentalItem getRentalItem();

    @Post("/api/setRentalItem")
    RentalItem postRentalItem(@Body RentalItem rentalItem);
}