package org.projeto.api.scheduler;

import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import org.projeto.domain.service.EventService;

@ApplicationScoped
public class EventDateCheck {

    private final EventService eventService;

    public EventDateCheck(EventService eventService) {
        this.eventService = eventService;
    }


    @Scheduled(cron = "0 0 * * * ?")
    void checkEventsStatus() {
        eventService.handleEventStatus();

    }

}
