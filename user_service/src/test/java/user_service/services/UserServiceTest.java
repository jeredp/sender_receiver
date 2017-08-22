package user_service.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import user_service.messaging.Sender;
import user_service.messaging.events.UserCreatedEvent;
import user_service.models.User;
import user_service.repositories.UserRepository;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

public class UserServiceTest {

    private static final String EMAIL = "jered@wex.com";

    @Mock
    private UserRepository mockUserRepository;
    @Mock
    private Sender mockSender;

    private User user = new User(EMAIL, "superSecret");

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        given(mockUserRepository.save(user)).willReturn(user);
    }

    @Test
    public void shouldSaveUser() throws JsonProcessingException, JSONException {
        UserService userService = new UserService(mockUserRepository, mockSender);
        userService.createUser(user);

        verify(mockUserRepository).save(user);
    }

    @Test
    public void shouldSendMessageToQueue() throws JsonProcessingException, JSONException {
        UserService userService = new UserService(mockUserRepository, mockSender);
        userService.createUser(user);

        ArgumentCaptor<UserCreatedEvent> eventArgumentCaptor = ArgumentCaptor.forClass(UserCreatedEvent.class);
        verify(mockSender).send(eq(null), eventArgumentCaptor.capture());

        assertEquals(user, eventArgumentCaptor.getValue().getValue());
    }

}