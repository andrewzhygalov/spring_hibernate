package hiber.dao;

import hiber.model.*;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;
import static java.util.stream.Collectors.toList;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
     sessionFactory.getCurrentSession().save(user);
   }

   @Override
   public List<User> listUsers() {
      return sessionFactory.getCurrentSession()
		.createQuery("from User", User.class).getResultList();
   }
   
   @Override
   public List<User> getUsersByCar(String model, int series) {
	  var query = """
		from User user where 
			user.car.model=:model and user.car.series=:series
	  """;

	  return sessionFactory.getCurrentSession()
		.createQuery(query, User.class)
		.setParameter("model", model)
		.setParameter("series", series)
		.getResultList();
   }

}
