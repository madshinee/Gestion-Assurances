package sn.javafx_diti4_2026;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import sn.javafx_diti4_2026.Entity.Assurance;
import sn.javafx_diti4_2026.repository.AssuranceRepository;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {


    private AssuranceRepository assuranceRepository;

    @FXML
    private Button add;

    @FXML
    private Button clearr;

    @FXML
    private Button delete;

    @FXML
    private Button edit;

    @FXML
    private TableColumn<?, ?> id_tab;

    @FXML
    private TextField montant;

    @FXML
    private TableColumn<?, ?> montant_tab;

    @FXML
    private TextField nomClient;

    @FXML
    private TableColumn<?, ?> nom_tab;

    @FXML
    private TableColumn<?, ?> numero_tab;

    @FXML
    private TableView<Assurance> tab_assurance;


    public HelloController() {

    }

    //Vrai controlleur
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.assuranceRepository = new AssuranceRepository();
        printAllAssurance();
    }

    public void printAllAssurance(){
        ObservableList<Assurance> assurances = assuranceRepository.findAll(); //recuperation db

        //Configuration
        id_tab.setCellValueFactory(new PropertyValueFactory<>("id"));
        montant_tab.setCellValueFactory(new PropertyValueFactory<>("montant"));
        nom_tab.setCellValueFactory(new PropertyValueFactory<>("nomClient"));
        numero_tab.setCellValueFactory(new PropertyValueFactory<>("numero"));
        //affercter les valeurs
        tab_assurance.setItems(assurances);
    }
    @FXML
    void deleteAssurance(ActionEvent event) {
            int id = tab_assurance.getSelectionModel().getSelectedItem().getId();
            assuranceRepository.delete(id);
            printAllAssurance();
    }



    @FXML
    void addAssurance(ActionEvent event) {
        Assurance assurance = new Assurance(nomClient.getText(), Double.parseDouble(montant.getText()) );
        this.assuranceRepository.insert(assurance);
        printAllAssurance();
        clearField(event);
    }

    @FXML
    void clearField(ActionEvent event) {
        nomClient.setText("");
        montant.setText("");
    }


}
