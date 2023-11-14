package com.example.booking.Dao;

import com.example.booking.entity.HistoryUser;
import com.example.booking.entity.User;

import java.util.List;

public interface UserDao {
    boolean saveOrUpdate(User user);

    List<User> findByCode(String code);
    List<HistoryUser> findById(Long id);
}
