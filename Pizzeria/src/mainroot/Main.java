package mainroot;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/View/Login/Login.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("/View/Customer/Admin.fxml"));
        primaryStage.setTitle("Pizzeria - oh Cheese!");
        Image logo_icon = new Image("/View/Global_Resources/Logo.png");
        primaryStage.getIcons().add(logo_icon);

        Scene scene = new Scene(root);
        scene.getStylesheets().addAll("/View/Login/Login.css");
//        scene.getStylesheets().addAll("/View/Customer/Admin.css");

//        primaryStage.setMinWidth(720);
//        primaryStage.setMinHeight(720);

        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
