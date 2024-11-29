package com.system.angels.controller;

import com.system.angels.domain.User;
import com.system.angels.dto.create.UserLoginDTO;
import com.system.angels.dto.create.UserRegisterDTO;
import com.system.angels.dto.response.UserRO;
import com.system.angels.security.JwtUtil;
import com.system.angels.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserService userService, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<UserRO> register(@RequestBody UserRegisterDTO userRegisterDTO) {
        var user = userService.createUser(userRegisterDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserLoginDTO userLoginDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginDTO.username(), userLoginDTO.password())
        );
        User user = userService.findByUsername(userLoginDTO.username());
        return jwtUtil.generateToken(user.getUsername());
    }
}
