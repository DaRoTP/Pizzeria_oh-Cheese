package ohcheese.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.List;

public class GeneralWindowControl {



    public void ChangeStage_PaassingID(ActionEvent event, String scenename, String stylesheetname, String packagename) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/" +packagename+"/"+scenename+".fxml"));
        Parent root = loader.load();

        if(scenename == "Customer") {
            CustomerControl CustomerC = loader.getController();
        }
        else if(scenename == "Employee"){
            EmployeeControl EmployeeC = loader.getController();
        }
        else{
            AdminControl AdminC = loader.getController();
        }

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.show();
        root.getStylesheets().add("/view/" +packagename+"/"+stylesheetname+".css");
    }


    public void changescene(ActionEvent event, String scenename, String stylesheetname, String scene_packagename,String stylesheet_packagename)throws IOException {
        Parent newScene = FXMLLoader.load(getClass().getResource("/view/" +scene_packagename+"/"+scenename+".fxml"));
        Scene scene = new Scene(newScene);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
        scene.getStylesheets().clear();
        if(stylesheetname != "GeneralWindowStyle")
            scene.getStylesheets().add("/view/Global_Resources/GeneralWindowStyle.css");
        scene.getStylesheets().add("/view/" +stylesheet_packagename+"/"+stylesheetname+".css");

    }

    public void openscene(ActionEvent event, String scenename, String stylesheetname, String scene_packagename,String stylesheet_packagename) throws IOException {
        Parent nn = FXMLLoader.load(getClass().getResource("/view/" +scene_packagename+"/"+scenename+".fxml"));
        Scene scene = new Scene(nn);
        Stage window = new Stage();
        window.setScene(scene);
        window.show();
        scene.getStylesheets().clear();
        if(stylesheetname != "GeneralWindowStyle")
            scene.getStylesheets().add("/view/Global_Resources/GeneralWindowStyle.css");
        scene.getStylesheets().add("/view/" +stylesheet_packagename+"/"+stylesheetname+".css");
        Image logo_icon = new Image("/view/Global_Resources/Logo.png");
        window.getIcons().add(logo_icon);
        window.setTitle("Pizzeria - oh Cheese!");
    }

    public void LogOut(ActionEvent event) throws IOException {
        changescene(event, "Login","Login","Login","Login");
        LoginControl.setLoggedinEmployee(null);
        LoginControl.setLoggedinCustomer(null);
    }


    public void settings(ActionEvent event)throws IOException {
        if(LoginControl.get_loggedinCustomer() != null)
            openscene(event,"settings","GeneralWindowStyle","settings","Global_Resources");
        else
            openscene(event,"settings_Employee","GeneralWindowStyle","settings","Global_Resources");

    }
}
