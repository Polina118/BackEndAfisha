package Afisha_Odzhetto.Event;


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

    @Autowired
    public EventController(EventRepository eventRepository, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<Event> getEvents(){
        return eventRepository.findAll();
    }

    @PostMapping(path = "create/{userId}")
    public String createEvent(@PathVariable("userId") int userId,@RequestBody Event event) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new IllegalStateException("not found user with id " + userId));
        if (!user.getIs_moderator())
            return "Not moderator";
        user.addEvent(event);
        return "success";
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
