package com.ioproject.reservetheweather.repository;

import com.ioproject.reservetheweather.entity.User;
import jakarta.persistence.Table;
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
}

