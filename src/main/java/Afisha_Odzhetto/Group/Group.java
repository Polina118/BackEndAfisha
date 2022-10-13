package Afisha_Odzhetto.Group;

import Afisha_Odzhetto.Th_user_group.Th_user_group;
import Afisha_Odzhetto.User.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@Table
@Entity(name = "groups")
public class Group {

    @Id
    @SequenceGenerator(
            name = "group_sequence",
            sequenceName = "group_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "group_sequence"
    )
    private Integer id;
    private String group_name;

    private String description;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "group_id")
    private List<User> users_of_group;

//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "groupid")
//    private List<Th_user_group> groupList;

    public Group(String group_name,String description) {
        this.group_name = group_name;
        this.description = description;
        this.users_of_group = new ArrayList<>();
    }

    public Group(String group_name,String description, ArrayList<User> users_of_group) {
        this.group_name = group_name;
        this.description = description;
        this.users_of_group = users_of_group;
    }

    public void addUser(User user){
        users_of_group.add(user);
    }

    public void deleteUser(User user) {
        users_of_group.remove(user);
    }
}
