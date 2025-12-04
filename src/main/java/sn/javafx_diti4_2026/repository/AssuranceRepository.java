package sn.javafx_diti4_2026.repository;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sn.javafx_diti4_2026.Entity.Assurance;
import sn.javafx_diti4_2026.repository.interfaceRepo.IInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AssuranceRepository implements IInterface<Assurance> {

    private Connection connection;

    public AssuranceRepository() {
        this.connection = new Database().getConnection();
    }


    @Override
    public void insert(Assurance assurance) {

        String query = "INSERT INTO assurance (nomclient, montant, numero) VALUES(?,?,?)";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setString(1, assurance.getNomClient());
            preparedStatement.setDouble(2, assurance.getMontant());
            preparedStatement.setString(3, assurance.getNumero());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(Assurance assurance) {
        String query = "UPDATE Assurance SET nomClient = ?, montant = ?, numero = ? WHERE id = ? ";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setString(1, assurance.getNomClient());
            preparedStatement.setDouble(2, assurance.getMontant());
            preparedStatement.setString(3, assurance.getNumero());
            preparedStatement.setInt(4, assurance.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

        @Override
    public void delete(int id) {
        String query = "DELETE FROM Assurance WHERE id = ?";
            try {
                PreparedStatement preparedStatement = this.connection.prepareStatement(query);
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }

    @Override
    public Assurance findById(int id) {
        Assurance assurance = new Assurance();
        String query = "SELECT * FROM Assurance WHERE id = ?";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                assurance.setId(resultSet.getInt("id"));
                assurance.setMontant(resultSet.getDouble("montant"));
                assurance.setNomClient(resultSet.getString("nomClient"));
                assurance.setNumero(resultSet.getString("numero"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return assurance ;
    }

    @Override
    public ObservableList<Assurance> findAll() {
        ObservableList<Assurance> assurances = FXCollections.observableArrayList();

        String query = "SELECT * FROM Assurance";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            ResultSet resultSet =  preparedStatement.executeQuery();
            while ((resultSet.next())) {
                Assurance assurance = new Assurance();
                assurance.setId(resultSet.getInt("id"));
                assurance.setMontant(resultSet.getDouble("montant"));
                assurance.setNomClient(resultSet.getString("nomClient"));
                assurance.setNumero(resultSet.getString("numero"));
                assurances.add(assurance);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return assurances;
    }
}
