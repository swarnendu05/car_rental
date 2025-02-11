package com.springSecurity.controller;

import com.springSecurity.Service.JWTService;
import com.springSecurity.Service.OTPService;
import com.springSecurity.Service.UserService;
import com.springSecurity.entity.User;
import com.springSecurity.payload.JWTTokenDTO;
import com.springSecurity.payload.LoginDTO;
import com.springSecurity.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/auth")  // ✅ Fixed Typo
public class AuthController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private OTPService otpservice;
    private  JWTService jwtService;

    public AuthController(JWTService jwtService, OTPService otpservice, PasswordEncoder passwordEncoder, UserRepository userRepository, UserService userService) {
        this.jwtService = jwtService;
        this.otpservice = otpservice;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return new ResponseEntity<>("User with this username already exists", HttpStatus.CONFLICT);
        }
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return new ResponseEntity<>("User with this email already exists", HttpStatus.CONFLICT);
        }

        // Hash the password before saving
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10));
        user.setPassword(hashedPassword);
        user.setRole("Role_User");
        userRepository.save(user);

        return new ResponseEntity<>("User created", HttpStatus.CREATED);
    }



    @PostMapping("/content-manager-signup")

    public ResponseEntity<String> createContentManagerAccount(@RequestBody User user) {
        Optional<User> opUsername = userRepository.findByUsername(user.getUsername());
        if (opUsername.isPresent()) {
            return new ResponseEntity<>("username exists", INTERNAL_SERVER_ERROR);
        }
        Optional<User> opEmail = userRepository.findByEmail(user.getEmail());
        if (opEmail.isPresent()) {
            return new ResponseEntity<>("email exists", INTERNAL_SERVER_ERROR);
        }
        //String encodedPassword =  passwordEncoder.encode(user.getPassword());
        //user.setPassword(encodedPassword);
        String hashpw = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10));
        user.setPassword(hashpw);
        user.setRole("ROLE_CONTENTMANAGER");
        userRepository.save(user);
        return new ResponseEntity<>("content manager created", HttpStatus.CREATED);

}

    @PostMapping("/blog-manager-signup")

    public ResponseEntity<String> createBlogManagerAccountUser(@RequestBody User user) {
        Optional<User> opUsername = userRepository.findByUsername(user.getUsername());
        if (opUsername.isPresent()) {
            return new ResponseEntity<>("username exists", INTERNAL_SERVER_ERROR);
        }
        Optional<User> opEmail = userRepository.findByEmail(user.getEmail());
        if (opEmail.isPresent()) {
            return new ResponseEntity<>("email exists", INTERNAL_SERVER_ERROR);
        }
        //String encodedPassword =  passwordEncoder.encode(user.getPassword());
        //user.setPassword(encodedPassword);
        String hashpw = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10));
        user.setPassword(hashpw);
        user.setRole("ROLE_BLOGMANAGER");
        userRepository.save(user);
        return new ResponseEntity<>("user created", HttpStatus.CREATED);
    }






    @PostMapping("/signin")  // ✅ Fixed Typo
    public ResponseEntity<?> userSignIn(@RequestBody LoginDTO loginDTO) {
        String jwtToken = userService.varifyLogin(loginDTO);

        if (jwtToken != null) {
            JWTTokenDTO jwtTokenDTO = new JWTTokenDTO();
            jwtTokenDTO.setToken(jwtToken);
            jwtTokenDTO.setTokenType("JWT");
            return new ResponseEntity<>(jwtTokenDTO, HttpStatus.OK);
        }

        return new ResponseEntity<>("Invalid User", HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/login-otp")
    public String generateOtp(
            @RequestParam  String mobile
    ){
       Optional<User>opUser= userRepository.findByMobile(mobile);
        if(opUser.isPresent()) {


            String otp = otpservice.generateOTP(mobile);
            return otp + " - " + mobile;
        }
        return "user not found";


     }

     @PostMapping("/validate-otp")
     public String validateOTP(
             @RequestParam String mobile,
             @RequestParam String otp
     ) {

         boolean status = otpservice.ValidateOTP(mobile, otp);
         if (status)  {
             Optional<User>opUser= userRepository.findByMobile(mobile);
             if(opUser.isPresent()) {
                 String jwtToken= jwtService.generateToken(opUser.get().getUsername());
                 return  jwtToken;
             }
         }
         return status ? "Otp validated " : "invalid otp";


     }

}
