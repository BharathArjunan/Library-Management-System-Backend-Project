package com.project.LibraryManagementSystemBackEnd.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {

    private Long bookId;
    private String bookName;
    private String authorName;
    private String bookDepartment;
    private int availableCopies;
    private int totalCopies;

}
