package rw.viden.basiapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

/**
 * Created by nic on 6/7/16.
 */
@Controller
public class WelcomeController {

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String getHomePage() {
        return "home";
    }
//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public ModelAndView getLoginPage(@RequestParam Optional<String> error) {
//        return new ModelAndView("login", "error", error);
//    }
}
