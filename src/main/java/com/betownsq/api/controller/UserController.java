package com.betownsq.api.controller;

import com.betownsq.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET, value = "/user/{userEmail}")
    public ResponseEntity<?> getUserProps(@PathVariable("userEmail") String userEmail) throws CloneNotSupportedException {
        return new ResponseEntity<>(userService.getUserProps(userEmail), HttpStatus.OK);
    }
}
