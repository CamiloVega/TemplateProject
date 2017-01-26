package com.cvdevelopers.restfull.services;

import com.cvdevelopers.restfull.api.RestManager;
import com.cvdevelopers.restfull.models.Response;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

/**
 * Created by Tilt on 1/26/17.
 */

@EBean(scope = EBean.Scope.Singleton)
public class PingService {

    @Bean
    RestManager restManager;

    public Response pingServer(){
        return restManager.getClient().pingServer();
    }
}
