package com.project.LibraryManagementSystemBackEnd.Controller;

import com.project.LibraryManagementSystemBackEnd.DTO.UserDTO;
import com.project.LibraryManagementSystemBackEnd.Entity.User;
import com.project.LibraryManagementSystemBackEnd.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDTO){
        if (userDTO == null || userDTO.getUserName() == null || userDTO.getUserPassword() == null){
            return ResponseEntity.badRequest().build();
        }
        UserDTO savedUser = userService.addUser(userDTO);
        return ResponseEntity.ok(savedUser);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long userId) {
        try {
            UserDTO userDTO = userService.getUserById(userId);
            return ResponseEntity.ok(userDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    public List<User> viewAllUsers(){
        return userService.getAllUsers();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(@RequestParam Long userId, @RequestParam Long userIdToDelete) {
        String response = userService.deleteUser(userId, userIdToDelete);
        return ResponseEntity.ok(response);
    }

}
