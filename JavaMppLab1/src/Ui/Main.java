package Ui;

import Controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Created by andrei on 3/10/17.
 */
public class Main extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {



        Controller cont = new Controller();
        primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("login.fxml"));
        AnchorPane ap1 = loader.load();


        loginController viewController1 = loader.getController();
        viewController1.setController(cont);
        Scene scene = new Scene(ap1);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
