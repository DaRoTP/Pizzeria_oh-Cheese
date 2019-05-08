package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Order;
import model.PizzaInfo;


public class CustomerControl extends GeneralWindowControl implements Initializable {

    private int largePizzaUnit = 0;
    private int mediumPizzaUnit = 0;
    private int smallPizzaUnit = 0;
    private int pizzaUnitsOrdered = 0;
    private int finalPrice = 0;
    private int chosenPizzaIndex = 0;

    private Order newOrder;

    private int amountOfPizzas = 9;
//    private PizzaInfo[] pizzas = new PizzaInfo[amountOfPizzas];
    private ArrayList<PizzaInfo> pizzas = new ArrayList<PizzaInfo>();
//    private Button[] picaBtnList = new Button[amountOfPizzas];
//    private boolean[] isButtonClicked = new boolean[amountOfPizzas];
    private ArrayList<Button> picaBtnList = new ArrayList<Button>();
    private ArrayList<Integer> isButtonClicked = new ArrayList<Integer>();


    private Button[] removePicaBtn = new Button[100];
    private HBox[] orderTab = new HBox[100];

    @FXML CheckBox newadress = new CheckBox();
    @FXML Label largePizzaLabel = new Label();
    @FXML Label mediumPizzaLabel = new Label();
    @FXML Label smallPizzaLabel = new Label();
    @FXML Label pizzacounterLabel = new Label();
    @FXML Label finalPriceLabel = new Label();

    @FXML VBox PizzaContent = new VBox();
    @FXML VBox OrderContent = new VBox();

    @FXML AnchorPane pizzaanchor = new AnchorPane();
    @FXML AnchorPane orderanchor = new AnchorPane();


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

// //       pizzas[0] = new PizzaInfo(0,"0","descr");
////        pizzas[1] = new PizzaInfo(1,"1","descr");
////        pizzas[2] = new PizzaInfo(2,"2","descr");
////        pizzas[3] = new PizzaInfo(3,"3","descr");
////        pizzas[4] = new PizzaInfo(4,"4","descr");
////        pizzas[5] = new PizzaInfo(5,"5","descr");
////        pizzas[6] = new PizzaInfo(6,"6","descr");
////        pizzas[7] = new PizzaInfo(7,"7","descr");
////        pizzas[8] = new PizzaInfo(8,"8","descr");
        pizzas.add(new PizzaInfo(0,"0","descr"));
        pizzas.add(new PizzaInfo(1,"1","descr"));
        pizzas.add(new PizzaInfo(2,"2","descr"));
        pizzas.add(new PizzaInfo(3,"3","descr"));
        pizzas.add(new PizzaInfo(4,"4","descr"));
        pizzas.add(new PizzaInfo(5,"5","descr"));
        pizzas.add(new PizzaInfo(6,"6","descr"));
        pizzas.add(new PizzaInfo(7,"7","descr"));
        pizzas.add(new PizzaInfo(8,"8","descr"));


