package com.ioproject.reservetheweather.repository;

import com.ioproject.reservetheweather.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Transactional
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query(value = "SELECT * FROM users WHERE email=?1", nativeQuery = true)
    Optional<User> findUserByMail(String mail);


    @Query(value = "SELECT * FROM users WHERE name=?1", nativeQuery = true)
    Optional<User> findUserByName(String name);
}

