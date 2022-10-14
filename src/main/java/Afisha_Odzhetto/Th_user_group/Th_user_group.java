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

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @JoinColumn(name = "group_id")
    Group group;

    public Th_user_group(User user, Group group) {
        this.user = user;
        this.group = group;
    }
}
