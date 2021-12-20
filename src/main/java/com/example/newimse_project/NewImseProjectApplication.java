package com.example.newimse_project;

import com.example.newimse_project.Filling.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class NewImseProjectApplication {

    @Bean
    public fillingUser startFillingUser() {
        return new fillingUser();
    }
    @Bean
    public fillingBook startFillingBook() {
        return new fillingBook();
    }
    @Bean
    public fillingPublisher startFillingPublisher() {
        return new fillingPublisher();
    }

    @Bean
    public fillingBook_publisher startfillingBook_publisher() {
        return new fillingBook_publisher();
    }

    @Bean
    public fillingcategory startfillingcategory() { return new fillingcategory ();
    }

    @Bean
    public fillingBook_Category startfillingBook_Category() { return new fillingBook_Category ();
    }

    @Bean
    public fillingChapters startfillingChapters() { return new fillingChapters ();
    }




    public static void main(String[] args) {
        SpringApplication.run(NewImseProjectApplication.class, args);
    }

}
