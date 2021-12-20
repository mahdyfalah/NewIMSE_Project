package com.example.newimse_project.Repository;

import com.example.newimse_project.Model.Book_User_Read;
import com.example.newimse_project.Model.Book_User_ReadId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface book_user_readRepository extends JpaRepository<Book_User_Read, Book_User_ReadId> {
}
