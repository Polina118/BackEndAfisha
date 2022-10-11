package Afisha_Odzhetto.Group;

import Afisha_Odzhetto.Th_user_group.Th_user_group;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    private String details;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "user_group_id", referencedColumnName = "id")
//    private Th_user_group group_of_users;


    public Group(String group_name,String details) {
        this.group_name = group_name;
        this.details = details;
    }
}
