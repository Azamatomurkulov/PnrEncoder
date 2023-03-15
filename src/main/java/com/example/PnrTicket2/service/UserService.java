package com.example.PnrTicket2.service;

import com.example.PnrTicket2.dto.UserSaveDto;
import com.example.PnrTicket2.entity.User;
import com.example.PnrTicket2.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    UserRepository userRepository;


    public User getUserById(Long id){
        return userRepository.findByIdAndRdtIsNull(id);
    }
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    public User addNewUser(User user){
        return userRepository.save(user);
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
}
