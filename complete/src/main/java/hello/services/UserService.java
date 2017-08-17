package hello.services;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import hello.messaging.Sender;
import hello.models.User;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class UserService {

    private MongoDatabase database;
    private Sender sender;

    @Value("${queue.users}")
    private String usersQueue;

    @Autowired
    public UserService(MongoDatabase database, Sender sender) {
        this.database = database;
        this.sender = sender;
    }

    public void createUser(User user) {
        MongoCollection<Document> collection = database.getCollection("users");

        Document document = new Document("email", user.getEmail())
                .append("password", user.getPassword());

        collection.insertOne(document);
        sender.send(usersQueue, user.getEmail());
    }
}
