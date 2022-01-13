package com.example.newimse_project;

import com.example.newimse_project.Filling.*;
import com.example.newimse_project.Model.mongo.mongoBook;
import com.example.newimse_project.Model.mongo.mongoCategory;
import com.example.newimse_project.Model.mongo.mongoPublisher;
import com.example.newimse_project.Model.mongo.mongoUser;
import com.example.newimse_project.Repository.Mongo.mongoCategoryRepository;
import com.example.newimse_project.Repository.Mongo.mongoPublisherRepository;
import com.example.newimse_project.Repository.Mongo.mongoUserRepository;
import com.example.newimse_project.Repository.Mongo.monogoBookRepository;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

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

//    @Bean
//    CommandLineRunner runner(monogoBookRepository bookRepository, mongoUserRepository userRepository, mongoPublisherRepository publisherRepository, mongoCategoryRepository categoryRepository){
//		return args -> {
//			mongoBook book1 = new mongoBook(
//					"dune",
//					"www.dune.com",
//					"frank herbert"
//			);
//
//            mongoBook book2 = new mongoBook(
//					"lotr",
//					"www.lotr.com",
//					"tolkien"
//			);
//
//            mongoBook book3 = new mongoBook(
//					"witcher",
//					"www.witcher.com",
//					"keaunu reaves"
//			);
//
//			bookRepository.insert(book1);
//			bookRepository.insert(book2);
//			bookRepository.insert(book3);
//
//
//
//            mongoUser user1 = new mongoUser(
//					"Mahdi Fallah",
//					"mahdyfalah@gmail.com",
//					"06606060619",
//					"Vienna"
//			);
//            mongoUser user2 = new mongoUser(
//					"Alas",
//					"alas_am@gmail.com",
//					"0123456789",
//					"KL"
//			);
//            mongoUser user3 = new mongoUser(
//					"Mirza",
//					"mirza@gmail.com",
//					"024683579",
//					"Bucharest"
//			);
//
//			userRepository.insert(user1);
//			userRepository.insert(user2);
//			userRepository.insert(user3);
//
//
//			mongoPublisher publisher1 = new mongoPublisher(
//					"uniwien",
//					"uniwien@gmail.com"
//			);
//
//			List<DBObject> dbObjectList = new ArrayList<DBObject>();
//
//			DBObject dbo = new BasicDBObject();
//			dbo.put("date", "1995-02-01");
//			dbo.put("book_id", new ObjectId(book1.getId()));
//			dbo.put("book_name", book1.getName());
//			dbObjectList.add(dbo);
//
//
//			dbo = new BasicDBObject();
//			dbo.put("date", "2003-02-01");
//			dbo.put("book_id", new ObjectId(book2.getId()));
//			dbo.put("book_name", book2.getName());
//			dbObjectList.add(dbo);
//
//			publisher1.setBooks(dbObjectList);
//
//			publisherRepository.insert(publisher1);
//            mongoPublisher publisher2 = new mongoPublisher(
//					"TU Vienna Publications",
//					"tuwien@gmail.com"
//			);
//
//			dbObjectList = new ArrayList<DBObject>();
//			dbo = new BasicDBObject();
//			dbo.put("date", "2005-10-15");
//			dbo.put("book_id", new ObjectId(book1.getId()));
//			dbo.put("book_name", book1.getName());
//			dbObjectList.add(dbo);
//
//			publisher2.setBooks(dbObjectList);
//
//			dbo = new BasicDBObject();
//			dbo.put("date", "2010-11-13");
//			dbo.put("book_id", new ObjectId(book3.getId()));
//			dbo.put("book_name", book3.getName());
//			dbObjectList.add(dbo);
//
//			publisher2.setBooks(dbObjectList);
//
//			publisherRepository.insert(publisher2);
//
////			books
////			book1
//			dbObjectList = new ArrayList<DBObject>();
//			dbo = new BasicDBObject();
//			dbo.put("date", "1995-02-01");
//			dbo.put("publisher_id", new ObjectId(publisher1.getId()));
//			dbo.put("publisher_name", publisher1.getFullName());
//			dbObjectList.add(dbo);
//
//			dbo = new BasicDBObject();
//			dbo.put("date", "2005-10-15");
//			dbo.put("publisher_id", new ObjectId(publisher2.getId()));
//			dbo.put("publisher_name", publisher2.getFullName());
//			dbObjectList.add(dbo);
//
//			book1.setPublishers(dbObjectList);
//			bookRepository.save(book1);
//
////			book2
//			dbObjectList = new ArrayList<DBObject>();
//			dbo = new BasicDBObject();
//			dbo.put("date", "2003-02-01");
//			dbo.put("publisher_id", new ObjectId(publisher1.getId()));
//			dbo.put("publisher_name", publisher1.getFullName());
//			dbObjectList.add(dbo);
//
//			book2.setPublishers(dbObjectList);
//			bookRepository.save(book2);
//
////			book3
//			dbObjectList = new ArrayList<DBObject>();
//			dbo = new BasicDBObject();
//			dbo.put("date", "2010-11-13");
//			dbo.put("publisher_id", new ObjectId(publisher2.getId()));
//			dbo.put("publisher_name", publisher2.getFullName());
//			dbObjectList.add(dbo);
//
//			book3.setPublishers(dbObjectList);
//			bookRepository.save(book3);
//
////			categories
//			mongoCategory category1 = new mongoCategory(
//					"Fantasy",
//					"Great Stories"
//			);
//
//			dbObjectList = new ArrayList<DBObject>();
//			dbo = new BasicDBObject();
//			dbo.put("book_id", new ObjectId(book2.getId()));
//			dbObjectList.add(dbo);
//
//			dbo = new BasicDBObject();
//			dbo.put("book_id", new ObjectId(book3.getId()));
//			dbObjectList.add(dbo);
//
//			category1.setBooks(dbObjectList);
//			categoryRepository.save(category1);
//
//            mongoCategory category2 = new mongoCategory(
//					"Science Fiction",
//					"Great Worlds"
//			);
//
//			dbObjectList = new ArrayList<DBObject>();
//			dbo = new BasicDBObject();
//			dbo.put("book_id", new ObjectId(book1.getId()));
//			dbObjectList.add(dbo);
//
//			category2.setBooks(dbObjectList);
//			categoryRepository.save(category2);
//
////			book
//			dbObjectList = new ArrayList<DBObject>();
//			dbo = new BasicDBObject();
//			dbo.put("category_id", new ObjectId(category1.getId()));
//			dbo.put("category_name", category1.getTitle());
//			dbo.put("category_description", category1.getDescription());
//			dbObjectList.add(dbo);
//
//			book2.setCategories(dbObjectList);
//			book3.setCategories(dbObjectList);
//			bookRepository.save(book2);
//			bookRepository.save(book3);
//
//			dbObjectList = new ArrayList<DBObject>();
//			dbo = new BasicDBObject();
//			dbo.put("category_id", new ObjectId(category2.getId()));
//			dbo.put("category_name", category2.getTitle());
//			dbo.put("category_description", category2.getDescription());
//
//			dbObjectList.add(dbo);
//
//			book1.setCategories(dbObjectList);
//			bookRepository.save(book1);
//
////			chapters
//			dbObjectList = new ArrayList<DBObject>();
//			dbo = new BasicDBObject();
//			dbo.put("Title", "chapter 1");
//			dbo.put("ChapterNo", 1);
//			dbObjectList.add(dbo);
//
//			dbo = new BasicDBObject();
//			dbo.put("Title", "chapter 2");
//			dbo.put("ChapterNo", 2);
//			dbObjectList.add(dbo);
//
//			book1.setChapters(dbObjectList);
//			bookRepository.save(book1);
//
//			dbo = new BasicDBObject();
//			dbo.put("Title", "chapter 3");
//			dbo.put("ChapterNo", 3);
//			dbObjectList.add(dbo);
//
//			book2.setChapters(dbObjectList);
//			bookRepository.save(book2);
//
//			dbo = new BasicDBObject();
//			dbo.put("Title", "chapter 4");
//			dbo.put("ChapterNo", 4);
//			dbObjectList.add(dbo);
//
//			book3.setChapters(dbObjectList);
//			bookRepository.save(book3);
//		};
//	}
//

    public static void main(String[] args) {
        SpringApplication.run(NewImseProjectApplication.class, args);
    }

}
