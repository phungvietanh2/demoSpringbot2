package com.example.booking.ControllerApi;

import com.example.booking.dto.RoomsDto;
import com.example.booking.entity.Rooms;
import com.example.booking.repository.RoomsRepository;
import com.example.booking.service.RoomsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RoomsControllerApi {

    @Autowired
    private RoomsService roomsService;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private RoomsRepository roomsRepository;

    @RequestMapping("/api/listrooms")
    public ResponseEntity<List<RoomsDto>> index(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Rooms> rooms = roomsRepository.findAll(pageable);

        return ResponseEntity.status(HttpStatus.OK).body(
                rooms.stream().map(room -> mapper.map(room, RoomsDto.class)).collect(Collectors.toList())
        );
    }

}
