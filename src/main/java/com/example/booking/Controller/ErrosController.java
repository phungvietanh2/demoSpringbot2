package com.example.booking.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrosController {
    @GetMapping("/error404")
    public String err(){
        return "404";
    }
}
