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
public class SessionBean {

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

	public List<Run> getRunsByPersonId(String personId) {
		Person person = em.find(Person.class, Long.parseLong(personId));
		List<Run> list = (List<Run>) person.getRuns();
		return list;
	}

	public boolean updateRun(String id, String type, String date) {
		Run r = em.find(Run.class, Long.parseLong(id));
		if (r != null) {
			r.setType(type);
			r.setDate(date);
			em.merge(r);
			return true;
		}
		return false;
	}

	public void deleteRun(String id) {
		System.out.println("runid " + id);
		Run r = em.find(Run.class, Long.parseLong(id));
		Long personId = r.getPerson().getId();
		System.out.println("personId " + personId);
		Person person = em.find(Person.class, personId);
		System.out.println("runs size " + person.getRuns().size());
		person.getRuns().remove(r);
		System.out.println("runs size " + person.getRuns().size());
		em.remove(r);
	}

	// ------ Lap ------
	public void addLap(String number, String distance, String time) {
		Lap l = new Lap();
		l.setNumber(Integer.parseInt(number));
		l.setDistanceM(Integer.parseInt(distance));
		l.setTimeS(Integer.parseInt(time));
		em.persist(l);
	}

	public boolean addLapToRun(String runId, String number, String distance,
			String time) {
		Run r = em.find(Run.class, Long.parseLong(runId));
		if (r != null) {
			Lap l = new Lap();
			l.setNumber(Integer.parseInt(number));
			l.setDistanceM(Integer.parseInt(distance));
			l.setTimeS(Integer.parseInt(time));
			l.setRun(r);
			em.merge(l);

			r.addLap(l);
			return true;
		}
		return false;
	}

	public boolean updateLap(String id, String number, String distance,
			String time) {
		Lap l = em.find(Lap.class, Long.parseLong(id));
		if (l != null) {
			l.setNumber(Integer.parseInt(number));
			l.setDistanceM(Integer.parseInt(distance));
			l.setTimeS(Integer.parseInt(time));
			em.merge(l);
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<Lap> getOrphanLaps() {
		return (List<Lap>) em.createQuery(
				"SELECT a FROM Lap a WHERE a.run IS NULL").getResultList();
	}

	public void deleteLap(String id) {
		Lap l = em.find(Lap.class, Long.parseLong(id));
		em.remove(l);
		Run r = em.find(Run.class, l.getRun().getId());
		Person p = em.find(Person.class, r.getPerson().getId());
		r.getLaps().remove(l);
		p.getRuns().remove(r);
		if (r.getLaps().size() > 0) {
			p.addRun(r);
		}
	}

}
