package ohcheese.controller.customerUntilities;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import ohcheese.Utilities.HibernateUtil;
import ohcheese.controller.GeneralWindowControl;
import ohcheese.controller.LoginControl;
import ohcheese.model.*;
import ohcheese.model.placeholders.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;


public class CustomerControl extends GeneralWindowControl implements Initializable {


    private int small_price_zl = 0;
    private int large_price_zl = 0;
    private int medium_price_zl = 0;
    private SessionFactory factory = HibernateUtil.getSessionFactory();

    private int largePizzaUnit = 0;
    private int mediumPizzaUnit = 0;
    private int smallPizzaUnit = 0;
    private int pizzaUnitsOrdered = 0;
    private int chosenPizzaIndex = 0;

    private Order newOrder;

    public List<Pizza> pizza_from_DataBase;

    private int addedPizzasCounter = 0;

    private ArrayList<Button> picaBtnList = new ArrayList<Button>();
    private ArrayList<Integer> isButtonClicked = new ArrayList<Integer>();
    private ArrayList<HBox> orderTab = new ArrayList<HBox>();
    private ArrayList<Integer> counterTab = new ArrayList<Integer>();


    @FXML Label largePizzaLabel = new Label();
    @FXML Label mediumPizzaLabel = new Label();
    @FXML Label smallPizzaLabel = new Label();
    @FXML Label pizzacounterLabel = new Label();
    @FXML Label finalPriceLabel = new Label();
    @FXML Label WelcomeUser = new Label();

    @FXML Label small_price = new Label();
    @FXML Label medium_price = new Label();
    @FXML Label large_price = new Label();

    @FXML VBox PizzaContent = new VBox();
    @FXML VBox OrderContent = new VBox();

    @FXML AnchorPane pizzaanchor = new AnchorPane();
    @FXML AnchorPane orderanchor = new AnchorPane();

    @FXML Button ViewStatus = new Button();
    @FXML Button CheckoutBtn = new Button();


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        WelcomeUser.setText("Welcome "+ LoginControl.get_loggedinCustomer().getName()+" !");
        set_pricing();
        get_PizzaFromDB();

