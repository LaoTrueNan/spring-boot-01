package gzq.upc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class gzqcontroller {

    @GetMapping("/")
    public String index(){
        return "home";
    }
}
