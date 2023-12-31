package org.jgmp.security.service;

import org.jgmp.security.domain.Users;
import org.jgmp.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean authenticate(String username, String password) {
        Users user = userRepository.findByUserName(username);
        if (user != null) {

            return passwordEncoder.matches(password, user.getPassword());
        }
        return false;
    }


}
