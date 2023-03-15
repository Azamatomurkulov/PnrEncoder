package com.example.PnrTicket2.security;

import com.example.PnrTicket2.entity.User;
import com.example.PnrTicket2.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {


    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByName(username);
        return new org.springframework.security.core.userdetails.User(user.getName(),user.getPassword(),user.getRole().getAuthorities());
    }
}