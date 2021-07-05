package com.tybootcamp.ecomm.advices;

import com.tybootcamp.ecomm.common.ErrorResponse;
import com.tybootcamp.ecomm.exceptions.BadRequest;
import com.tybootcamp.ecomm.exceptions.BaseHttpException;
import com.tybootcamp.ecomm.exceptions.EntityNotFound;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CommonControllerAdvice extends ResponseEntityExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonControllerAdvice.class);

    @ExceptionHandler(EntityNotFound.class)
    public ResponseEntity<?> handleEntityNotFound(EntityNotFound ex) {
        return handleHttpError(ex);
    }

    @ExceptionHandler(BadRequest.class)
    public ResponseEntity<?> handleBadRequest(BadRequest ex) {
        return handleHttpError(ex);
    }

    private ResponseEntity<?> handleHttpError(BaseHttpException ex) {
        ErrorResponse errorResponse = ex.getErrorResponse();
        LOGGER.error("Exception occurred, returnCode= {}, returnDesc= {}, detailErrMsg= {}",
                errorResponse.getReturnCode(), errorResponse.getReturnDesc(), errorResponse.getDetailMessage());
        return ResponseEntity.status(errorResponse.getStatus())
                .body(errorResponse);
    }
}
