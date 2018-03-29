package com.codingdojo.events.services;

import com.codingdojo.events.repositories.EventRepository;
import org.springframework.stereotype.Service;
import com.codingdojo.events.models.*;

import java.util.HashMap;
import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public void addEvent(Event event) {
        eventRepository.save(event);
    }

    public Event eventById(Long eventId) {
        return eventRepository.findById(eventId).orElse(null);
    }

    public List<Event> allEvents() {
        return eventRepository.findAll();
    }

    public List<Event> userLocalEvents(User user) {
        return eventRepository.findByLocationState(user.getState());
    }

    public List<Event> otherEvents(User user) {
        return eventRepository.findByLocationStateNot(user.getState());
    }

    public Event refillEvent(Event event, User user) {
        Event existingEvent = eventById(event.getId());
        if (existingEvent.getHost().getId() == user.getId()) {
            event.setHost(existingEvent.getHost());
            event.setAttendees(existingEvent.getAttendees());
            event.setMessages(existingEvent.getMessages());
            return event;
        } else {
            return null;
        }
    }

    public void updateEvent(Event event) {
        eventRepository.save(event);
    }

    public void joinEvent(Event event, User user) {
        if (!event.getAttendees().contains(user)) {
            event.getAttendees().add(user);
        }
        eventRepository.save(event);
    }

    public void leaveEvent(Event event, User user) {
        if (event.getAttendees().contains(user)) {
            event.getAttendees().remove(user);
        }
        eventRepository.save(event);
    }

    public HashMap<Long,Boolean> userEvents(User user) {
        List<Event> userEventList = eventRepository.findByUser(user);
        HashMap<Long,Boolean> userAttendedEvents = new HashMap<>();
        for (Event event : userEventList) {
            userAttendedEvents.put(event.getId(),true);
        }
        return userAttendedEvents;
    }

    public void deleteEvent(Event event) {
        eventRepository.delete(event);
    }
}
