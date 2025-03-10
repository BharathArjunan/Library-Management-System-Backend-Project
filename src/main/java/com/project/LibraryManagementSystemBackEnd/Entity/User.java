package com.project.LibraryManagementSystemBackEnd.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_name" , nullable = false)
    private String userName;

    @Column(name = "user_password" , nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role" , nullable = false)
    private UserRole role;

    @Column(name = "user_department" , nullable = false)
    private String userDepartment;

}
