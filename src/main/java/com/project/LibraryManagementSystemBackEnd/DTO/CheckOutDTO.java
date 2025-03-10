package com.project.LibraryManagementSystemBackEnd.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CheckOutDTO {

    private Long checkoutId;
    private Long userId;
    private Long bookId;
    private LocalDate checkoutDate;
    private LocalDate dueDate;
    private String  status;

}
