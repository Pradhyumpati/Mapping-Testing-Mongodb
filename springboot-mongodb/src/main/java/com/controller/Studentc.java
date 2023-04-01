package com.controller;

import com.model.student;
import com.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/")
public class Studentc{
    @Autowired
    private StudentRepo studentrepo;


    @RequestMapping("/")
    String home() {

        return "Hello World";
    }

    @GetMapping("/get")
    public List<student> get() {
        System.out.println("!111");

        return studentrepo.findAll();
    }
    @PostMapping("add")
    public ResponseEntity<?> addStudent(@RequestBody student stu)
    {
        student save = this.studentrepo.save(stu);
        return ResponseEntity.ok(save);
    }
}