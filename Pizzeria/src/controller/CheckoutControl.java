package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import model.Order;
import model.PizzaInfo;

import java.io.IOException;
import java.net.ContentHandler;
import java.net.URL;
import java.util.ResourceBundle;

public class CheckoutControl implements Initializable {

    Order newOrder;

    @FXML RadioButton card = new RadioButton();
    @FXML RadioButton cash = new RadioButton();
    @FXML Label warning = new Label();
    @FXML Label Price = new Label();
    @FXML TextArea OrderInfo = new TextArea();


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

    }

    public void test(Order some) {
        System.out.println("Customer ID: " + some.getCustumerID());
        System.out.println("Final Price: " + some.getFinalPrice());
        System.out.println("Method: " + some.getPaymentMethod());
        System.out.println("------ Pizzas ----");
        for (int i = 0; i < some.getPizzasIDs().size(); i++) {
            System.out.println("- ID: " + some.getPizzasIDs().get(i) + " -size: " + some.getSizeIDs().get(i));
        }
    }

    public String displayOrderInfo(){
        String message = "";
        message += "Price:  "+newOrder.getFinalPrice()+" zlt\n";
        message += "----------------\n";
        for (int i = 0; i < newOrder.getPizzasIDs().size(); i++) {
            message += "- ID: " + newOrder.getPizzasIDs().get(i) + " -size: " + newOrder.getSizeIDs().get(i)+"\n";
        }
        return message;
    }

    public void getOrder(Order newOrder){
        this.newOrder = newOrder;
        Price.setText(newOrder.getFinalPrice()+" zlt");
        OrderInfo.setText(displayOrderInfo());
    }


    public void submit(ActionEvent event) throws IOException {
        if (cash.isSelected() || card.isSelected()) {
//                Parent extended_calculator = FXMLLoader.load(getClass().getResource("/view/checkout/thankyou.fxml"));
//                Scene scene = new Scene(extended_calculator);
//                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
//                window.setScene(scene);
//                window.show();
//                scene.getStylesheets().clear();
//                scene.getStylesheets().add("/view/Global_Resources/GeneralWindowStyle.css");

            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.close();


                if(cash.isSelected())
                    newOrder.setPaymentMethod(0);
                else
                    newOrder.setPaymentMethod(1);

//                test(newOrder);
        }
        else
        {
            warning.setText("Please check payment method");
        }
    }
}
