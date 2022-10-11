package Afisha_Odzhetto.Participation;

import Afisha_Odzhetto.Event.Event;
import Afisha_Odzhetto.User.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@Data
@Table
@Entity(name = "participation")
public class Participation {
    @Id
    @SequenceGenerator(
            name = "part_sequence",
            sequenceName = "part_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "part_sequence"
    )
    private Integer id;
    private LocalDate date_of_participation;
    private Boolean is_invited;
    private LocalDate date_of_create;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="event_id")
    private Event event;

    public Participation(LocalDate date_of_participation,
                         Boolean is_invited,
                         LocalDate date_of_create) {
        this.date_of_participation = date_of_participation;
        this.is_invited = is_invited;
        this.date_of_create = date_of_create;
    }
}

