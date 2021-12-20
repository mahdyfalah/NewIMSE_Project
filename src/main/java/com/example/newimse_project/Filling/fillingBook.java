package com.example.newimse_project.Filling;

import com.example.newimse_project.Model.Book;
import com.example.newimse_project.Model.User;
import com.example.newimse_project.Repository.bookRepository;
import com.example.newimse_project.Repository.userRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.util.List;

@RequiredArgsConstructor
public class fillingBook implements CommandLineRunner {


    @Autowired
    private bookRepository repository;

    @Override
    public void run(String... args) throws Exception {

        List<Book> bookList = generateData.generateBook();
        for (int i = 0; i < bookList.size(); i++) {
            repository.save(bookList.get(i));
        }
    }
}
