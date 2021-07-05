package com.tybootcamp.ecomm.exceptions;

import com.tybootcamp.ecomm.common.ErrorResponse;

public class EntityNotFound extends BaseHttpException {
    public EntityNotFound() {
        super(ErrorResponse.ENTITY_NOT_FOUND, "Entity not found!");
    }
}
