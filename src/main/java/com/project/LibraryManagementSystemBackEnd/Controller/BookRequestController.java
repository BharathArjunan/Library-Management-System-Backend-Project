package com.project.LibraryManagementSystemBackEnd.Controller;

import com.project.LibraryManagementSystemBackEnd.DTO.BookRequestDTO;
import com.project.LibraryManagementSystemBackEnd.Exception.UserNotFoundException;
import com.project.LibraryManagementSystemBackEnd.Service.BookRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookRequests")
public class BookRequestController {

    @Autowired
    private BookRequestService bookRequestService;

    @PostMapping("/request/{userId}")
    public ResponseEntity<String> requestBook(@PathVariable Long userId , @RequestBody BookRequestDTO bookRequestDTO){
        return bookRequestService.requestBook(userId , bookRequestDTO);
    }

    @PostMapping("/process")
    public ResponseEntity<String> processBookRequest(@RequestParam Long userId, @RequestParam Long requestId) {
        try {
            String response = bookRequestService.processBookRequest(userId, requestId);
            return ResponseEntity.ok(response);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing the request.");
        }
    }

}
