package DAO.Dat107;

import jakarta.persistence.*;
import Entity.Dat107.Avdeling;
import Entity.Dat107.Ansatt;

import java.util.List;

public class AvdelingDAO {
	

    private EntityManagerFactory emf;

	public AvdelingDAO() {
	   emf = Persistence.createEntityManagerFactory("ansattPersistenceUnit");
    }
    

    public Avdeling finnAvdelingMedId(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Avdeling.class, id);
        } finally {
            em.close();
        }
    }

    public List<Avdeling> hentAlleAvdelinger() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT a FROM Avdeling a", Avdeling.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void leggTilAvdeling(String navn, Ansatt sjef) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

           
            Avdeling nyAvdeling = new Avdeling(navn, sjef);
            em.persist(nyAvdeling);

            em.flush(); 

            sjef.setAvdeling(nyAvdeling);
            em.merge(sjef);

            tx.commit();

            System.out.println("Avdeling '" + navn + "' opprettet med " +
                    sjef.getFornavn() + " " + sjef.getEtternavn() + " som sjef.");
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public List<Ansatt> hentAnsatteIVedAvdeling(int avdelingId) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Ansatt> query = em.createQuery(
                "SELECT a FROM Ansatt a WHERE a.avdeling.avdelingId = :avdId", Ansatt.class);
            query.setParameter("avdId", avdelingId);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public void oppdaterAvdelingNavn(int avdelingId, String nyttNavn) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Avdeling avdeling = em.find(Avdeling.class, avdelingId);
            if (avdeling != null) {
                avdeling.setNavn(nyttNavn);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void endreSjef(int avdelingId, Ansatt nySjef) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Avdeling avdeling = em.find(Avdeling.class, avdelingId);
            if (avdeling != null && nySjef != null) {
                avdeling.setSjef(nySjef);
                nySjef.setAvdeling(avdeling); 
                em.merge(nySjef);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public boolean kanSletteAvdeling(int avdelingId) {
        return hentAnsatteIVedAvdeling(avdelingId).isEmpty();
    }

    public void slettAvdeling(int avdelingId) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Avdeling avdeling = em.find(Avdeling.class, avdelingId);
            if (avdeling != null && avdeling.getAnsatte().isEmpty()) {
                em.remove(avdeling);
            } else {
                System.out.println("Kan ikke slette: avdelingen har ansatte.");
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
}
