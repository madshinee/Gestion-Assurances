package sn.javafx_diti4_2026.Entity;

import javax.persistence.*;

@Entity
@Table(name = "assurance")
public class Assurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    @Column(name = "number")
    protected String numero;
    protected String nomClient;
    protected double montant;
    private static int compteur = 0;

    @ManyToOne
    @JoinColumn(name = "type_assurance_id")
    private TypeAssurance typeAssurance;

    public Assurance() {
        this.numero = genererNumeroContrat();
    }

    public Assurance(String nomClient, Double montant) {
        this();
        this.nomClient = nomClient;
        this.montant = montant;
    }

    private String genererNumeroContrat() {
        compteur++;
        return String.format("ASS%04d", compteur);
    }

    public double calculerPrime() {
        return this.montant;
    }

    @Override
    public String toString() {
        return "numero='" + numero + '\'' +
               ", nomClient='" + nomClient + '\'' +
               ", montant=" + montant;
    }

    public double getMontant() {
        return montant;
    }

    public String getNomClient() {
        return nomClient;
    }

    public String getNumero() {
        return numero;
    }

    public static int getNombreContrats(){
        return compteur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
// j'ai ajouté ce gs
    public TypeAssurance getTypeAssurance() {
        return typeAssurance;
    }

    public void setTypeAssurance(TypeAssurance typeAssurance) {
        this.typeAssurance = typeAssurance;
    }
}
