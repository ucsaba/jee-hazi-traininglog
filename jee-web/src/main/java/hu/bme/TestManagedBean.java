package hu.bme;

import hu.bme.entities.Run;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import lombok.Getter;
import lombok.Setter;

@ManagedBean
@SessionScoped
public class TestManagedBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	@Getter	@Setter	private TestSessionBean testSessionBean;

	// ----- Person -----
	@Getter	@Setter	private String name;
	@Getter @Setter	private Collection<Run> runs;

	// ----- Run -----
	@Getter	@Setter	private String personId;
	@Getter @Setter	private String type;
	@Getter	@Setter	private String date;
	@Getter	@Setter	private Collection<String> chosenLaps;

	// ----- Lap -----
	@Getter	@Setter	private String lapId;
	@Getter	@Setter	private String number;
	@Getter	@Setter	private String distance;
	@Getter	@Setter	private String time;

	public Collection<Run> getRunsByPersonId() {
		if (personId != null) {
			return testSessionBean.getRunsByPersonId(personId);
		}
		return null;
	}

	public String doPerson() {
		if (name == null || name.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Name is null or empty", null));
		}
		else {
			testSessionBean.addPerson(name);
		}
		return null;
	}

	public String doRun() {
		if (chosenLaps.size() == 0) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No free laps, create at least one", null));
		}
		else if (type.isEmpty() || date.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Some \"New run\" fields are empty", null));
		}
		else {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			try {
				df.parse(date);
				testSessionBean.addRun(personId, type, date, chosenLaps);
				
				getRunsByPersonId();
				
			} catch (ParseException e) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid date format. Valid format: yyyy-mm-dd", null));
			}
		}
		return null;
	}

	public String doLap() {
		if (number.isEmpty() || distance.isEmpty() || time.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Some \"New lap\" fields are empty", null));
		}
		else {
			testSessionBean.addLap(number, distance, time);
		}
		return null;
	}
	
}
