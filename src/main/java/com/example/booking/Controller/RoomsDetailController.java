package com.example.booking.Controller;

import com.example.booking.Dao.Iml.RoomDaoImpl;
import com.example.booking.Dao.Iml.RoomsImgDaoImpl;
import com.example.booking.Dao.RoomDao;
import com.example.booking.Dao.RoomsImgDao;
import com.example.booking.entity.Rooms;
import com.example.booking.entity.RoomsImg;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping
public class RoomsDetailController {
    @GetMapping("roomsdetail")
    private String roomdetailgetID(@RequestParam("roomid") Long id, Model model){
        RoomDao roomDao = new RoomDaoImpl();
        List<Rooms> roomsList = roomDao.findById(id);
        model.addAttribute("roomslistdetail",roomsList);

        RoomsImgDao roomsImgDao = new RoomsImgDaoImpl();
        List<RoomsImg> roomImages = roomsImgDao.findall(id);
        model.addAttribute("roomImages", roomImages);

        return "roomsdetail";
    }
}
