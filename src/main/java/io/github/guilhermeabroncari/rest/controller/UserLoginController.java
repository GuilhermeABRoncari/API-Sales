package io.github.guilhermeabroncari.rest.controller;

import io.github.guilhermeabroncari.config.security.jwt.JwtService;
import io.github.guilhermeabroncari.domain.entity.UserLogin;
import io.github.guilhermeabroncari.exceptions.InvalidPasswordException;
import io.github.guilhermeabroncari.rest.dto.CredentialsDTO;
import io.github.guilhermeabroncari.rest.dto.TokenDTO;
import io.github.guilhermeabroncari.service.impl.UserLoginServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserLoginController {

    private final UserLoginServiceImp userLoginServiceImp;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserLogin save(@RequestBody @Valid UserLogin userLogin) {
        userLogin.setPassword(passwordEncoder.encode(userLogin.getPassword()));
        return userLoginServiceImp.saveUserLogin(userLogin);
    }

    @PostMapping("/auth")
    public TokenDTO authenticate(@RequestBody CredentialsDTO credentialsDTO) {
        try {
            UserLogin userLogin = UserLogin.builder()
                    .login(credentialsDTO.getLogin())
                    .password(credentialsDTO.getPassword()).build();

            UserDetails authenticateUser = userLoginServiceImp.authenticate(userLogin);

            String token = jwtService.tokenGenerator(userLogin);

            return new TokenDTO(userLogin.getLogin(), token);

        } catch (UsernameNotFoundException | InvalidPasswordException ex) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ex.getMessage());
        }
    }
}
