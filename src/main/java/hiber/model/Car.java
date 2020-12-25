package hiber.model;

import javax.persistence.*;

@Entity
@Table(name = "cars")
public class Car { 
   @Id
   private Long id;
   
   private String model;
   
   private Integer series;
   
   @OneToOne(fetch=FetchType.LAZY)
   @MapsId
   private User user;
   
   public Car(String model, Integer series) {
	   this.model = model;
	   this.series = series;
   }
   public Car() {}
   
   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getModel() {
      return model;
   }

   public void setModel(String model) {
      this.model = model;
   }

   public Integer getSeries() {
      return series;
   }

   public void setSeries(Integer series) {
      this.series = series;
   }
   public User getUser() {
      return user;
   }

   public void setUser(User user) {
      this.user = user;
   }
   @Override
   public String toString() {
	   return model + " " + series;
   }
}