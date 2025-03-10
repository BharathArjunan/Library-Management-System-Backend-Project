package com.project.LibraryManagementSystemBackEnd.Service;

import com.project.LibraryManagementSystemBackEnd.DTO.CheckOutDTO;
import com.project.LibraryManagementSystemBackEnd.Entity.Book;
import com.project.LibraryManagementSystemBackEnd.Entity.CheckOut;
import com.project.LibraryManagementSystemBackEnd.Entity.User;
import com.project.LibraryManagementSystemBackEnd.Exception.BookNotFoundException;
import com.project.LibraryManagementSystemBackEnd.Exception.UserNotFoundException;
import com.project.LibraryManagementSystemBackEnd.Repository.BookRepo;
import com.project.LibraryManagementSystemBackEnd.Repository.CheckOutRepo;
import com.project.LibraryManagementSystemBackEnd.Repository.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class CheckOutService {

    @Autowired
    private CheckOutRepo checkoutRepo;

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private UserRepo userRepo;

    @Transactional
    public String checkOutBook(CheckOutDTO checkOutDTO) {

        User user = userRepo.findById(checkOutDTO.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found with Id: " + checkOutDTO.getUserId()));

        Book book = bookRepo.findById(checkOutDTO.getBookId())
                .orElseThrow(() -> new BookNotFoundException("Book not found with Id: " + checkOutDTO.getBookId()));

        if (book.getAvailableCopies() <= 0) {
            return "Sorry, no available copies of this book.";
        }

        // Decrement available copies
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepo.save(book);

        // Create a new checkout record
        CheckOut checkOut = new CheckOut();
        checkOut.setUser(user);
        checkOut.setBook(book);
        checkOut.setCheckoutDate(LocalDate.now());
        checkOut.setDueDate(LocalDate.now().plusDays(10));
        checkOut.setStatus("Checked Out");

        checkoutRepo.save(checkOut);

        return "Book checked out successfully.";
    }

    @Transactional
    public Optional<CheckOut> getCheckOutById(Long checkoutId) {
        return checkoutRepo.findById(checkoutId);
    }

    public ResponseEntity<String> returnBook(Long checkoutId , Long userId){
        Optional<CheckOut> checkOutOptional = checkoutRepo.findById(checkoutId);

        if (checkOutOptional.isPresent()){
            CheckOut checkOut = checkOutOptional.get();

            if(!checkOut.getUser().getUserId().equals(userId)){
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not allowed to return this book");
            }

            Book book = checkOut.getBook();

            book.setAvailableCopies(book.getAvailableCopies() + 1);
            bookRepo.save(book);

            checkOut.setStatus("Returned");
            checkoutRepo.save(checkOut);

            return ResponseEntity.ok("Book returned successfully. Available copies updated.");
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("CheckOut record not found. Unable to return the book.");
        }
    }

}
