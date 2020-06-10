package ru.spbstu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import ru.spbstu.entity.User;
import ru.spbstu.service.IAuthService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    
    private final IAuthService authService;
    
    @Autowired
    public AuthController(IAuthService authService) {
        this.authService = authService;
    }
    
    @PostMapping(value = "/logIn", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Map<Object, Object>> signIn(@RequestBody AuthRequest request) {
        Map<Object, Object> map = new HashMap<>();
        try {
            map = authService.signIn(request);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (AuthenticationException e) {
            return new ResponseEntity<>(map, HttpStatus.FORBIDDEN);
        }
    }
    
    @PostMapping(value = "/signUp", consumes = "application/json", produces = "application/json")
    public ResponseEntity<User> signUp(@RequestBody AuthRequest request) {
        User user = authService.signUp(request);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}