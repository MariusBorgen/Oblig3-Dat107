package DAO.Dat107;

import jakarta.persistence.*;
import java.util.List;
import java.time.LocalDate;
import Entity.Dat107.Ansatt;

public class AnsattDAO {
    
    private EntityManagerFactory emf;

    public AnsattDAO() {
        emf = Persistence.createEntityManagerFactory("ansattPersistenceUnit");
    }

    public Ansatt finnAnsattMedId(int ansattId) {
        EntityManager em = emf.createEntityManager();
        Ansatt ansatt = null;
        try {
            ansatt = em.find(Ansatt.class, ansattId);
        } finally {
            em.close();
        }
        return ansatt;
    }

    public Ansatt finnAnsattMedBrukernavn(String brukernavn) {
        EntityManager em = emf.createEntityManager();
        Ansatt ansatt = null;
        try {
            TypedQuery<Ansatt> query = em.createQuery(
                "SELECT a FROM Ansatt a WHERE a.brukernavn = :brukernavn", Ansatt.class);
            query.setParameter("brukernavn", brukernavn);
            ansatt = query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("Ingen ansatt funnet med brukernavn: " + brukernavn);
        } finally {
            em.close();
        }
        return ansatt;
    }

    public List<Ansatt> hentAlleAnsatte() {
        EntityManager em = emf.createEntityManager();
        List<Ansatt> ansatte = null;
        try {
            ansatte = em.createQuery("SELECT a FROM Ansatt a", Ansatt.class).getResultList();
        } finally {
            em.close();
        }
        return ansatte;
    }

    public void oppdaterAnsatt(int ansattId, String nyStilling, Double nyLonn) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Ansatt ansatt = em.find(Ansatt.class, ansattId);
            if (ansatt != null) {
                if (nyStilling != null && !nyStilling.isEmpty()) {
                    ansatt.setStilling(nyStilling);
                }
                if (nyLonn != null) {
                    ansatt.setManedslonn(nyLonn);
                }
                em.merge(ansatt);
                em.getTransaction().commit();
            } else {
                System.out.println("Ingen ansatt funnet med ID: " + ansattId);
            }
        } finally {
            em.close();
        }
    }

    public void leggTilAnsatt(String brukernavn, String fornavn, String etternavn, 
                              LocalDate ansettelsesdato, String stilling, double maanedslonn) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Ansatt nyAnsatt = new Ansatt(brukernavn, fornavn, etternavn, 
                                         ansettelsesdato, stilling, maanedslonn);
            em.persist(nyAnsatt);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
