package Afisha_Odzhetto.Th_user_group;

import Afisha_Odzhetto.Group.Group;
import Afisha_Odzhetto.User.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Table
@Entity(name = "th_user_group")
public class Th_user_group {
    @Id
    @SequenceGenerator(
            name = "usgr_sequence",
            sequenceName = "usgr_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "usgr_sequence"
    )
    private Integer id;
    private Integer user_id;
    private Integer group_id;

//    @OneToOne(mappedBy = "user_in_group")
//    private User user;
//
//    @OneToOne(mappedBy = "group_of_users")
//    private Group group;

    public Th_user_group(Integer user_id, Integer group_id) {
        this.user_id = user_id;
        this.group_id = group_id;
    }
}
