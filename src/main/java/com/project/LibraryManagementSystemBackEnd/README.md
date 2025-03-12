# **Library Management System - Backend**

 This is a **Spring Boot-based Library Management System(LMS)** that enables efficient management of books, users, book requests and checkouts in a library.
 The system provides functionalities for **librarians, professors and students** to interact with books and perform operations such as borrowing, returning, and requesting books.

## **Project Structure**

### ***1.Entity Classes(Data Models)***

These classes represent the database tables and define the structure of the application's data.

  + **User** → Represents a library user(Librarian, Professor or Student).
  + **Book** → Stores book details, including available copies.
  + **CheckOut** → Represens the checkout of a book by a user.
  + **BookRequest** → Stores request made by professor for new books.

### ***2.DTO(Data Transfer Object) Classes***

DTOs are used to transfer data between different layers of the application.

  + **UserDTO** → Carries user details between API and service layers.
  + **BookDTO** → Holds book information.
  + **CheckOutDTO** → Manages book checkout data.
  + **BookRequestDTO** → Transfers book request data.

### ***3.Repository Layer(Data Access)***

Spring Data JPA repositories interact with the database.
  
  + **UserRepo** → Handles CRUD operations for users.
  + **BookRepo** → Manages book records.
  + **CheckOutRepo** → Stores checkout details.
  + **BookRequestRepo** → Maintains book request records.

### ***4.Service Layer(Buisness Logic)***

Service classes contain core logic for handling various operations.

  + **UserService**
    + Adds user to the system.
    + Retrieves user details.
    + Allows librarians to delete users.
  + **BookService**
    + Adds books to the library.
    + Fetches book details by ID.
  + **CheckOutService**
    + Allow users to borrow books(if available).
    + Handles book returns and updates availability.
  + **BookRequestService**
    + Enables professors to request new books.
    + Allows librarians to approve book requests.

### ***5.Controller Layer(REST APIs)***

Controllers expose endpoints for interacting with the system.

  + **UserController** → Handles user-related operations(add, view, delete).
  + **BookController** → Manages books(add, fetch by ID).
  + **CheckOutController** → Allows users to borrow and return books.
  + **BookRequestController** → Manages book request from professors.

### ***6.Exception Handling***

Custom exceptions ensure meaningful error responses.

  + UserNotFoundException → Thrown when a user is not found.
  + BookNotFoundException → Raised when a requested book doesn't exist.

## **Core Features**

✅***User Management***

   + Librarians can add or remove users.
   + Professors and students can be registered.

 ✅***Book Management***

   + Users can add books to the system.
   + Books can be searched using book IDs.

 ✅***Book Borrowing & Returning***

   + Users can borrow books if available.
   + Book return updates the system accordingly.

 ✅***Book Requests***

   + Professors can request books for the library.
   + Librarians review and approve book requests.

 ✅***Role-Bases Access***

   + **Librarian** → Manages users, books , and requests.
   + **Professor** → Can borrow, return and request books.
   + **Student** → Can borrow and return books.

## **How to Run the Project**

### ***Prerequisites***

  + Java 17+
  + Spring Boot
  + MySQL Database
  + Maven

### ***Steps to Run***

  1. *Clone the repository:*
       git clone
       https://github.com/your-repo/LibraryManagementSystem.git
  2. *Navigate to the project directory:*
       cd LibraryManagementSystem
  3. *Configure application properties with your MySQL database details.*
  4. *Run the application using Maven:*
        mvn spring-boot:run
  5. *The API will be at:*
        http://localhost:8080

## **API Endpoints**

  |    Endpoint                         | Methods      |    Description                         |                                                                    
  |  :---------------------------------:|:------------:|:--------------------------------------:|
  |     /users/add                      |  POST        | Add a new user                         |
  |     /users/{userId}                 |  GET         | Fetch user by ID                       |          
  |     /users/all                      |  GET         | View all users                         |                                         
  |     /users/delete                   |  DELETE      | Delete a user(Librarian only)          |                                                     
  |     /books/add                      |  POST        | Add a new book                         |                                                        
  |     /books/{bookId}                 |  GET         | Get book details                       |                                                     
  |     /checkouts/book                 |  POST        | Borrow a book                          |                                                        
  |     /checkouts/{checkoutId}| GET    |  GET         | checkout details                       |                        
  |     /checkouts/return/{checkoutId}  |  POST        | Return a book                          |
  |     /bookRequests/request/{userId}  |  POST        | Request a book(Professor only)         |
  |     /bookRequests/process           |  POSt        | Process a book request(Librarian only) |


