package com.example.booking.Dao;

import com.example.booking.entity.Rooms;

import java.util.List;

public interface RoomDao {

    boolean saveorUpdate(Rooms rooms);

     List<Rooms> findAll();
    List<Rooms> findById(Long id);

    List<Rooms> findByUserId(Long id);
    public List<Rooms> findByRoomUser(Long id);

}
