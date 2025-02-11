package com.springSecurity.Exception;


import com.springSecurity.payload.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


import java.util.Date;

@ControllerAdvice
public class ExceptionHandling {


    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<?> ResourceNotFound(
            ResourceNotFound r,
            WebRequest req

    ){
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setErrorMessage(r.getMessage());
        errorDTO.setRequest(req.getDescription(false));
        errorDTO.setDate(new Date());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);

    }

}
