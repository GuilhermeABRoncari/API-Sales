package io.github.guilhermeabroncari.service.impl;

import io.github.guilhermeabroncari.domain.entity.UserLogin;
import io.github.guilhermeabroncari.domain.repository.UserLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import javax.validation.Valid;

@Service
public class UserLoginServiceImp implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserLoginRepository userLoginRepository;
    @Transactional
    public UserLogin saveUserLogin(@RequestBody @Valid UserLogin userLogin){
        return userLoginRepository.save(userLogin);
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserLogin userLogin = userLoginRepository.findByLogin(userName)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found on database."));

        String[] roles = userLogin.isAdmin() ? new String[]{"ADMIN", "USER"} : new String[]{"USER"};

        return User.builder()
                .username(userLogin.getLogin())
                .password(userLogin.getPassword())
                .roles()
                .build();
    }
}
