package com.example.newimse_project.Repository;

import com.example.newimse_project.Model.Book;
 import org.springframework.data.jpa.repository.JpaRepository;

public interface bookRepository extends JpaRepository<Book,Long> {
}
