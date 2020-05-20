package gzq.upc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/seller")
public class gzqcontroller {

    @PostMapping("/showgzq")
    @ResponseBody
    public String showgzq(){
        return "hello jquery!";
    }

    @GetMapping("/showgtr")
    @ResponseBody
    public String showgtr(@RequestParam("user") String user){return user;}

}
