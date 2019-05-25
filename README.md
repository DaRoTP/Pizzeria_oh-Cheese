# Pizzeia "oh-Cheese!!"

This is a pizzeria Java application, the theme of this project is pretty simple - application that allows customers to view, order pizzas and Employees to see and respond to the orders. Inspiration for this project came from various pizza ordering websites like Dominos, Pizza-Hut, pizza-Portal and many more.

## Authors

* **Darek**  - [DaRoTP](https://github.com/DaRoTP)

## What can it do ?
* **Modes** - Has three modes **Customer**, **Employee**, **Admin** and each has different actions that others can not perform
* **Login** - First you are greated by **Login Screen** there you can type in your *username* and *password* (if you have and account in database) and choose who do you want to log in as (Customers and only log in as Customers, Employees only as Employees and Admins as Admins and Employees)
* **Sign In** - if you don't have an account you can click sign in, fill out sign in form and create an account (a customer account).
* **logout** - each user once loged in can log out of the account and will be returned to the main Login screen.
* **edit profile** - each user can edit their profile to do so they have to click on the cog icon on the right top corner, then new window will be opened and they can edit their profile information.
* **Customer** - when you log in as *Customer* you can view pizzas that are available in the database and order the.
  * **ordering pizzas** - to do so you have to click on the grey buttoon of your chosen pizza and choose size and quantity on the top of the table, once you have chosen everything click **+** button on the right and pizzas will be added to the order, you can repeat this action multiple times.
  * **checkout** - once you picked your pizzas, you click check out button, a new window will apear with your Order, if you don't like what you've orderd you can close the window and edit your order, but if you are happy with what you've picked you can click **submit** button and an Order will be made.
  * **redeem promo code** - before clicking submit in checkout you also have the option to redeem a promo code (if you have it). Once redeemed it will change your final price and then you can click **submit** and confrm your order.
 * **View status** - after completing checkout a button on the right top corner will apear that once clicked will transition in to a new scene where you can view your Order status *(Accepted, baking, Sent to deliver)*.
* **Employee** -
## Bugs
* @ManyToMany relationship in Java (hibernate) does not work properly
* TBD (to be discovered)

## Tools used to build this 

* JavaFX Scene Builder 8.5.0
* Java Version 8 (build 1.8.0_201-b09)
* Hibernate
* Maven
* MySQL
