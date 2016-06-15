package rw.viden.basiapp.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import rw.viden.basiapp.models.CurrentUser;
import rw.viden.basiapp.service.UserService;

@Controller
public class UsersController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsersController.class);
    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/users")
    public ModelAndView getUsersPage() {
        LOGGER.debug("Getting users page");
        return new ModelAndView("users", "users", userService.getAllUsers());
    }
    @RequestMapping("/changepassword")
    public String getChangePassword(Model model, Authentication authentication){
        CurrentUser currentUser=(CurrentUser)authentication.getPrincipal();
        model.addAttribute("user",currentUser);
        return "redirect:/user/"+currentUser.getUser().getId();

    }


}
