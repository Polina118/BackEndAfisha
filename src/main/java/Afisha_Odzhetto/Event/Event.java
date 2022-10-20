package Afisha_Odzhetto.Event;

import Afisha_Odzhetto.Group.Group;
import Afisha_Odzhetto.Phase_event.Phase_event;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@NoArgsConstructor
@Table
@Data
@Entity
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
    @Lob
    private String description;
    private LocalDate date_of_create;
    private String date_of_event;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="event")
    private List<Phase_event> phases;

    public Event(String event_name, String description, String date_of_event) {
        this.event_name = event_name;
        this.description = description;
        this.date_of_create = LocalDate.now();
        this.date_of_event = date_of_event;
        this.phases = new ArrayList<>();
    }

    public Event(String event_name,
                 String description,
                 String date_of_event,
                 ArrayList<Phase_event> phases) {
        this.event_name = event_name;
        this.description = description;
        this.date_of_create = LocalDate.now();
        this.date_of_event = date_of_event;
        for (Phase_event phase_event : phases)
            if (phase_event.getPhase_name()!=null || phase_event.getPhase_name().length() != 0)
                this.phases.add(phase_event);
    }

  /*  public void addPhase(Phase_event phase_event){
        this.phases.add(phase_event);
    }*/

    @ManyToMany (cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "event_group",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    private List<Group> groups=new ArrayList<>();

    public void addGroup(Group group){
        this.groups.add(group);
        group.getEvents().add(this);
    }

    public void removeGroup(Group group){
        this.groups.remove(group);
        group.getEvents().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof Event)) return false;

        return id != null && id.equals(((Event) o).getId());
    }

    @Override
    public int hashCode() {
        return 31;
    }

}
