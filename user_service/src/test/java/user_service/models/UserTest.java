package user_service.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void shouldSerializeUserWithoutPassword() throws JsonProcessingException {
        User user = new User("email", "password");
        String userString = new ObjectMapper().writeValueAsString(user);
        assertEquals("{\"email\":\"email\"}", userString);
    }

    @Test
    public void shouldIdWhenExists() throws JsonProcessingException {
        User user = new User("email", "password");
        user.setId("jo");
        String userString = new ObjectMapper().writeValueAsString(user);
        assertEquals("{\"id\":\"jo\",\"email\":\"email\"}", userString);
    }

}