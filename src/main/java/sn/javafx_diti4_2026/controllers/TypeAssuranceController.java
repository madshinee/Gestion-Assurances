package sn.javafx_diti4_2026.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import sn.javafx_diti4_2026.Entity.TypeAssurance;
import sn.javafx_diti4_2026.repository.TypeAssuranceRepository;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TypeAssuranceController implements Initializable {

    @FXML private TextField txtLabel;
    @FXML private Button btnAddType;
    @FXML private Button btnUpdateType;
    @FXML private Button btnDeleteType;
    @FXML private Button btnClear;
    @FXML private TableView<TypeAssurance> tableTypeAssurance;
    @FXML private TableColumn<TypeAssurance, Integer> colId;
    @FXML private TableColumn<TypeAssurance, String> colLabel;

    private TypeAssuranceRepository typeAssuranceRepository;
    private TypeAssurance selectedTypeAssurance;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        typeAssuranceRepository = new TypeAssuranceRepository();
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colLabel.setCellValueFactory(new PropertyValueFactory<>("label"));
        
        // Listener to populate form on table selection
        tableTypeAssurance.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            selectedTypeAssurance = newSelection;
            if (newSelection != null) {
                txtLabel.setText(newSelection.getLabel());
            } else {
                txtLabel.clear();
            }
        });

        loadTable();
    }

    private void loadTable() {
        List<TypeAssurance> types = typeAssuranceRepository.findAll();
        ObservableList<TypeAssurance> typeList = FXCollections.observableArrayList(types);
        tableTypeAssurance.setItems(typeList);
    }

    @FXML
    void addType(ActionEvent event) {
        String label = txtLabel.getText();
        if (label == null || label.trim().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Erreur de saisie", "Le libellé ne peut pas être vide.");
            return;
        }
        
        TypeAssurance newType = new TypeAssurance();
        newType.setLabel(label.trim());
        typeAssuranceRepository.insert(newType);
        
        loadTable();
        clearSelection();
    }

    @FXML
    void updateType(ActionEvent event) {
        if (selectedTypeAssurance == null) {
            showAlert(Alert.AlertType.WARNING, "Aucune sélection", "Veuillez sélectionner un type à modifier.");
            return;
        }
        
        String label = txtLabel.getText();
        if (label == null || label.trim().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Erreur de saisie", "Le libellé ne peut pas être vide.");
            return;
        }
        
        TypeAssurance typeToUpdate = typeAssuranceRepository.findById(selectedTypeAssurance.getId());
        if (typeToUpdate != null) {
            typeToUpdate.setLabel(label.trim());
            typeAssuranceRepository.update(typeToUpdate);
        }
        
        loadTable();
        clearSelection();
    }

    @FXML
    void deleteType(ActionEvent event) {
        if (selectedTypeAssurance == null) {
            showAlert(Alert.AlertType.WARNING, "Aucune sélection", "Veuillez sélectionner un type à supprimer.");
            return;
        }
        
        typeAssuranceRepository.delete(selectedTypeAssurance.getId());
        
        loadTable();
        clearSelection();
    }

    @FXML
    void clearField(ActionEvent event) {
        clearSelection();
    }
    
    private void clearSelection() {
        txtLabel.clear();
        selectedTypeAssurance = null;
        tableTypeAssurance.getSelectionModel().clearSelection();
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
