package com.example.newimse_project.Repository.Mongo;

import com.example.newimse_project.Model.mongo.mongoReadReport;
import com.example.newimse_project.Model.mongo.mongoUser;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface mongoUserRepository extends MongoRepository<mongoUser, String>{
    @Query(value = "{'bookList.?0' : { $exists:true } }", fields = "{'fullName': 1, 'bookList.2021-12-26' : 1}")
    List<mongoUser> findReadByDate(String date);

    @Query(value = "{'_id' : ?0, 'newBookList.date' : ?1 }", fields = "{'newBookList' : { '$elemMatch' : { 'date' : ?1 } } }")
    mongoUser queryByDateAndId(String id, String date);

    @Query(value = "{'newBookList.date' : ?0 }", fields = "{'newBookList' : { '$elemMatch' : { 'date' : ?0 } }, 'fullName' : 1 }")
    List<mongoUser> dateQuery(String date);

    @Aggregation(pipeline = {
            "{ '$unwind' : '$bookList' }",
            "{ '$group' : { '_id': '$bookList.date' } }",
            "{ '$sort' : { '_id' : 1 } }"
    })
    List<String> getAllUniqueDates();

//    @Aggregation(pipeline = {
//            "{ '$unwind' : '$bookList' }",
//            "{ '$match' : { 'bookList.date': ?0 } }",
//            "{ '$group' : { '_id' : '$_id', " +
//                    "'fullName' : { '$first' : '$fullName' }, " +
//                    "'bookList' : { '$push' : '$bookList' } } }",
//            "{ '$sort' : { 'fullName' : 1 } }"
//    })
//    List<User> readReport(String date);

    @Aggregation(pipeline = {
            "{ '$unwind' : '$bookList' }",
            "{ '$match' : { 'bookList.date': ?0 } }",
            "{ '$lookup' : { 'from' : 'Book', 'localField' : 'bookList.book_id', 'foreignField' : '_id', 'as' : 'readReport' } }",
            "{ '$unwind' : '$readReport' }",
            "{ '$addFields' : { 'user_fullName' : '$fullName', 'book_name' : '$readReport.name' } }",
            "{ '$sort' : { 'user_fullName' : 1 } }"
    })
    List<mongoReadReport> readReport(String date);



    mongoUser save(mongoUser mongoUser);
}
