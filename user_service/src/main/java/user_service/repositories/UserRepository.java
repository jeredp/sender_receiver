package user_service.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import user_service.models.User;

public interface UserRepository extends MongoRepository<User, String> {
    List<User> findByEmail(String email);
}