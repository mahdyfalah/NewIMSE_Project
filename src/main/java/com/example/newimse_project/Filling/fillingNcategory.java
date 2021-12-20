package com.example.newimse_project.Filling;

import com.example.newimse_project.Model.Book;
import com.example.newimse_project.Model.Ncategory;
import com.example.newimse_project.Repository.bookRepository;
import com.example.newimse_project.Repository.nCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.util.List;

@RequiredArgsConstructor
public class fillingNcategory implements CommandLineRunner {

    @Autowired
    private nCategoryRepository repository;

    @Override
    public void run(String... args) throws Exception {

        List<Ncategory> ncategories = generateData.generateNcategory();
        for (int i = 0; i < ncategories.size(); i++) {
            repository.save(ncategories.get(i));
        }
    }
}
