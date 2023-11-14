package com.example.booking.Controller;

import com.example.booking.Dao.Iml.RoomDaoImpl;
import com.example.booking.Dao.Iml.RoomsUserDaoImpl;
import com.example.booking.Dao.RoomDao;
import com.example.booking.Dao.RoomsUserDao;
import com.example.booking.config.CurrentUser;
import com.example.booking.entity.Rooms;
import com.example.booking.entity.RoomsUser;
import com.example.booking.entity.State;
import com.example.booking.entity.User;
import com.example.booking.repository.RoomUsersRepository;
import com.example.booking.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class MyhomeController {
    @Autowired
    private CurrentUser currentUser;

    @Autowired
    private UserService userService;

    @Autowired
    private RoomUsersRepository usersRepository;
    @Autowired
    private ModelMapper mapper;



    @GetMapping({"myhome"})
    private String index(Model model) {
//        RoomDao roomDao = new RoomDaoImpl();
//        RoomsUserDao roomsUserDao = new RoomsUserDaoImpl();
        RoomsUserDao roomsUserDao = new RoomsUserDaoImpl();

        List<RoomsUser> userList = roomsUserDao.findByUserID(currentUser.getUserId());

        model.addAttribute("finduserid", userList);
        model.addAttribute("roles", currentUser.getRoles());

//        model.addAttribute("finduser", roomsUserDao.findByID(currentUser.getUserId()));
//
//        List<Rooms> roomsList = roomDao.findByRoomUser(currentUser.getUserId());
//
////        List<RoomsDto> roomsDtoMap =
////                roomsList.stream().map(rooms -> mapper.map(rooms, RoomsDto.class)).collect(Collectors.toList());
////        model.addAttribute("finduser", roomsDtoMap);
//
//
//        List<RoomsDto> roomsDtoList = roomsList.stream()
//                .map(rooms -> new RoomsDto(rooms))
//                .collect(Collectors.toList());
//        model.addAttribute("finduser", roomsDtoList);


        return "myhome";


    }

    @GetMapping("/addmembers")
    private String addmembers(Model model) {

        RoomDao roomDao = new RoomDaoImpl();
        model.addAttribute("finduserid", roomDao.findByUserId(currentUser.getUserId()));

        System.out.println(roomDao.findByUserId(currentUser.getUserId()));
        return "myhomeadd";
    }


    @PostMapping("/addmembers")
    private String save(@RequestParam("housedeposit") Float housedeposit,
                        @RequestParam("code") String code,
                        @RequestParam("service") Float service,
                        @RequestParam("roomid") Long roomid,
                        @RequestParam("userId") Long userid) {
        System.out.println("id++"+userid);
        RoomsUser roomsUser = new RoomsUser();
        roomsUser.setHousedeposit(housedeposit);
        roomsUser.setRooms_date_created(LocalDateTime.now());
        roomsUser.setRooms_date_update(LocalDateTime.now());
        roomsUser.setRooms_users_code(code);
        roomsUser.setService(service);
        roomsUser.setState(State.LIVE);
        Rooms rooms = new Rooms();
        rooms.setRooms_id(roomid);
        User user = new User();
        user.setUser_id(userid);
        roomsUser.setRooms(rooms);
        roomsUser.setUsers(user);
        userService.addRoomUser(roomsUser);
        return "myhome";
    }
}
