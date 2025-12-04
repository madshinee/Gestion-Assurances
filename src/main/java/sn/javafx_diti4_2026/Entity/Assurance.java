package sn.javafx_diti4_2026.Entity;

public  class  Assurance {

    //Attribtus
    protected int id;
    protected String numero;
    protected String nomClient;
    protected double montant;

    private static int compteur = 0;

    public Assurance() {
    }

    public Assurance(String nomClient, Double montant) {
        this.nomClient = nomClient;
        this.montant = montant;
        this.numero = genererNumeroContrat();
    }



    private String genererNumeroContrat(){
        compteur++;
        return String.format("ASS%04d", compteur);
    }


    //Abstract
    //public abstract double calculerPrime() ;
    //public abstract String getTypeAssurance();

   /* public Double calculerCoutTotal(int nbAnnees){
        return calculerPrime() * nbAnnees;
    }*/

    @Override
    public String toString() {
        return
                "numero='" + numero + '\'' +
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
}
