package sn.javafx_diti4_2026.Entity;

import javax.persistence.Entity;

@Entity
public class AssuranceVie extends Assurance {
    /*
    private int age;
    private double capital;
    private String beneficiaire;
    */

    public AssuranceVie() {
    }

    public AssuranceVie(String nomClient, double cotisation/*, int age, double capital, String beneficiaire*/) {
        super(nomClient, cotisation);
        /*
        this.age = age;
        this.capital = capital;
        this.beneficiaire = beneficiaire;
        */
    }

    @Override
    public double calculerPrime() {
        // return montant * (1 + age / 100.0) * (capital / 100000.0);
        return this.getMontant();
    }

    /*
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getCapital() {
        return capital;
    }

    public void setCapital(double capital) {
        this.capital = capital;
    }

    public String getBeneficiaire() {
        return beneficiaire;
    }

    public void setBeneficiaire(String beneficiaire) {
        this.beneficiaire = beneficiaire;
    }
    */

    @Override
    public String toString() {
        return super.toString();
        /*
        return String.format("%s - %d ans - Capital: %.0f€ - Bénéf: %s",
                super.toString(), age, capital, beneficiaire);
        */
    }
}
