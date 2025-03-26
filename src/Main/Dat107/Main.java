package Main.Dat107;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

import Entity.Dat107.Ansatt;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {
            // 1. Opprett EntityManagerFactory
            emf = Persistence.createEntityManagerFactory("ansattPersistenceUnit");
            
            // 2. Opprett EntityManager
            em = emf.createEntityManager();
            
            // 3. Hent ut alle ansatte med riktig JPQL-syntaks
            List<Ansatt> ansatte = em.createQuery("SELECT a FROM Ansatt a", Ansatt.class)
                                   .getResultList();
            
            // 4. Skriv ut resultatene
            if (ansatte.isEmpty()) {
                System.out.println("Ingen ansatte funnet i databasen.");
            } else {
                System.out.println("--- Liste over ansatte ---");
                ansatte.forEach(Ansatt::skrivUt);
            }

        } catch (Exception e) {
            System.err.println("Feil under databaseoperasjon:");
            e.printStackTrace();
        } finally {
            if (em != null) em.close();
            if (emf != null) emf.close();
        }
    }
}