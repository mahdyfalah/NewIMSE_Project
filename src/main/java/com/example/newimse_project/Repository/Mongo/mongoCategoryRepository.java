package com.example.newimse_project.Repository.Mongo;

import com.example.newimse_project.Model.mongo.mongoCategory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface mongoCategoryRepository extends MongoRepository<mongoCategory, String> {
}
