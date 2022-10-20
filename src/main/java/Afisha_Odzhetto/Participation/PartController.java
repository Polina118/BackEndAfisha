package Afisha_Odzhetto.Participation;

import Afisha_Odzhetto.Event.Event;
import Afisha_Odzhetto.Event.EventRepository;
import Afisha_Odzhetto.Notifications.NotificRepository;
import Afisha_Odzhetto.Notifications.Notifications;
import Afisha_Odzhetto.User.User;
import Afisha_Odzhetto.User.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/part")
public class PartController {

    ParticipationRepository participationRepository;
    NotificRepository notificRepository;
    UserRepository userRepository;
    EventRepository eventRepository;
    private final JavaMailSender javaMailSender;
    @Value("$spring.mail.sender.email")
    private String senderEmail;

    public PartController(ParticipationRepository participationRepository,
                          NotificRepository notificRepository,
                          UserRepository userRepository,
                          EventRepository eventRepository,
                          JavaMailSender javaMailSender) {
        this.participationRepository = participationRepository;
        this.notificRepository = notificRepository;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
        this.javaMailSender = javaMailSender;
    }

    @PostMapping("/{userId}confirm{eventId}")
    public void confirmPart(@PathVariable("userId") int userId, @PathVariable("eventId") int eventId){
        Optional<Participation> optionalPart = participationRepository.findbyUseridEventid(userId, eventId);
        if(optionalPart.isEmpty())
            throw new IllegalStateException("part not found");
        optionalPart.get().setIs_invited(true);

        notificRepository.save(new Notifications(userId, eventId));
        Event event = eventRepository.findById(eventId).orElseThrow(()->
                new IllegalStateException(""));

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(senderEmail);
        User receiver = userRepository.findById(userId).orElseThrow(()->
                new IllegalStateException("user not found"));
        message.setTo(receiver.getLogin());
        message.setSubject("Вы записались на мероприятие");
        String textEmail = "Вы записались на мероприятие" + event.getEvent_name()+
                "\n" + event.getDescription() +
                "\n дата проведения события : " + event.getDate_of_event();
        message.setText(textEmail);

        javaMailSender.send(message);
    }
}
