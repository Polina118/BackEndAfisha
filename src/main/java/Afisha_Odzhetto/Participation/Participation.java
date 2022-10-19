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
    private Boolean is_invited;
    private LocalDate date_of_create;
    private int user_id;
    private int event_id;

    public Participation(int user_id, int event_id) {
        this.user_id = user_id;
        this.event_id = event_id;
        this.is_invited = false;
        this.date_of_create = LocalDate.now();
    }
}

