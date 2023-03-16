package com.example.PnrTicket2.service;

import com.example.PnrTicket2.dto.UserSaveDto;
import com.example.PnrTicket2.entity.User;
import com.example.PnrTicket2.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@AllArgsConstructor
public class UserService {

    UserRepository userRepository;
    DefaultEmailService defaultEmailService;


    public UserSaveDto getUserById(Long id){
        User user = userRepository.findByIdAndRdtIsNull(id);
        return userToDto(user);
    }
    public List<UserSaveDto> getAllUsers(){
        List<User> users = userRepository.findAllByRdtIsNull();
        List<UserSaveDto> dtos = new ArrayList<>();
        for(User user: users){
            dtos.add(userToDto(user));
        }return dtos;
    }
    public UserSaveDto addNewUser(UserSaveDto dto){
        User user = new User();
        user.setLogin(dto.getLogin());
        user.setFullName(dto.getFullName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());

        user = userRepository.save(user);
        dto.setId(user.getId());
        return dto;
    }

    public UserSaveDto userToDto(User user){
        UserSaveDto dto = new UserSaveDto();
        dto.setId(user.getId());
        dto.setFullName(user.getFullName());
        dto.setLogin(user.getLogin());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setRdt(user.getRdt());
        dto.setRole(user.getRole());
        return dto;
    }
    public LocalDate deleteUserById(Long id){
        User user = userRepository.findById(id).get();
        user.setRdt(LocalDate.now());
        userRepository.save(user);
        return user.getRdt();

    }
    public UserSaveDto updateUserById(Long id,UserSaveDto dto){
        User user = userRepository.findById(id).get();
        if(dto.getLogin()!=null){
            user.setLogin(dto.getLogin());
        }
        if(dto.getFullName()!=null){
            user.setFullName(dto.getFullName());
        }
        if(dto.getEmail()!=null){
            user.setEmail(dto.getEmail());
        }
        if(dto.getPassword()!=null){
            user.setPassword(dto.getPassword());
        }
        if(dto.getRole()!=null){
            user.setRole(dto.getRole());
        }
        if(dto.getRdt()!=null){
            user.setRdt(dto.getRdt());
        }
        user = userRepository.save(user);
        dto.setId(user.getId());
        dto = userToDto(user);
        return dto;

    }

    public String passwordUpdate(String name){
        User user = userRepository.findByLogin(name);
        user.setPassword(passwordCode());
        user = userRepository.save(user);
        String email = user.getEmail();
        String newPassword = user.getPassword();
        String message = "This is your new password.";
        defaultEmailService.sendSimpleEmail(email,message,newPassword);

        return "New password sended to user with name: "+name;

    }
    private String passwordCode() {
        int length = 8;
        Random r = new Random();
        String s = r.ints(48, 122)
                .filter(i -> (i < 57 || i > 65) && (i < 90 || i > 97))
                .mapToObj(i -> (char) i)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();

        return s;
    }

    public String passwordUpdateForUser(String name,String newPassword){
        User user = userRepository.findByLogin(name);
        user.setPassword(newPassword);
        userRepository.save(user);
        return "The password for: "+ name+" been updated.";
    }
}
