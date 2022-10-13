package Afisha_Odzhetto.Event;


import Afisha_Odzhetto.Group.Group;
import Afisha_Odzhetto.Participation.Participation;
import Afisha_Odzhetto.Participation.ParticipationRepository;
import Afisha_Odzhetto.User.User;
import Afisha_Odzhetto.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "events")
@CrossOrigin
public class EventController {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final ParticipationRepository participationRepository;

    @Autowired
    public EventController(EventRepository eventRepository,
                           UserRepository userRepository,
                           ParticipationRepository participationRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.participationRepository = participationRepository;
    }

    @GetMapping
    public List<Event> getEvents(){
        return eventRepository.findAll();
    }

    @PostMapping(path = "create")
    public String createEvent(@RequestBody Event event) {
        eventRepository.save(event);
        return "success";
    }

    @PostMapping(path = "/addParticipants{eventId}")
    public void addParts(@PathVariable("eventId") int eventId, @RequestBody Group group){
        Event event = eventRepository.findById(eventId).orElseThrow(()->
                new IllegalStateException("not found event with id " + eventId));
        event.addGroup(group);
            List<User> users = group.getUsers_of_group();
            for (User user : users){
                participationRepository.save(new Participation(user.getId(), eventId));
            }
        }

    @Transactional
    @PutMapping(path = "update/{eventId}")
    public void updateEvent(@PathVariable("eventId") int eventId,
                            @RequestParam String event_name,
                            @RequestParam String description)
    {
        Event event = eventRepository.findById(eventId).orElseThrow(() ->
                new IllegalStateException("not found event with id " + eventId));
        event.setEvent_name(event_name);
        event.setDescription(description);
    }

    @DeleteMapping(path = "delete/{eventId}")
    public String deleteEvent(@PathVariable("eventId") int eventId){
        Event event = eventRepository.findById(eventId).orElseThrow(() ->
                new IllegalStateException("not found event with id " + eventId));
        eventRepository.delete(event);
        return "deleted";
    }
}
