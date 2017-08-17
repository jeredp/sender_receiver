package user_service.models;

import org.springframework.data.annotation.Id;

public class User {
    @Id
    public String id;
    public String email;
    public String password;

    public User(){}

    public User(String email, String password) {
        this.email = email;
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
