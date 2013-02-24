package hu.bme;

import hu.bme.entities.TestEntity;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
@LocalBean
public class TestSessionBean {
    
    @PersistenceContext
    EntityManager em;
    
    public void addTestEntity(String data){
        TestEntity te=new TestEntity();
        te.setDataProperty(data);
        em.persist(te);
    }

    public List<TestEntity> getTestEntities(){
        return (List<TestEntity>)em.createQuery("SELECT a FROM TestEntity a").getResultList();
    }
}
