package edu.comillas.icai.gitt.pat.spring.p5.model;

import edu.comillas.icai.gitt.pat.spring.p5.entity.AppUser;
import edu.comillas.icai.gitt.pat.spring.p5.entity.Token;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.apache.juli.logging.Log;
import org.hibernate.exception.DataException;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


class RegisterRequestUnitTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    public void testValidRequest() {
        // Given ...
        RegisterRequest registro = new RegisterRequest(
                "Nombre", "nombre@email.com",
                Role.USER, "aaaaaaA1");
        // When ...
        Set<ConstraintViolation<RegisterRequest>> violations =
                validator.validate(registro);
        // Then ...
        assertTrue(violations.isEmpty()); //Este test comprueba que no se espere niguna excepción
        //ya que los datos introducidos en el registro, siguen el modelo correcto.
    }

    @Test
    public void testValidRequestWithoutName(){
        RegisterRequest registro = new RegisterRequest("", "nombre@email.com",Role.ADMIN, "Futbol123");
        Set<ConstraintViolation<RegisterRequest>> violations =validator.validate(registro);
        assertFalse(violations.isEmpty()); //Se espera que el si que haya violaciones en el código
        //por lo tanto, se pone assertFalse(violations.isEmpty()); ya que no esperamos que violations
        //quede vacío debido a que el campo name esta vacio.
    }

    @Test
    public void testWithIncorrectEmailAndIncorrectPassword(){
        RegisterRequest registro = new RegisterRequest("Carlos", "Carlossemail.com",Role.ADMIN, "barsa123");
        Set<ConstraintViolation<RegisterRequest>> violations =validator.validate(registro);
        assertFalse(violations.isEmpty());
        //Aqui están ocurriendo mas de una violación en el código, la primera de ellas
        //no se está siguiente el formato de Email correctamente, ya que falta por poner la @.
        //Por otro lado, a la contraseña le falta una mayuscula por lo tanto esto también generará una
        //violacion en violations.

    }

}