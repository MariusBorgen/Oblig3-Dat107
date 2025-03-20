package Iterasjon1.Dat107;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Opprett en EntityManagerFactory basert på persistence.xml
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ansattPersistenceUnit");
        EntityManager em = emf.createEntityManager();

        try {
            // Hent ut alle ansatte fra databasen
            List<Ansatt> ansatte = em.createQuery("SELECT a FROM Ansatt a", Ansatt.class).getResultList();
            
            // Skriv ut alle ansatte
            if (ansatte.isEmpty()) {
                System.out.println("Ingen ansatte funnet i databasen.");
            } else {
                for (Ansatt ansatt : ansatte) {
                    ansatt.skrivUt(); // Bruker skrivUt() metoden fra Ansatt-klassen
                }
            }

        } catch (Exception e) {
            System.err.println("Feil ved henting av ansatte: " + e.getMessage());
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
