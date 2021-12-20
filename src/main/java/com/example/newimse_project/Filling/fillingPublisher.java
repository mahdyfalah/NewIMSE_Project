package com.example.newimse_project.Filling;

import com.example.newimse_project.Model.Book;
import com.example.newimse_project.Model.Publisher;
import com.example.newimse_project.Repository.bookRepository;
import com.example.newimse_project.Repository.publisherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class fillingPublisher implements CommandLineRunner {


    @Autowired
    private publisherRepository publisherRepository;

    @Override
    public void run(String... args) throws Exception {

        List<Publisher> publishers = generateData.generatePublisher();
        for (int i = 0; i < publishers.size(); i++) {
            publisherRepository.save(publishers.get(i));
        }

    }
}
