package com.cvdevelopers.restfull.api;

import org.androidannotations.annotations.EBean;
import org.androidannotations.rest.spring.annotations.RestService;

/**
 * Created by Camilo Vega on 1/26/17.
 */
@EBean(scope = EBean.Scope.Singleton)
public class RestManager {

    public static final String ROOT_URL = "http://192.168.0.4:3000";

    @RestService
    RestClient restClient;

    public synchronized RestClient getClient(){
        return restClient;
    }
}
