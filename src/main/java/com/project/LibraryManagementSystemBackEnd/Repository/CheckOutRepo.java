package com.project.LibraryManagementSystemBackEnd.Repository;

import com.project.LibraryManagementSystemBackEnd.Entity.CheckOut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckOutRepo extends JpaRepository <CheckOut , Long>{

    @Modifying
    @Query("DELETE FROM CheckOut c WHERE c.user.id = :userId")
    void deleteByUserId(@Param("userId") Long userId);

}
