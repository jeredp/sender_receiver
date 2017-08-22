package user_service.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import user_service.models.User;

public interface UserRepository extends MongoRepository<User, String> {
    @Query("{ 'email' : {$regex : ?0}}")
    List<User> findByEmail(String email);
}