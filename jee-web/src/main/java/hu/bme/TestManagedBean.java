package hu.bme;

import hu.bme.entities.Person;

import java.util.Collection;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import lombok.Getter;
import lombok.Setter;

@ManagedBean
@RequestScoped
public class TestManagedBean {

	@EJB
	@Getter
	@Setter
	private TestSessionBean testSessionBean;

	// ----- Person -----
	@Getter
	@Setter
	private String name;

	// ----- Run -----
	@Getter
	@Setter
	private String personId;
	@Getter
	@Setter
	private String type;
	@Getter
	@Setter
	private String date;
	@Getter
	@Setter
	private Collection<String> chosenLaps;

	// ----- Lap -----
	@Getter
	@Setter
	private String lapId;
	@Getter
	@Setter
	private String number;
	@Getter
	@Setter
	private String distance;
	@Getter
	@Setter
	private String time;

	public String doPerson() {
		testSessionBean.addPerson(name);
		return null;
	}

	public String doRun() {
		System.out.println("size of chosenLaps: " + chosenLaps.size());
		testSessionBean.addRun(personId, type, date, chosenLaps);
		return null;
	}

	public String doLap() {
		testSessionBean.addLap(number, distance, time);
		return null;
	}

	public void deletePerson(Person p) {
		testSessionBean.deletePerson(p);
	}

	public void toEditPerson(Person p) {
		// Redirect redirect = Redirect.getInstance();
		// redirect.setViewId("/xxx.xhtml");
	}

}
