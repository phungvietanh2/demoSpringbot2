package com.example.booking.repository;

import com.example.booking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UsersRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
//
//    Boolean existsByEmail(String email);

    Optional<User> findByUsernameOrEmail(String username, String email);



//    boolean existsByUsername(String username);

}
