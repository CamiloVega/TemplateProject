package com.cvdevelopers.restfull.services;

import android.util.Log;

import com.cvdevelopers.restfull.api.exceptions.ApiHttpErrorException;
import com.cvdevelopers.restfull.api.exceptions.ApiUnavailableException;
import com.cvdevelopers.restfull.api.exceptions.ServiceException;
import com.cvdevelopers.restfull.models.ApiResponseModel;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;

/**
 * Utility class used to enforce the handling of runtime exceptions thrown by the RestClient.  All direct usage
 * of the RestClient should be wrapped in an ApiRequest.
 */
public abstract class ApiRequest<ModelType extends ApiResponseModel, ExceptionType extends ServiceException> {

    private static final String TAG = ApiRequest.class.getName();

    public ModelType invoke(ExceptionType defaultException)
            throws ApiUnavailableException, ExceptionType {
        try {
            ResponseEntity<ModelType> response = onRequest();
            return response != null ? response.getBody() : null;
        } catch (ResourceAccessException e) {
            throw new ApiUnavailableException(e);
        } catch (HttpClientErrorException e) {
            ExceptionType exToThrow = defaultException;
            if (e.getStatusCode() == HttpStatus.SERVICE_UNAVAILABLE) {
                throw new ApiUnavailableException(e);
            }
            // parse the HttpClientErrorException's JSON body:
            final ApiHttpErrorException apiEx = new ApiHttpErrorException(e);

            throw exToThrow;
        } catch (RestClientException e) {
            // something unexpected happened in the RestClient not necessarily related
            // to a network event
            Log.w(TAG, e);
            throw defaultException;
        }
    }

    protected abstract ResponseEntity<ModelType> onRequest();

}
