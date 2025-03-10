package com.project.LibraryManagementSystemBackEnd.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "book_requests")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private Long requestId;

    @Column(name = "requested_book" , nullable = false)
    private String bookName;

    @Column(name = "requested_author" , nullable = false)
    private String authorName;

    @Column(name = "requested_book_department" , nullable = false)
    private String bookDepartment;

    @Column(name = "request_no_copies" , nullable = false)
    private int copiesRequested;

    @ManyToOne
    @JoinColumn(name = "user_id" , nullable = false)
    private User user;

    @Column(name = "request_status" , nullable = false)
    @Enumerated(EnumType.STRING)
    private BookRequestStatus status = BookRequestStatus.PENDING;

}
