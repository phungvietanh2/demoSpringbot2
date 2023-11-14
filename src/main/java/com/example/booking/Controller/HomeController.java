package com.example.booking.Controller;

import com.example.booking.config.CurrentUser;
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
public class HomeController {

    @Autowired
    private RoomsService roomsService;

    @Autowired
    private ModelMapper mapper;


    @Autowired
    private RoomsRepository roomsRepository;

    @Autowired
    private CurrentUser currentUser;

    @GetMapping({"/","home"})
    private String index(Model model){
        Pageable pageable = PageRequest.of(0, 4);
        Page<Rooms> rooms = roomsRepository.findAll(pageable);
        model.addAttribute("listrooms",rooms);
        model.addAttribute("roles", currentUser.getRoles());
            return "index";
    }
}
