package com.DemoTesting.entitty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity

@Table(name = "jpa_student")
public class Laptop {
    @Id
    private int laptopId;
    private String modelNumber;
    private String brand;

    public int getLaptopId() {
        return laptopId;
    }

    public void setLaptopId(int laptopId) {
        this.laptopId = laptopId;
    }

    public String getModelNumber() {
        return modelNumber;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public com.DemoTesting.entitty.student getStudent() {
        return student;
    }

    public void setStudent(com.DemoTesting.entitty.student student) {
        this.student = student;
    }

    public Laptop()
    {

    }
    public Laptop(int laptopId, String modelNumber, String brand, com.DemoTesting.entitty.student student) {
        this.laptopId = laptopId;
        this.modelNumber = modelNumber;
        this.brand = brand;
        this.student = student;
    }

    @OneToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
    @JoinColumn(name = "student_id")
    @JsonIgnore
    private com.DemoTesting.entitty.student student;
}