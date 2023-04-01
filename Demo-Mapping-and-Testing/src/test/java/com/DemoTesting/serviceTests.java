package com.DemoTesting;

import com.DemoTesting.entitty.student;
import com.DemoTesting.repository.studentRepository;
import com.DemoTesting.service.studentservice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class serviceTests {
    @Autowired
    private studentservice stservice;

    @Mock
    studentRepository studentrepository;

    @Test
    void testGetStudent() {
        student st = new student(1,"Pradhyum",11);
        Mockito.when(studentrepository.findById(Mockito.anyInt())).thenReturn(Optional.of(st));
        student student1 = stservice.getStudent(1);
        Assertions.assertNotNull(student1);
    }
    @Test
    void testGelAllStudent() {
        List<student> list = new ArrayList<>();
        student student1 = new student(1,"abc",22);
        student student2 = new student(2,"rahul",44);
        list.add(student1);
        list.add(student2);
        Mockito.when(studentrepository.findAll()).thenReturn(list);
        List<student> list1 = studentrepository.findAll();
        Assertions.assertNotNull(list);
        Assertions.assertEquals(2,list1.size());
    }
    @Test
    void testAddStudentWhenNotPresent() {
        student st = new student(1,"Pradhyum",77);
        Mockito.when(studentrepository.save(st)).thenReturn(st);
        String str = stservice.addStudent(st);
        Assertions.assertEquals(str,"added successfully");
    }
    @Test
    void testUpdateStudent() {
        student st = new student(1,"ram",11);
        studentrepository.save(st);

        Mockito.when(studentrepository.existsById(1)).thenReturn(true);
        String str = stservice.updateStudent(st,5);
        Assertions.assertEquals(str,"not found");
        Assertions.assertNotNull(str);
    }
    @Test
    void testUpdateWhenPresent() {
        student st = new student(1,"Pradhyum",13);
        Mockito.when(studentrepository.save(Mockito.any(student.class))).thenReturn(st);
        String str = stservice.updateStudent(st,1);
        Assertions.assertNotEquals(str,"data updated");
        Assertions.assertNotNull(str);
    }

    @Test
    void testDeleteStudent() {
        student st = new student(1,"Pradhyum",15);
        studentrepository.save(st);
        Mockito.when(studentrepository.existsById(Mockito.anyInt())).thenReturn(true);
        String str = stservice.deleteStudent(3);
        Assertions.assertNotNull(str);
        Assertions.assertEquals(str,"does not exist with this id");
    }



}
