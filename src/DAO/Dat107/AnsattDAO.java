package DAO.Dat107;

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
	        
	    public Ansatt finnAnsattMedId(int id) { // Endret fra int til Long
	        EntityManager em = emf.createEntityManager();
	        Ansatt ansatt = null;
	        try {
	            ansatt = em.find(Ansatt.class, id);
	        } finally {
	            em.close();
	        }
	        return ansatt;
	    }

	  }
