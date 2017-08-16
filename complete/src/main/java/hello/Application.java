package hello;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import hello.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }

        };
    }

    @Bean
    public MongoDatabase database() {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        return mongoClient.getDatabase("wex");
    }

    @Bean
    public UserService userService(MongoDatabase database) {
        return new UserService(database);
    }

}
