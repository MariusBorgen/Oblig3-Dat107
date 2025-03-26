package DAO.Dat107;

import java.util.List;
import java.util.Map;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import Entity.Dat107.Ansatt;

public class AnsattDAO {
	
	 private EntityManagerFactory emf;

	    public AnsattDAO() {
	    
	    	emf = Persistence.createEntityManagerFactory("ansattPersistenceUnit");
	    }
	        
	    public Ansatt finnAnsattMedId(int id) {
	        EntityManager em = emf.createEntityManager();
	        Ansatt ansatt = null;
	        try {
	            ansatt = em.find(Ansatt.class, id);
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

	  }
