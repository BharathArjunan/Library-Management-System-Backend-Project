package com.project.LibraryManagementSystemBackEnd.Controller;

import com.project.LibraryManagementSystemBackEnd.DTO.CheckOutDTO;
import com.project.LibraryManagementSystemBackEnd.Entity.CheckOut;
import com.project.LibraryManagementSystemBackEnd.Service.CheckOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/checkouts")
public class CheckOutController {

    @Autowired
    private CheckOutService checkOutService;

    @PostMapping("/book")
    public ResponseEntity<String> checkoutBook(@RequestBody CheckOutDTO checkOutDTO) {

        if (checkOutDTO.getUserId() == null || checkOutDTO.getBookId() == null) {
            return ResponseEntity.badRequest().body("User ID and Book ID are required.");
        }

        String message = checkOutService.checkOutBook(checkOutDTO);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/{checkoutId}")
    public ResponseEntity<CheckOut> getCheckOutById(@PathVariable String checkoutId) {
        checkoutId = checkoutId.trim(); // Remove unwanted characters
        Optional<CheckOut> checkout = checkOutService.getCheckOutById(Long.parseLong(checkoutId));

        return checkout.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build()); // Use notFound().build() instead of body()
    }


    @PostMapping("/return/{checkoutId}")
    public ResponseEntity<String> returnBook(@PathVariable Long checkoutId, @RequestParam Long userId){
        return checkOutService.returnBook(checkoutId , userId);
    }

}
