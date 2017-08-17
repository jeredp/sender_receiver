package user_service.services;

import com.google.common.collect.Lists;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import user_service.messaging.Sender;
import user_service.models.User;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import user_service.repositories.UserRepository;

import java.util.List;

public class UserService {

    private UserRepository userRepository;
    private Sender sender;

    @Value("${queue.users}")
    private String usersQueue;

    @Autowired
    public UserService(UserRepository userRepository, Sender sender) {
        this.userRepository = userRepository;
        this.sender = sender;
    }

    public List<User> findUsersByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    public User createUser(User user) {
        User saved = this.userRepository.save(user);
        this.sender.send(usersQueue, saved);
        return saved;
    }
}
