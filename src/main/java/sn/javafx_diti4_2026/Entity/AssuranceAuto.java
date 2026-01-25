package sn.javafx_diti4_2026.Entity;

import javax.persistence.*;

@Entity
public class AssuranceAuto extends Assurance {

    /*
    private String immat;
    private int pf;
    private int bonusMalus;
    */

    public AssuranceAuto() {
        // Constructeur par défaut requis par JPA
    }

    public AssuranceAuto(String nomClient, Double montant/*, String immat, int pf, int bonusMalus*/) {
        super(nomClient, montant);
        /*
        this.immat = immat;
        this.pf = pf;
        this.bonusMalus = bonusMalus;
        */
    }

    @Override
    public double calculerPrime() {
        // return this.getMontant() + (this.getMontant() * (pf / 100.0)) + (this.getMontant() * (this.bonusMalus / 100.0));
        return this.getMontant();
    }

    /*
    public String getImmat() { return immat; }
    public void setImmat(String immat) { this.immat = immat; }
    public int getPf() { return pf; }
    public void setPf(int pf) { this.pf = pf; }
    public int getBonusMalus() { return bonusMalus; }
    public void setBonusMalus(int bonusMalus) { this.bonusMalus = bonusMalus; }
    */

    @Override
    public String toString() {
        return super.toString();
        /*
               + ", immat='" + immat + '\'' +
               ", pf=" + pf +
               ", bonusMalus=" + bonusMalus;
        */
    }
}
