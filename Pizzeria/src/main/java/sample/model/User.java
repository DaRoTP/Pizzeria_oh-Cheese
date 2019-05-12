package sample.model;


public class User {

    //VARIABLES
    private String name;
    private String surname;
    private String e_mail;
    private String phone_number;
    private String username;
    private String password;
    private String city;
    private String street;
    private String house_number;
    private String apartment_number;
    private String zip_code;
    private String city_temp;
    private String street_temp;
    private String house_number_temp;
    private String apartment_number_temp;
    private String zip_code_temp;

    //GETTERS & SETTERS
        //*Name
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
        //*Surname
    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }
        //*E-Mail
    public String getE_mail() { return e_mail; }
    public void setE_mail(String e_mail) { this.e_mail = e_mail; }
        //*Phone Number
    public String getPhone_number() { return phone_number; }
    public void setPhone_number(String phone_number) { this.phone_number = phone_number; }
        //*Username
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
        //*Password
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
        //*City
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
        //*Street
    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }
        //*House Number
    public String getHouse_number() { return house_number; }
    public void setHouse_number(String house_number) { this.house_number = house_number; }
        //*Apartment Number
    public String getApartment_number() { return apartment_number; }
    public void setApartment_number(String apartment_number) { this.apartment_number = apartment_number; }
        //*ZIP CODE
    public String getZip_code() { return zip_code; }
    public void setZip_code(String zip_code) { this.zip_code = zip_code; }
        //* Temporary City
    public String getCity_temp() { return city_temp; }
    public void setCity_temp(String city_temp) { this.city_temp = city_temp; }
        //*Temporary Street
    public String getStreet_temp() { return street_temp; }
    public void setStreet_temp(String street_temp) { this.street_temp = street_temp; }
        //*Temporary House Number
    public String getHouse_number_temp() { return house_number_temp; }
    public void setHouse_number_temp(String house_number_temp) { this.house_number_temp = house_number_temp; }
        //*Temporary Apartment Number
    public String getApartment_number_temp() { return apartment_number_temp; }
    public void setApartment_number_temp(String apartment_number_temp) { this.apartment_number_temp = apartment_number_temp; }
        //*Temporary ZIP CODE
    public String getZip_code_temp() { return zip_code_temp; }
    public void setZip_code_temp(String zip_code_temp) { this.zip_code_temp = zip_code_temp; }


    //CONSTRUCTOR
    public User(String name, String surname, String e_mail, String phone_number, String username, String password, String city, String street, String house_number, String zip_code,String apartment_number) {
        this.name = name;
        this.surname = surname;
        this.e_mail = e_mail;
        this.phone_number = phone_number;
        this.username = username;
        this.password = password;
        this.city = city;
        this.street = street;
        this.house_number = house_number;
        this.apartment_number = apartment_number;
        this.zip_code = zip_code;
    }

    public void GetTempAdress(String city, String street, String house_number, String zip_code, String apartment_number){
        this.city_temp = city;
        this.street_temp = street;
        this.house_number_temp = house_number;
        this.apartment_number_temp = apartment_number;
        this.zip_code_temp = zip_code;
    }
}
