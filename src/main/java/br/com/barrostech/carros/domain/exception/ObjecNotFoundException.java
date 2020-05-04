package br.com.barrostech.carros.domain.exception;

import br.com.barrostech.carros.dto.CarroDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ObjecNotFoundException extends RuntimeException {

    public ObjecNotFoundException(String message){
        super(message);
    }

    public ObjecNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
