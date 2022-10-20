package Afisha_Odzhetto;

import Afisha_Odzhetto.Event.Event;
import Afisha_Odzhetto.Event.EventRepository;
import Afisha_Odzhetto.Group.Group;
import Afisha_Odzhetto.Group.GroupRepository;
import Afisha_Odzhetto.User.User;
import Afisha_Odzhetto.User.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Application {
	//events_project
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(UserRepository userRepository,
										EventRepository eventRepository,
										GroupRepository groupRepository) {
		return args -> {
			try {

				User polina = new User("p_guk@inbox.ru", "12");
				polina.setIs_admin(true);
				polina.setIs_moderator(true);
				User user1 = new User("user1@gmail.com", "11");
				User user2 = new User("user2@gmail.com", "22");
				User user3 = new User("user3@gmail.com", "33");
				//userRepository.saveAll(List.of(polina, user3));

				Event event1 = new Event("event1", "desc1","2022-8-14");
				Event event2 = new Event("event2", "desc1", "2022-8-14");
				Event event3 = new Event("event3", "desc1", "2022-8-14");
                Event event4 = new Event("event4", "desc1", "2022-8-14");

				Group group1 = new Group("group1", "details");
				Group group2 = new Group("group2", "details");
				Group group3 = new Group("group3", "details");

				group1.addUser(polina);
				group1.addUser(user2);

				group2.addUser(user1);

				event1.addGroup(group1);
				event2.addGroup(group2);

				eventRepository.saveAll(List.of(event1, event2, event3, event4));

			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		};
	}
}
