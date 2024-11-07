package org.example.madspild.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(){
        return "home/index";
    }

    @GetMapping("/consumer")
    public String consumer(){
        return "home/consumer";
    }

    @GetMapping("/company")
    public String company(){
        return "home/company";
    }

    @GetMapping("/org")
    public String org(){
        return "home/org";
    }

    @GetMapping("/events")
    public String events(){
        return "home/events";
    }

    @GetMapping("/createLogin")
    public String createLogin(){
        return "home/createLogin";
    }
}
