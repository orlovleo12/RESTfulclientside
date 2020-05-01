package web.controller;


import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @GetMapping(path = "/")
    public String listUserGetController(ModelMap model, HttpSession session) {
        return "admin";
    }

    @GetMapping(path = "users/read")
    public String readUserGetController(ModelMap model, HttpSession session) {
        model.addAttribute("user", session.getAttribute("user"));
        return "user";
    }
}
