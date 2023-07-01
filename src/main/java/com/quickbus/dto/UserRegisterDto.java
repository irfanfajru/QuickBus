package com.quickbus.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserRegisterDto {
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    @NotEmpty
    private String name;
    @NotEmpty
    private String address;
    @NotEmpty
    private String phone;
}
