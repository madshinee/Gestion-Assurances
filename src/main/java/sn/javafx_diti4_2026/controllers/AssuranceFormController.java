package sn.javafx_diti4_2026.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sn.javafx_diti4_2026.Entity.Assurance;
import sn.javafx_diti4_2026.Entity.TypeAssurance;
import sn.javafx_diti4_2026.repository.AssuranceRepository;
import sn.javafx_diti4_2026.repository.TypeAssuranceRepository;

import java.util.List;
import java.util.stream.Collectors;

public class AssuranceFormController {

    @FXML private Text formTitle;
    @FXML private TextField nomClient;
    @FXML private TextField montant;
    @FXML private ComboBox<String> typeAssurance;

    /*
    @FXML private VBox formAuto;
    @FXML private VBox formHabitation;
    @FXML private VBox formVie;

    @FXML private TextField txtImmat;
    @FXML private TextField txtPf;
    @FXML private TextField txtBonusMalus;

    @FXML private TextField txtAdresse;
    @FXML private TextField txtSuperficie;
    @FXML private CheckBox chkZoneRisque;

    @FXML private TextField txtAge;
    @FXML private TextField txtCapital;
    @FXML private TextField txtBeneficiaire;
    */

    private AssuranceRepository assuranceRepository;
    private TypeAssuranceRepository typeAssuranceRepository;
    private Assurance currentAssurance;
    private HelloController helloController;

    public void initialize() {
        assuranceRepository = new AssuranceRepository();
        typeAssuranceRepository = new TypeAssuranceRepository();
        loadTypeAssuranceComboBox();

        /*
        typeAssurance.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            toggleForms(newValue);
        });
        */

        if (currentAssurance == null && !typeAssurance.getItems().isEmpty()) {
            typeAssurance.getSelectionModel().selectFirst();
        }
    }

    public void setHelloController(HelloController helloController) {
        this.helloController = helloController;
    }

    public void setAssurance(Assurance assurance) {
        this.currentAssurance = assurance;
        formTitle.setText("Modifier une Assurance");
        nomClient.setText(assurance.getNomClient());
        montant.setText(String.valueOf(assurance.getMontant()));

        if (assurance.getTypeAssurance() != null) {
            typeAssurance.setValue(assurance.getTypeAssurance().getLabel());
        }

        /*
        if (assurance instanceof AssuranceAuto) {
            AssuranceAuto auto = (AssuranceAuto) assurance;
            txtImmat.setText(auto.getImmat());
            txtPf.setText(String.valueOf(auto.getPf()));
            txtBonusMalus.setText(String.valueOf(auto.getBonusMalus()));
        } else if (assurance instanceof AssuranceHab) {
            AssuranceHab hab = (AssuranceHab) assurance;
            txtAdresse.setText(hab.getAdresse());
            txtSuperficie.setText(String.valueOf(hab.getSuperficie()));
            chkZoneRisque.setSelected(hab.getZoneRisque() != null && hab.getZoneRisque());
        } else if (assurance instanceof AssuranceVie) {
            AssuranceVie vie = (AssuranceVie) assurance;
            txtAge.setText(String.valueOf(vie.getAge()));
            txtCapital.setText(String.valueOf(vie.getCapital()));
            txtBeneficiaire.setText(vie.getBeneficiaire());
        }
        */
    }

    private void loadTypeAssuranceComboBox() {
        List<TypeAssurance> types = typeAssuranceRepository.findAll();
        List<String> labels = types.stream().map(TypeAssurance::getLabel).collect(Collectors.toList());
        typeAssurance.setItems(FXCollections.observableArrayList(labels));
    }

    /*
    private void toggleForms(String type) {
        if (type == null) return;
        formAuto.setVisible("Assurance Auto".equalsIgnoreCase(type));
        formAuto.setManaged("Assurance Auto".equalsIgnoreCase(type));
        formHabitation.setVisible("Assurance Habitation".equalsIgnoreCase(type));
        formHabitation.setManaged("Assurance Habitation".equalsIgnoreCase(type));
        formVie.setVisible("Assurance Vie".equalsIgnoreCase(type));
        formVie.setManaged("Assurance Vie".equalsIgnoreCase(type));
    }
    */

    @FXML
    void saveAssurance(ActionEvent event) {
        try {
            String typeLabel = typeAssurance.getValue();
            String nom = nomClient.getText();
            Double mnt = Double.parseDouble(montant.getText());
            
            if (typeLabel == null || typeLabel.trim().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Erreur", "Veuillez sélectionner un type d'assurance.");
                return;
            }

            Assurance assuranceToSave = currentAssurance;

            if (assuranceToSave == null) {
                assuranceToSave = new Assurance();
            }

            assuranceToSave.setNomClient(nom);
            assuranceToSave.setMontant(mnt);
            
            TypeAssurance selectedType = typeAssuranceRepository.findAll().stream()
                .filter(t -> t.getLabel().equals(typeLabel))
                .findFirst().orElse(null);
            assuranceToSave.setTypeAssurance(selectedType);

            if (currentAssurance != null) {
                assuranceRepository.update(assuranceToSave);
            } else {
                assuranceRepository.insert(assuranceToSave);
            }

            helloController.printAllAssurance();
            closeWindow();
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Erreur de saisie", "Veuillez vérifier les champs numériques.");
        }
    }

    @FXML
    void cancel(ActionEvent event) {
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) nomClient.getScene().getWindow();
        stage.close();
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
