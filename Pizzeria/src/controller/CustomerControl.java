package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.PizzaInfoTiles;


public class CustomerControl extends GeneralWindowControl implements Initializable {

    private int largePizzaUnit = 0;
    private int mediumPizzaUnit = 0;
    private int smallPizzaUnit = 0;
    private int pizzaUnits = 0;
    private int finalPrice = 0;

    private int prefheight = 100;

    private Image pica = new Image("view/pizzainfo/pizzaPhotos/Pepperoni.png");


    @FXML CheckBox newadress = new CheckBox();
    @FXML Label largePizzaLabel = new Label();
    @FXML Label mediumPizzaLabel = new Label();
    @FXML Label smallPizzaLabel = new Label();
    @FXML Label pizzacounterLabel = new Label();
    @FXML Label finalPriceLabel = new Label();

    @FXML VBox PizzaCoontent = new VBox();

    @FXML AnchorPane pizzaanchor = new AnchorPane();


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        for(int i = 0; i < 15; i++){
            pizzaanchor.setPrefHeight(prefheight);
            PizzaCoontent.setPrefHeight(prefheight);
            prefheight += 100;

            HBox Pizzatab = new HBox();
            Pizzatab.setPrefSize(589,70);
//            Button nowy = new Button("Button "+ i);
//            nowy.setPrefSize(500,70);
            TextArea Description = new TextArea();
            Description.setPrefSize(500,70);
            Description.setText("Teste tsts tsst");
//            Description.setDisable(true);
            Description.setEditable(false);

            ImageView Picaphoto = new ImageView();
            Picaphoto.setFitHeight(70);
            Picaphoto.setFitWidth(70);
            Picaphoto.setImage(pica);

            Button picachoice = new Button("");
            picachoice.setPrefSize(50,50);

            Pizzatab.getChildren().addAll(Picaphoto,Description,picachoice);
            Pizzatab.setAlignment(Pos.CENTER);
            Pizzatab.setSpacing(10);
            PizzaCoontent.getChildren().add(Pizzatab);
        }



    }


    public void Checkout(ActionEvent event) throws IOException {
        openscene(event,"checkout","GeneralWindowStyle","checkout","Global_Resources");
        if(newadress.isSelected()){
            openscene(event, "Adress","GeneralWindowStyle","Adress","Global_Resources");
        }
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
        //add to the counter label
        pizzaUnits += smallPizzaUnit + mediumPizzaUnit + largePizzaUnit;
        pizzacounterLabel.setText(Integer.toString(pizzaUnits));
        //Sum everything
        finalPrice += 40 * largePizzaUnit + 30 * mediumPizzaUnit + 25 * smallPizzaUnit;
        finalPriceLabel.setText(Integer.toString(finalPrice)+" zlt");
        //null everything
        largePizzaUnit = 0;
        mediumPizzaUnit = 0;
        smallPizzaUnit = 0;
        largePizzaLabel.setText("0");
        mediumPizzaLabel.setText("0");
        smallPizzaLabel.setText("0");

    }
}
