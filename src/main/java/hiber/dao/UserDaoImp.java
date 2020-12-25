package hiber.dao;

import hiber.model.*;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
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
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }
   
   @Override
   public List<User> getUsersByCar(String model, int series) {
	   String hql = "FROM Car where model = :model and series = :series";
	   TypedQuery<Car> query=sessionFactory.getCurrentSession().createQuery(hql);
	   query.setParameter("model", model);
	   query.setParameter("series", series);
	   List <Car> cars = query.getResultList();
	   
	   return cars.stream().map(Car::getId)
		.map(id -> sessionFactory.getCurrentSession().get(User.class, id))
		.collect(toList());
	   
   }

}
