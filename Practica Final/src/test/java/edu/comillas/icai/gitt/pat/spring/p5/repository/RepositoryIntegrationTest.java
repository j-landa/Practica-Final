package edu.comillas.icai.gitt.pat.spring.p5.repository;

import edu.comillas.icai.gitt.pat.spring.p5.entity.AppUser;
import edu.comillas.icai.gitt.pat.spring.p5.entity.Token;
import edu.comillas.icai.gitt.pat.spring.p5.model.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class RepositoryIntegrationTest {
    @Autowired TokenRepository tokenRepository;
    @Autowired AppUserRepository appUserRepository;

    @Test void saveTest() {
        // Given ...
        AppUser user = new AppUser();
        Token token = new Token();

        // When ...
        user.setEmail("carlos@gmail.com");
        user.setPassword("Barsa123");
        user.setName("Carlos");
        user.setRole(Role.USER);

        token.setAppUser(user);

        tokenRepository.save(token); //Aqui se guardan en los repositorios
        appUserRepository.save(user);

        // Then ...

        Assertions.assertTrue(appUserRepository.findByEmail(user.getEmail()).equals(user));
        Assertions.assertTrue(tokenRepository.findByAppUser(user).equals(token));
        //Esperamos que se haya creado correctamente
    }

    @Test void deleteCascadeTest() {
        // Given ...
        AppUser user = new AppUser();
        Token token = new Token();

        user.setEmail("carlos@gmail.com");
        user.setPassword("Barsa123");
        user.setName("Carlos");
        user.setRole(Role.USER);

        token.setAppUser(user);

        tokenRepository.save(token); //Aqui se guardan en los repositorios
        appUserRepository.save(user);

        // When ...
        appUserRepository.delete(user);

        // Then ...
        Assertions.assertEquals(0, appUserRepository.count());
        Assertions.assertNull(tokenRepository.findByAppUser(user));

    }
}