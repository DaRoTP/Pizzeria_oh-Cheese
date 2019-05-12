package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
//        try {
//            Parent root = FXMLLoader.load(getClass().getResource("/sample.fxml"));
//            primaryStage.setTitle("Hello World");
//            primaryStage.setScene(new Scene(root, 300, 275));
//            primaryStage.show();
//        }
//        catch(NullPointerException e){
//            System.out.print("lol");
//        }
        Parent root = FXMLLoader.load(getClass().getResource("/view/Login/Login.fxml"));
        primaryStage.setTitle("Pizzeria - oh Cheese!");
        Image logo_icon = new Image("/view/Global_Resources/Logo.png");
        primaryStage.getIcons().add(logo_icon);

        Scene scene = new Scene(root);
        scene.getStylesheets().addAll("/view/Login/Login.css");

//        primaryStage.setMinWidth(720);
//        primaryStage.setMinHeight(720);

        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {



        launch(args);
    }
}
