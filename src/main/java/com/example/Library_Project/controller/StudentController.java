package com.example.Library_Project.controller;

import com.example.Library_Project.dto.Student;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping(value = "/student")
public class StudentController {

    List<Student> studentList = new LinkedList<>();

    public StudentController() {
        Student student = new Student();
        student.setId(UUID.randomUUID().toString());
        student.setName("Ali");
        student.setSurName("Aliyev");
        student.setPhone("123");
        student.setCreatedDate(LocalDate.parse("2022-06-06"));
        studentList.add(student);
    }

    @GetMapping(value = "/studentList")
    public List<Student> getAll() {
        System.out.println("Assalom :)");
        return studentList;
    }

    @PostMapping(value = "/createStudent")
    public Student create(@RequestBody Student student) {
        student.setId(UUID.randomUUID().toString());
        studentList.add(student);
        return student;
    }

    @PostMapping(value = "/create/allStudent")
    public Boolean createAll(@RequestBody List<Student> list) {
        for (Student student : list) {
            student.setId(UUID.randomUUID().toString());
            studentList.add(student);
        }
        return true;
    }

    @PutMapping(value = "/updateStudent/{id}")
    public Boolean update(@PathVariable("id") String id,
                          @RequestBody Student student) {
        for (Student dto : studentList) {
            if (dto.getId().equals(id)) {
                dto.setName(student.getName());
                dto.setSurName(student.getSurName());
                dto.setPhone(student.getPhone());
                dto.setCreatedDate(student.getCreatedDate());
                return true;
            }
        }
        return false;
//         Optional<StudentDto> studentToUpdate = studentList.stream()
//                .filter(dto -> dto.getId().equals(id))
//                .findFirst();
//        if (studentToUpdate.isPresent()) {
//            StudentDto dto = studentToUpdate.get();
//            dto.setName(student.getName());
//            dto.setSurName(student.getSurName());
//            return true;
//        }
//        return false;
    }

    @GetMapping(value = "/getStudent/{id}")
    public Student getById(@PathVariable("id") String id) {
        Optional<Student> optional = studentList.stream()
                .filter(studentDto -> studentDto.getId().equals(id))
                .findAny();
        return optional.orElse(null);
    }

    @DeleteMapping(value = "/deleteStudent/{id}")
    public boolean delete(@PathVariable("id") String id) {
        return studentList.removeIf(studentDto -> (studentDto.getId().equals(id)));
    }

}

