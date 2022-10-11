package Afisha_Odzhetto.Group;

import Afisha_Odzhetto.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<User, Integer> {
}
