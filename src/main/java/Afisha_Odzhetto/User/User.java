package Afisha_Odzhetto.User;

import Afisha_Odzhetto.Group.Group;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@NoArgsConstructor
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "login_unique", columnNames = "login") })
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

    public User(String login, String password) {
        this.login = login;
        this.password = password;
        this.is_moderator = false;
        this.is_admin = false;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getIs_moderator() {
        return is_moderator;
    }

    public void setIs_moderator(Boolean is_moderator) {
        this.is_moderator = is_moderator;
    }

    public Boolean getIs_admin() {
        return is_admin;
    }

    public void setIs_admin(Boolean is_admin) {
        this.is_admin = is_admin;
    }

    @ManyToMany(mappedBy = "users")
    private Set<Group> groups=new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof Group)) return false;

        return id != null && id.equals(((User) o).getId());
    }

    @Override
    public int hashCode() {
        return 31;
    }

    public Set<Group> getGroups() {
        return groups;
    }
}