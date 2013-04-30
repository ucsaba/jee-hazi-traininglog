package hu.bme;

import hu.bme.entities.TestEntity;
import hu.bme.entities.Delivery;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
@Startup
public class StartupListener {
    
    @PersistenceContext
    EntityManager em;
    
    @PostConstruct
    public void onStartup(){
        if(em.createQuery("SELECT a FROM TestEntity a").setMaxResults(1).getResultList().isEmpty()){
            System.out.println("Database is empty, populating TestEntity with default data");
            {   
                TestEntity te=new TestEntity();
                te.setDataProperty("data1");
                em.persist(te);
            }
            {   
                TestEntity te=new TestEntity();
                te.setDataProperty("data2");
                em.persist(te);
            }
        }
	if(em.createQuery("SELECT a FROM Delivery a").setMaxResults(1).getResultList().isEmpty()){
            System.out.println("Database is empty, populating Delivery with default data");
            {   
                Delivery d=new Delivery();
                d.setItem("bunch of carrots");
                d.setSender(new Long("1"));
                d.setReceiver(new Long("2"));
                d.setRunner(new Long("3"));
                em.persist(d);
            }
            {   
                Delivery d=new Delivery();
		d.setItem("Alestorm CD");
                d.setSender(new Long("2"));
                d.setReceiver(new Long("1"));
                d.setRunner(new Long("4"));
                em.persist(d);
            }
        }

    }
}
