package io.github.guilhermeabroncari.rest.controller;

import io.github.guilhermeabroncari.domain.entity.UserLogin;
import io.github.guilhermeabroncari.domain.repository.UserLoginRepository;
import io.github.guilhermeabroncari.service.impl.UserLoginServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserLoginController {

    private final UserLoginServiceImp userLoginServiceImp;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserLogin save(@RequestBody @Valid UserLogin userLogin) {
        String cryptedPassword = passwordEncoder.encode(userLogin.getPassword());
        userLogin.setPassword(cryptedPassword);
        return userLoginServiceImp.saveUserLogin(userLogin);
    }
}
