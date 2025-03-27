package Entity.Dat107;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "ansatt", schema = "oblig3")
public class Ansatt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ansattid")
    private int ansattId;
    

    @Column(name = "brukernavn", nullable = false, unique = true, length = 4)
    private String brukernavn;

    @Column(name = "fornavn", nullable = false, length = 50)
    private String fornavn;

    @Column(name = "etternavn", nullable = false, length = 50)
    private String etternavn;

    @Column(name = "datoforansettelse", nullable = false)
    private LocalDate ansettelsesdato;

    @Column(name = "stilling", nullable = false, length = 100)
    private String stilling;

    @Column(name = "manedslonn", nullable = false)
    private double maanedslonn;
    
    @ManyToOne
    @JoinColumn(name = "avdelingid", referencedColumnName = "avdelingid", nullable = true)
    private Avdeling avdeling;

    public Ansatt() {}

    public Ansatt(String brukernavn, String fornavn, String etternavn, 
                 LocalDate ansettelsesdato, String stilling, double maanedslonn) {
        this.brukernavn = brukernavn;
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.ansettelsesdato = ansettelsesdato;
        this.stilling = stilling;
        this.maanedslonn = maanedslonn;
    }

    public int getAnsattId() { return ansattId; }
    public String getBrukernavn() { return brukernavn; }
    public String getFornavn() { return fornavn; }
    public String getEtternavn() { return etternavn; }
    public LocalDate getAnsettelsesdato() { return ansettelsesdato; }
    public String getStilling() { return stilling; }
    public double getManedslonn() { return maanedslonn; }
    public Avdeling getAvdeling() {return avdeling;}
    public void setAvdeling(Avdeling avdeling) {this.avdeling = avdeling;}


    public void setStilling(String nyStilling) {
        if (nyStilling != null && !nyStilling.trim().isEmpty()) {
            this.stilling = nyStilling;
        }
    }

    public void setManedslonn(double nyLonn) {
        if (nyLonn > 0) {
            this.maanedslonn = nyLonn;
        }
    }

    @Override
    public String toString() {
        return String.format(
            "ID: %d | %s %s (%s) | Stilling: %s | Lønn: %.2f | Ansettelse: %s",
            ansattId, fornavn, etternavn, brukernavn, stilling, maanedslonn, ansettelsesdato
        );
    }

    public void skrivUt() {
        System.out.println(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Ansatt ansatt = (Ansatt) obj;
        return ansattId == ansatt.ansattId && Objects.equals(brukernavn, ansatt.brukernavn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ansattId, brukernavn);
    }
}
