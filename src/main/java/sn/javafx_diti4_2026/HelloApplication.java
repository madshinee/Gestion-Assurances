package sn.javafx_diti4_2026;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sn.javafx_diti4_2026.Entity.TypeAssurance;
import sn.javafx_diti4_2026.repository.TypeAssuranceRepository;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Initialisation des données
        initializeData();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("XML/hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);

        
        stage.setTitle("DITI 4 ");
        stage.setScene(scene);
        stage.show();
    }

    private void initializeData() {
        TypeAssuranceRepository typeAssuranceRepository = new TypeAssuranceRepository();
        List<TypeAssurance> typeAssurances = typeAssuranceRepository.findAll();

        if (typeAssurances.isEmpty()) {
            System.out.println("Base de données vide. Initialisation des types d'assurance...");

            TypeAssurance auto = new TypeAssurance();
            auto.setLabel("Assurance Auto");
            typeAssuranceRepository.insert(auto);

            TypeAssurance habitation = new TypeAssurance();
            habitation.setLabel("Assurance Habitation");
            typeAssuranceRepository.insert(habitation);

            TypeAssurance vie = new TypeAssurance();
            vie.setLabel("Assurance Vie");
            typeAssuranceRepository.insert(vie);

            System.out.println("Initialisation terminée.");
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
