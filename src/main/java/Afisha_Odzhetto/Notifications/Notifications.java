package Afisha_Odzhetto.Notifications;

import Afisha_Odzhetto.Event.Event;
import Afisha_Odzhetto.Participation.Participation;
import Afisha_Odzhetto.User.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@Data
@Table
@Entity(name = "notifications")
 public class Notifications {

    @Id
    @SequenceGenerator(
            name = "notify_sequence",
            sequenceName = "notify_sequence",
            allocationSize = 1
        )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "notify_sequence"
        )
    private Integer id;
    private Integer user_id;
    private Integer event_id;
    private LocalDate date_of_send;
    private Boolean is_read;

    public Notifications(Integer user_id, Integer event_id) {
        this.user_id = user_id;
        this.event_id = event_id;
        this.date_of_send = LocalDate.now();
        this.is_read = false;
    }

}
