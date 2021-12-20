package com.example.newimse_project.Filling;

import com.example.newimse_project.Model.Book_Publisher;
import com.example.newimse_project.Model.Publisher;
import com.example.newimse_project.Repository.book_publisherRepository;
import com.example.newimse_project.Repository.publisherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.util.List;

@RequiredArgsConstructor
public class fillingBook_publisher implements CommandLineRunner {

    @Autowired
    private book_publisherRepository repository;

     @Override
    public void run(String... args) throws Exception {

        List<Book_Publisher> bookPublishers = generateData.generateBook_publishers();
        for (int i = 0; i < bookPublishers.size(); i++) {
            repository.save(bookPublishers.get(i));
        }

    }
}
