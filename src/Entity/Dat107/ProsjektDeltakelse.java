package Entity.Dat107;

import jakarta.persistence.*;

@Entity
@Table(name = "prosjekt_deltakelse", schema = "oblig3")
public class ProsjektDeltakelse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deltakelseid")
    private int deltakelseId;

    @ManyToOne
    @JoinColumn(name = "ansattid", nullable = false)
    private Ansatt ansatt;

    @ManyToOne
    @JoinColumn(name = "prosjektid", nullable = false)
    private Prosjekt prosjekt;

    @Column(name = "rolle", nullable = false, length = 50)
    private String rolle;

    @Column(name = "arbeidstimer", nullable = false)
    private int arbeidstimer;

    public ProsjektDeltakelse() {}

    public ProsjektDeltakelse(Ansatt ansatt, Prosjekt prosjekt, String rolle, int arbeidstimer) {
        this.ansatt = ansatt;
        this.prosjekt = prosjekt;
        this.rolle = rolle;
        this.arbeidstimer = arbeidstimer;
    }

    public int getDeltakelseId() { return deltakelseId; }
    public Ansatt getAnsatt() { return ansatt; }
    public Prosjekt getProsjekt() { return prosjekt; }
    public String getRolle() { return rolle; }
    public int getArbeidstimer() { return arbeidstimer; }

    public void setRolle(String rolle) { this.rolle = rolle; }
    public void setArbeidstimer(int arbeidstimer) { this.arbeidstimer = arbeidstimer; }

    @Override
    public String toString() {
        return String.format("Deltakelse ID: %d | Ansatt: %s %s | Prosjekt: %s | Rolle: %s | Timer: %d",
                deltakelseId, ansatt.getFornavn(), ansatt.getEtternavn(), prosjekt.getNavn(), rolle, arbeidstimer);
    }
}
