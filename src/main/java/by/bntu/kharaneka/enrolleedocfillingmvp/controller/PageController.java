package by.bntu.kharaneka.enrolleedocfillingmvp.controller;

import by.bntu.kharaneka.enrolleedocfillingmvp.entity.BoolDTO;
import by.bntu.kharaneka.enrolleedocfillingmvp.entity.Contract;
import by.bntu.kharaneka.enrolleedocfillingmvp.entity.Document;
import by.bntu.kharaneka.enrolleedocfillingmvp.entity.User;
import by.bntu.kharaneka.enrolleedocfillingmvp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PageController {
    private final UserService userService;

    @Autowired
    public PageController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String getIndexPage() {
        return "index1";
    }

    @GetMapping("/registration")
    public String getRegistration() {
        return "signup";
    }

    @GetMapping("/try")
    public String getTry() {
        return "try";
    }

}
