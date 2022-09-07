package com.expenseapp.controller;

import com.expenseapp.model.User;
import com.expenseapp.model.UserModel;
import com.expenseapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

//    no need to create rest endpoint to read al users since a user should not be allowed to see all users only
//    admin should have that access.

    @GetMapping("/profile")
    public ResponseEntity<User> getUser() {
        return new ResponseEntity<User>(userService.readUser(), HttpStatus.OK);
    }

    @PutMapping("/profile")
    public ResponseEntity<User> updateUser(@RequestBody UserModel user) {
        return new ResponseEntity<User>(userService.updateUser(user), HttpStatus.OK);
    }

    @DeleteMapping("deactivate")
    public ResponseEntity<HttpStatus> deleteUser() {
        userService.deleteUser();
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }

}
