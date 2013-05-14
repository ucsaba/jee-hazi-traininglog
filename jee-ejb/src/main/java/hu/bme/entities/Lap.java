package hu.bme.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlTransient;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Lap implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter private Long id;
    @Getter @Setter private Integer number;
    @Getter @Setter private Integer distanceM;
    @Getter @Setter private Integer timeS;
    
    @ManyToOne
    private Run run;

    public void setRun(Run run) {
		this.run = run;
        if (run.getLaps() != null && !run.getLaps().contains(this)) {
        	run.getLaps().add(this);
        }
	}

    @XmlTransient
    public Run getRun() {
		return run;
	}
    
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((distanceM == null) ? 0 : distanceM.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		result = prime * result + ((timeS == null) ? 0 : timeS.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lap other = (Lap) obj;
		if (distanceM == null) {
			if (other.distanceM != null)
				return false;
		} else if (!distanceM.equals(other.distanceM))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		if (timeS == null) {
			if (other.timeS != null)
				return false;
		} else if (!timeS.equals(other.timeS))
			return false;
		return true;
	}

}
