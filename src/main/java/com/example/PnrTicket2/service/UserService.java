package com.example.PnrTicket2.service;

import com.example.PnrTicket2.dto.UserSaveDto;
import com.example.PnrTicket2.entity.User;
import com.example.PnrTicket2.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    UserRepository userRepository;


    public UserSaveDto getUserById(Long id){
        User user = userRepository.findByIdAndRdtIsNull(id);
        return userToDto(user);
    }
    public List<UserSaveDto> getAllUsers(){
        List<User> users = userRepository.findAll();
        List<UserSaveDto> dtos = new ArrayList<>();
        for(User user: users){
            dtos.add(userToDto(user));
        }return dtos;
    }
    public UserSaveDto addNewUser(UserSaveDto dto){
        User user = new User();
        user.setName(dto.getName());
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
        dto.setName(user.getName());
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
        if(dto.getName()!=null){
            user.setName(dto.getName());
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
}
