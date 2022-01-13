package com.example.newimse_project.Model.mongo.service;


import com.example.newimse_project.Model.mongo.mongoUser;
import com.example.newimse_project.Repository.Mongo.mongoUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UserService {
    private final mongoUserRepository mongoUserRepository;

    public List<mongoUser> getAllUser(){
        return mongoUserRepository.findAll();
    }
}
