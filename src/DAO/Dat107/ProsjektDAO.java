
package DAO.Dat107;

import jakarta.persistence.*;

import java.util.List;

import Entity.Dat107.Ansatt;
import Entity.Dat107.Prosjekt;
import Entity.Dat107.ProsjektDeltakelse;

public class ProsjektDAO {

    private EntityManagerFactory emf;

    public ProsjektDAO() {
        emf = Persistence.createEntityManagerFactory("ansattPersistenceUnit");
    }

    public Prosjekt finnProsjektMedId(int prosjektId) {
        EntityManager em = emf.createEntityManager();
        Prosjekt prosjekt = null;
        try {
            prosjekt = em.find(Prosjekt.class, prosjektId);
        } finally {
            em.close();
        }
        return prosjekt;
    }
    
    public List<Prosjekt> hentAlleProsjekter() {
        EntityManager em = emf.createEntityManager();
        List<Prosjekt> prosjekter = null;
        try {
            prosjekter = em.createQuery("SELECT p FROM Prosjekt p", Prosjekt.class).getResultList();
        } finally {
            em.close();
        }
        return prosjekter;
    }
    
    public void leggTilProsjekt(String navn, String beskrivelse) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Prosjekt nyttProsjekt = new Prosjekt(navn, beskrivelse);
            em.persist(nyttProsjekt);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    public void registrerDeltakelse(int ansattId, int prosjektId, String rolle, int arbeidstimer) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            Ansatt ansatt = em.find(Ansatt.class, ansattId);
            Prosjekt prosjekt = em.find(Prosjekt.class, prosjektId);

            if (ansatt == null || prosjekt == null) {
                System.out.println("Fant ikke ansatt eller prosjekt.");
                em.getTransaction().rollback();
                return;
            }

            ProsjektDeltakelse deltakelse = new ProsjektDeltakelse(ansatt, prosjekt, rolle, arbeidstimer);
            em.persist(deltakelse);

            em.getTransaction().commit();
            System.out.println("Deltakelse registrert.");
        } finally {
            em.close();
        }
    }
    
    public List<ProsjektDeltakelse> hentDeltakelserForProsjekt(int prosjektId) {
        EntityManager em = emf.createEntityManager();
        List<ProsjektDeltakelse> deltakelser = null;

        try {
            deltakelser = em.createQuery(
                "SELECT pd FROM ProsjektDeltakelse pd WHERE pd.prosjekt.prosjektId = :prosjektId", 
                ProsjektDeltakelse.class)
                .setParameter("prosjektId", prosjektId)
                .getResultList();
        } finally {
            em.close();
        }

        return deltakelser;
    }


}