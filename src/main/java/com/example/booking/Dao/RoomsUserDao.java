package com.example.booking.Dao;

import com.example.booking.entity.RoomsUser;

import java.util.List;

public interface RoomsUserDao {
    boolean saveOrUpdate(RoomsUser roomsUser);
    public List<RoomsUser> findByID(Long id);

    public List<RoomsUser> findByUserID(Long roomid);
}
