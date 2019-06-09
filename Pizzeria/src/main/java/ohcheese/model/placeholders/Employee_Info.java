package ohcheese.model.placeholders;

import javafx.scene.control.Button;
import ohcheese.controller.GeneralWindowControl;
import ohcheese.model.Address;
import ohcheese.model.Job_Position;

import java.io.IOException;

public class Employee_Info extends GeneralWindowControl {

    private  int employee_ID;
    private String name;
    private String surname;
    private String phone_number;
    private String e_mail;
    private String username;
    private String password;
    private Address address;
    private String birthday;
    private String PESEL;
    private float salary;
    private Job_Position job_position;
    private String Position_Name;
    private Button edit_btn;

    public static boolean class_type;
    public static int temp_id;

    public Employee_Info(int employee_ID, String name, String surname, String phone_number, String e_mail, String username,
                         String password, Address address, String birthday, String PESEL, float salary, Job_Position job_position) {
        this.employee_ID = employee_ID;
        this.name = name;
        this.surname = surname;
        this.phone_number = phone_number;
        this.e_mail = e_mail;
        this.username = username;
        this.password = password;
        this.address = address;
        this.birthday = birthday;
        this.PESEL = PESEL;
        this.salary = salary;
        this.job_position = job_position;
        this.Position_Name = this.job_position.getPosition_Name();
        this.class_type = false;
        this.edit_btn = new Button("Edit");
        this.edit_btn.setOnAction(e->{
            this.temp_id = this.employee_ID;
            this.class_type = true;
            try {
                openScene( "edit_employee","GeneralWindowStyle", "Admin/tools","Global_Resources");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        this.edit_btn.getStyleClass().add("edit_btn");
    }

    public int getEmployee_ID() { return employee_ID; }
    public void setEmployee_ID(int employee_ID) { this.employee_ID = employee_ID; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }

    public String getPhone_number() { return phone_number; }
    public void setPhone_number(String phone_number) { this.phone_number = phone_number; }

    public String getE_mail() { return e_mail; }
    public void setE_mail(String e_mail) { this.e_mail = e_mail; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }

    public String getBirthday() { return birthday; }
    public void setBirthday(String birthday) { this.birthday = birthday; }

    public String getPESEL() { return PESEL; }
    public void setPESEL(String PESEL) { this.PESEL = PESEL; }

    public float getSalary() { return salary; }
    public void setSalary(float salary) { this.salary = salary; }

    public Job_Position getJob_position() { return job_position; }
    public void setJob_position(Job_Position job_position) { this.job_position = job_position; }

    public String getPosition_Name() { return Position_Name; }
    public void setPosition_Name(String position_Name) { Position_Name = position_Name; }

    public Button getEdit_btn() { return edit_btn; }
    public void setEdit_btn(Button edit_btn) { this.edit_btn = edit_btn; }

    public static boolean isClass_type() { return class_type; }
    public static void setClass_type(boolean class_type) { Employee_Info.class_type = class_type; }

    public static int getTemp_id() { return temp_id; }
    public static void setTemp_id(int temp_id) { Employee_Info.temp_id = temp_id; }
}
