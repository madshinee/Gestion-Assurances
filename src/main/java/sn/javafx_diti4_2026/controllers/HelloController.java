package sn.javafx_diti4_2026.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sn.javafx_diti4_2026.Entity.Assurance;
import sn.javafx_diti4_2026.HelloApplication;
import sn.javafx_diti4_2026.repository.AssuranceRepository;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML private TableView<Assurance> tab_assurance;
    @FXML private TableColumn<Assurance, Integer> id_tab;
    @FXML private TableColumn<Assurance, String> nom_tab;
    @FXML private TableColumn<Assurance, String> numero_tab;
    @FXML private TableColumn<Assurance, Double> montant_tab;
    @FXML private TableColumn<Assurance, String> type_tab;
    @FXML private TextField searchField;

    private AssuranceRepository assuranceRepository;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.assuranceRepository = new AssuranceRepository();
        printAllAssurance();

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null || newValue.trim().isEmpty()) {
                printAllAssurance(); 
            } else {
                searchAssurances(newValue);
            }
        });
    }

    public void printAllAssurance() {
        ObservableList<Assurance> assurances = FXCollections.observableArrayList(assuranceRepository.findAll()); // recuperation db

        // Configuration
        id_tab.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom_tab.setCellValueFactory(new PropertyValueFactory<>("nomClient"));
        numero_tab.setCellValueFactory(new PropertyValueFactory<>("numero"));
        montant_tab.setCellValueFactory(new PropertyValueFactory<>("montant"));
        
        type_tab.setCellValueFactory(cellData -> {
            if (cellData.getValue().getTypeAssurance() != null) {
                return new SimpleStringProperty(cellData.getValue().getTypeAssurance().getLabel());
            } else {
                return new SimpleStringProperty("");
            }
        });

        tab_assurance.setItems(assurances);
    }

    private void searchAssurances(String keyword) {
        List<Assurance> assuranceList = assuranceRepository.search(keyword);
        ObservableList<Assurance> assurances = FXCollections.observableArrayList(assuranceList);
        tab_assurance.setItems(assurances);
    }

    @FXML
    void openAddAssuranceWindow(ActionEvent event) {
        openAssuranceForm(null);
    }

    @FXML
    void openEditAssuranceWindow(ActionEvent event) {
        Assurance selectedAssurance = tab_assurance.getSelectionModel().getSelectedItem();
        if (selectedAssurance != null) {
            openAssuranceForm(selectedAssurance);
        } else {
            showAlert(Alert.AlertType.WARNING, "Aucune sélection", "Veuillez sélectionner une assurance à modifier.");
        }
    }

    private void openAssuranceForm(Assurance assurance) {
        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("XML/assurance-form-view.fxml"));
            Parent root = loader.load();

            AssuranceFormController controller = loader.getController();
            controller.setHelloController(this);
            if (assurance != null) {
                controller.setAssurance(assurance);
            }

            Stage stage = new Stage();
            Scene scene = new Scene(root);

            stage.setTitle(assurance == null ? "Ajouter une Assurance" : "Modifier une Assurance");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erreur", "Impossible d'ouvrir le formulaire d'assurance.");
        }
    }

    @FXML
    void deleteAssurance(ActionEvent event) {
        int id = tab_assurance.getSelectionModel().getSelectedItem().getId();
        assuranceRepository.delete(id);
        printAllAssurance();
    }

    @FXML
    void openTypeAssuranceWindow(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("XML/type-assurance-view.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            Scene scene = new Scene(root);

            stage.setTitle("Gestion des Types d'Assurance");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erreur", "Impossible d'ouvrir la fenêtre.");
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
