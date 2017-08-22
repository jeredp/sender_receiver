package receiver.events;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import receiver.models.User;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserCreatedEvent extends Event<User>{

}
