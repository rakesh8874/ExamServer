package com.project.examserver.controller;

import com.project.examserver.domain.Role;
import com.project.examserver.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-auth")
public class RoleController {

    @Autowired
    IRoleService roleService;

    @PostMapping("/create")
    public ResponseEntity<?> createRole(@RequestBody Role role){
     return new ResponseEntity<>(roleService.createRole(role), HttpStatus.CREATED);
    }

}
