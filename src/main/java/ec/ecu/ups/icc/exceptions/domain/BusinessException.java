package ec.ecu.ups.icc.exceptions.domain;

import org.springframework.http.HttpStatus;

import ec.ecu.ups.icc.exceptions.base.ApplicationException;

public class BusinessException extends ApplicationException {

    public BusinessException(String message) {
        super(HttpStatus.UNPROCESSABLE_CONTENT, message);
    }

    protected BusinessException(HttpStatus status, String message) {
        super(status, message);
    }
}