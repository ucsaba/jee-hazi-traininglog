package hu.bme;

import java.io.Serializable;

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
public class LapBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	@Getter @Setter private SessionBean sessionBean;

	@ManagedProperty("#{param.lapId}")
	@Getter @Setter private String id;
	@ManagedProperty("#{param.lapNumber}")
	@Getter @Setter private String number;
	@ManagedProperty("#{param.lapDistance}")
	@Getter @Setter private String distance;
	@ManagedProperty("#{param.lapTime}")
	@Getter @Setter private String time;

	public String edit() {
		System.out.println(id + " " + number + " " + distance + " " + time);

		if (!sessionBean.updateLap(id, number, distance, time)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "There is no run with id " + id, null));
			return null;
		}
		return "edited";
	}
	
	public String delete() {
		sessionBean.deleteLap(id);
        return "edited";
	}

}
