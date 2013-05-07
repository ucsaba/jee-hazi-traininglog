package hu.bme;

import hu.bme.entities.Lap;
import hu.bme.entities.Person;
import hu.bme.entities.Run;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class TestSessionBean {

	@PersistenceContext
	EntityManager em;

	// ------ Person ------
	public void addPerson(String name) {
		Person p = new Person();
		p.setName(name);
		p.setRuns(null);
		em.persist(p);
	}

	public List<Person> getPersons() {
		return (List<Person>) em.createQuery("SELECT a FROM Person a")
				.getResultList();
	}

	public void deletePerson(Person p) {
		p = em.merge(p);
		em.remove(p);
	}

	public Person savePerson(Person p) {
		em.persist(p);
		return p;
	}

	// ------ Run ------
	public void addRun(String personId, String type, String date) {
		Run r = new Run();
		r.setPersonId(Long.parseLong(personId));
		r.setType(type);
		r.setDate(date);
		r.setLaps(null);
		em.persist(r);
	}

	public List<Run> getRuns() {
		return (List<Run>) em.createQuery("SELECT a FROM Run a")
				.getResultList();
	}

	public void deleteRun(Run r) {
		r = em.merge(r);
		em.remove(r);
	}

	public Run saveRun(Run r) {
		em.persist(r);
		return r;
	}

	// ------ Lap ------
	public void addLap(String number, String distance, String time) {
		Lap l = new Lap();
		l.setNumber(Integer.parseInt(number));
		l.setDistanceM(Integer.parseInt(distance));
		l.setTimeS(Integer.parseInt(time));
		em.persist(l);
	}

	public List<Lap> getLaps() {
		return (List<Lap>) em.createQuery("SELECT a FROM Lap a")
				.getResultList();
	}

	public void deleteLap(Lap l) {
		l = em.merge(l);
		em.remove(l);
	}

	public Lap saveLap(Lap l) {
		em.persist(l);
		return l;
	}

}
