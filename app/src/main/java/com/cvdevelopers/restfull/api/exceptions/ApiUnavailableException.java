package com.cvdevelopers.restfull.api.exceptions;

import org.springframework.web.client.RestClientException;

/**
 * Thrown when connectivity to api is unavailable.  That this class does not extend {@link ApiException}
 * is intentional as these exceptions represent unrelated exception pathways should always be handled independently.
 */
public class ApiUnavailableException extends Exception {

    public ApiUnavailableException(RestClientException e) {
        super(e);
    }
}
