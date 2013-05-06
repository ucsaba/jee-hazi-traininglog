package hu.bme.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Runner implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String name;
    @Column(unique=true)
	private String uname;
	private String pwd;
	private String tel;
	private Boolean dispatcher;
    
	@OneToMany(mappedBy="runner")//,orphanRemoval=true, cascade={CascadeType.ALL})
	private Collection<Delivery> deliveries;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Boolean getDispatcher() {
		return dispatcher;
	}

	public void setDispatcher(Boolean dispatcher) {
		this.dispatcher = dispatcher;
	}

	public Collection<Delivery> getDeliveries() {
		return deliveries;
	}

	public void setDeliveries(Collection<Delivery> deliveries) {
		this.deliveries = deliveries;
	}

	@Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + (this.id != null ? this.id.hashCode() : 0);
       /* hash = 19 * hash + (this.item != null ? this.item.hashCode() : 0);
        hash = 19 * hash + (this.sender != null ? this.sender.hashCode() : 0);
        hash = 19 * hash + (this.receiver != null ? this.receiver.hashCode() : 0);
        hash = 19 * hash + (this.runner != null ? this.runner.hashCode() : 0);*/
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Runner other = (Runner) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        /*if ((this.dataProperty == null) ? (other.dataProperty != null) : !this.dataProperty.equals(other.dataProperty)) {
            return false;
        }*/
        return true;
    }

    @Override
	public String toString() {
		return "Runner [id=" + id + ", name=" + name + ", uname=" + uname
				+ ", pwd=" + pwd + ", tel=" + tel + ", dispatcher="
				+ dispatcher + ", deliveries=" + deliveries + "]";
	}


    
}
