package com.quickbus.controller;

import com.quickbus.dto.UserRegisterDto;
import com.quickbus.response.ResponseMap;
import com.quickbus.view.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/user-register")
    public ResponseEntity<ResponseMap> register(@Valid @RequestBody UserRegisterDto user){
        ResponseMap response = userService.registerUser(user);
        return new ResponseEntity<ResponseMap>(response,response.getCode());
    }

    @GetMapping("/whoiam")
    public ResponseEntity<ResponseMap> whoiam(){
        ResponseMap response = userService.getAuthenticatedUser();
        return new ResponseEntity<ResponseMap>(response,response.getCode());
    }

}
