package com.example.newimse_project.Model;


import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "Category")
public class Ncategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryID;
    private String title;
    private String description;


    @OneToMany(mappedBy = "category")
    private List<Book_Ncategory> book_nCategories = new ArrayList<>();

    public Ncategory()
    {}
    public Ncategory(String title, String description) {
        this.title = title;
        this.description = description;

    }


}
