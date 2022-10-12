package Afisha_Odzhetto;

import Afisha_Odzhetto.Event.Event;
import Afisha_Odzhetto.User.User;
import Afisha_Odzhetto.User.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
				polina.addEvent(event1);
				userRepository.save(polina);
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		};
	}
}
