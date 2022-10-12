package Afisha_Odzhetto.Event;

import Afisha_Odzhetto.Participation.Participation;
import Afisha_Odzhetto.Phase_event.Phase_event;
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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="event_id")
    private List<Phase_event> phases;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name="part_id")
//    private Participation participation;

    public Event(String event_name, String description) {
        this.event_name = event_name;
        this.description = description;
        this.date_of_create = LocalDate.now();
        this.phases = new ArrayList<>();
        //this.participation = new Participation();
    }

    public void addPhase(Phase_event phase_event){
        this.phases.add(phase_event);
    }
}
