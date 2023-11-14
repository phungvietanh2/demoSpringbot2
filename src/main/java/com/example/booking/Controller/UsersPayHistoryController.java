package com.example.booking.Controller;

import com.example.booking.Dao.Iml.UserDaoImpl;
import com.example.booking.Dao.UserDao;
import com.example.booking.config.UserPayHistoryExcelExporter;
import com.example.booking.entity.HistoryUser;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class UsersPayHistoryController {
    @GetMapping({"paymenthistory"})
    private String index(@RequestParam("idroom") Long id, Model model){
        UserDao userDao = new UserDaoImpl();
        model.addAttribute("list",userDao.findById(id));
        model.addAttribute("idroom",id);
        return "users payment history";
    }
    @GetMapping("/userspayhistory/export/excel")
    public void exportToExcel(@RequestParam("iduser_room") Long id,HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
        UserDao userDao = new UserDaoImpl();
        List<HistoryUser> listUsers = userDao.findById(id);

        UserPayHistoryExcelExporter excelExporter = new UserPayHistoryExcelExporter(listUsers);

        excelExporter.export(response);
    }
}
