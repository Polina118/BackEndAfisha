package Afisha_Odzhetto.Th_user_group;

import Afisha_Odzhetto.Group.Group;
import Afisha_Odzhetto.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ThUserGroupRepository extends JpaRepository<Th_user_group, Integer> {

    @Query("SELECT ug FROM th_user_group ug WHERE ug.group = ?1")
    List<Th_user_group> findAllByGroup(Group group);
}
