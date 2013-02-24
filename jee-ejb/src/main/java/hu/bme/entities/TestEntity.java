package hu.bme.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TestEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String dataProperty;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDataProperty() {
        return dataProperty;
    }

    public void setDataProperty(String dataProperty) {
        this.dataProperty = dataProperty;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 19 * hash + (this.dataProperty != null ? this.dataProperty.hashCode() : 0);
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
        final TestEntity other = (TestEntity) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if ((this.dataProperty == null) ? (other.dataProperty != null) : !this.dataProperty.equals(other.dataProperty)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TestEntity{" + "id=" + id + ", dataProperty=" + dataProperty + '}';
    }


    
}