        createPizzaTile(pizzas, amountOfPizzas);
        newOrder = new Order(1);
    }

    public void test(Order some){
        System.out.println("Customer ID: "+some.getCustumerID());
        System.out.println("Final Price: "+some.getFinalPrice());
        System.out.println("Method: "+some.getPaymentMethod());
        System.out.println("------ Pizzas ----");
        for(int i = 0; i < some.getPizzasIDs().size(); i++){
            System.out.println("- ID: "+some.getPizzasIDs().get(i)+" -size: "+some.getSizeIDs().get(i));
        }
    }

    public void removePizzaFromOrder(int OrderIndex){
        OrderContent.getChildren().remove(orderTab[OrderIndex]);
        //Shrink anchor pane and Vbox
        orderanchor.setPrefHeight(orderanchor.getPrefHeight() - 55);
        OrderContent.setPrefHeight(OrderContent.getPrefHeight() - 55);
        //change price
        newOrder.subFrom_FinalPrice(newOrder.getSizeIDs().get(OrderIndex));
        newOrder.setFinalPrice(newOrder.getFinalPrice() - 1);
        //change units bought
        pizzaUnitsOrdered -= 1;
        //Update Labels
        pizzacounterLabel.setText(String.valueOf(pizzaUnitsOrdered));
        finalPriceLabel.setText(newOrder.getFinalPrice()+" zlt");

    }

    public void addPizzaToOrder(PizzaInfo chosenPizza, String pizzaPrice, String pizzaSize, int OrderIndex){
        orderanchor.setPrefHeight(orderanchor.getPrefHeight() + 55);
        OrderContent.setPrefHeight(OrderContent.getPrefHeight() + 55);

        orderTab[OrderIndex] = new HBox();
        orderTab[OrderIndex].setPrefSize(354, 20);

        TextArea information_About_Chosen_Pizza = new TextArea();
        information_About_Chosen_Pizza.setPrefSize(300, 20);
        information_About_Chosen_Pizza.setText(chosenPizza.getPizzaname()+" -  | "+pizzaSize+"cm | "+pizzaPrice+" zlt");

        removePicaBtn[OrderIndex] = new Button("X");
        removePicaBtn[OrderIndex].setPrefSize(40, 20);
        removePicaBtn[OrderIndex].getStyleClass().add("removebtn");

        removePicaBtn[OrderIndex].setOnAction(
                event -> {removePizzaFromOrder(OrderIndex);
                    newOrder.removedFromOrdered(OrderIndex);
                });

        orderTab[OrderIndex].getChildren().addAll(information_About_Chosen_Pizza,removePicaBtn[OrderIndex]);
        orderTab[OrderIndex].setAlignment(Pos.CENTER);
        orderTab[OrderIndex].setSpacing(10);

        OrderContent.getChildren().add(orderTab[OrderIndex]);

//        newOrder.setPizzaAndSize(chosenPizza.getPizzaID(),pizzaSize,OrderIndex);
        newOrder.setPizzaAndSize(chosenPizza.getPizzaID(),pizzaSize);

    }

    public void createPizzaTile(ArrayList pizzas, int amountOfPizzas){
        for(int i = 0; i < amountOfPizzas; i++) {
            pizzaanchor.setPrefHeight(pizzaanchor.getPrefHeight() + 80);
            PizzaContent.setPrefHeight(PizzaContent.getPrefHeight() + 80);

            HBox Pizzatab = new HBox();
            Pizzatab.setPrefSize(589, 70);


            TextArea Description = new TextArea();
            Description.setPrefSize(500, 70);
            Description.setText(((PizzaInfo) pizzas.get(i)).getPizzaname()+"\n__________________\n"+((PizzaInfo) pizzas.get(i)).getDescriptioon());

            //            Description.setDisable(true);
            Description.setEditable(false);

            Image pica = new Image("view/pizzainfo/pizzaPhotos/"+((PizzaInfo) pizzas.get(i)).getPizzaname()+".png");
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
        //openscene(event,"checkout","GeneralWindowStyle","checkout","Global_Resources");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/checkout/checkout.fxml"));
        Parent root = loader.load();

        CheckoutControl checkoutc = loader.getController();
        checkoutc.getPrice((int) newOrder.getFinalPrice());

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        root.getStylesheets().add("/view/Global_Resources/GeneralWindowStyle.css");
        if(newadress.isSelected()){
            openscene(event, "Adress","GeneralWindowStyle","Adress","Global_Resources");
        }
        test(newOrder);


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
        newOrder.setFinalPrice(newOrder.getFinalPrice() + 40 * largePizzaUnit + 30 * mediumPizzaUnit + 25 * smallPizzaUnit);
        finalPriceLabel.setText(newOrder.getFinalPrice()+" zlt");

        //adds a tile to Order Tab
        try {
            for (int i = 0; i < smallPizzaUnit; i++) {
                addPizzaToOrder(pizzas.get(chosenPizzaIndex), "25", "25", pizzaUnitsOrdered);
                pizzaUnitsOrdered++;
            }
            for (int i = 0; i < mediumPizzaUnit; i++) {
                addPizzaToOrder(pizzas.get(chosenPizzaIndex), "30", "30", pizzaUnitsOrdered);
                pizzaUnitsOrdered++;
            }
            for (int i = 0; i < largePizzaUnit; i++) {
                addPizzaToOrder(pizzas.get(chosenPizzaIndex), "40", "35", pizzaUnitsOrdered);
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
