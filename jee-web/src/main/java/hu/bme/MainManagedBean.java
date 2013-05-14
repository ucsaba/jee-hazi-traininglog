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
public class MainManagedBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	@Getter	@Setter	private SessionBean sessionBean;

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
			return sessionBean.getRunsByPersonId(personId);
		}
		return null;
	}

	public String doPerson() {
		if (name == null || name.isEmpty()) {
			showErrorMessage("Name is null or empty");
		}
		else {
			sessionBean.addPerson(name);
		}
		return null;
	}

	public String doRun() {
		if (chosenLaps.size() == 0) {
			showErrorMessage("No free laps, create at least one");
		}
		else if (type.isEmpty() || date.isEmpty()) {
			showErrorMessage("Some \"New run\" fields are empty");
		}
		else {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			try {
				df.parse(date);
				sessionBean.addRun(personId, type, date, chosenLaps);
				
				getRunsByPersonId();
				
			} catch (ParseException e) {
				showErrorMessage("Invalid date format. Valid format: yyyy-mm-dd");
			}
		}
		return null;
	}

	public String doLap() {
		if (number.isEmpty() || distance.isEmpty() || time.isEmpty()) {
			showErrorMessage("Some \"New lap\" fields are empty");
		}
		else {
			sessionBean.addLap(number, distance, time);
		}
		return null;
	}
	
	private void showErrorMessage(String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null));
	}
	
}
