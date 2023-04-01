package com.DemoTesting.repository;

import com.DemoTesting.entitty.Address;
import com.DemoTesting.entitty.Laptop;
import com.DemoTesting.entitty.student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface studentRepository extends JpaRepository<student, Integer> {

    void save(Laptop lp);
    void save(Address ad);
}