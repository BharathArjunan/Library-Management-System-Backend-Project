package com.project.LibraryManagementSystemBackEnd.Service;

import com.project.LibraryManagementSystemBackEnd.DTO.BookDTO;
import com.project.LibraryManagementSystemBackEnd.Entity.Book;
import com.project.LibraryManagementSystemBackEnd.Repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepo bookrepo;

    public BookDTO addBook(BookDTO bookDTO){
        Book book = new Book();
        book.setBookName(bookDTO.getBookName());
        book.setAuthorName(bookDTO.getAuthorName());
        book.setBookDepartment(bookDTO.getBookDepartment());
        book.setAvailableCopies(bookDTO.getAvailableCopies());
        book.setTotalCopies(bookDTO.getTotalCopies());

        Book savedBook = bookrepo.save(book);
        return convertToDTO(savedBook);

    }

    public BookDTO getBookById(Long bookId){
        Optional<Book> book = bookrepo.findById(bookId);
        return book.map(this :: convertToDTO).orElse(null);
    }

    private BookDTO convertToDTO(Book book){
        return new BookDTO(
                book.getBookId(),
                book.getBookName(),
                book.getAuthorName(),
                book.getBookDepartment(),
                book.getAvailableCopies(),
                book.getTotalCopies()
        );
    }

    private Book convertToEntity(BookDTO bookDTO){
        Book book = new Book();
        book.setBookId(bookDTO.getBookId());
        book.setBookName(bookDTO.getBookName());
        book.setAuthorName(bookDTO.getAuthorName());
        book.setBookDepartment(bookDTO.getBookDepartment());
        book.setAvailableCopies(bookDTO.getAvailableCopies());
        book.setTotalCopies(bookDTO.getTotalCopies());
        return book;
    }

}
