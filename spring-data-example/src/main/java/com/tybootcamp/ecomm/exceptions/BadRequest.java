package com.tybootcamp.ecomm.exceptions;

import com.tybootcamp.ecomm.common.ErrorResponse;

public class BadRequest extends BaseHttpException {

    public BadRequest(String detailErrorMsg) {
        super(ErrorResponse.BAD_REQUEST, detailErrorMsg);
    }

    public BadRequest() {
        super(ErrorResponse.BAD_REQUEST, "Bad request!");
    }
}
