package Entity.Dat107;

import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "avdeling", schema = "oblig3")
public class Avdeling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "avdelingid")
    private int avdelingId;

    @Column(name = "navn", nullable = false, length = 100, unique = true)
    private String navn;

    @OneToOne
    @JoinColumn(name = "sjefid", referencedColumnName = "ansattid", nullable = false)
    private Ansatt sjef;

    @OneToMany(mappedBy = "avdeling", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Ansatt> ansatte;

    public Avdeling() {}

    public Avdeling(String navn, Ansatt sjef) {
        this.navn = navn;
        this.sjef = sjef;
    }

    public int getAvdelingId() { return avdelingId; }
    public String getNavn() { return navn; }
    public Ansatt getSjef() { return sjef; }
    public List<Ansatt> getAnsatte() { return ansatte; }

    public void setNavn(String navn) { this.navn = navn; }
    public void setSjef(Ansatt sjef) { this.sjef = sjef; }
    public void setAnsatte(List<Ansatt> ansatte) { this.ansatte = ansatte; }

    @Override
    public String toString() {
        return String.format("Avdeling ID: %d | Navn: %s | Sjef: %s %s", 
                              avdelingId, navn, sjef.getFornavn(), sjef.getEtternavn());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Avdeling avd = (Avdeling) obj;
        return avdelingId == avd.avdelingId && Objects.equals(navn, avd.navn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(avdelingId, navn);
    }
}
