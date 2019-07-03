package com.example.hello.service.impl;

import com.example.hello.model.User;
import com.example.hello.repository.UserRepository;
import com.example.hello.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User findUser() {
        List<User> users =  userRepository.findAll();
        if (users.size() > 0) {
            return users.get(0);
        }
        return null;
    }
}
