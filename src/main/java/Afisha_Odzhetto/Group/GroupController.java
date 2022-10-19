package Afisha_Odzhetto.Group;

import Afisha_Odzhetto.Th_user_group.ThUserGroupRepository;
import Afisha_Odzhetto.Th_user_group.Th_user_group;
import Afisha_Odzhetto.User.User;
import Afisha_Odzhetto.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/group")
@CrossOrigin
public class GroupController {

    GroupRepository groupRepository;
    UserRepository userRepository;

    @Autowired
    public GroupController(GroupRepository groupRepository,
                           UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }

    @GetMapping(path = "/all")
    public List<Group> getAllGroups(){
        return groupRepository.findAll();
    }

    @PostMapping(path = "/create")
    public void createGroup(@RequestBody Group group){
        groupRepository.save(group);
    }

    @PostMapping(path = "{groupId}/adduser")
    public void addUser(@PathVariable ("groupId") int groupId, @RequestBody User user){
        Group group = groupRepository.findById(groupId).orElseThrow(()->
                new IllegalStateException("not found group"));
        group.addUser(user);
    }

    @PostMapping(path = "/delete{groupId}")
    public void deleteGroup(@PathVariable ("groupId") int groupId){
        groupRepository.deleteById(groupId);
    }

    @PostMapping(path = "/delete{groupId}/{userId}")
    public void deleteUser(@PathVariable ("groupId") int groupId, @PathVariable ("userId") int userId){
        Group group = groupRepository.findById(groupId).orElseThrow(()->
                new IllegalStateException("not found"));
        User user = userRepository.findById(userId).orElseThrow(()->
                new IllegalStateException("not found"));
        group.removeUser(user);
    }
}
