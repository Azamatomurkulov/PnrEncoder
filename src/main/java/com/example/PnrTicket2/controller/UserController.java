package com.example.PnrTicket2.controller;

import com.example.PnrTicket2.entity.User;
import com.example.PnrTicket2.service.UserService;
import lombok.AllArgsConstructor;
import net.bytebuddy.asm.Advice;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
public class UserController {

    UserService userService;

    @GetMapping("/user/{id}")
    User getById(@PathVariable Long id){
        return userService.getUserById(id);
    }
    @GetMapping("/user/all")
    List<User> getAllUsers(){
        return userService.getAllUsers();
    }
    @PostMapping("/user/newuser")
    User addNewUser(@RequestBody User user){
        return userService.addNewUser(user);
    }
    @PutMapping("/user/delete/{id}")
    LocalDate deleteUser(@PathVariable Long id){
        return userService.deleteUserById(id);
    }

}
