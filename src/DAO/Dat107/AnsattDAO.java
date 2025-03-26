package DAO.Dat107;

import java.util.List;
import java.util.Map;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
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
	    public List<Ansatt> finnAlleAnsatte() {
	        EntityManager em = emf.createEntityManager();
	        List<Ansatt> ansatte = null;
	        try {
	            TypedQuery<Ansatt> query = em.createQuery("SELECT a FROM Ansatt a", Ansatt.class);
	            ansatte = query.getResultList();
	        } finally {
	            em.close();
	        }
	        return ansatte;
	    }

	    public Ansatt finnAnsattMedBrukernavn(String brukernavn) {
	        EntityManager em = emf.createEntityManager();
	        Ansatt ansatt = null;
	        try {
	            TypedQuery<Ansatt> query = em.createQuery(
	                "SELECT a FROM Ansatt a WHERE a.brukernavn = :brukernavn", Ansatt.class);
	            query.setParameter("brukernavn", brukernavn);
	            ansatt = query.getSingleResult();
	        } finally {
	            em.close();
	        }
	        return ansatt;
	    }

	    public void oppdaterAnsatt(int id, String nyStilling, double nyLønn) {
	        EntityManager em = emf.createEntityManager();
	        try {
	            em.getTransaction().begin();
	            Ansatt ansatt = em.find(Ansatt.class, id);
	            if (ansatt != null) {
	                ansatt.setStilling(nyStilling);
	                ansatt.setLønn(nyLønn);
	                em.merge(ansatt);
	            }
	            em.getTransaction().commit();
	        } finally {
	            em.close();
	        }
	    }

	    public void leggTilAnsatt(Ansatt nyAnsatt) {
	        EntityManager em = emf.createEntityManager();
	        try {
	            em.getTransaction().begin();
	            em.persist(nyAnsatt);
	            em.getTransaction().commit();
	        } finally {
	            em.close();
	        }
	    }
	}

	  
