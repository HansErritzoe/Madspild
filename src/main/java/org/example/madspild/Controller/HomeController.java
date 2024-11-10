package org.example.madspild.Controller;

import jakarta.servlet.http.HttpSession;
import org.example.madspild.Model.User;
import org.example.madspild.Repository.UserRepository;
import org.example.madspild.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
            model.addAttribute("successMessage","Success! Du er nu oprettet og kan logge ind");
            return "home/createLogin"; //TODO add proper page/handle properly
        } else {
            model.addAttribute("errorMessage", "Dette brugernavn er optaget - prøv igen");
            return "home/createLogin";
        }
    }

    @PostMapping("/login")
    public String login(Model model, HttpSession session, @RequestParam String username, @RequestParam String password){
        //if user exists
        if(userService.validateLoginInfo(username,password)){
            User user = userService.getUserByUsername(username);
            session.setAttribute("loggedInUser", user); //add user to session
            model.addAttribute("successMessage","Success! Du er nu logget ind");
            return "home/loginPage";
        } else {
            model.addAttribute("errorMessage", "Forkert brugernavn eller password - prøv igen");
            return "home/loginPage";
        }
    }
}
