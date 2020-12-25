package hiber;

import hiber.config.AppConfig;
import hiber.model.*;
import hiber.service.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
	  TransactionManager tm = context.getBean(TransactionManager.class);
      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
	  User user2 = new User("User2", "Lastname2", "user2@mail.ru");
	  Car t = new Car("Toyota", 42);
	  Car ld = new Car("Lada", 14);
      tm.executeAsTransaction(session -> {
		  session.save(user1);
		  session.save(user2);
		  t.setUser(user1);
		  ld.setUser(user2);
		  session.save(t);
		  session.save(ld);
	  });
	  
      List<User> users = userService.getUsersByCar("Toyota", 42);
      users.forEach(System.out::println);

      context.close();
   }
}
