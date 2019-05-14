CREATE TABLE Address
(
    ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY	NOT NULL,
	City VARCHAR(100)	NOT NULL,
	Street VARCHAR(100)	NOT NULL,
	House_Number INT	NOT NULL,
	Apartment_Number	INT,
	ZIP_Code	VARCHAR(7)	NOT NULL
);


CREATE TABLE Job_Position
(
	ID	INT AUTO_INCREMENT	NOT NULL PRIMARY KEY,
	Position_Name	VARCHAR(100)	NOT NULL
);


CREATE TABLE Customer
(
	ID	INT	AUTO_INCREMENT	NOT NULL	PRIMARY KEY,
	Customer_Name	VARCHAR(50)	NOT NULL,
	Customer_Surname	VARCHAR(50)	NOT NULL,
	Customer_Phone_Number VARCHAR(10)	NOT NULL,
	Customer_Email	VARCHAR(50)	NOT NULL,
    
    Customer_Username	VARCHAR(50)	NOT NULL,
    Customer_Password	VARCHAR(50)	NOT NULL,

	Address_ID	INT NOT NULL	REFERENCES Address(ID)
);

CREATE TABLE Employee
(
	ID	INT	AUTO_INCREMENT	NOT NULL	PRIMARY KEY,
	Employee_Name	VARCHAR(50)	NOT NULL,
	Employee_Surname	VARCHAR(50)	NOT NULL,
	Employee_Date_Of_Birth	DATE	NOT NULL,
	Employee_Phone_Number VARCHAR(10) NOT NULL,
	Employee_Email	VARCHAR(50)	NOT NULL,
	Employee_PESEL	VARCHAR(11)	NOT NULL,
	Employee_Salary	DECIMAL	NOT NULL,
    
	Employee_Username	VARCHAR(50)	NOT NULL,
    Employee_Password	VARCHAR(50)	NOT NULL,

	Address_ID	INT NOT NULL	REFERENCES Address(ID),
	Position_ID	INT	NOT NULL	REFERENCES Job_Position(ID)
);


CREATE TABLE Pizza
(
	ID	INT	AUTO_INCREMENT	NOT NULL	PRIMARY KEY,
	Pizza_Name	VARCHAR(100)	NOT NULL
);


CREATE TABLE Pizza_Type
(
	ID	INT	AUTO_INCREMENT	NOT NULL	PRIMARY KEY,
	Pizza_Type	VARCHAR(100)	NOT NULL
);

CREATE TABLE PizzaToType
(
	Pizza_ID	INT NOT NULL REFERENCES Pizza(ID),
	Pizza_Type_ID	INT	NOT NULL	REFERENCES Pizza_Type(ID),

	PRIMARY KEY(Pizza_ID,Pizza_Type_ID)
);

CREATE TABLE Toppings
(
	ID	INT AUTO_INCREMENT	NOT NULL	PRIMARY KEY,
	Topping_Name	VARCHAR(100)	NOT NULL
);

CREATE TABLE PizzaToppings
(
	Pizza_ID	INT	NOT NULL	REFERENCES Pizza(ID),
	Topping_ID	INT	NOT NULL	REFERENCES Toppings(ID),

	PRIMARY KEY(Pizza_ID, Topping_ID)
);

CREATE TABLE Size
(
	ID	INT AUTO_INCREMENT	NOT NULL	PRIMARY KEY,
	Size	VARCHAR(10)	NOT NULL,
	Price	DECIMAL	NOT NULL
);

CREATE TABLE Promo_Codes
(
	ID	INT AUTO_INCREMENT	NOT NULL	PRIMARY KEY,
    Promo_Code	VARCHAR(50)	NOT NULL,
    Percent_Off INT NOT NULL
);

CREATE TABLE Order_status
(
	ID	INT AUTO_INCREMENT	NOT NULL	PRIMARY KEY,
    Order_status	VARCHAR(50)	NOT NULL
);

CREATE TABLE Shopping_Cart
(
	ID	INT AUTO_INCREMENT	NOT NULL	PRIMARY KEY,
    
    Customer_ID	INT	NOT NULL	REFERENCES Customer(ID),
    Address INT NOT NULL	REFERENCES Address(ID),
    Promo_Code	INT	REFERENCES Promo_Codes(ID),
    Order_status	INT	NOT NULL	REFERENCES Order_status(ID)
);

CREATE TABLE Pizza_Order
(
	Order_ID	INT	AUTO_INCREMENT	NOT NULL	PRIMARY KEY,
	
	Pizza_ID	INT	NOT NULL	REFERENCES Pizza(ID),
	Size_ID	INT	NOT NULL	REFERENCES Size(ID),
    Shopping_Cart_ID	INT	NOT NULL	REFERENCES Shopping_Cart(ID)
);

CREATE TABLE Shopping_Cart_Employee
(
	Shopping_Cart_ID	INT	NOT NULL	REFERENCES Shopping_Cart(ID),
	Employee_ID	INT NOT NULL	REFERENCES Employee(Employee_ID),

	PRIMARY KEY(Shopping_Cart_ID, Employee_ID)
);
 