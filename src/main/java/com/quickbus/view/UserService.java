package com.quickbus.view;

import com.quickbus.dto.UserRegisterDto;
import com.quickbus.response.ResponseMap;

public interface UserService {

    public ResponseMap registerUser(UserRegisterDto user);
}
