package sn.javafx_diti4_2026.Entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class TypeAssurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String label;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "typeAssurance")
    List<Assurance> assurances;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
