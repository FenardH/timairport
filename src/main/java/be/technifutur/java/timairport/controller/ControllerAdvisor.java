package be.technifutur.java.timairport.controller;

import be.technifutur.java.timairport.exceptions.NoCompanyAvailableException;
import be.technifutur.java.timairport.exceptions.NoPlaneAvailableException;
import be.technifutur.java.timairport.exceptions.RessourceNotFoundException;
import be.technifutur.java.timairport.model.dto.ErrorDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor {
    @ExceptionHandler(RessourceNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleRessourceNotFoundException(
            RessourceNotFoundException e, HttpServletRequest request
    ) {
        ErrorDTO errorDTO = new ErrorDTO(
                request.getMethod(),
                request.getRequestURL().toString(),
                e.getMessage(),
                HttpStatus.BAD_REQUEST.value()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTO);
    }

    @ExceptionHandler(NoPlaneAvailableException.class)
    public ResponseEntity<ErrorDTO> handleNoPlaneAvailableException(
            NoPlaneAvailableException e, HttpServletRequest request
    ) {
        ErrorDTO errorDTO = new ErrorDTO(
                request.getMethod(),
                request.getRequestURL().toString(),
                e.getMessage(),
                HttpStatus.BAD_REQUEST.value()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTO);
    }

    @ExceptionHandler(NoCompanyAvailableException.class)
    public ResponseEntity<ErrorDTO> handleNoCompanyAvailableException(
            NoCompanyAvailableException e, HttpServletRequest request
    ) {
        ErrorDTO errorDTO = new ErrorDTO(
                request.getMethod(),
                request.getRequestURL().toString(),
                e.getMessage(),
                HttpStatus.BAD_REQUEST.value()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTO);
    }
}
