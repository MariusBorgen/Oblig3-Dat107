package Entity.Dat107;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "prosjekt", schema = "oblig3")
public class Prosjekt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prosjektid")
    private int prosjektId;

    @Column(name = "navn", nullable = false, length = 100)
    private String navn;

    @Column(name = "beskrivelse", nullable = false, length = 500)
    private String beskrivelse;

    @OneToMany(mappedBy = "prosjekt", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ProsjektDeltakelse> deltakelser = new HashSet<>();

    public Prosjekt() {}

    public Prosjekt(String navn, String beskrivelse) {
        this.navn = navn;
        this.beskrivelse = beskrivelse;
    }

    public int getProsjektId() { return prosjektId; }
    public String getNavn() { return navn; }
    public String getBeskrivelse() { return beskrivelse; }
    public Set<ProsjektDeltakelse> getDeltakelser() { return deltakelser; }

    public void setNavn(String navn) { this.navn = navn; }
    public void setBeskrivelse(String beskrivelse) { this.beskrivelse = beskrivelse; }
    public void setDeltakelser(Set<ProsjektDeltakelse> deltakelser) { this.deltakelser = deltakelser; }

    @Override
    public String toString() {
        return String.format("Prosjekt ID: %d | Navn: %s | Beskrivelse: %s | Antall deltakere: %d",
                prosjektId, navn, beskrivelse, deltakelser.size());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Prosjekt prosjekt = (Prosjekt) obj;
        return prosjektId == prosjekt.prosjektId && Objects.equals(navn, prosjekt.navn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prosjektId, navn);
    }
}
