package ru.spbstu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.spbstu.controller.AuthRequest;
import ru.spbstu.entity.User;
import ru.spbstu.repository.UserRepository;
import ru.spbstu.security.jwt.JwtTokenProvider;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService implements IAuthService {
    
    private final AuthenticationManager authenticationManager;
    
    private final JwtTokenProvider jwtTokenProvider;
    
    private final PasswordEncoder passwordEncoder;
    
    private final UserRepository userRepository;
    
    @Autowired
    public AuthService(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }
    
    @Override
    public Map<Object, Object> signIn(AuthRequest request) {
        String userName = request.getUserName();
        String password = request.getPassword();
        String token = jwtTokenProvider
                .createToken(
                        userName, password, userRepository
                                .findUserByUserName(userName)
                                .orElseThrow(() -> new UsernameNotFoundException("User not found"))
                                .getRoles()
                );
        
        Map<Object, Object> model = new HashMap<>();
        model.put("userName", userName);
        model.put("token", token);
        
        return model;
    }
    
    @Override
    public User signUp(AuthRequest request) {
        String userName = request.getUserName();
        String password = request.getPassword();
        if (userRepository.findUserByUserName(userName).isPresent()) {
            return null;
        }
        
        User user = new User(userName, passwordEncoder.encode(password), Collections.singletonList("ROLE_USER"));
        userRepository.save(user);
        return user;
    }
}
