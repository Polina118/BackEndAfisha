package Afisha_Odzhetto.Event;


import Afisha_Odzhetto.Group.Group;
import Afisha_Odzhetto.Group.GroupRepository;
import Afisha_Odzhetto.Participation.Participation;
import Afisha_Odzhetto.Participation.ParticipationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/events")
@CrossOrigin
public class EventController {

    private final EventRepository eventRepository;
    private final ParticipationRepository participationRepository;
    private final GroupRepository groupRepository;

    @Autowired
    public EventController(EventRepository eventRepository,
                           ParticipationRepository participationRepository,
                           GroupRepository groupRepository) {
        this.eventRepository = eventRepository;
        this.participationRepository = participationRepository;
        this.groupRepository = groupRepository;
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
    public void addParts(@PathVariable("eventId") int eventId, @RequestParam int groupId){
        Event event = eventRepository.findById(eventId).orElseThrow(()->
                new IllegalStateException("not found event with id " + eventId));
        Group group = groupRepository.findById(groupId).orElseThrow(()->
                new IllegalStateException("not found group with id " + groupId));
        // установить связь
        event.addGroup(group);
        // восстановить свзяь юзера и группы
//        List<Th_user_group> user_groups = userGroupRepository.findAllByGroup(group);
//            for (var ug : user_groups){
//                participationRepository.save(new Participation(ug.getUser().getId(), eventId));
//            }
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
