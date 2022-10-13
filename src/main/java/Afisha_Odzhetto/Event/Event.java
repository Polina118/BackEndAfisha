package Afisha_Odzhetto.Event;

import Afisha_Odzhetto.Group.Group;
import Afisha_Odzhetto.Participation.Participation;
import Afisha_Odzhetto.Phase_event.Phase_event;
import Afisha_Odzhetto.Th_user_group.Th_user_group;
import Afisha_Odzhetto.User.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@NoArgsConstructor
@Data
@Table
@Entity(name = "event")
public class Event {
    @Id
    @SequenceGenerator(
            name = "event_sequence",
            sequenceName = "event_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "event_sequence"
    )
    private Integer id;
    private String event_name;
    private String description;
    private LocalDate date_of_create;

    private LocalDate date_of_event;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="event_id")
    private List<Phase_event> phases;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "event_id")
    private List<Group> groups;


    public Event(String event_name, String description, LocalDate date_of_event) {
        this.event_name = event_name;
        this.description = description;
        this.date_of_create = LocalDate.now();
        this.date_of_event = date_of_event;
        this.phases = new ArrayList<>();
        this.groups = new ArrayList<>();
    }

    public Event(String event_name,
                 String description,
                 LocalDate date_of_event,
                 ArrayList<Phase_event> phases,
                 ArrayList<Group> groups) {
        this.event_name = event_name;
        this.description = description;
        this.date_of_create = LocalDate.now();
        this.date_of_event = date_of_event;
        this.phases = phases;
        this.groups = groups;
    }

    public void addPhase(Phase_event phase_event){
        this.phases.add(phase_event);
    }

    public void addGroup(Group group){
        this.groups.add(group);
    }
}
