package com.example.demo.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//Cette controleur sera partagé par tous les autres controleurs,
//Equivalente a le cas dont tous les classes herite cette classe
@ControllerAdvice
public class ApiExceptionsHandler extends ResponseEntityExceptionHandler {

   // ****** Avec cette methode on va handler the notFoundException error, mais cette methode est catastrophique si on a bcp des
    // erreurs pour les handler *****
    //**** SOLUTION: la creation d'une classe ApiBaseException qui va prend on charge le handle des errors !
    // @ExceptionHandler(NotFoundException.class)
    //public ResponseEntity<ErrorDetails> handlerApiExceptions(NotFoundException ex , WebRequest request){
     //   ErrorDetails details = new ErrorDetails(ex.getMessage(),request.getDescription(false));
      //  return new ResponseEntity<>(details, ex.getStatus());
    //}

    @ExceptionHandler(ApiBaseException.class)
    public ResponseEntity<ErrorDetails> handlerApiExceptions(ApiBaseException ex , WebRequest request){
       ErrorDetails details = new ErrorDetails(ex.getMessage(),request.getDescription(false));
      return new ResponseEntity<>(details, ex.getStatus());
    }

}
