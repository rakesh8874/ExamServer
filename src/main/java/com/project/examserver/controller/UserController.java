package com.project.examserver.controller;
import com.project.examserver.domain.User;
import com.project.examserver.exception.UserAlreadyExist;
import com.project.examserver.exception.UserNotFound;
import com.project.examserver.security.IGenerateJwt;
import com.project.examserver.service.IUserService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/user-authenticate")
public class UserController {
    @Autowired
    IUserService userService;


    @Autowired
    IGenerateJwt generateJwt;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) throws UserAlreadyExist {
        return new ResponseEntity<>(userService.registerUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/getUsers")
    public ResponseEntity<?> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) throws UserAlreadyExist {
        User tempUser = userService.loginUser(user.getEmail(), user.getPassword());
            if(tempUser != null){
                Map<String, String> map = generateJwt.getJwtToken(tempUser);
                return new ResponseEntity<>(map, HttpStatus.CREATED);
            }
            return new ResponseEntity<>("Wrong Email Or Password", HttpStatus.NOT_FOUND);
        }

        @GetMapping("/login-user")
        public ResponseEntity<?> getCurrentUser(HttpServletRequest request) throws UserNotFound {
        Claims claims = (Claims)  request.getAttribute("claims");
        String email = claims.getSubject();
        return new ResponseEntity<>(userService.getCurrentUser(email), HttpStatus.OK);
        }


}
