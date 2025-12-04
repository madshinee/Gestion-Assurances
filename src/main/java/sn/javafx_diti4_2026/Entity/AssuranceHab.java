package sn.javafx_diti4_2026.Entity;/*package Entity;

public class AssuranceHab extends Assurance {

    private  String adresse;
    private Double superficie;
    private Boolean zoneRisque;

    public AssuranceHab(String nomClient, Double montant, String adresse, Double superficie, Boolean zoneRisque) {
        super(nomClient, montant);
        this.adresse = adresse;
        this.superficie = superficie;
        this.zoneRisque = zoneRisque;
    }

    @Override
    public double calculerPrime() {
        return this.montant * (1 + superficie/1000) * (zoneRisque? 1.3:1.0);
    }

    @Override
    public String getTypeAssurance() {
        return "Entity.Assurance Habitation";
    }


    public String getAdresse() {
        return adresse;
    }

    public Double getSuperficie() {
        return superficie;
    }

    public Boolean getZoneRisque() {
        return zoneRisque;
    }

    @Override
    public String toString() {
        return  super.toString()+
                "adresse='" + adresse + '\'' +
                ", superficie=" + superficie +
                ", zoneRisque=" + zoneRisque ;
    }
}
*/