package org.itstep.service;

import org.itstep.domain.entity.Authority;
import org.itstep.domain.entity.User;
import org.itstep.domain.repository.AuthorityRepository;
import org.itstep.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;

@Service
@Transactional
public class SecurityService {
    UserRepository userRepository;
    AuthorityRepository authorityRepository;
    PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityService(UserRepository userRepository, AuthorityRepository authorityRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String encode(String password) {
        return passwordEncoder.encode(password);
    }


    public void createAdmin(User user) {
        Authority authority = new Authority("ROLE_ADMIN");
        authority = authorityRepository.save(authority);
        user.setRoles(Collections.singletonList(authority));
        userRepository.save(user);
    }

    public void createAuthor(User user) {
        Authority authority = new Authority("ROLE_AUTHOR");
        authority = authorityRepository.save(authority);
        user.setRoles(Collections.singletonList(authority));
        userRepository.save(user);
    }
}
