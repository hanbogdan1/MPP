package Ui;

import Controller.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import Repozitory.*;

import java.sql.*;

/**
 * Created by Andrei on 3/26/2017.
 */
public class loginController {

    @FXML
    TextField user_text;
    @FXML
    PasswordField password_text;
    @FXML
    Button login_button;

    Controller controllerr;



    public void setController(Controller controller) {
        this.controllerr = controller;
    }

    @FXML
    public void initialize() {
    }

    public void login_user()  {
        try {

            if (controllerr.check_user(user_text.getText().toString(), password_text.getText().toString()) == true) {
                    Stage primaryStage;
                    primaryStage = new Stage();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(Main.class.getResource("CazWindow.fxml"));
                    AnchorPane ap1 = loader.load();


                    CazWindowController viewController1 = loader.getController();
                    viewController1.setController(controllerr);
                    Scene scene = new Scene(ap1);
                    primaryStage.setScene(scene);
                    primaryStage.setTitle(user_text.getText().toString());
                    primaryStage.show();


            }
            else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Eroare logare");
                alert.setHeaderText(null);
                alert.setContentText("User-ul sau parola gresita. Incercati din nou!");

                alert.showAndWait();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).showAndWait();
        }
    }
}
