package hu.bme.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlTransient;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Run implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter @Setter private Long id;
	@Getter @Setter private String type;
	@Getter @Setter private String date;

	@ManyToOne
	private Person person;

	@OneToMany(mappedBy="run", cascade={CascadeType.ALL}, orphanRemoval=true)
	@Getter @Setter private Collection<Lap> laps;

	public void setPerson(Person person) {
		this.person = person;
        if (person.getRuns() != null && !person.getRuns().contains(this)) {
        	person.getRuns().add(this);
        }
	}
	
	@XmlTransient
	public Person getPerson() {
		return person;
	}
	
	public void addLap(Lap lap) {
		if(laps != null) {
			laps.add(lap);
	        if (lap.getRun() != this) {
	        	lap.setRun(this);
	        }
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
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
		Run other = (Run) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Run [id=" + id + ", type=" + type + ", date=" + date
				+ ", person=" + person + ", laps=" + laps.size() + "]";
	}

}
