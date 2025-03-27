package DAO.Dat107;

import jakarta.persistence.*;
import java.util.List;
import java.time.LocalDate;
import Entity.Dat107.Ansatt;
import Entity.Dat107.Avdeling;

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

    public boolean erAnsattSjef(int ansattId) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(a) FROM Avdeling a WHERE a.sjef.ansattId = :ansattId", Long.class);
            query.setParameter("ansattId", ansattId);
            Long antall = query.getSingleResult();
            return antall > 0;
        } finally {
            em.close();
        }
    }
    public void flyttAnsattTilAvdeling(int ansattId, int nyAvdelingId) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            Ansatt ansatt = em.find(Ansatt.class, ansattId);
            Avdeling nyAvdeling = em.find(Avdeling.class, nyAvdelingId);

            if (ansatt == null || nyAvdeling == null) {
                System.out.println("Fant ikke ansatt eller avdeling.");
            } else {
                ansatt.setAvdeling(nyAvdeling);
                em.merge(ansatt);
            }

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    public void leggTilAnsatt(String brukernavn, String fornavn, String etternavn,
            				  LocalDate ansettelsesdato, String stilling, double maanedslonn,
            				  Avdeling avdeling) {
    	
    	EntityManager em = emf.createEntityManager();
    	try {
    		em.getTransaction().begin();

    		Ansatt nyAnsatt = new Ansatt(brukernavn, fornavn, etternavn,
                       ansettelsesdato, stilling, maanedslonn);
    		nyAnsatt.setAvdeling(avdeling);

    		em.persist(nyAnsatt);
    		em.getTransaction().commit();
    	} finally {
    		em.close();
    	}
    }


}
