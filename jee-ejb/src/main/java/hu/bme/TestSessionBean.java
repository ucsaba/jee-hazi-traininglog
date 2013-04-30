package hu.bme;

import hu.bme.entities.TestEntity;
import hu.bme.entities.Delivery;
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
    
   
    public void addDelivery(String item, Long sender, Long receiver, Long runner){
        Delivery d=new Delivery();
        d.setItem(item);
        d.setSender(sender);
        d.setReceiver(receiver);
        d.setRunner(runner);
        em.persist(d);
    }

    public List<Delivery> getDeliveries(){
        return (List<Delivery>)em.createQuery("SELECT a FROM Delivery a").getResultList();
    }

    public void addTestEntity(String data){
        TestEntity te=new TestEntity();
        te.setDataProperty(data);
        em.persist(te);
    }

    public List<TestEntity> getTestEntities(){
        return (List<TestEntity>)em.createQuery("SELECT a FROM TestEntity a").getResultList();
    }


}
