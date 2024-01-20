package com.lotus.controller;

import com.lotus.dto.LoginRequestDTO;
import com.lotus.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    @GetMapping("/hello")
    public ResponseEntity<String> get(){
        return ResponseEntity.ok("Hello from the other side");
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO request) {
        UsernamePasswordAuthenticationToken token = new
                UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
        authenticationManager.authenticate(token);

        // if there is no exception thrown from authentication manager,
        // we can generate a JWT token and give it to the user.
        String jwt = jwtUtil.generate(request.getUsername());
        return ResponseEntity.ok(jwt);


    }
}
