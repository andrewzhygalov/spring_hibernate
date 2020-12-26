package hiber.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "name")
   private String firstName;

   @Column(name = "last_name")
   private String lastName;

   @Column(name = "email")
   private String email;
   
   @OneToOne(cascade=CascadeType.ALL)
   @JoinTable(name = "user_car", 
	joinColumns = 
	{@JoinColumn(name = "user_id", referencedColumnName = "id")},
	inverseJoinColumns = 
	{@JoinColumn(name = "car_id", referencedColumnName = "id")})
   private Car car;

   public User() {}
   
   public User(String firstName, String lastName, String email, Car car) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.email = email;
	  this.car = car;
	  car.setUser(this);
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }
   public Car getCar() {
      return car;
   }

   public void setCar(Car car) {
	  car.setUser(this);
      this.car = car;
   }
   
   @Override
   public String toString() {
	   return new StringBuilder().append(firstName).append(" ")
			.append(lastName).append(", email: ")
			.append(email).append(", car: ").append(car).toString();
   }
}
