package Afisha_Odzhetto.Phase_event;

import Afisha_Odzhetto.Event.Event;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@Data
@Table
@Entity(name = "phase_event")
public class Phase_event {
    @Id
    @SequenceGenerator(
            name = "phase_sequence",
            sequenceName = "phase_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "phase_sequence"
    )
    private Integer id;

//    private Integer event_id;
//    private LocalDate beginning;
//    private LocalDate ending;
    private String phase_name;
    private String phase_description;

    public Phase_event(String phase_name, String phase_description) {
        this.phase_name = phase_name;
        this.phase_description = phase_description;
    }

//    public Phase_event(LocalDate beginning, LocalDate ending, String phase_name, String phase_description) {
//        this.beginning = beginning;
//        this.ending = ending;
//        this.phase_name = phase_name;
//        this.phase_description = phase_description;
//    }
}
