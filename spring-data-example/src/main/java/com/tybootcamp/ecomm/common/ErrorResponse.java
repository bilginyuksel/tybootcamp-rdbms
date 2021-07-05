package com.tybootcamp.ecomm.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.http.HttpStatus;

public class ErrorResponse {
    public static final ErrorResponse BAD_REQUEST =
            new ErrorResponse(HttpStatus.BAD_REQUEST, "100", "Illegal request arguments.");
    public static final ErrorResponse ENTITY_NOT_FOUND =
            new ErrorResponse(HttpStatus.NOT_FOUND, "101", "Entity not found.");

    private final String returnCode;
    private final String returnDesc;
    private String detailMessage;

    @JsonIgnore
    private final HttpStatus status;

    public ErrorResponse(HttpStatus status, String returnCode, String returnDesc) {
        this.status = status;
        this.returnCode = returnCode;
        this.returnDesc = returnDesc;
    }

    public ErrorResponse(HttpStatus status, String returnCode, String returnDesc, String detailMessage) {
        this(status, returnCode, returnDesc);
        this.detailMessage = detailMessage;
    }

    public ErrorResponse(ErrorResponse other, String detailMessage) {
        this(other.status, other.returnCode, other.returnDesc);
        this.detailMessage = detailMessage;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public String getReturnDesc() {
        return returnDesc;
    }

    public String getDetailMessage() {
        return detailMessage;
    }

}