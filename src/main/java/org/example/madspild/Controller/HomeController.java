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

    @GetMapping("/userInfoPage")
    public String userInfoPage(HttpSession session, Model model){
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            model.addAttribute("loggedInUser", loggedInUser);
        } else {
            model.addAttribute("loggedInUser", null);
        }
        return "home/userInfoPage";
    }

    @PostMapping("/createLogin")
    public String createLogin(@ModelAttribute User p, Model model){
        if(!userService.doesUserExists(p)){ //if user doesnt exist, add user
            userService.addUser(p);
            model.addAttribute("successMessage","Success! Du er nu oprettet og kan logge ind");
            return "home/createLogin"; //TODO add proper page/handle properly
        } else {
            model.addAttribute("errorMessage", "Dette brugernavn er optaget");
            return "home/createLogin";
        }
    }

    @PostMapping("/login")
    public String login(Model model, HttpSession session, @RequestParam String username, @RequestParam String password){
        //check if user exists
        if(userService.validateLoginInfo(username,password)){
            User user = userService.getUserByUsername(username);
            session.setAttribute("loggedInUser", user); //add user to session
            model.addAttribute("successMessage","Success!<br> Du er nu logget ind");
            return "home/loginPage";
        } else {
            model.addAttribute("errorMessage", "Forkert brugernavn eller password");
            return "home/loginPage";
        }
    }

    //deletes session data when user clicks logout and redirects to loginPage
    @PostMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate(); //delete session data
        return "redirect:/loginPage";
    }
}
