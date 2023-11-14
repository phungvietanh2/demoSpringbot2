package com.example.booking.Dao;

import com.example.booking.entity.RoomsImg;

import java.util.List;

public interface RoomsImgDao {
    boolean saveorUpdate(RoomsImg roomsImg);
    List<RoomsImg> findall(Long id);
}
