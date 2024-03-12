package com.dsi.studentmanagementsystem.service;

import com.dsi.studentmanagementsystem.dto.MyUserDetails;
import com.dsi.studentmanagementsystem.entity.User;
import com.dsi.studentmanagementsystem.entity.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class MyUserDetailsService implements UserDetailsService {


    private final UserRepository userRepository;

    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUserName(userName);
        user.orElseThrow(()->new UsernameNotFoundException("Not found "+ userName));
        return user.map(MyUserDetails::new).get();
    }
}
