package user_service.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import user_service.models.User;

public class Sender {

    private static final Logger LOGGER = LoggerFactory.getLogger(Sender.class);

    private JmsTemplate jmsTemplate;

    @Autowired
    public Sender(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void send(String destination, User user) {
        try {
            jmsTemplate.convertAndSend(destination, new ObjectMapper().writeValueAsString(user));
        } catch (JsonProcessingException e) {
            LOGGER.error("could not send message", e);
            e.printStackTrace();
        }
    }
}
