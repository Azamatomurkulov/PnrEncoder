package com.example.PnrTicket2.controller;

import com.example.PnrTicket2.dto.UserSaveDto;
import com.example.PnrTicket2.service.UserService;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    UserService userService;



    @GetMapping("/{id}")
    UserSaveDto getById(@PathVariable Long id){
        return userService.getUserById(id);
    }
    @GetMapping("/all")
    List<UserSaveDto> getAllUsers(){
        return userService.getAllUsers();
    }
    @PostMapping("/newUser")
    UserSaveDto addNewUser(@RequestBody UserSaveDto dto){
        return userService.addNewUser(dto);
    }
    @PutMapping("/delete/{id}")
    String deleteUser(@PathVariable Long id){
        return userService.deleteUserById(id);
    }
    @PutMapping("/update/{id}")
    UserSaveDto updateUser(@PathVariable Long id,
                           @RequestBody UserSaveDto dto){
        return userService.updateUserById(id, dto);
    }
    @PutMapping("/newPassword/{login}")
    String newPassword(@PathVariable String login){
        return userService.passwordUpdate(login);
    }
    @PutMapping("/passwordUpdate")
    String passwordUpdateForUser(@RequestParam String login,
                                 @RequestParam String password){
        return userService.passwordUpdateForUser(login,password);
    }

}
