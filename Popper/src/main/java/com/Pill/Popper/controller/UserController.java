package com.Pill.Popper.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Pill.Popper.dao.entity.UserEntity;
import com.Pill.Popper.dao.service.UserService;
import com.Pill.Popper.exception.ResourceNotFoundException2;



@RestController
@RequestMapping("/api/v1/")
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(UserService userService){
        super();
        this.userService = userService;
    }

    // get users

    @GetMapping("users")
    public List<UserEntity> getAllUsers(){
        return this.userService.getAllUsers();
    }

    // get user by id
    @GetMapping("users/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable("id") long userId) throws ResourceNotFoundException2 {
        return new ResponseEntity<UserEntity>(userService.getUserById(userId), HttpStatus.OK);
    }
    
    // save user
    @PostMapping("users")
    public UserEntity createUser(@RequestBody UserEntity user){
        return this.userService.save(user);
    }

    // update user
    @PutMapping("users/{id}")
    public ResponseEntity<UserEntity>updateUser(@PathVariable("id") long userId, @RequestBody UserEntity user) throws ResourceNotFoundException2 {
        return new ResponseEntity<UserEntity>(userService.updateUser(user, userId), HttpStatus.OK );
    }

    // delete user
    @DeleteMapping("users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") long userId) throws ResourceNotFoundException2 {
        userService.deleteUserById(userId);
        return new ResponseEntity<String>("User details deleted successfully!", HttpStatus.OK);

    }

}