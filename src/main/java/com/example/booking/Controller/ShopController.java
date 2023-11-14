package com.example.booking.Controller;

import com.example.booking.entity.Rooms;
import com.example.booking.repository.RoomsRepository;
import com.example.booking.service.RoomsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ShopController {

    @Autowired
    private RoomsService roomsService;
    @Autowired
    private RoomsRepository roomsRepository;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/shop")
    private String shop(Model model){
        Pageable pageable = PageRequest.of(0, 9);
        Page<Rooms> rooms = roomsRepository.findAll(pageable);
        model.addAttribute("listrooms",rooms);
        return "shop";
    }
}
