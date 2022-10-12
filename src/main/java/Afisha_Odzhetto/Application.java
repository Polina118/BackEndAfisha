package Afisha_Odzhetto;

import Afisha_Odzhetto.Event.Event;
import Afisha_Odzhetto.User.User;
import Afisha_Odzhetto.User.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(UserRepository userRepository) {
		return args -> {
			try {
				User polina = new User("login", "12");
				polina.setIs_admin(true);
				polina.setIs_moderator(true);
				Event event1 = new Event("name1", "desc1");
				Event event2 = new Event("name2", "desc1");
				Event event3 = new Event("name3", "desc1");
                Event event4 = new Event("name4", "desc1");
				polina.setEvents(List.of(event1, event2, event3, event4));
				userRepository.save(polina);
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		};
	}
}
