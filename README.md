# Pizzeria "oh-Cheese!!"

This is a pizzeria Java application, the theme of this project is pretty simple - application that allows customers to view, order pizzas and Employees to see and respond to the orders. Inspiration for this project came from various pizza ordering websites like Dominos, Pizza-Hut, pizza-Portal and many more.

## Authors

* **Darek**  - [DaRoTP](https://github.com/DaRoTP)

## What can it do ?
* **ACCESSIBLE FOR "ALL USERS"** :
  * **Login** - each user *(that has an account in database)* can Login by typing in their login information and choosing a correct mode (Customer | Employee | Admin).
  * **Sign Up** - fill out the sign up form to register a new *Customer* account.
  * **Log Out** - once loged in, a user has a privilege to log out of their account.
  * **Account Settings** - All users that have an account in the database can change their profile information (eg. Name, Surname, Phone Num., Address ....).
  
* **ACCESSIBLE FOR "CUSTOMERS"** :
  * **Add pizzas to order** - Customers may click on pizza of their choice, choose size and quantity, and add pizza to order. This action can be repeated multiple times.
  * **Redeem Promo Code** - when clicked on checkout a new window appears with confirmation of the order. Customer can also type in a discount code (if he/she has one) to reduce the price of the order.
  * **View Order Status** - after completing an order customer can view Order status, it will be represented by 3 stages *Accepted*, *Baking*, *Sent to deliver*.
  
* **ACCESSIBLE FOR "EMPLOYEES"** :
  * **Manage pizzas** - Edit, add or remove pizzas.
  * **Manage toppings** - Edit, add or remove toppings.
  * **Manage size** - Edit, add or remove sizes.
  * **Manage promo-codes** - Add or remove a promo-codes.
  * **Respond to an Order** - Once customer makes an Order, Employee sees it in their interface. employee can see all needen information like Address name, surname and phone number of the customer and most important Order detail. Then Employee can accept the order, assign a delivery person to the order and update order status.
  
* **ACCESSIBLE FOR "ADMINS"** :
  * **ALL EMPLOYEE PRIVILEGES ARE ACCESIBLE FOR ADMIN**
  * **Manage employees** - Edit, add or remove employees.
  * **Manage customers** - Edit, add or remove cistomers.
  * **Manage job positions** - Edit, add or remove job positions.
  * **Manage addresses** - Edit, add or remove addresses.
  
## Database schema
![ERD](https://i.imgur.com/gxaI6uI.png "ERD")

## Bugs
* @ManyToMany relationship in Java (hibernate) does not work properly
* TBD (to be discovered)

## Tools used to build this project

* JavaFX Scene Builder 8.5.0
* Java Version 8 (build 1.8.0_201-b09)
* Hibernate
* Maven
* MySQL
