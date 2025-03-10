package com.project.LibraryManagementSystemBackEnd.Repository;

import com.project.LibraryManagementSystemBackEnd.Entity.BookRequest;
import com.project.LibraryManagementSystemBackEnd.Entity.BookRequestStatus;
import com.project.LibraryManagementSystemBackEnd.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRequestRepo extends JpaRepository <BookRequest , Long> {

    boolean existsByUserAndBookNameAndStatus(User user, String bookName, BookRequestStatus status);

}
