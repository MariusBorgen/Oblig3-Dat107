
package DAO.Dat107;

import jakarta.persistence.*;

import java.util.List;

import Entity.Dat107.Prosjekt;

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


}