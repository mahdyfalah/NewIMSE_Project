package com.example.newimse_project.Filling;

import com.example.newimse_project.Model.User;
import com.example.newimse_project.Repository.userRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.util.List;

@RequiredArgsConstructor
public class fillingUser implements CommandLineRunner {

    @Autowired
    private userRepository  userRepository;

    @Override
    public void run(String... args) throws Exception {

        List<User> userList = generateData.generateUser();
        for (int i = 0; i < userList.size(); i++) {
            userRepository.save(userList.get(i));
        }
    }
}
