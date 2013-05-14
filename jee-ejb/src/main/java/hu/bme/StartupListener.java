package hu.bme;

import hu.bme.entities.Lap;
import hu.bme.entities.Person;
import hu.bme.entities.Run;

import java.util.ArrayList;
import java.util.Arrays;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
@Startup
public class StartupListener {

	@PersistenceContext
	EntityManager em;

	@PostConstruct
	public void onStartup() {
		if (em.createQuery("SELECT a FROM Person a").setMaxResults(1)
				.getResultList().isEmpty()) {

			Person p1 = new Person();
			p1.setName("person1");
			em.persist(p1);

			Run r = new Run();
			r.setPerson(p1);
			r.setDate("2013-05-07");
			r.setType("longrun");
			em.persist(r);
			
			Lap l1 = new Lap();
			l1.setNumber(1);
			l1.setDistanceM(3000);
			l1.setTimeS(900);
			l1.setRun(r);
			em.persist(l1);

			Lap l2 = new Lap();
			l2.setNumber(2);
			l2.setDistanceM(2500);
			l2.setTimeS(700);
			l2.setRun(r);
			em.persist(l2);
			
			r.setLaps(new ArrayList<Lap>(Arrays.asList(l1, l2)));
		}
	}
}
