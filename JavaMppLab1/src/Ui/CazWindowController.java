package Ui;

import com.sun.javafx.stage.StageHelper;
import Domain.*;
import com.sun.org.glassfish.external.probe.provider.annotations.Probe;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import Utils.Observable;
import Utils.Observer;
import Controller.Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class CazWindowController implements Observer<Proba> {

    @FXML
    private TableView tableProbe;
    @FXML
    private TableView tableParticipanti;
    @FXML
    private TableColumn VarstaColumn;
    @FXML
    private TableColumn NumeColumn;
    @FXML
    private TableColumn Stil2Column;
    @FXML
    private TableColumn Distanta2Column;
    @FXML
    private TableColumn Participanti2Column;

    @FXML
    private Label Varsta;


    @FXML
    private TextField nume;
    @FXML
    Button logout_button;
    @FXML
    Button adaugare_participant;

    @FXML
    private Button Cautare;

    @FXML
    private Slider VarstaSlider;

    @FXML
    private ComboBox<String> Distante;

    @FXML
    private ComboBox<String> Stiluri;

    public CazWindowController() {
    }

    private ObservableList<Proba> model;
    private ObservableList<Participant> modelParticipant;

    private Controller controller;

    public void setController(Controller controller) {
        this.controller = controller;
        model = FXCollections.observableArrayList(controller.get_all_probe());

        tableProbe.setItems(model);
        tableParticipanti.setItems(modelParticipant);
    }


    @FXML
    void set_varsta() {
        Varsta.setText("Varsta :" + (int) VarstaSlider.getValue());

    }

    @FXML
    public void initialize() {
//        DistantaColumn.setCellValueFactory(new PropertyValueFactory<Probe, String>("Distanta"));

//        VarstaColumn;
//        StilColumn;
//        NumeColumn;
//        Numar_participantiColumn;
        Stil2Column.setCellValueFactory(new PropertyValueFactory<Proba, String>("_stil"));
        Distanta2Column.setCellValueFactory(new PropertyValueFactory<Proba, String>("_distanta"));
        Participanti2Column.setCellValueFactory(new PropertyValueFactory<Proba, String>("_nr_participanti"));

        NumeColumn.setCellValueFactory(new PropertyValueFactory<Participant, String>("_nume"));
        VarstaColumn.setCellValueFactory(new PropertyValueFactory<Participant, String>("_varsta"));
        set_varsta();
        Distante.getItems().addAll("50", "200", "800", "1500");
        Stiluri.getItems().addAll("liber", "fluture", "mixt", "spate");

    }

    public void apasare_cautare() {
        if (Distante.getSelectionModel().isEmpty() || Stiluri.getSelectionModel().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Alegeti stilul si distanta ! \n").showAndWait();
            return;
        } else
            modelParticipant = FXCollections.observableArrayList(controller.get_all_participant_inscrisi(Stiluri.getValue(), Distante.getValue()));
        tableParticipanti.setItems(modelParticipant);
    }

    public void add_participant() {
        if (tableProbe.getSelectionModel().isEmpty() || nume.getText() == "") {
            new Alert(Alert.AlertType.ERROR, "Alegeti un element din tabel si completati campul cu nume! \n").showAndWait();
            return;
        } else {
            try {
                controller.add_part_proba(nume.getText(),(int)VarstaSlider.getValue(),Stiluri.getSelectionModel().getSelectedItem(),Integer.parseInt(Distante.getSelectionModel().getSelectedItem()));
                update();
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).showAndWait();
            }
        }
    }


    public void logout(ActionEvent actionEvent) {
        Stage stage = (Stage) logout_button.getScene().getWindow();
        stage.close();

    }

    @Override
    public void update() {
        model.setAll(controller.get_all_probe());
        modelParticipant = FXCollections.observableArrayList(controller.get_all_participant_inscrisi(Stiluri.getValue(), Distante.getValue()));
        tableParticipanti.setItems(modelParticipant);
    }
}
