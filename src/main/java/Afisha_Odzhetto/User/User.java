package Afisha_Odzhetto.User;

import Afisha_Odzhetto.Event.Event;
import Afisha_Odzhetto.Group.Group;
import Afisha_Odzhetto.Notifications.Notifications;
import Afisha_Odzhetto.Participation.Participation;
import Afisha_Odzhetto.Th_user_group.Th_user_group;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Data
@Table
@Entity(name = "users")
public class User {

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Integer id;
    private String login;
    private String password;
    private Boolean is_moderator;
    private Boolean is_admin;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private List<Event> events;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "creator_id")
    private List<Group> groups_created;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    private List<Participation> participations;


    public User(String login, String password) {
        this.login = login;
        this.password = password;
        this.is_moderator = false;
        this.is_admin = false;
        this.events = new ArrayList<>();
        this.groups_created = new ArrayList<>();
        this.participations = new ArrayList<>();
    }

    public void addEvent(Event event){
        this.events.add(event);
    }

    public void addGroup(Group group){
        this.groups_created.add(group);
    }
}