package com.cvdevelopers.restfull.api;

import android.util.Log;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.rest.spring.annotations.RestService;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

/**
 * Created by Camilo Vega on 1/26/17.
 */
@EBean(scope = EBean.Scope.Singleton)
public class RestManager {
    private static final String TAG = RestManager.class.getName();

    public static final String ROOT_URL = "http://192.168.0.4:3000";

    @RestService
    RestClient restClient;

    public synchronized RestClient getClient(){
        return restClient;
    }

    @AfterInject
    protected void afterInject() {
        restClient.getRestTemplate().setRequestFactory(
                new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));

        // TODO: global HTTP header setup goes here

        Log.d(TAG,"RestManager initialized.");
    }
}
