package com.DemoTesting;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.DemoTesting.controller.StudentController;
import com.DemoTesting.entitty.student;
import com.DemoTesting.service.studentservice;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@WebMvcTest(value = StudentController.class)
public class ControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private studentservice studentservice;

    @Test
    public void testAddStudent() throws Exception {
        student st  = new student(1,"Pradhyum",22);

        String inputInJson = this.mapToJson(st);
        String URI = "add";
        Mockito.when(studentservice.addStudent(Mockito.any(student.class))).thenReturn("data added successfully");
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(URI)
                .accept(MediaType.APPLICATION_JSON).content(inputInJson)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String outputInJson = response.getContentAsString();
        Assertions.assertThat(outputInJson).isEqualTo("data added successfully");

    }
    @Test
    public void testGetStudentById() throws  Exception {
        student st = new student(1,"Pradhyum",22);
        Mockito.when(studentservice.getStudent(Mockito.anyInt())).thenReturn(st);
        String URI = "/Demo/getById/1";
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(URI)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String excepted = this.mapToJson(st);
        String output = result.getResponse().getContentAsString();
        Assertions.assertThat(output).isEqualTo(excepted);
    }
    @Test
    public void testGetAllStudent() throws  Exception {
        student student1 = new student(1,"Hitesh",33);
        student student2 = new student(2,"yash",44);
        List<student> list = new ArrayList<>();
        list.add(student1);
        list.add(student2);
        String URI = "/Demo/getAll";
        Mockito.when(studentservice.getAllStudent()).thenReturn(list);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(URI)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String output = result.getResponse().getContentAsString();
        String expected = this.mapToJson(list);
        Assertions.assertThat(output).isEqualTo(expected);
    }

    @Test
    public void testUpdateStudent() throws  Exception {
        student student1 = new student(1,"arun",66);
        String inputInJson = this.mapToJson(student1);
        String URI = "/Demo/Update/1";
        Mockito.when(studentservice.updateStudent(Mockito.any(student.class),Mockito.anyInt())).thenReturn("data updated successfully");
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put(URI)
                .accept(MediaType.APPLICATION_JSON)
                .content(inputInJson)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String output = response.getContentAsString();
        Assertions.assertThat(output).isEqualTo("data updated successfully");
    }
    @Test
    public void testDeleteStudent() throws Exception {
        student student1 = new student(1,"Aditya",55);
        String  URI = "/Demo/Delete/1";
        Mockito.when(studentservice.deleteStudent(Mockito.anyInt())).thenReturn(student1.getName());
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete(URI)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result  = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String output = response.getContentAsString();
        Assertions.assertThat(output).isEqualTo(student1.getName());
    }

    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return  objectMapper.writeValueAsString(object);
    }

}
