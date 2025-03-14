package com.project.LibraryManagementSystemBackEnd.Repository;


import com.project.LibraryManagementSystemBackEnd.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepo extends JpaRepository <Book , Long> {

    Optional<Book> findByBookNameAndAuthorName(String bookName , String authorName);

}
