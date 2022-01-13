package com.example.newimse_project.Repository;

import com.example.newimse_project.Model.Book_User_Comment;
import com.example.newimse_project.Model.Book_User_CommentId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface book_user_commentRepository extends JpaRepository<Book_User_Comment, Long> {
}
