package user_service.messaging.events;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class Event<EventModel> {

    private final String type;
    private final EventModel value;

    Event(EventModel value) {
        this.value = value;
        this.type = this.getClass().getSimpleName();
    }

    @JsonProperty
    public String type() {
        return this.type;
    }

    @JsonProperty
    public EventModel getValue() {
        return value;
    }
}
