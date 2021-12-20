package com.example.newimse_project.Repository;

import com.example.newimse_project.Model.BookPublisherId;
import com.example.newimse_project.Model.Book_Ncategory;
import com.example.newimse_project.Model.Book_NcategoryId;
import com.example.newimse_project.Model.Book_Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface book_ncategoryRepository extends JpaRepository<Book_Ncategory, Book_NcategoryId> {
}
