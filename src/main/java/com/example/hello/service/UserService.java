package com.example.hello.service;

import com.example.hello.model.User;

import java.util.List;

public interface UserService {

    User findUser();

    List<User> findAll();

    void addUserA();
}
