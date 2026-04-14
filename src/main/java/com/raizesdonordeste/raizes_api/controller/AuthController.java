package com.raizesdonordeste.raizes_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.raizesdonordeste.raizes_api.service.JwtService;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");

        // Aqui é possível implementar a lógica de autenticação (verificar username e password)
        String token = jwtService.generateToken(username);
        return Map.of("token", token);
    }

}
