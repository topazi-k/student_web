package com.urianskui.studentweb.service;

import com.urianskui.studentweb.model.Student;
import com.urianskui.studentweb.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ActiveProfiles("test")
@Sql("/init_data.sql")
class StudentServiceTest {

    @Autowired
    StudentService service;



    @Test
    public void shouldReturnStudentWithNameFromSearchParameter(){
        String search = "firstName:Anna";
        List<Student> studentList =service.findAll(search, null, null);
        assertEquals(studentList.size(), 1);
        assertEquals(studentList.get(0).getFirstName(),"Anna");
    }


//    @Test
//    public void shoudThrow
}