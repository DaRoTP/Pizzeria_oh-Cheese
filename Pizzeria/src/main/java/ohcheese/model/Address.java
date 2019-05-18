package ohcheese.model;

import javax.persistence.*;

@Entity
@Table(name = "Address")
public class Address {
    @Id
    @Column(name = "Address_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "City")
    private String city;
    @Column(name = "Street")
    private String street;
    @Column(name = "House_Number")
    private String house_Number;
    @Column(name = "Apartment_Number")
    private String apartment_Number;
    @Column(name = "ZIP_Code")
    private String ZIP_Code;

    public Address() { }

    public Address(String city, String street, String house_Number, String apartment_Number, String ZIP_Code) {
        this.city = city;
        this.street = street;
        this.house_Number = house_Number;
        this.apartment_Number = apartment_Number;
        this.ZIP_Code = ZIP_Code;
    }


    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }

    public String getHouse_Number() { return house_Number; }
    public void setHouse_Number(String house_Number) { this.house_Number = house_Number; }

    public String getApartment_Number() { return apartment_Number; }
    public void setApartment_Number(String apartment_Number) { this.apartment_Number = apartment_Number; }

    public String getZIP_Code() { return ZIP_Code; }
    public void setZIP_Code(String ZIP_Code) { this.ZIP_Code = ZIP_Code; }


}
