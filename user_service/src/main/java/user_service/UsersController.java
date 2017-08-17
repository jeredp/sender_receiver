package user_service;

import org.springframework.web.bind.annotation.*;
import user_service.models.User;
import user_service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@RestController
public class UsersController {

    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }
    
    @RequestMapping("/")
    public List<User> users(@RequestParam("email") Optional<String> email) {
        if(email.isPresent()) {
            return userService.findUsersByEmail(email.get());
        } else {
            return userService.findAll();
        }
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public User createUser(@RequestBody() User user) {
        return this.userService.createUser(user);
    }
}
