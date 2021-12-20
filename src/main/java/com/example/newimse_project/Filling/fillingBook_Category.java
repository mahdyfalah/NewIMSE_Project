package com.example.newimse_project.Filling;

import com.example.newimse_project.Model.Book_Ncategory;
import com.example.newimse_project.Model.Book_Publisher;
import com.example.newimse_project.Repository.book_ncategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.util.List;

public class fillingBook_Category  implements CommandLineRunner {

    @Autowired
  private   book_ncategoryRepository repository;
    @Override
    public void run(String... args) throws Exception {

        List<Book_Ncategory> book_ncategories = generateData.geneBook_ncategories();
        for (int i = 0; i < book_ncategories.size(); i++) {
            repository.save(book_ncategories.get(i));
        }

    }

}
