package com.cvdevelopers.restfull.api.exceptions;

/**
 * An exception thrown as a result of a failed invocation of an Api endpoint that is *not* connectivity related,
 * such as a Json parse error, etc.
 */
public class ApiException extends Exception {

    public ApiException(String message) {
        super(message);
    }

    public ApiException(Exception exception) {
        super(exception);
    }
}
