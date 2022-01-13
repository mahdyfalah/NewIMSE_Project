package com.example.newimse_project.Repository.Mongo;

import com.example.newimse_project.Model.mongo.mongoBook;
import com.example.newimse_project.Model.mongo.mongoCommentReport;
import com.example.newimse_project.Model.mongo.mongoReadReport;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface monogoBookRepository extends MongoRepository<mongoBook, String> {
    @Query(value = "{ 'name' : ?0 }",fields = "{'publishers' : {'publisher_id' : 0}}")
    List<mongoBook> findBookByName(String firstname);

    @Aggregation(pipeline = {
            "{ '$unwind' : '$userList' }",
            "{ '$group' : { '_id': '$userList.date' } }",
            "{ '$sort' : { '_id' : 1 } }"
    })
    List<String> getAllUniqueReadDates();

    @Aggregation(pipeline = {
            "{ '$unwind' : '$userList' }",
            "{ '$match' : { 'userList.date': ?0 } }",
            "{ '$lookup' : { 'from' : 'User', 'localField' : 'userList.user_id', 'foreignField' : '_id', 'as' : 'readReport' } }",
            "{ '$unwind' : '$readReport' }",
            "{ '$addFields' : { 'user_fullName' : '$readReport.fullName', 'book_name' : '$name'} }",
            "{ '$project' : { 'user_fullName' : 1, 'book_name' : 1, 'publishers.publisher_name' : 1 } }",
            "{ '$sort' : { 'user_fullName' : 1 } }"
    })
    List<mongoReadReport> readReport(String date);


    @Aggregation(pipeline = {
            "{ '$unwind' : '$comments' }",
            "{ '$group' : { '_id': '$comments.date' } }",
            "{ '$sort' : { '_id' : 1 } }"
    })
    List<String> getAllUniqueCommentDates();

    @Aggregation(pipeline = {
            "{ '$unwind' : '$comments' }",
            "{ '$match' : { 'comments.date': ?0 } }",
            "{ '$lookup' : { 'from' : 'User', 'localField' : 'comments.user_id', 'foreignField' : '_id', 'as' : 'commentReport' } }",
            "{ '$unwind' : '$commentReport' }",
            "{ '$addFields' : { 'user_fullName' : '$commentReport.fullName', 'book_name' : '$name', 'comment_text' : '$comments.comment_text'} }",
            "{ '$project' : { 'user_fullName' : 1, 'book_name' : 1, 'comment_text' : 1,  'categories.category_name' : 1 } }",
            "{ '$sort' : { 'book_name' : 1 } }"
    })
    List<mongoCommentReport> commentReport(String date);


    mongoBook save(mongoBook mongoBook);
}
