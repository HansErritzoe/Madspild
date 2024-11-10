package org.example.madspild.Controller;

import org.example.madspild.Model.User;
import org.example.madspild.Repository.UserRepository;
import org.example.madspild.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    @Autowired
    UserService userService;

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

    @GetMapping("/loginPage")
    public String loginPage(){
        return "home/loginPage";
    }

    @GetMapping("/createLogin")
    public String createLogin(){
        return "home/createLogin";
    }

    @PostMapping("/createLogin")
    public String createLogin(@ModelAttribute User p, Model model){
        if(!userService.doesUserExists(p)){ //if user doesnt exist, add user
            userService.addUser(p);
            model.addAttribute("successMessage","Success! \n Du er nu oprettet og kan logge ind");
            return "home/createLogin"; //TODO add proper page/handle properly
        } else {
            model.addAttribute("errorMessage", "Dette brugernavn er optaget - prøv igen");
            return "home/createLogin";
        }
    }
}