## **1.User Management APIs**

### **1.1 Add a User**
**API Request:**  http://localhost:8080/uers/add
**Response(200 OK):**  
{  
  "userName" : "Geetha" ,  
  "userPassword" : "34rty" ,   
  "userRole" : "PROFESSOR" ,   
  "userDepartent" : "ECE"  
}

### **1.2 Get User by ID**
**API Request:** hhtp://localhost:8080/uers/1
**Response(200 OK):**  
{  
  "userId" : 1 ,   
  "userName" : "Geetha" ,  
  "userRole" : "PROFESSOR" ,  
  "UserDepartment" : "ECE"  
}

### **1.3 Get All Users**
**API Request:** http://localhost:8080/users/all  

### **1.4 Delete a User(Librarian Only):**
**API Request** http://localhost:8080/users/delete  
**Response(200 OK):**  
"User deleted successfully."

## **2.Book Management APIs**

### **2.1 Add a Book**
**API Request:** http://localhost:8080/books/add  
**Response(200 OK):**  
{  
  "bookName" : "Java Full Stack" ,  
  "authorName" : "Joshua Bloch" ,  
  "bookDepartment" : "Computer Science" ,   
  "availableCopies" : "5"  
}

### **2.2 Get Book by ID**
**API Request:** http://localhost:8080/books/1  
**Response(200 OK):**   
{  
  "bookId" : 1 ,  
  "bookName" : "Java Full Stack" ,  
  "authorName" : "Joshua Bloch" ,   
  "bookDepartment" : "Computer Science" ,   
  "availableCopies" : "5"  
}

## **3.Book Borrowing & Returning(CheckOut) APIs**

### **3.1 Borrow a Book**
**API Request:** http://localhost:8080/checkouts/book  
**Response(200 OK):**  
{  
  "userId" : 2,  
  "bookId" : 1  
}  
"Book checked out successfully."  

### **3.2 Get CheckOut Details**
**API Request:** http://localhost:8080/checkouts/1   
**Response:**  
{  
  "checkoutId" : 1 ,  
  "userId" : 2 ,  
  "bookId" : 1 ,  
  "checkoutDate" : "2025-03-10" ,   
  "dueDate" : "2025-03-17"  
}  

### **3.3 Return a Book**
**API Request:** http://localhost:8080/checkouts/return/1?userId=2  
**Response:**  
"Book returned successfully."  

## **4.Book Requests(Professors & Librarians)**  

### **4.1 Request a Book(professors only)**
**API Request:** http://localhost:8080/bookRequests/request/2  
**Request:**   
{  
  "bookName" : "Patterns" ,  
  "authorName" : "Erich Gamma" ,   
  "bookDepartment" : "Computer Science" ,   
  "copiesRequested" : 3  
}  
**Response(200 Ok):**  
Book request submitted successfully!

### **4.2 Process a Book Request(Librarian Only)**  
**API Request:** http://localhost:8080/bookRequests/process?userId=1&requestId=5  
**Response(200 OK):**  
Book request processed successfully.The book has been added to the system.


## **Authentication & Authorization Notes**  
  + **only Librarians** can add/delete users and process book requests.
  + **Only Professors** can request books.
  + **All users(Professors & students)** can borrow and return books.

## **Technologies Used**

   ✅ **Spring Boot**      → For backend logic and REST APIs.  
   ✅ **Spring Data JPA**  → For database interactions.  
   ✅ **MySQL**            → As the relational database.  
   ✅ **Maven**            → For project build management.  

## **Future Enhancements**
  + Implement authentication and authorization(Spring Security Configuration).
  + Add email notification for book requests and approvals.
  + Create a frontend UI using React or Angular.
   
  

 
