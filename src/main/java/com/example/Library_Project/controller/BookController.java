package com.example.Library_Project.controller;

import com.example.Library_Project.dto.Book;
import com.example.Library_Project.dto.Student;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class BookController {
    List<Book> bookList = new LinkedList<>();

    public BookController() {
        Book book = new Book();
        book.setId(UUID.randomUUID().toString());
        book.setTitle("Java");
        book.setAuthor("John");
        book.setPublishYear(LocalDate.parse("2015-02-02"));
        bookList.add(book);
    }

    @GetMapping(value = "/listBook")
    public List<Book> getAll() {
        System.out.println("Assalom :)");
        return bookList;
    }

    @PostMapping(value = "/createBook")
    public Book create(@RequestBody Book book) {
        book.setId(UUID.randomUUID().toString());
        bookList.add(book);
        return book;
    }

    @PostMapping(value = "/create/allBook")
    public Boolean createAll(@RequestBody List<Book> list) {
        for (Book book : list) {
            book.setId(UUID.randomUUID().toString());
            bookList.add(book);
        }
        return true;
    }

    @PutMapping(value = "/updateBook/{id}")
    public Boolean update(@PathVariable("id") String id,
                          @RequestBody Book book) {
        for (Book dto : bookList) {
            if (dto.getId().equals(id)) {
                dto.setTitle(book.getTitle());
                dto.setAuthor(book.getAuthor());
                dto.setPublishYear(book.getPublishYear());
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

    @GetMapping(value = "/getBook/{id}")
    public Book getById(@PathVariable("id") String id) {
        Optional<Book> optional = bookList.stream()
                .filter(book -> book.getId().equals(id))
                .findAny();
        return optional.orElse(null);
    }

    @DeleteMapping(value = "/deleteBook/{id}")
    public boolean delete(@PathVariable("id") String id) {
        return bookList.removeIf(book -> (book.getId().equals(id)));
    }

}
