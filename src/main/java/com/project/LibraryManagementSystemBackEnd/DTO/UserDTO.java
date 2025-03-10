package com.project.LibraryManagementSystemBackEnd.DTO;

import com.project.LibraryManagementSystemBackEnd.Entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long userId;
    private String userName;
    private String userPassword;
    private UserRole userRole;
    private String userDepartment;

}
