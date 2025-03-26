package Entity.Dat107;

import jakarta.persistence.*;
import java.time.LocalDate;

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

    @Column(name = "manedslonn", nullable = false, precision = 10, scale = 2)
    private double maanedslonn;

    // Obligatorisk tom konstruktør for JPA
    public Ansatt() {}

    // Konstruktør med parametre
    public Ansatt(String brukernavn, String fornavn, String etternavn, 
                 LocalDate ansettelsesdato, String stilling, double maanedslonn) {
        this.brukernavn = brukernavn;
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.ansettelsesdato = ansettelsesdato;
        this.stilling = stilling;
        this.maanedslonn = maanedslonn;
    }

    // Gettere og settere
    public int getAnsattId() { return ansattId; }
    public String getBrukernavn() { return brukernavn; }
    public String getFornavn() { return fornavn; }
    public String getEtternavn() { return etternavn; }
    public LocalDate getAnsettelsesdato() { return ansettelsesdato; }
    public String getStilling() { return stilling; }
    public double getManedslonn() { return maanedslonn; }

    public void skrivUt() {
        System.out.printf(
            "ID: %d | %s %s (%s) | %s | Lønn: %.2f | Ansettelse: %s%n",
            ansattId, fornavn, etternavn, brukernavn, 
            stilling, maanedslonn, ansettelsesdato
        );
    }
    
}