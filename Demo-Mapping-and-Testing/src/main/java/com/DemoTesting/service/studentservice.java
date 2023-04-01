package com.DemoTesting.service;

import com.DemoTesting.entitty.Address;
import com.DemoTesting.entitty.Laptop;
import com.DemoTesting.entitty.student;
import com.DemoTesting.repository.studentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class studentservice {
    @Autowired
    private studentRepository studentrepository;

    public List<student> getAllStudent() {
        return this.studentrepository.findAll();
    }
    public student getStudent(int id) {
        if(studentrepository.existsById(id)) {
            return this.studentrepository.findById(id).get();
        }
        return new student();
    }
    public String addStudent(student st) {
        if(studentrepository.existsById(st.getId())) {
            return "student present with this id";
        }
        else {
            studentrepository.save(st);
            return "Added successfully";
        }
    }

    public String addlaptop(Laptop lp) {
        if(studentrepository.existsById(lp.getLaptopId())) {
            return "laptop present with this id";
        }
        else {
            studentrepository.save(lp);
            return "Added successfully";
        }
    }

    public String addaddress(Address ad) {
        if(studentrepository.existsById(ad.getAddressId())) {
            return "address present with this id";
        }
        else {
            studentrepository.save(ad);
            return "Added successfully";
        }
    }



    public String updateStudent(student st, int id) {
        if(studentrepository.existsById(id)==false) {

            return "not found";
        }
        studentrepository.save(st);
        return "data updated";

    }
    public String deleteStudent(int id) {
        if(studentrepository.existsById(id)) {
            studentrepository.deleteById(id);
            return "record deleted";
        }
        return "does not exist with this id";
    }


}
