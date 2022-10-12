package Afisha_Odzhetto.User;

import Afisha_Odzhetto.Event.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM users u WHERE u.login = ?1")
    Optional<User> findByLogin(String login);
}
