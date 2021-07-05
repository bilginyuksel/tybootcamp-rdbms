package com.tybootcamp.ecomm.exceptions;

import com.tybootcamp.ecomm.common.ErrorResponse;

public abstract class BaseHttpException extends RuntimeException {
    protected ErrorResponse errorResponse;
    protected String detailErrorMsg;

    protected BaseHttpException(ErrorResponse errorResponse, String detailErrorMsg) {
        this.errorResponse = errorResponse;
        this.detailErrorMsg = detailErrorMsg;
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }

    public String getDetailErrorMsg() {
        return detailErrorMsg;
    }
}
