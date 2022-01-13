package com.example.newimse_project.Model.mongo.service;

import com.example.newimse_project.Model.mongo.mongoBook;
import com.example.newimse_project.Repository.Mongo.monogoBookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class BookService {

    private final monogoBookRepository monogoBookRepository;

    public List<mongoBook> getAllBooks() {
        return monogoBookRepository.findAll();
    }
}
