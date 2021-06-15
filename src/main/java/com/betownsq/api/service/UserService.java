package com.betownsq.api.service;

import com.betownsq.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String getUserProps(String email) throws CloneNotSupportedException {
        return userRepository.getUserProps(email);
    }
}
