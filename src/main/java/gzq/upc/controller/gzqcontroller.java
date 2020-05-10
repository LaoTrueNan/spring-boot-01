package gzq.upc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/seller")
public class gzqcontroller {

    @PostMapping("/showgzq")
    @ResponseBody
    public String showgzq(){
        return "hello jquery!";
    }
}
