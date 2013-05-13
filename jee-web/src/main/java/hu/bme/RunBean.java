package hu.bme;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import lombok.Getter;
import lombok.Setter;

@ManagedBean
@RequestScoped
public class RunBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	@Getter	@Setter	private TestSessionBean testSessionBean;
	
	@ManagedProperty("#{param.runId}")
	@Getter @Setter private String id;
	@ManagedProperty("#{param.runPersonId}")
	@Getter @Setter private Long personId;
	@ManagedProperty("#{param.runType}")
	@Getter @Setter private String type;
	@ManagedProperty("#{param.runDate}")
	@Getter @Setter private String date;
	@ManagedProperty("#{param.lapNumber}")
	@Getter @Setter private String lapNumber;
	@ManagedProperty("#{param.lapDistance}")
	@Getter @Setter private String lapDistance;
	@ManagedProperty("#{param.lapTime}")
	@Getter @Setter private String lapTime;
	
	public String edit(){
		System.out.println(id+" "+type+" "+date);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			df.parse(date);
			if(!testSessionBean.updateRun(id, type, date)) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "There is no run with id " + id, null));
				return null;
			}
			if(lapNumber.isEmpty() || lapDistance.isEmpty() || lapTime.isEmpty()) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Some \"Add lap\" fields are empty", null));
			}
			else {
				testSessionBean.addLapToRun(id, lapNumber, lapDistance, lapTime);
			}
	        return "edited";
		} catch (ParseException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid date format. Valid format: yyyy-mm-dd", null));
		}
		return null;
    }
	
	public String delete() {
		testSessionBean.deleteRun(id);
        return "edited";
	}
}
