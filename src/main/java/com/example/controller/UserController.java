package com.example.controller;

import com.example.payloads.ApiResponse;
import com.example.payloads.UserDto;
import com.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    //Post create user
    @PostMapping("/")
    public ResponseEntity<UserDto>createUser(@RequestBody UserDto userDto){
        UserDto createUserDto=this.userService.createUser(userDto);
        return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }
    //Put update user
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable("userId") Integer uid){
        UserDto updateUser=this.userService.updateUser(userDto,uid);
        return ResponseEntity.ok(updateUser);
    }
    //Delete user
    @DeleteMapping("/{userId}")
//    public ResponseEntity<?> deleteUser(@PathVariable("userId")Integer uid){
//        this.deleteUser(uid);
//        return new ResponseEntity(Map.of("message","User deleted Successfully"),HttpStatus.OK);
        public ResponseEntity<ApiResponse>deleteUser(@PathVariable("userId") Integer uid){
        this.deleteUser(uid);
        return new ResponseEntity<>(new ApiResponse("User deleted Successfully",true),HttpStatus.OK);
    }
    //Get user
    @GetMapping("/")
    public ResponseEntity<List<UserDto>>getAllUsers(){
        return ResponseEntity.ok(this.userService.getAllUsers());
    }
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto>getSingleUser(@PathVariable Integer userId){
        return ResponseEntity.ok(this.userService.getUserById(userId));
    }
}
