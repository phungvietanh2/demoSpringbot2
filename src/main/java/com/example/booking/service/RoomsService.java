package com.example.booking.service;

import com.example.booking.Dao.Iml.RoomDaoImpl;
import com.example.booking.Dao.RoomDao;
import com.example.booking.dto.RoomsDto;
import com.example.booking.entity.Rooms;
import com.example.booking.repository.RoomsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomsService {

    @Autowired
    private RoomsRepository roomsRepository;

    @Autowired
    private ModelMapper mapper;

    public List<RoomsDto> getAllRooms() {
//        List<Rooms> rooms = roomsRepository.findAll();
        RoomDao roomDao = new RoomDaoImpl();
        List<Rooms> rooms = roomDao.findAll();
        return rooms.stream()
                .map(room -> mapper.map(room, RoomsDto.class))
                .collect(Collectors.toList());
    }

    public List<RoomsDto> findByid(Long id) {
        RoomDao roomDao = new RoomDaoImpl();
        List<Rooms> rooms = roomDao.findById(id);
        return rooms.stream()
                .map(rooms1 -> mapper.map(rooms1, RoomsDto.class))
                .collect(Collectors.toList());
    }





}
