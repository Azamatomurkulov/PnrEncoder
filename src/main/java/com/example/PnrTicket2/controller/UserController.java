package com.example.PnrTicket2.controller;

import com.example.PnrTicket2.dto.UserSaveDto;
import com.example.PnrTicket2.entity.User;
import com.example.PnrTicket2.service.UserService;
import lombok.AllArgsConstructor;
import net.bytebuddy.asm.Advice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    UserService userService;

    @GetMapping("/{id}")
    ResponseEntity<UserSaveDto> getById(@PathVariable Long id) throws Exception {
        try {return new ResponseEntity<>( userService.getUserById(id), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(null,
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    List<UserSaveDto> getAllUsers(){
        return userService.getAllUsers();
    }
    @PostMapping("/newuser")
    UserSaveDto addNewUser(@RequestBody UserSaveDto dto){
        return userService.addNewUser(dto);
    }
    @PutMapping("/delete/{id}")
    LocalDate deleteUser(@PathVariable Long id){
        return userService.deleteUserById(id);
    }
    @PutMapping("/update/{id}")
    UserSaveDto updateUser(@PathVariable Long id,
                           @RequestBody UserSaveDto dto){
        return userService.updateUserById(id, dto);
    }
    @PutMapping("/newpassword/{name}")
    String newPassword(@PathVariable String name){
        return userService.passwordUpdate(name);
    }
    @PutMapping("/passwordupdate")
    String passwordUpdateForUser(@RequestParam String name,
                                 @RequestParam String pw){
        return userService.passwordUpdateForUser(name,pw);
    }

}
