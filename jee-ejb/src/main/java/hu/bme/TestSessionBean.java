package hu.bme;

import hu.bme.entities.Lap;
import hu.bme.entities.Person;
import hu.bme.entities.Run;

import java.util.ArrayList;
import java.util.Collection;
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
		em.persist(p);
	}

	@SuppressWarnings("unchecked")
	public List<Person> getPersons() {
		return (List<Person>) em.createQuery("SELECT a FROM Person a")
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	public Person getPerson(String personId) {
		return ((List<Person>) em
				.createQuery("SELECT a FROM Person a WHERE a.id=" + personId)
				.setMaxResults(1).getResultList()).get(0);
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
	public void addRun(String personId, String type, String date,
			Collection<String> lapIds) {
		Person person = em.find(Person.class, Long.parseLong(personId));

		Run r = new Run();
		r.setPerson(person);
		r.setType(type);
		r.setDate(date);

		List<Lap> chosenLaps = new ArrayList<Lap>();
		for (String id : lapIds) {
			Lap l = em.find(Lap.class, Long.parseLong(id));
			l.setRun(r);
			chosenLaps.add(l);
		}

		r.setLaps(chosenLaps);
		em.persist(r);
	}

	@SuppressWarnings("unchecked")
	public List<Run> getRuns() {
		return (List<Run>) em.createQuery("SELECT a FROM Run a")
				.getResultList();
	}

	public List<Run> getRunsByPersonId(String personId) {
		Person person = em.find(Person.class, Long.parseLong(personId));
		return (List<Run>) person.getRuns();
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

	@SuppressWarnings("unchecked")
	public List<Lap> getLaps() {
		return (List<Lap>) em.createQuery(
				"SELECT a FROM Lap a WHERE a.run IS NULL").getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Lap> getOrphanLaps() {
		return (List<Lap>) em.createQuery(
				"SELECT a FROM Lap a WHERE a.run IS NULL").getResultList();
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
