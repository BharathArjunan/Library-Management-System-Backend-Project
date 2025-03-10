package com.project.LibraryManagementSystemBackEnd.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="books")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "book_name" , nullable = false)
    private String bookName;

    @Column(name = "author_name" , nullable = false)
    private String authorName;

    @Column(name = "book_department" , nullable = false)
    private String bookDepartment;

    @Column(name = "available_copies" , nullable = false)
    private int availableCopies;

    @Column(name = "total_copies" , nullable = false)
    private int totalCopies;

    @OneToMany(mappedBy = "book" , cascade = CascadeType.ALL , orphanRemoval = true)
    private java.util.List<CheckOut> CheckOuts;

}
