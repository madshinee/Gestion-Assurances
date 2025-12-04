package sn.javafx_diti4_2026.Entity;/*package Entity;

public class AssuranceAuto extends Assurance {

    private String immat;
    private int pf;
    private int bonusMalus;

    public AssuranceAuto(String nomClient, Double montant, String immat, int pf, int bonusMalus) {
        super(nomClient, montant);
        this.immat = immat;
        this.pf = pf;
        this.bonusMalus = bonusMalus;
    }

    @Override
    public double calculerPrime() {
        return this.getMontant() +(this.getMontant() * (this.bonusMalus/100));
    }

    @Override
    public String getTypeAssurance() {
        return "Entity.Assurance Auto";
    }

    public String getImmat() {
        return immat;
    }

    public int getPf() {
        return pf;
    }

    public int getBonusMalus() {
        return bonusMalus;
    }

    @Override
    public String toString() {
        return  super.toString()+
                "immat='" + immat + '\'' +
                ", pf=" + pf +
                ", bonusMalus=" + bonusMalus;

    }
}
*/