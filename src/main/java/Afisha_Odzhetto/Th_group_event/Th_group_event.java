package Afisha_Odzhetto.Th_group_event;

import Afisha_Odzhetto.Event.Event;
import Afisha_Odzhetto.Group.Group;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Table
@Entity(name = "th_group_event")
public class Th_group_event {

    @Id
    @SequenceGenerator(
            name = "grev_seq",
            sequenceName = "grev_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "grev_seq"
    )
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "group_id")
    Group group;

    @ManyToOne
    @JoinColumn(name = "event_id")
    Event event;

    public Th_group_event(Group group, Event event) {
        this.group = group;
        this.event = event;
    }
}
