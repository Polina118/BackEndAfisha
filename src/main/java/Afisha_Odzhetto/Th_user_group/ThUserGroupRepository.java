package Afisha_Odzhetto.Th_user_group;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ThUserGroupRepository extends JpaRepository<Th_user_group, Integer> {

    @Modifying
    @Query("DELETE FROM th_user_group ug WHERE ug.user_id=?1 AND ug.group_id = ?2")
    void deleteTh(int userId, int groupId);

    @Query("DELETE FROM th_user_group ug WHERE ug.group_id = ?1")
    void deleteGroup(int groupId);
}
