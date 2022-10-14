package Afisha_Odzhetto.Group;

import Afisha_Odzhetto.Author;
import Afisha_Odzhetto.Book;
import Afisha_Odzhetto.Event.Event;
import Afisha_Odzhetto.Th_group_event.Th_group_event;
import Afisha_Odzhetto.Th_user_group.Th_user_group;
import Afisha_Odzhetto.User.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@NoArgsConstructor
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

    public Group(String group_name,String description) {
        this.group_name = group_name;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public String getDescription() {
        return description;
    }

    @ManyToMany (cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "group_user",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users = new ArrayList<>();

    @ManyToMany(mappedBy = "groups")
    private Set<Event> events=new HashSet<>();

    public void addUser(User user){
        this.users.add(user);
        user.getGroups().add(this);
    }

    public void removeUser(User user){
        this.users.remove(user);
        user.getGroups().remove(this);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof Group)) return false;

        return id != null && id.equals(((Group) o).getId());
    }

    @Override
    public int hashCode() {
        return 31;
    }

    public Set<Event> getEvents() {
        return events;
    }
}
