package hu.bme;

import hu.bme.entities.Person;
import hu.bme.entities.Run;

import java.io.Serializable;
import java.util.Collection;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import lombok.Getter;
import lombok.Setter;

@ManagedBean
//@RequestScoped
@SessionScoped
public class TestManagedBean implements Serializable {
	private static final long serialVersionUID = 1L;
    
    @EJB
    @Getter @Setter private TestSessionBean testSessionBean;
    
    //----- Person -----
    @Getter @Setter private String name;
    @Getter @Setter private Collection<Run> runs;
	
	//----- Run -----
    @Getter @Setter private String personId;
    @Getter @Setter private String type;
    @Getter @Setter private String date;
    @Getter @Setter private Collection<String> chosenLaps; 

	//----- Lap -----
    @Getter @Setter private String lapId;
    @Getter @Setter private String number;
    @Getter @Setter private String distance;
    @Getter @Setter private String time;    

    public Collection<Run> getRunsByPersonId() {
    	if(personId == null) return null;
    	return testSessionBean.getRunsByPersonId(personId);
    }
    
	public String doPerson(){
        testSessionBean.addPerson(name);
        return null;
    }
	
	public String doRun(){
        testSessionBean.addRun(personId, type, date, chosenLaps);
        return null;
    }
	
	public String doLap(){
        testSessionBean.addLap(number, distance, time);
        return null;
    }
	
	public void deletePerson(Person p) {
		testSessionBean.deletePerson(p);
	}

	public void toEditPerson(Person p) {
//		Redirect redirect = Redirect.getInstance();
//		redirect.setViewId("/xxx.xhtml");
	}
	
}
