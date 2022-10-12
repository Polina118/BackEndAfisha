package Afisha_Odzhetto.User;

import Afisha_Odzhetto.Event.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(path = "/users")
@CrossOrigin()
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping(path = "/registration")
    public String RegisterNewUser(@RequestBody User user) {
        userService.addUser(user);
        return "Success";
    }

    @PostMapping(path = "/authorization")
    public String AuthorizationUser(@RequestBody User user) {
        return userService.autorization(user.getLogin(), user.getPassword());
    }

    @PostMapping(path = "/addModerator/{userId}")
    public void addModerator(@PathVariable("userId") int userId){
        userService.addModerator(userId);
    }

    @PostMapping(path = "/deleteModerator/{userId}")
    public void deleteModerator(@PathVariable("userId") int userId){
        userService.deleteModerator(userId);
    }


    @DeleteMapping(path = "/delete{id}")
    public String DeleteUser(@PathVariable("id") int id) {
        return userService.deleteUser(id)? "Deleted" : "Not found user with id " + id;
    }
}
