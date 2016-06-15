package rw.viden.basiapp.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rw.viden.basiapp.models.CurrentUser;
import rw.viden.basiapp.models.Role;
import rw.viden.basiapp.models.User;
import rw.viden.basiapp.models.validator.UserCreateFormValidator;
import rw.viden.basiapp.service.UserService;

import javax.validation.Valid;
import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    private final UserCreateFormValidator userCreateFormValidator;

    @Autowired
    public UserController(UserService userService, UserCreateFormValidator userCreateFormValidator) {
        this.userService = userService;
        this.userCreateFormValidator = userCreateFormValidator;
    }

    @InitBinder("user")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(userCreateFormValidator);
    }

    @PreAuthorize("hasRole('READ_PRIVILEGE')")
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public String getUserPage(@PathVariable Long id, Authentication authentication, Model model) {
        LOGGER.debug("Getting user page for user={}", id);
        CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();
        Optional<User> userOptional=userService.getUserById(id);
        if(!userOptional.isPresent()){
            throw new NoSuchElementException("User not found");
        }
//        User user=userOptional.get();
//        model.addAttribute("user", user);
//        for(Role role:user.getRoles())
//        role.getPrivileges()){
//            return "userEdit";
//        }
        return "userEdit";

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/user/create", method = RequestMethod.GET)
    public String getUserCreatePage(Model model) {
        LOGGER.debug("Getting user create form");
        model.addAttribute("user",new User());
        return "user_create";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/user/create", method = RequestMethod.POST)
    public String handleUserCreateForm(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        LOGGER.debug("Processing user create form={}, bindingResult={}", user, bindingResult);
        if (bindingResult.hasErrors()) {
            // failed validation
            return "user_create";
        }
        try {
            userService.create(user);
        } catch (DataIntegrityViolationException e) {
            // probably email already exists - very rare case when multiple admins are adding same user
            // at the same time and form validation has passed for more than one of them.
            LOGGER.warn("Exception occurred when trying to save the user, assuming duplicate username", e);
            bindingResult.reject("username.exists", "username already exists");
            return "user_create";
        }
        redirectAttributes.addFlashAttribute("saveDefaulters", "success");
        return "redirect:/users";
    }

    @RequestMapping(value = "/user/update", method = RequestMethod.POST)
    public String handleUserUpdateForm(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, final RedirectAttributes redirectAttributes) {

        user.setId(userService.getUserByUsername(user.getUsername()).get().getId());
        LOGGER.debug(user.getId()+" =====================================================");
        LOGGER.debug("Processing user create form={}, bindingResult={}", user, bindingResult);
//        if (bindingResult.hasErrors()) {
//            // failed validation
//            return "user";
//        }
        try {
            userService.create(user);
        } catch (DataIntegrityViolationException e) {
            LOGGER.warn("Exception occurred when trying to save the user, assuming duplicate username", e);
            bindingResult.reject("username.exists", "username already exists");
            redirectAttributes.addFlashAttribute("saveDefaulters", "unsuccess");
            return "user";
        }
        redirectAttributes.addFlashAttribute("saveDefaulters", "success");
        return "redirect:/users";
    }

}
