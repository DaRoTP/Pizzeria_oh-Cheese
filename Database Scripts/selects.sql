-- Order info about shopping car 1
select p.Pizza_Name, s.Size, s.Price from Pizza_Order po 
JOIN Pizza p ON p.Pizza_ID = po.Pizza_ID  
JOIN Size s ON s.Size_ID = po.Size_ID where shopping_Cart_ID = 1 ;


-- who was resposible for the order
select e.Employee_Name,e.Employee_Surname,jp.Position_Name from Shopping_Cart_Employee sce 
join Employee e ON e.ID = sce.Employee_ID 
join Job_Position jp ON jp.ID = e.Position_ID
where shopping_Cart_ID = 1 ;

-- LOGIN check if this username and password exist
select ID from Customer where Customer_Username='Deloris34' and Customer_Password='AmADancer33';

-- pizza toppings
select t.Topping_Name from Pizza p JOIN PizzaToppings tp ON p.Pizza_ID = tp.Pizza_ID
JOIN Toppings t ON t.Toppings_ID = tp.Topping_ID 
where p.Pizza_ID = 2 ;


-- TOP 10 Pizzas
select p.Pizza_Name,count(*) from Pizza_Order po
JOIN Pizza p ON p.Pizza_ID = po.Pizza_ID
 group by po.Pizza_ID 
 LIMIT 10;
 
 -- ORDERS -- employee point of view
 select sc.ID,c.Customer_Username,os.Order_status from Shopping_Cart sc
 JOIN Order_Status os ON os.ID = sc.Order_status_ID
 JOIN Customer c ON c.ID = sc.Customer_ID 
 where sc.Order_status_ID != 4;
 
 /*update Shopping_Cart set Order_status_ID=4 where ID=1; */

  -- ORDERS -- Customer point of view
 select p.Pizza_Name,s.Size, count(*) Quantity from Pizza_Order po 
 JOIN Pizza p ON p.ID = po.Pizza_ID
 JOIN Size s On s.ID = po.Size_ID
 GROUP BY p.Pizza_Name,s.Size;
 
 -- EMployee info
 select e.Employee_Name, e.Employee_Surname, e.Employee_Phone_Number,e.Employee_Email, e.Employee_Salary, jp.Position_Name from Employee e
 JOIN job_position jp ON jp.ID = e.Position_ID;
 
 -- employee of the month
 select e.ID,e.Employee_Name,e.Employee_Surname,Count(*) as 'Orders complete' from Shopping_Cart_Employee sce 
 LEFT JOIN Employee e on sce.Employee_ID = e.ID
 group by e.ID,e.Employee_Name,e.Employee_Surname;
 
 
 select * from Pizza;
 select * from toppings;
 