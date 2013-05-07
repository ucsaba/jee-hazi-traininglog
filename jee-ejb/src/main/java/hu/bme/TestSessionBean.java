package hu.bme;

import hu.bme.entities.Customer;
import hu.bme.entities.Runner;
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
    
//---------------------------------------- Delivery ----------------------------------------   
    public void addDelivery(String item, String senderID, String receiverID, String runnerID){
    	Customer sender = em.find(Customer.class, Long.valueOf(senderID));
    	Customer receiver = em.find(Customer.class, Long.valueOf(receiverID));
    	Runner runner = em.find(Runner.class, Long.valueOf(runnerID));
    	
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
    
    public void deleteDelivery(Delivery d) {
		d = em.merge(d);
		em.remove(d);
	}
    
    public Delivery saveDelivery(Delivery d) {
		em.persist(d);
		return d;
	}
//---------------------------------------- Customer ----------------------------------------
    public void addCustomer(String name, String addr, String tel){
        Customer c= new Customer();
        c.setName(name);
        c.setAddr(addr);
        c.setTel(tel);
        em.persist(c);
    }

    public List<Customer> getCustomers(){
        return (List<Customer>)em.createQuery("SELECT a FROM Customer a").getResultList();
    }
    
    public void deleteCustomer(Customer c) {
		c = em.merge(c);
		em.remove(c);
	}
    
    public Customer saveCustomer(Customer c) {
		em.persist(c);
		return c;
	}
//---------------------------------------- Runner ----------------------------------------    
    public void addRunner(String name, String uname, String pwd, String tel, String dispatcher){
        Runner r= new Runner();
        r.setName(name);
        r.setUname(uname);
        r.setPwd(pwd);
        r.setTel(tel);
        r.setDispatcher(Boolean.valueOf(dispatcher));
        em.persist(r);
    }

    public List<Runner> getRunners(){
        return (List<Runner>)em.createQuery("SELECT a FROM Runner a").getResultList();
    }

    public void deleteRunner(Runner r) {
		r = em.merge(r);
		em.remove(r);
	}
    
}
