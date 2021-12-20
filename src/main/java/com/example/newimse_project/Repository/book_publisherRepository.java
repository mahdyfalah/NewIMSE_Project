package com.example.newimse_project.Repository;

import com.example.newimse_project.Model.BookPublisherId;
import com.example.newimse_project.Model.Book_Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface book_publisherRepository extends JpaRepository<Book_Publisher, BookPublisherId> {
}
