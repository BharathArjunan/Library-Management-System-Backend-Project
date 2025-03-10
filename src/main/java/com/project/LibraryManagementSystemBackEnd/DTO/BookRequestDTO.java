package com.project.LibraryManagementSystemBackEnd.DTO;

import com.project.LibraryManagementSystemBackEnd.Entity.BookRequestStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookRequestDTO {

    private Long requestId;
    private String bookName;
    private String authorName;
    private String bookDepartment;
    private int copiesRequested;
    private Long userId;
    private BookRequestStatus status;

}
