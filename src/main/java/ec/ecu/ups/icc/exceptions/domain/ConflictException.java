package ec.ecu.ups.icc.exceptions.domain;

import org.springframework.http.HttpStatus;
import ec.ecu.ups.icc.exceptions.base.ApplicationException;


public class ConflictException extends ApplicationException {

    public ConflictException(String message) {
        super(HttpStatus.CONFLICT, message);
    }
}