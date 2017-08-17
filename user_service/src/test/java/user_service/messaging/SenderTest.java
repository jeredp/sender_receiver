package user_service.messaging;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.activemq.junit.EmbeddedActiveMQBroker;
import org.json.JSONObject;
import org.junit.AfterClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import user_service.models.User;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

public class SenderTest {
    public static final String QUEUE = "helloworld.q";
    private Sender sender;

    @Mock
    private JmsTemplate jmsTemplate;

    @Test
    public void shouldSendToQueue() throws Exception {
        MockitoAnnotations.initMocks(this);

        sender = new Sender(jmsTemplate);
        User user = new User("user", "pass");
        sender.send(QUEUE, user);

        verify(jmsTemplate).convertAndSend(QUEUE, "{\"id\":null,\"email\":\"user\",\"password\":\"pass\"}");

    }
}