package com.project.LibraryManagementSystemBackEnd.Controller;

import com.project.LibraryManagementSystemBackEnd.DTO.BookDTO;
import com.project.LibraryManagementSystemBackEnd.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/add")
    public ResponseEntity<BookDTO> addBook(@RequestBody BookDTO bookDTO){
        BookDTO savedBook = bookService.addBook(bookDTO);
        return ResponseEntity.ok(savedBook);
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long bookId){
        BookDTO bookDTO = bookService.getBookById(bookId);

        if(bookDTO == null){
            return ResponseEntity.notFound().build();
        }
        else{
            return ResponseEntity.ok(bookDTO);
        }
    }

}
