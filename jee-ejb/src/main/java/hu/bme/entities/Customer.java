package hu.bme.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;    
	private String addr;
	private String tel;

	@OneToMany(mappedBy="sender")//,orphanRemoval=true, cascade={CascadeType.ALL})
	private Collection<Delivery> deliveries_sended;
	
	@OneToMany(mappedBy="receiver")//,orphanRemoval=true, cascade={CascadeType.ALL})
	private Collection<Delivery> deliveries_received;


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

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
	
	public Collection<Delivery> getDeliveries_sended() {
		return deliveries_sended;
	}

	public void setDeliveries_sended(Collection<Delivery> deliveries_sended) {
		this.deliveries_sended = deliveries_sended;
	}

	public Collection<Delivery> getDeliveries_received() {
		return deliveries_received;
	}

	public void setDeliveries_received(Collection<Delivery> deliveries_received) {
		this.deliveries_received = deliveries_received;
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
        final Customer other = (Customer) obj;
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
		return "Customer [id=" + id + ", name=" + name + ", addr=" + addr
				+ ", tel=" + tel + ", deliveries_sended=" + deliveries_sended
				+ ", deliveries_received=" + deliveries_received + "]";
	}




    
}
