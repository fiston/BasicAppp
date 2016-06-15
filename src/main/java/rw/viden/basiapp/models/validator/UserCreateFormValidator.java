package rw.viden.basiapp.models.validator;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import rw.viden.basiapp.models.User;
import rw.viden.basiapp.service.UserService;

@Component
public class UserCreateFormValidator implements Validator {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserCreateFormValidator.class);
    private final UserService userService;

    @Autowired
    public UserCreateFormValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(User.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        LOGGER.debug("Validating {}", target);
        User user = (User) target;
        validateUsername(errors, user);
    }


    private void validateUsername(Errors errors, User user) {
        if (userService.getUserByUsername(user.getUsername()).isPresent()) {
            errors.reject("username.exists", "User with this username already exists");
        }
    }
}
