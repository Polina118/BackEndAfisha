package Afisha_Odzhetto.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(User user) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(user.getLogin());
        if(!matcher.matches())
            throw new IllegalStateException("incorrect login");
        Optional<User> optionalUser = userRepository.findByLogin(user.getLogin());
        if (optionalUser.isPresent())
            throw new IllegalStateException("login is taken");
        userRepository.save(new User(user.getLogin(), user.getPassword()));
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

    public User autorization(String login, String password) {
        User user = userRepository.findByLogin(login).orElseThrow(()->
                new IllegalStateException("user not found"));
        if (!user.getPassword().equals(password))
            throw new IllegalStateException((" --!incorrect password!-- "));
        return user;

    }

    @Transactional
    public void addModerator(int userId) {
        User user = userRepository.findById(userId).orElseThrow(()->
                new IllegalStateException("user not found with id " + userId));
        user.setIs_moderator(true);
    }

    @Transactional
    public void deleteModerator(int userId) {
        User user = userRepository.findById(userId).orElseThrow(()->
                new IllegalStateException("user not found with id " + userId));
        user.setIs_moderator(false);
    }
}
