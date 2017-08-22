package user_service.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.annotation.Id;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    @Id
    private String id;
    private String email;
    @JsonIgnore
    private String password;

    public User(){}

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String email, String password, String id) {
        this.email = email;
        this.password = password;
        this.setId(id);
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "user: {" +
                "id:'" + id + '\'' +
                ", email:'" + email + '\'' +
                ", password:'" + password + '\'' +
                '}';
    }
}
