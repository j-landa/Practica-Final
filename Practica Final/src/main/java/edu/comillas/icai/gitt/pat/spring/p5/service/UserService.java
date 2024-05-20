package edu.comillas.icai.gitt.pat.spring.p5.service;

import edu.comillas.icai.gitt.pat.spring.p5.entity.AppUser;
import edu.comillas.icai.gitt.pat.spring.p5.entity.Pez;
import edu.comillas.icai.gitt.pat.spring.p5.entity.Token;
import edu.comillas.icai.gitt.pat.spring.p5.model.*;
import edu.comillas.icai.gitt.pat.spring.p5.repository.PezRepository;
import edu.comillas.icai.gitt.pat.spring.p5.repository.TokenRepository;
import edu.comillas.icai.gitt.pat.spring.p5.repository.AppUserRepository;
import edu.comillas.icai.gitt.pat.spring.p5.util.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
public class UserService implements UserServiceInterface {
    @Autowired
    AppUserRepository appUserRepository;
    @Autowired
    TokenRepository tokenRepository;
    @Autowired
    PezRepository pezRepository;

    @Autowired Hashing hashing;

    public Token login(String email, String password) {
        AppUser appUser = appUserRepository.findByEmail(email);
        if (appUser == null) return null;
        if(hashing.compare(appUser.getPassword(),password)) {  //Devuelve true si la contraseña cifrada y sin cifrar son la misma
            Token token = new Token();
            token.setAppUser(appUser);
            tokenRepository.save(token);
            return token;
        }
        return null;
    }

    public AppUser authentication(String tokenId) {
        Token token = tokenRepository.findByid(tokenId);
         if(token!=null){
             return token.getAppUser();
         }
        return null;
    }

    public ProfileResponse profile(AppUser appUser) { //Si el usuario pasado como parametro existe
        if(appUserRepository.findByid(appUser.getId())!=null){
            return new ProfileResponse(appUser.getName(),appUser.getEmail(),appUser.getRole());
        }
        return null; //Si no existe

    }
    public ProfileResponse profile(AppUser appUser, ProfileRequest profile) {
        if(appUserRepository.findByid(appUser.getId())!=null){ //Si existe dicho usuario
            appUser.setName(profile.name());
            appUser.setRole(profile.role());
            appUser.setPassword(hashing.hash(profile.password())); //Se asigna la contraseña cifrada
            appUserRepository.save(appUser);

            return new ProfileResponse(appUser.getName(),appUser.getEmail(),appUser.getRole());

        }
        return null;
    }
    public ProfileResponse profile(RegisterRequest register) {
        AppUser appUser = new AppUser();
        appUser.setEmail(register.email());
        appUser.setName(register.name());
        appUser.setRole(register.role());
        appUser.setPassword(hashing.hash(register.password())); //A la hora de crear el perfil, se asigna la contraseña cifrada
        appUserRepository.save(appUser);
        return new ProfileResponse(appUser.getName(),appUser.getEmail(),appUser.getRole());
    }

    public void logout(String tokenId){
        tokenRepository.delete(tokenRepository.findByid(tokenId));
    }

    public PezRequest pezProfile(PezRequest pezRequest) {
        AppUser user = appUserRepository.findByEmail(pezRequest.correo());
        if(user != null) {
            if (user.getRole().equals(Role.ADMIN) && user.getEmail() != null) {
                Pez pez = new Pez();
                pez.setColor(pezRequest.color());
                pez.setSexo(pezRequest.sexo());
                pez.setTipo(pezRequest.tipo());
                pez.setCorreo(pezRequest.correo());
                pezRepository.save(pez);
            }
        }
        return null;
    }

    public List<Pez> getPeces(){
        List<Pez> peces = (List<Pez>) pezRepository.findAll();
        return peces;
    }

    public void borraPez(Long id){
        Pez pez = pezRepository.findByid(id);
        if(pez!=null) {
            pezRepository.delete(pez);
        }
    }

}
