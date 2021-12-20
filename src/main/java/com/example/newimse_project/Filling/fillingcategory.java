package com.example.newimse_project.Filling;

 import com.example.newimse_project.Model.Ncategory;
 import com.example.newimse_project.Repository.nCategoryRepository;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.util.List;

public class fillingcategory implements CommandLineRunner {

    @Autowired
    private nCategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {

        List<Ncategory> categories = generateData.generateNcategory();
        for (int i = 0; i < categories.size(); i++) {
            categoryRepository.save(categories.get(i));
        }
    }
}
