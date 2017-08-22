package receiver.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import receiver.events.Event;
import receiver.events.UserCreatedEvent;
import receiver.models.User;

import java.io.IOException;

public class Receiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

    @JmsListener(destination = "${queue.users}")
    public void receive(String message) {
        LOGGER.debug(message);
        JsonElement jsonElement = new JsonParser().parse(message);
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        String type = jsonObject.get("type").getAsString();
        if(type.equals("UserCreatedEvent")) {
            String userJson = jsonObject.get("value").toString();
            try {
                User user = new ObjectMapper().readValue(userJson, User.class);
                Event event = new ObjectMapper().readValue(message, UserCreatedEvent.class);
                LOGGER.info(String.format("successfully received user %s", user.toString()));
            } catch (IOException e) {
                LOGGER.error("could not deserialize user from event");
            }
        }
    }
}