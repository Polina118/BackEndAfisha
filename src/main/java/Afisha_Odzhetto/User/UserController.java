package Afisha_Odzhetto.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<UserResponse> getUsers() { // id login mod admin
        return userService.getUsers();
    }

    @PostMapping(path = "/registration")
    public String RegisterNewUser(@RequestBody User user) {
        userService.addUser(user);
        return "Success";
    }

    @PostMapping(path = "/authorization")
    public User AuthorizationUser(@RequestBody User user) {
        return userService.autorization(user.getLogin(), user.getPassword());
    }

    @PutMapping(path = "/addModerator/{userId}")
    public void addModerator(@PathVariable("userId") int userId){
        userService.addModerator(userId);
    }

    @PutMapping(path = "/deleteModerator/{userId}")
    public void deleteModerator(@PathVariable("userId") int userId){
        userService.deleteModerator(userId);
    }


    @DeleteMapping(path = "/delete{id}")
    public String DeleteUser(@PathVariable("id") int id) {
        return userService.deleteUser(id)? "Deleted" : "Not found user with id " + id;
    }
}
