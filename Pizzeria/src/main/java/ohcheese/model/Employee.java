package ohcheese.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Employee")
public class Employee {
    @Id
    @Column(name = "Employee_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "Employee_Name")
    private String name;
    @Column(name = "Employee_Surname")
    private String surname;
    @Column(name = "Employee_Date_Of_Birth")
    private String date_Of_Birth;
    @Column(name = "Employee_Phone_Number")
    private String phone_Number;
    @Column(name = "Employee_Email")
    private String email;
    @Column(name = "Employee_PESEL")
    private String PESEL;
    @Column(name = "Employee_Salary")
    private float salary;
    @Column(name = "Employee_Username")
    private String username;
    @Column(name = "Employee_Password")
    private String password;
    @ManyToOne
    @JoinColumn(name = "Address_ID")
    private Address address_ID;
    @ManyToOne
    @JoinColumn(name = "Job_Position_ID")
    private Job_Position position_ID;
    @ManyToMany
    private Set<Shopping_Cart> shopping_cart = new HashSet<>();

    public Employee() { }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }

    public String getDate_Of_Birth() { return date_Of_Birth; }
    public void setDate_Of_Birth(String date_Of_Birth) { this.date_Of_Birth = date_Of_Birth; }

    public String getPhone_Number() { return phone_Number; }
    public void setPhone_Number(String phone_Number) { this.phone_Number = phone_Number; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPESEL() { return PESEL; }
    public void setPESEL(String PESEL) { this.PESEL = PESEL; }

    public float getSalary() { return salary; }
    public void setSalary(float salary) { this.salary = salary; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Address getAddress_ID() { return address_ID; }
    public void setAddress_ID(Address address_ID) { this.address_ID = address_ID; }

    public Job_Position getPosition_ID() { return position_ID; }
    public void setPosition_ID(Job_Position position_ID) { this.position_ID = position_ID; }

    public Set<Shopping_Cart> getShopping_cart() { return shopping_cart; }
    public void setShopping_cart(Set<Shopping_Cart> shopping_cart) { this.shopping_cart = shopping_cart; }
}
