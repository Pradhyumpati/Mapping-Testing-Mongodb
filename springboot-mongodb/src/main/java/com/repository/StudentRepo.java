package com.repository;

import java.util.Optional;

import com.model.student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentRepo extends MongoRepository<student, Integer> {

}