package com.quickbus.view.impl;

import com.quickbus.dto.UserRegisterDto;
import com.quickbus.model.UserDetail;
import com.quickbus.model.oauth.Role;
import com.quickbus.model.oauth.User;
import com.quickbus.repository.UserDetailRepo;
import com.quickbus.repository.oauth.RoleRepo;
import com.quickbus.repository.oauth.UserRepo;
import com.quickbus.response.ResponseMap;
import com.quickbus.view.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private UserDetailRepo userDetailRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseMap registerUser(UserRegisterDto user){
        try{
//            check existing email
            if(userRepo.checkExistingEmail(user.getUsername())!=null){
                return new ResponseMap().error(HttpStatus.BAD_REQUEST,"Email is already taken");
            }

            User newUser = new User();
            newUser.setUsername(user.getUsername());
            newUser.setPassword(passwordEncoder.encode(user.getPassword()));
            List<Role> roles = roleRepo.findByNameIn(new String[]{"ROLE_USER"});
            newUser.setRoles(roles);
            User createdUser = userRepo.save(newUser);
            UserDetail userDetail = new UserDetail();
            userDetail.setName(user.getName());
            userDetail.setAddress(user.getAddress());
            userDetail.setPhone(user.getPhone());
            userDetail.setUser(createdUser);
            userDetailRepo.save(userDetail);
            createdUser.setUserDetail(userDetail);
            return new ResponseMap().success(createdUser,"User register success");
        }catch (Exception e){
            return new ResponseMap().error(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Failed to create user"
            );
        }
    }

    @Override
    public ResponseMap getAuthenticatedUser(){
        try{
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = userRepo.findOneByUsername(auth.getName());
            return new ResponseMap().success(user,"Success get authenticated user");

        }catch (Exception e){
            return new ResponseMap().error(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Failed to get authenticated user"
            );
        }
    }
}
