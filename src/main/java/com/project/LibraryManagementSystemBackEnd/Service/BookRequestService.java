package com.project.LibraryManagementSystemBackEnd.Service;

import com.project.LibraryManagementSystemBackEnd.DTO.BookRequestDTO;
import com.project.LibraryManagementSystemBackEnd.Entity.*;
import com.project.LibraryManagementSystemBackEnd.Repository.BookRepo;
import com.project.LibraryManagementSystemBackEnd.Repository.BookRequestRepo;
import com.project.LibraryManagementSystemBackEnd.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookRequestService {

    @Autowired
    private BookRequestRepo bookrequestrepo;

    @Autowired
    private UserRepo userrepo;

    @Autowired
    private BookRepo bookrepo;

    public ResponseEntity<String> requestBook(Long userId, BookRequestDTO bookRequestDTO) {
        User user = userrepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        if (!UserRole.PROFESSOR.equals(user.getRole())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Only professors can request books.");
        }

        boolean existingRequest = bookrequestrepo.existsByUserAndBookNameAndStatus(
                user, bookRequestDTO.getBookName(), BookRequestStatus.PENDING);

        if (existingRequest) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("You have already requested this book. Please wait for approval.");
        }

        BookRequest bookRequest = new BookRequest();
        bookRequest.setBookName(bookRequestDTO.getBookName());
        bookRequest.setAuthorName(bookRequestDTO.getAuthorName());
        bookRequest.setBookDepartment(bookRequestDTO.getBookDepartment());
        bookRequest.setCopiesRequested(bookRequestDTO.getCopiesRequested());
        bookRequest.setUser(user);
        bookRequest.setStatus(BookRequestStatus.PENDING);

        bookrequestrepo.save(bookRequest);
        return ResponseEntity.ok("Book request submitted successfully!");
    }


    public String processBookRequest(Long userId, Long requestId) {
        User user = userrepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        if (!UserRole.LIBRARIAN.equals(user.getRole())) {
            return "Only Librarians can process book requests.";
        }

        BookRequest request = bookrequestrepo.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Book Request not found"));

        Optional<Book> existingBook = bookrepo.findByBookNameAndAuthorName(request.getBookName(), request.getAuthorName());

        if (existingBook.isPresent()) {
            request.setStatus(BookRequestStatus.APPROVED);
            bookrequestrepo.save(request);
            return "Book request approved. The book already exists in the library.";
        }
        else {
            // Convert BookRequest entity to BookRequestDTO
            BookRequestDTO bookRequestDTO = convertToDTO(request);

            // Create a new Book entity using BookRequestDTO
            Book book = new Book();
            book.setBookName(bookRequestDTO.getBookName());
            book.setAuthorName(bookRequestDTO.getAuthorName());
            book.setBookDepartment(bookRequestDTO.getBookDepartment());
            int copiesRequested = bookRequestDTO.getCopiesRequested();
            book.setTotalCopies(copiesRequested);
            book.setAvailableCopies(copiesRequested);

            bookrepo.save(book);

            request.setStatus(BookRequestStatus.APPROVED);
            bookrequestrepo.save(request);


            return "Book request processed successfully. The book has been added to the system.";
        }
    }

    private BookRequestDTO convertToDTO(BookRequest request){
        return new BookRequestDTO(
                request.getRequestId(),
                request.getBookName(),
                request.getAuthorName(),
                request.getBookDepartment(),
                request.getCopiesRequested(),
                request.getUser().getUserId(),
                request.getStatus()
        );
    }

}
