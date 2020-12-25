package hiber.service;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.function.Consumer;

@Service
public class TransactionManager {
   @Autowired
   private SessionFactory sessionFactory;
   
   @Transactional
   public void executeAsTransaction(Consumer <? super Session> unitOfWork) {
	   unitOfWork.accept(sessionFactory.getCurrentSession());
   }
}