package ec.ecu.ups.icc.exceptions.domain;

import org.springframework.http.HttpStatus;

import ec.ecu.ups.icc.exceptions.base.ApplicationException;

public class NotFoundException extends ApplicationException {

    public NotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}