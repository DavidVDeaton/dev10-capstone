package learn.easypacking.domain;

import learn.easypacking.data.EventRepository;
import learn.easypacking.models.Event;
import learn.easypacking.models.ToDo;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EventService {

    private EventRepository repository;

    public EventService(EventRepository repository) {
        this.repository = repository;
    }

    public Event findById(int eventId){return repository.findById(eventId);}

    public List<Event>findByUserId(int userId){return repository.findByUserId(userId);}

    public Result<Event> createEvent(Event event){
        Result<Event> result = validate(event);

        if(!result.isSuccess()){
            return result;
        }

        if(event.getEventId() != 0){
            result.setMessages("eventId cannot be set before the createToDo operation", ResultType.INVALID);
            return result;
        }

        event = repository.createEvent(event);
        result.setPayload(event);
        return result;
    }

    public Result<Event> updateEvent(Event event){
        Result<Event> result = validate(event);
        if(!result.isSuccess()){
            return result;
        }

        if(event.getEventId() <= 0){
            result.setMessages("eventId required for update operation", ResultType.INVALID);
            return result;
        }

        if(!repository.updateEvent(event)){
            String message = String.format("eventId: %s not found", event.getEventId());
            result.setMessages(message,  ResultType.NOT_FOUND);
        }
        return result;
    }

    public Result<Event> deleteEvent(int eventId){
        Result<Event> result = new Result<>();
        if(eventId <= 0){
            result.setMessages("eventId required for update operation", ResultType.INVALID);
            return result;
        }
        if(!repository.deleteEvent(eventId)){
            String message = String.format("eventId: %s not found", eventId);
            result.setMessages(message,  ResultType.NOT_FOUND);
        }
        return result;
    }

    private Result<Event> validate(Event event) {
        Result<Event> result = new Result<>();

        if (event == null) {
            result.setMessages("Event cannot be null", ResultType.INVALID);
            return result;
        }

        if (event.getAppUserId() == 0) {
            result.setMessages("App User Id is required", ResultType.INVALID);
        }

        return result;
    }
}
