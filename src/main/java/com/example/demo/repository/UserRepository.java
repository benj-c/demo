package com.example.demo.repository;

import com.example.demo.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    @Modifying
    @Query("update UserEntity u set u.enabled = :isEnabled where u.id = :uid")
    void actDeactUser(
            @Param("isEnabled") boolean isEnabled,
            @Param("uid") int uid
    );

//    @Query("from UserEntity u where u.userName = :userName")
    Optional<UserEntity> findByUserName(String userName);
}
