package com.example.newimse_project.Repository;

import com.example.newimse_project.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface reportRepository extends JpaRepository<Book, Long> {


    @Query(value = "SELECT \n" +
            " u.full_name,b.name,p.pulisher_name from User u join book_user_read bu on u.userid = bu.user_id join Book b \n" +
            "on b.bookid = bu.book_id join Publisher p on p.userid = bu.publisher_id \n" +
            "WHERE bu.read_date = :filterDate ORDER BY  u.full_name ASC", nativeQuery = true)
    List<Object[]> reportfirst(@Param("filterDate") String filterDate);

    @Query(value = "SELECT \n" +
            " u.full_name,b.name,p.pulisher_name, bu.read_id,bu.user_id,bu.book_id from Book b join book_publisher bp on b.bookid = bp.book_id join Publisher p \n" +
            "on bp.publisher_id = p.userid join book_user_read bu on b.bookid = bu.book_id join user u on u.userid = bu.user_id \n" +
            "WHERE bu.read_date = :filterDate ORDER BY  u.full_name ASC", nativeQuery = true)
    List<Object[]> report_book(@Param("filterDate") String filterDate);

    @Query(value = "SELECT \n" +
            " u.full_name,b.name,p.title,bu.comment_text,bu.comment_id,bu.user_id,bu.book_id from Book b join book_category bp on b.bookid = bp.book_id join category p \n" +
            "on bp.category_id = p.categoryid join book_user_comment bu on b.bookid = bu.book_id join user u on u.userid = bu.user_id \n" +
            "WHERE bu.comment_date = :filterDate ORDER BY  b.name ASC", nativeQuery = true)
    List<Object[]> report_comment(@Param("filterDate") String filterDate);


    @Query(value = "SELECT \n" +
            " c.comment_id from book_user_comment c \n" +
            "WHERE c.book_id = :book_id and c.user_id = :user_id", nativeQuery = true)
    List<Object[]> get_id_user_comment(@Param("user_id") Long user_id,@Param("book_id") Long book_id);

    @Query(value = "SELECT \n" +
            " c.read_id from book_user_read c \n" +
            "WHERE c.book_id = :book_id and c.user_id = :user_id", nativeQuery = true)
    List<Object[]> get_id_user_read(@Param("user_id") Long user_id,@Param("book_id") Long book_id);

}