        newOrder = new Order(small_price_zl,medium_price_zl,large_price_zl);
        createPizzaTile();
        ViewStatus.setDisable(false);
        CheckoutBtn.setDisable(true);

    }

    public void set_pricing(){
        Session session = factory.getCurrentSession();
        try {
            session.getTransaction().begin();

            Query query = session.createQuery("from Size");
            List<Size> prices = query.list();

            small_price.setText(prices.get(0).getPrice()+" zł");
            small_price_zl = Integer.parseInt(prices.get(0).getPrice());
            medium_price.setText(prices.get(1).getPrice()+" zł");
            medium_price_zl = Integer.parseInt(prices.get(1).getPrice());
            large_price.setText(prices.get(2).getPrice()+" zł");
            large_price_zl = Integer.parseInt(prices.get(2).getPrice());

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
    }

    public void get_PizzaFromDB(){
        Session session = factory.getCurrentSession();
        try {
            session.getTransaction().begin();

            Query query = session.createQuery("from Pizza");
            pizza_from_DataBase = query.list();

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
    }

    public void removePizzaFromOrder(int OrderIndex){
        OrderContent.getChildren().remove(OrderIndex);
        orderTab.remove(OrderIndex);
        counterTab.remove(OrderIndex);
        //Shrink anchor pane and Vbox
        orderanchor.setPrefHeight(orderanchor.getPrefHeight() - 55);
        OrderContent.setPrefHeight(OrderContent.getPrefHeight() - 55);
        //change price
        newOrder.subFrom_FinalPrice(newOrder.getSizeIDs().get(OrderIndex));
        //remove pizzas from order
        newOrder.removedFromOrdered(OrderIndex);
        //change units bought
        pizzaUnitsOrdered -= 1;
        //Update Labels
        pizzacounterLabel.setText(String.valueOf(pizzaUnitsOrdered));
        finalPriceLabel.setText(((int)newOrder.getFinalPrice())+" zł");

        if(newOrder.getFinalPrice() != 0){
            ViewStatus.setDisable(true);
            CheckoutBtn.setDisable(false);
        }
        else {
            ViewStatus.setDisable(true);
            CheckoutBtn.setDisable(true);
        }

    } //*-------------------

    public void addPizzaToOrder(Pizza chosenPizza, String pizzaPrice, String pizzaSize){
        orderanchor.setPrefHeight(orderanchor.getPrefHeight() + 59);
        OrderContent.setPrefHeight(OrderContent.getPrefHeight() + 59);

        orderTab.add(new HBox());
        counterTab.add(addedPizzasCounter);

        orderTab.get(orderTab.size()-1).setPrefSize(354, 40);

        Label information_About_Chosen_Pizza = new Label();
        information_About_Chosen_Pizza.getStyleClass().add("added_pizza");
        information_About_Chosen_Pizza.setPrefSize(280, 50);
        information_About_Chosen_Pizza.setText(chosenPizza.getPizza_Name()+" -  | "+pizzaSize+" | "+pizzaPrice+" zlt");

        Button removePicaBtn = new Button("X");
        removePicaBtn.setPrefSize(35, 20);
        removePicaBtn.getStyleClass().add("removebtn");

        int finalI = addedPizzasCounter;

        removePicaBtn.setOnAction(
                event -> {
                    try {
                        removePizzaFromOrder(counterTab.indexOf(finalI));
                    }
                    catch(IndexOutOfBoundsException e){
                        System.out.println("Blad");
                    }
                });

        orderTab.get(orderTab.size()-1).getChildren().addAll(information_About_Chosen_Pizza,removePicaBtn);
        orderTab.get(orderTab.size()-1).setAlignment(Pos.CENTER);
        orderTab.get(orderTab.size()-1).setSpacing(10);

        OrderContent.getChildren().add(orderTab.get(orderTab.size()-1));

        newOrder.setPizzaAndSize(chosenPizza.getId(),pizzaSize);

        if(newOrder.getFinalPrice() != 0){
            ViewStatus.setDisable(true);
            CheckoutBtn.setDisable(false);
        }
        else {
            ViewStatus.setDisable(true);
            CheckoutBtn.setDisable(true);
        }

        addedPizzasCounter ++;
    }//*-------------------

    public void createPizzaTile(){
        for(int i = 0; i < pizza_from_DataBase.size(); i++) {
            pizzaanchor.setPrefHeight(pizzaanchor.getPrefHeight() + 80);
            PizzaContent.setPrefHeight(PizzaContent.getPrefHeight() + 80);

            HBox Pizzatab = new HBox();
            Pizzatab.setPrefSize(750, 70);
            Session session = factory.getCurrentSession();
            session.getTransaction().begin();


            TextArea Description = new TextArea();
            Description.setPrefSize(620, 70);
            Description.setEditable(false);
            String temp_description ="● "+ pizza_from_DataBase.get(i).getPizza_Name();
            temp_description += "\n type: "+ pizza_from_DataBase.get(i).getPizza_Type_ID().getPizza_Type()+"\n";
            for(Toppings top : pizza_from_DataBase.get(i).getToppings()){
                temp_description +=  top.getTopping_Name() +", ";
            }


            Description.setText(temp_description);

            session.close();
            Image pica;
            try{
                pica = new Image(pizza_from_DataBase.get(i).getPizza_image());
            }
            catch(Exception e){
                pica = new Image("view/Global_Resources/Logo.png");
            }
            ImageView Picaphoto = new ImageView();
            Picaphoto.setFitHeight(70);
            Picaphoto.setFitWidth(70);
            Picaphoto.setImage(pica);

            picaBtnList.add(new Button("●"));
            picaBtnList.get(i).setPrefSize(50, 50);
            picaBtnList.get(i).setPrefSize(50, 50);

            isButtonClicked.add(1);

            int finalI = i;
            picaBtnList.get(i).setOnAction(
                    event -> {
                        //if button clicked
                        if (isButtonClicked.get(finalI) == 1) {
                            picaBtnList.get(finalI).getStyleClass().add("addbtn");
                            isButtonClicked.set(finalI, 0);

                            for (int j = 0; j < picaBtnList.size(); j++)
                                if (j != finalI) {
                                    picaBtnList.get(j).getStyleClass().remove("addbtn");
                                    isButtonClicked.set(j, 1);
                                }

                            //set chosen pizza index
                            chosenPizzaIndex = finalI;
                        }
                        //if button unclicked
                        else {
                            picaBtnList.get(finalI).getStyleClass().remove("addbtn");
                            isButtonClicked.set(finalI, 1);
                        }

                    });

            Pizzatab.getChildren().addAll(Picaphoto, Description, picaBtnList.get(i));
            Pizzatab.setAlignment(Pos.CENTER);
            Pizzatab.setSpacing(10);
            PizzaContent.getChildren().add(Pizzatab);
        }
    }

    public void Checkout(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Customer/checkout/checkout.fxml"));
        Parent root = loader.load();

        CheckoutControl checkoutc = loader.getController();
        checkoutc.getOrder(newOrder);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        root.getStylesheets().add("/view/Global_Resources/GeneralWindowStyle.css");
        Image logo_icon = new Image("/view/Global_Resources/Logo.png");
        stage.getIcons().add(logo_icon);
        stage.setTitle("Pizzeria - oh Cheese!");

        ViewStatus.setDisable(false);
        CheckoutBtn.setDisable(true);
    }

    //VIEW STATUS
    public void viewStatus(ActionEvent event) throws IOException {
        changeScene(event,"view_status","view_Status","Customer/view_status","Customer/view_status");

    }
    //LARGE PIZZA
    public void addLargePizza(ActionEvent event) throws IOException {
        largePizzaUnit += 1;
        largePizzaLabel.setText(Integer.toString(largePizzaUnit));
    }
    public void removeLargePizza(ActionEvent event) throws IOException {
        largePizzaUnit -= 1;
        if(largePizzaUnit == -1)
            largePizzaUnit = 0;
        largePizzaLabel.setText(Integer.toString(largePizzaUnit));
    }
    //MEDIUM PIZZA
    public void addMediumPizza(ActionEvent event) throws IOException {
        mediumPizzaUnit += 1;
        mediumPizzaLabel.setText(Integer.toString(mediumPizzaUnit));
    }
    public void removeMediumPizza(ActionEvent event) throws IOException {
        mediumPizzaUnit -= 1;
        if(mediumPizzaUnit == -1)
            mediumPizzaUnit = 0;
        mediumPizzaLabel.setText(Integer.toString(mediumPizzaUnit));
    }
    //SMALL PIZZA
    public void addSmallPizza(ActionEvent event) throws IOException {
        smallPizzaUnit += 1;
        smallPizzaLabel.setText(Integer.toString(smallPizzaUnit));
    }
    public void removeSmallPizza(ActionEvent event) throws IOException {
        smallPizzaUnit -= 1;
        if(smallPizzaUnit == -1)
            smallPizzaUnit = 0;
        smallPizzaLabel.setText(Integer.toString(smallPizzaUnit));
    }


    public void addToOrder(ActionEvent event) throws IOException {
        //Sum everything
        newOrder.setFinalPrice(newOrder.getFinalPrice() + large_price_zl * largePizzaUnit + medium_price_zl * mediumPizzaUnit + small_price_zl * smallPizzaUnit);
        finalPriceLabel.setText(((int)newOrder.getFinalPrice())+" zł");

        //adds a tile to Order Tab
        try {
            for (int i = 0; i < smallPizzaUnit; i++) {
                addPizzaToOrder(pizza_from_DataBase.get(chosenPizzaIndex), Integer.toString(small_price_zl), "small");
                pizzaUnitsOrdered++;
            }
            for (int i = 0; i < mediumPizzaUnit; i++) {
                addPizzaToOrder(pizza_from_DataBase.get(chosenPizzaIndex), Integer.toString(medium_price_zl), "medium");
                pizzaUnitsOrdered++;
            }
            for (int i = 0; i < largePizzaUnit; i++) {
                addPizzaToOrder(pizza_from_DataBase.get(chosenPizzaIndex), Integer.toString(large_price_zl), "large");
                pizzaUnitsOrdered++;
            }
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            System.out.println("ERROR: OUT OF BOUNDS");
        }

        //add to the counter label
        pizzacounterLabel.setText(Integer.toString(pizzaUnitsOrdered));

        //null everything
        largePizzaUnit = 0;
        mediumPizzaUnit = 0;
        smallPizzaUnit = 0;
        largePizzaLabel.setText("0");
        mediumPizzaLabel.setText("0");
        smallPizzaLabel.setText("0");
        picaBtnList.get(chosenPizzaIndex).fire();
    }


}
