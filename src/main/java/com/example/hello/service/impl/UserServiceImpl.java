package com.example.hello.service.impl;

import com.example.hello.dao.UserDao;
import com.example.hello.model.User;
import com.example.hello.repository.UserRepository;
import com.example.hello.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private UserDao userDao;

    @Override
    public User findUser() {
        int id = 1;
        String name = "ZKT";
        List<User> users =  userDao.findByParam(id, name);
        if (users.size() > 0) {
            return users.get(0);
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addUserA() {
        User user = new User();
        user.setCode("sxh");
        user.setName("孙晓航");
        userRepository.save(user);
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
            @Override
            public void afterCommit() {
                addUserB();
            }
        });
    }

    @Transactional(rollbackFor = Exception.class)
    public void addUserB() {
        User user = new User();
        user.setCode("zkt");
        user.setName("张恺悌");
        userRepository.save(user);
//        System.out.println(1/0);
        addUserC();
    }

    @Transactional(rollbackFor = Exception.class)
    public void addUserC() {
        User user = new User();
        user.setCode("jy");
        user.setName("江杨");
        userRepository.save(user);
        System.out.println(1/0);
    }
}
