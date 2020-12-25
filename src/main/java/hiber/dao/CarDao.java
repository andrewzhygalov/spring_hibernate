package hiber.dao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import hiber.model.Car;
@Repository
public class CarDao {

   @Autowired
   private SessionFactory sessionFactory;

   public void add(Car car) {
      sessionFactory.getCurrentSession().save(car);
   }
}