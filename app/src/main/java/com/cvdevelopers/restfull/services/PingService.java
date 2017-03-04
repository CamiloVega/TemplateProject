package com.cvdevelopers.restfull.services;

import com.cvdevelopers.restfull.api.RestManager;
import com.cvdevelopers.restfull.api.exceptions.ApiUnavailableException;
import com.cvdevelopers.restfull.api.exceptions.ServiceException;
import com.cvdevelopers.restfull.models.Response;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.springframework.http.ResponseEntity;

/**
 * Created by Tilt on 1/26/17.
 */

@EBean(scope = EBean.Scope.Singleton)
public class PingService {

    @Bean
    RestManager restManager;

    public Response pingServer() throws ApiUnavailableException, ServiceException {
        return new ApiRequest<Response, ServiceException>() {
            @Override
            protected ResponseEntity<Response> onRequest() {
                return restManager.getClient().pingServer();
            }
        }.invoke(new ServiceException());
    }
}
