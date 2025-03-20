package Iterasjon1.Dat107;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ansatt")
public class Ansatt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ansattId;

    @Column(nullable = false, unique = true)
    private String brukernavn;

    @Column(nullable = false)
    private String fornavn;

    @Column(nullable = false)
    private String etternavn;

    @Column(nullable = false)
    private LocalDate ansettelsesdato;

    @Column(nullable = false)
    private String stilling;

    @Column(nullable = false)
    private double manedslonn;

    public Ansatt(String brukernavn, String fornavn, String etternavn, LocalDate ansettelsesdato, String stilling, double manedslonn) {
        this.brukernavn = brukernavn;
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.ansettelsesdato = ansettelsesdato;
        this.stilling = stilling;
        this.manedslonn = manedslonn;
    }

    public Long getAnsattId() {
        return ansattId;
    }

    public void setAnsattId(Long ansattId) {
        this.ansattId = ansattId;
    }

    public String getBrukernavn() {
        return brukernavn;
    }

    public void setBrukernavn(String brukernavn) {
        this.brukernavn = brukernavn;
    }

    public String getFornavn() {
        return fornavn;
    }

    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }

    public String getEtternavn() {
        return etternavn;
    }

    public void setEtternavn(String etternavn) {
        this.etternavn = etternavn;
    }

    public LocalDate getAnsettelsesdato() {
        return ansettelsesdato;
    }

    public void setAnsettelsesdato(LocalDate ansettelsesdato) {
        this.ansettelsesdato = ansettelsesdato;
    }

    public String getStilling() {
        return stilling;
    }

    public void setStilling(String stilling) {
        this.stilling = stilling;
    }

    public double getManedslonn() {
        return manedslonn;
    }

    public void setManedslonn(double manedslonn) {
        this.manedslonn = manedslonn;
    }

    public void skrivUt() {
        System.out.println("Ansatt: " + fornavn + " " + etternavn + ", Brukernavn: " + brukernavn + ", Ansettelsesdato: " + ansettelsesdato + ", Stilling: " + stilling + ", Manedslonn: " + manedslonn);
    }
    
}
