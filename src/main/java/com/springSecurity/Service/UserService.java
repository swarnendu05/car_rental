package com.springSecurity.Service;


import com.springSecurity.entity.User;
import com.springSecurity.payload.LoginDTO;
import com.springSecurity.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.Optional;

@Service
public class UserService {


    private UserRepository userRepository;
    private JWTService jwtService;

    public UserService(UserRepository userRepository, JWTService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    public String varifyLogin(LoginDTO loginDTO){

        Optional<User> opuser = userRepository.findByUsername(loginDTO.getUserName());

        if(opuser.isPresent()){
                User user=opuser.get();
                if(BCrypt.checkpw(loginDTO.getPassdWord(),user.getPassword())){
                  return   jwtService.generateToken(user.getUsername());
                }
        }
        return  null;

    }
}
