package com.project.LibraryManagementSystemBackEnd.Exception;

import com.project.LibraryManagementSystemBackEnd.Entity.Book;

public class BookNotFoundException extends RuntimeException{

    public BookNotFoundException(String message){
        super(message);
    }

}
