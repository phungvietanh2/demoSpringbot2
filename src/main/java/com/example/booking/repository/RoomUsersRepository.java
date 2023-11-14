package com.example.booking.repository;

import com.example.booking.entity.RoomsUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomUsersRepository extends JpaRepository<RoomsUser, Long> {
}
