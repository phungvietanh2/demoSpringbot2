package com.example.booking.ControllerApi;

import com.example.booking.Dao.Iml.UserDaoImpl;
import com.example.booking.Dao.UserDao;
import com.example.booking.dto.UserDto;
import com.example.booking.entity.User;
import com.example.booking.repository.RoomsRepository;
import com.example.booking.service.RoomsSpecificationsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MyhomeControllerApi {
    @Autowired
    private RoomsRepository roomsRepository;
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private RoomsSpecificationsService roomsSpecificationsService;

    @RequestMapping("/api/myhome_code")
    private ResponseEntity<List<UserDto>> specification(@RequestParam("code") String code){

        UserDao dao = new UserDaoImpl();

        List<User> userList = dao.findByCode(code);

        return ResponseEntity.status(HttpStatus.OK).body(
                        userList.stream().map(user -> mapper.map(user, UserDto.class)).collect(Collectors.toList())
        );
    }


}
