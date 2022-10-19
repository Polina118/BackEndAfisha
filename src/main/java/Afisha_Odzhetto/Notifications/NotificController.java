package Afisha_Odzhetto.Notifications;

import Afisha_Odzhetto.Participation.ParticipationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.internet.MimeMessage;

@RestController
@RequestMapping("/notify")
public class NotificController {

    NotificRepository notificRepository;
    ParticipationRepository participationRepository;
    private final JavaMailSender javaMailSender;
    @Value("$spring.mail.sender.email")
    private String senderEmail;

    @Autowired
    public NotificController(NotificRepository notificRepository,
                             ParticipationRepository participationRepository,
                             JavaMailSender javaMailSender) {
        this.notificRepository = notificRepository;
        this.participationRepository = participationRepository;
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(String receiver){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(senderEmail);
        message.setTo(receiver);
        message.setSubject("Тестовое письмо");
        message.setText("Текстовое сообщение в тестовом письме.\nВторая строка.");
        javaMailSender.send(message);
    }

    public void sendHtmlEmail(String receiver){
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setFrom(senderEmail);
            helper.setTo(receiver);
            String event_name = "event";
            String phase1 = "этап 1";
            String phase2 = "этап 2";
            helper.setSubject("Вы записаны на мероприятие " + event_name);
            String text = String.format("много текста про это <b style='color:red;'>мероприятие %n</b>, оно очень крутое. А еще есть этапы<br>" +
                    " %p : его описание" +
                    "  : его описание" +
                    "ЧТобы подтвердить участие, нажмите на кнопку ниже", event_name, phase1);
            helper.setText(text, true);
            javaMailSender.send(message);
        }
        catch (Exception e) {
            System.out.println("error");
        }

    }
}
