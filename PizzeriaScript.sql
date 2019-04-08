CREATE TABLE Adress
(
	Adress_ID INT	IDENTITY	PRIMARY KEY	NOT NULL,
	City VARCHAR(100)	NOT NULL,
	Street VARCHAR(100)	NOT NULL,
	House_Number VARCHAR(10)	NOT NULL,
	Flat_Number	VARCHAR(10),
	ZIP_Code VARCHAR(7)	NOT NULL
);

CREATE TABLE Position
(
	Position_ID	INT IDENTITY	NOT NULL PRIMARY KEY,
	Position_Name	VARCHAR(100)	NOT NULL,
	Position_Description	VARCHAR(200)	NOT NULL
);

CREATE TABLE Klient
(
	Klient_ID	INT	IDENTITY	NOT NULL	PRIMARY KEY,
	Klient_Name	VARCHAR(50)	NOT NULL,
	Klient_Surname	VARCHAR(50)	NOT NULL,
	Klient_Phone_Number VARCHAR(10)	NOT NULL,
	Klient_Email	VARCHAR(50)	NOT NULL,

	Adress_ID	INT NOT NULL	REFERENCES Adress(Adress_ID)
);

CREATE TABLE Employee
(
	Employee_ID	INT	IDENTITY	NOT NULL	PRIMARY KEY,
	Employee_Name	VARCHAR(50)	NOT NULL,
	Employee_Surname	VARCHAR(50)	NOT NULL,
	Employee_Date_Of_Birth	DATE	NOT NULL,
	Employee_Phone_Number VARCHAR(10) NOT NULL,
	Employee_Email	VARCHAR(50)	NOT NULL,
	Employee_PESEL	INT	NOT NULL,
	Employee_Salary	MONEY	NOT NULL,

	Adress_ID	INT NOT NULL	REFERENCES Adress(Adress_ID),
	Position_ID	INT	NOT NULL	REFERENCES Position(Position_ID)
);

CREATE TABLE Pizza
(
	Pizza_ID	INT	NOT NULL	PRIMARY KEY,
	Pizza_Name	VARCHAR(100)	NOT NULL
);

CREATE TABLE Pizza_Type
(
	Pizza_Type_ID	INT	IDENTITY	NOT NULL	PRIMARY KEY,
	Pizza_Type	VARCHAR(100)	NOT NULL
);

CREATE TABLE PizzaToType
(
	Pizza_ID	INT NOT NULL REFERENCES Pizza(Pizza_ID),
	Pizza_Type_ID	INT	NOT NULL	REFERENCES Pizza_Type(Pizza_Type_ID),

	PRIMARY KEY(Pizza_ID,Pizza_Type_ID)
);

CREATE TABLE Toppings
(
	Topping_ID	INT IDENTITY	NOT NULL	PRIMARY KEY,
	Topping_Name	VARCHAR(100)	NOT NULL,
	Topping_Price	MONEY	NOT NULL
);

CREATE TABLE Size
(
	Size_ID	INT	NOT NULL	PRIMARY KEY,
	Size	FLOAT	NOT NULL,
	Price	MONEY	NOT NULL,
);

CREATE TABLE PizzaToppings
(
	Pizza_ID	INT	NOT NULL	REFERENCES Pizza(Pizza_ID),
	Topping_ID	INT IDENTITY	NOT NULL	REFERENCES Toppings(Topping_ID)

	PRIMARY KEY(Pizza_ID, Topping_ID)
);

CREATE TABLE Pizza_Order
(
	Order_ID	INT	IDENTITY	NOT NULL	PRIMARY KEY,

	Pizza_ID	INT	NOT NULL	REFERENCES Pizza(Pizza_ID),
	Size_ID	INT	NOT NULL	REFERENCES Size(Size_ID),
	Klient_ID	INT	NOT NULL	REFERENCES Klient(Klient_ID)
);

CREATE TABLE Pizza_Order_Employee
(
	Order_ID	INT	NOT NULL	REFERENCES Pizza_Order(Order_ID),
	Employee_ID	INT NOT NULL	REFERENCES Employee(Employee_ID),

	PRIMARY KEY(Order_ID, Employee_ID)
);
 DROP TABLE Pizza_Order_Employee, Pizza_Order, PizzaToppings, Size, Toppings, PizzaToType, Pizza_type, Pizza, Employee, Position, Klient, Adress




INSERT INTO Adress(City, Street, House_Number, Flat_Number, ZIP_Code) VALUES('Gdansk', 'Polanki', '14', '35', '85-6589')
INSERT INTO Adress(City, Street, House_Number, Flat_Number, ZIP_Code) VALUES('Gdansk', 'Polanki', '14', '35', '85-6589')
INSERT INTO Adress(City, Street, House_Number, Flat_Number, ZIP_Code) VALUES('Gdansk', 'Polanki', '14', '35', '85-6589')
INSERT INTO Adress(City, Street, House_Number, Flat_Number, ZIP_Code) VALUES('Gdansk', 'Polanki', '14', '35', '85-6589')

INSERT INTO Position(Position_Name, Position_Description) VALUES('Cook','Bakes Pizzas')
INSERT INTO Position(Position_Name, Position_Description) VALUES('Cook','Bakes Pizzas')
INSERT INTO Position(Position_Name, Position_Description) VALUES('Cook','Bakes Pizzas')


INSERT INTO Klient(Klient_Name,Klient_Surname,Klient_Phone_Number,Klient_Email, Adress_ID) VALUES('Dariusz','Rodzewicz','891214045','Der.Rodz@Gmail.com',1)