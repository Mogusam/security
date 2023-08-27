package org.jgmp.security.service;

import org.jgmp.security.domain.Users;
import org.jgmp.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    public static final String YOUR_SALT = "your_salt";
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registerUser(String username, String password) {
        String salt = YOUR_SALT; // Сгенеруйте випадкову сіль для кожного користувача
        String saltedPassword = password + salt;
        String hashedPassword = passwordEncoder.encode(saltedPassword);

        Users newUser = new Users(username, hashedPassword, 1);
        userRepository.save(newUser);
    }
}
