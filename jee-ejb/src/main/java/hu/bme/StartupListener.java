package hu.bme;

import hu.bme.entities.TestEntity;
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
            System.out.println("Database is empty, populating with default data");
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
    }
}
