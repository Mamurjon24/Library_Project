package com.example.Library_Project.controller;

import com.example.Library_Project.dto.Book;
import com.example.Library_Project.dto.Student;
import com.example.Library_Project.dto.StudentBook;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/studentBook")
public class StudentBookController {
    List<StudentBook> studentBookList = new LinkedList<>();

    public StudentBookController() {
        StudentBook studentBook = new StudentBook();
        studentBook.setId(UUID.randomUUID().toString());
        Student student = new Student();
        student.setId(UUID.randomUUID().toString());
        student.setName("Najim");
        student.setSurName("Najimov");
        student.setPhone("1");
        student.setCreatedDate(LocalDate.parse("2021-02-11"));
        studentBook.setStudent(student);
        Book book = new Book();
        book.setId(UUID.randomUUID().toString());
        book.setTitle("Matematika");
        book.setAuthor("Professorbek");
        book.setPublishYear(LocalDate.parse("2022-03-12"));
        studentBook.setBook(book);
        studentBook.setCreatedDate(LocalDate.parse("2023-03-25"));
        studentBook.setReturnedDate(LocalDate.parse("2023-03-29"));
        studentBook.setDuration("4 day");
        studentBookList.add(studentBook);
    }

    @GetMapping(value = "/listStudentBook")
    public List<StudentBook> getAll() {
        System.out.println("Assalom :)");
        return studentBookList;
    }

    @PostMapping(value = "/createStudentBook")
    public StudentBook create(@RequestBody StudentBook studentBook) {
        studentBook.setId(UUID.randomUUID().toString());
        studentBookList.add(studentBook);
        return studentBook;
    }

    @PostMapping(value = "/create/allStudentBook")
    public Boolean createAll(@RequestBody List<StudentBook> list) {
        for (StudentBook studentBook : list) {
            studentBook.setId(UUID.randomUUID().toString());
            studentBookList.add(studentBook);
        }
        return true;
    }

    @PutMapping(value = "/updateStudentBook/{id}")
    public Boolean update(@PathVariable("id") String id,
                          @RequestBody StudentBook studentBook) {
//        Optional<Book> bookdto = bookList.stream()
//                .filter(dto -> dto.getId().equals(id))
//                .findFirst();
        for (StudentBook studentBook1 : studentBookList) {
            if (studentBook1.getId().equals(id)) {

                return true;
            }
        }
        return false;
    }

    @GetMapping(value = "/getStudentBook/{id}")
    public StudentBook getById(@PathVariable("id") String id) {
        Optional<StudentBook> optional = studentBookList.stream()
                .filter(book -> book.getId().equals(id))
                .findAny();
        return optional.orElse(null);
    }

    @DeleteMapping(value = "/deleteStudentBook/{id}")
    public boolean delete(@PathVariable("id") String id) {
        return studentBookList.removeIf(studentBook -> (studentBook.getId().equals(id)));
    }
}
