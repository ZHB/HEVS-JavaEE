package ch.hevs.test;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.apache.log4j.BasicConfigurator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import ch.hevs.businessobject.Flight;
import ch.hevs.businessobject.Gender;
import ch.hevs.businessobject.Paraglider;
import ch.hevs.businessobject.Pilot;
import ch.hevs.businessobject.Plane;
import ch.hevs.businessobject.Site;
import ch.hevs.businessobject.WingApproval;

public class PersistenceTest {

	private EntityManagerFactory emf;

    private EntityManager em;
    
	@Before
    public void initEmfAndEm() {
        BasicConfigurator.configure();
        Logger.getLogger("org").setLevel(Level.ERROR);

        emf = Persistence.createEntityManagerFactory("bankPU");
        em = emf.createEntityManager();
    }

    @After
    public void cleanup() {
        em.close();
    }
    
	@Test
	public void test() {


			//EntityManagerFactory emf = Persistence.createEntityManagerFactory("bankPU");
			
			//em = emf.createEntityManager();
			
			/*EntityManager em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			*/
			/*
			 * InternalAccount c = new InternalAccount("0","madescr");
			 * c.addOperation(new Operation("intitule",19,new Date()));
			 * em.persist(c);
			 */
			
			
			
			Flight f1 = new Flight();
			f1.defineDeparture(new Site("Sonchaux", 46.5345, 6.7898));
			f1.defineArrival(new Site("Villeneuve", 46.4878, 6.8965));
			
			
			Plane pl1 = new Paraglider(WingApproval.A);
			pl1.addFlight(f1);
			
			Pilot p1 = new Pilot("Vincent", "Huck", Gender.MALE, "2386330");
			p1.addFlight(f1);
			


			
			//em.persist(pl1);
			
			/*
			tx.commit();
			*/

		

	}
}
