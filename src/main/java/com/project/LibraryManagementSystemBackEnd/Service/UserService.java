package com.project.LibraryManagementSystemBackEnd.Service;

import com.project.LibraryManagementSystemBackEnd.DTO.UserDTO;
import com.project.LibraryManagementSystemBackEnd.Entity.User;
import com.project.LibraryManagementSystemBackEnd.Entity.UserRole;
import com.project.LibraryManagementSystemBackEnd.Repository.CheckOutRepo;
import com.project.LibraryManagementSystemBackEnd.Repository.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {


    private final UserRepo userRepo;

    private final CheckOutRepo checkOutRepo;

    public UserService(UserRepo userRepo, CheckOutRepo checkOutRepo) {
        this.userRepo = userRepo;
        this.checkOutRepo = checkOutRepo;
    }

    public UserDTO addUser(UserDTO userDTO){

        if(userDTO == null || userDTO.getUserName() == null || userDTO.getUserPassword() == null || userDTO.getUserRole() == null){
            throw new IllegalArgumentException("User name and password cannot be null");
        }

        User user = new User();
        user.setUserName(userDTO.getUserName());
        user.setPassword(userDTO.getUserPassword());
        user.setRole(userDTO.getUserRole());
        user.setUserDepartment(userDTO.getUserDepartment());

        User savedUser = userRepo.save(user);
        return new UserDTO(savedUser.getUserId(), savedUser.getUserName(), savedUser.getPassword(), savedUser.getRole(), savedUser.getUserDepartment());
    }

    public UserDTO getUserById(Long userId){
        Optional<User> userOptional = userRepo.findById(userId);
        return userOptional.map(user -> new UserDTO(user.getUserId() , user.getUserName() , user.getPassword() , user.getRole() , user.getUserDepartment())).orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
    }

    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

    @Transactional
    public String deleteUser(Long userId, Long userIdToDelete) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        if (!UserRole.LIBRARIAN.equals(user.getRole())) {
            return "Only Professors can delete users.";
        }

        User userToDelete = userRepo.findById(userIdToDelete)
                .orElseThrow(() -> new RuntimeException("User to delete not found"));

        // Delete dependent records in check_out table first
        checkOutRepo.deleteByUserId(userIdToDelete);

        // Now delete the user
        userRepo.delete(userToDelete);

        return "User deleted successfully.";
    }



}
