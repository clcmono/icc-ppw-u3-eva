package ec.ecu.ups.icc.exceptions.domain;

import org.springframework.http.HttpStatus;

import ec.ecu.ups.icc.exceptions.base.ApplicationException;

public class BadRequestException extends ApplicationException {

    public BadRequestException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}