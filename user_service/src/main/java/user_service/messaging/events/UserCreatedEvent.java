package user_service.messaging.events;

import user_service.models.User;

public class UserCreatedEvent extends Event<User> {
    public UserCreatedEvent(User value) {
        super(value);
    }
}
