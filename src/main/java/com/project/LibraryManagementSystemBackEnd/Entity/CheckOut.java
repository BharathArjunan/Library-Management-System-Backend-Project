package com.project.LibraryManagementSystemBackEnd.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "check_out")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CheckOut {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "checkout_id")
    private Long checkoutId;

    @ManyToOne
    @JoinColumn(name = "user_id" , nullable = false)
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id" , nullable = false)
    @JsonIgnore
    private Book book;

    @Column(name = "checkout_date")
    private LocalDate checkoutDate;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "status")
    private String status = "Checked Out";

}
