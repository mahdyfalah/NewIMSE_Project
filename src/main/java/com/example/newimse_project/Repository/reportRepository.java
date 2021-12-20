package com.example.newimse_project.Repository;

import com.example.newimse_project.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface reportRepository extends JpaRepository<Book, Long> {

    @Query(value = "select u.full_name,b.name,p.pulisher_name,bp.published_date from User u,Book b,Publisher p,book_user_read bu,book_publisher bp WHERE u.userid = bu.user_id AND b.bookid = bu.book_id AND b.bookid = bp.book_id AND p.userid = bp.publisher_id", nativeQuery = true)
    List<Object> reportfirst();


}
