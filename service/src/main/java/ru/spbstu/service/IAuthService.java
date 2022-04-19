package ru.spbstu.service;

import ru.spbstu.controller.AuthRequest;
import ru.spbstu.entity.User;

import java.util.Map;

public interface IAuthService {
    Map<Object, Object> signIn(AuthRequest authRequest);
    User signUp(AuthRequest authRequest);
}