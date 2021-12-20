package com.example.newimse_project.Filling;

import com.example.newimse_project.Model.Chapters;
import com.example.newimse_project.Model.User;
import com.example.newimse_project.Repository.chapterRepository;
import com.example.newimse_project.Repository.userRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.util.List;


@RequiredArgsConstructor
public class fillingChapters implements CommandLineRunner {


    @Autowired
    chapterRepository repository;

    @Override
    public void run(String... args) throws Exception {

        List<Chapters> chapters = generateData.generatechapters();
        for (int i = 0; i < chapters.size(); i++) {
            repository.save(chapters.get(i));
        }
    }
}
