package hello.services;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import hello.models.User;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;

public class UserService {

    private MongoDatabase database;

    @Autowired
    public UserService(MongoDatabase database) {
        this.database = database;
    }

    public void createUser(User user) {
//        database.createCollection("users");
        MongoCollection<Document> collection = database.getCollection("users");

        Document document = new Document("email", user.getEmail())
                .append("password", user.getPassword());

        collection.insertOne(document);

        Document found = collection.find().first();
        

        System.out.println(found.toJson());
    }
}
