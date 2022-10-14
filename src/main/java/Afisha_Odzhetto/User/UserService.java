package Afisha_Odzhetto.User;

import Afisha_Odzhetto.Event.Event;
import Afisha_Odzhetto.Group.Group;
import Afisha_Odzhetto.Group.GroupRepository;
import Afisha_Odzhetto.Th_user_group.ThUserGroupRepository;
import Afisha_Odzhetto.Th_user_group.Th_user_group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final ThUserGroupRepository thUserGroupRepository;

    @Autowired
    public UserService(UserRepository userRepository,
                       GroupRepository groupRepository,
                       ThUserGroupRepository thUserGroupRepository) {
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
        this.thUserGroupRepository = thUserGroupRepository;
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public List<UserResponse> getUsers() // id login mod admin
    {
        List<User> users = userRepository.findAll();
        List<UserResponse> response = new ArrayList<>(users.size());
        for (int i = 0; i < users.size(); i++){
            User user = users.get(i);
            response.add(i, new UserResponse(user.getId(), user.getLogin(), user.getIs_moderator(), user.getIs_admin()));
        }
        return response;
    }

    public boolean deleteUser(int id) {
        if (!userRepository.existsById(id))
            return false;
        userRepository.deleteById(id);
        return true;
    }

    public String autorization(String login, String password) {
        User user = userRepository.findByLogin(login).orElseThrow(()->
                new IllegalStateException("user not found"));
        if (!user.getPassword().equals(password))
            throw new IllegalStateException((" --!incorrect password!-- "));
        return "success";

    }

    public void addModerator(int userId) {
        User user = userRepository.findById(userId).orElseThrow(()->
                new IllegalStateException("user not found with id " + userId));
        user.setIs_moderator(true);
    }

    public void deleteModerator(int userId) {
        User user = userRepository.findById(userId).orElseThrow(()->
                new IllegalStateException("user not found with id " + userId));
        user.setIs_moderator(false);
    }
}
