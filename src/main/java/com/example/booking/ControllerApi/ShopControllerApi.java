package com.example.booking.ControllerApi;

import com.example.booking.entity.Rooms;
import com.example.booking.repository.RoomsRepository;
import com.example.booking.service.RoomsSpecificationsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ShopControllerApi {
    @Autowired
    private RoomsRepository roomsRepository;
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private RoomsSpecificationsService roomsSpecificationsService;

    @RequestMapping("/api/search_shop")
    private ResponseEntity<List<Rooms>> specification(@RequestParam("minPrice") Float minprice,@RequestParam("maxprice") Float maxprice){
//,@RequestParam("page") Integer page
        RoomsSpecificationsService specification = new RoomsSpecificationsService();
        Page<Rooms> roomsList = roomsSpecificationsService.searchRooms(null,minprice,maxprice,0,6);
        return ResponseEntity.status(HttpStatus.OK).body(
                roomsList.stream().map(rooms -> mapper.map(rooms,Rooms.class)).collect(Collectors.toList())
        );
    }
}
