package ohcheese.model;

import javax.persistence.*;

@Entity
@Table(name = "Customer")
public class Customer {
    @Id
    @Column(name = "Customer_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "Customer_Name")
    private String Name;
    @Column(name = "Customer_Surname")
    private String Surname;
    @Column(name = "Customer_Phone_Number")
    private String Phone_Number;
    @Column(name = "Customer_Email")
    private String Email;
    @Column(name = "Customer_Username")
    private String Username;
    @Column(name = "Customer_Password")
    private String Password;
    @ManyToOne
    @JoinColumn(name = "Address_ID")
    private Address Address_ID;

    public Customer() { }

    public Customer(String name, String surname, String phone_Number, String email, String username, String password) {
        Name = name;
        Surname = surname;
        Phone_Number = phone_Number;
        Email = email;
        Username = username;
        Password = password;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return Name; }
    public void setName(String name) { Name = name; }

    public String getSurname() { return Surname; }
    public void setSurname(String surname) { Surname = surname; }

    public String getPhone_Number() { return Phone_Number; }
    public void setPhone_Number(String phone_Number) { Phone_Number = phone_Number; }

    public String getEmail() { return Email; }
    public void setEmail(String email) { Email = email; }

    public String getUsername() { return Username; }
    public void setUsername(String username) { Username = username; }

    public String getPassword() { return Password; }
    public void setPassword(String password) { Password = password; }

    public Address getAddress_ID() { return Address_ID; }
    public void setAddress_ID(Address address_ID) { Address_ID = address_ID; }
}
