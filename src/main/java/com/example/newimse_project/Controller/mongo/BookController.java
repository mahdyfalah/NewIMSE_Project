package com.example.newimse_project.Controller.mongo;

import com.example.newimse_project.Model.mongo.mongoBook;
import com.example.newimse_project.Model.mongo.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("api/books")
    public List<mongoBook> fetchAllBooks(){
        return bookService.getAllBooks();
    }
}
