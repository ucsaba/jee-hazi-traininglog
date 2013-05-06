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
    
   
    public void addDelivery(String item, Customer sender, Customer receiver, Runner runner){
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
    
    public void addRunner(String name, String uname, String pwd, String tel, Boolean dispatcher){
        Runner r= new Runner();
        r.setName(name);
        r.setUname(uname);
        r.setPwd(pwd);
        r.setTel(tel);
        r.setDispatcher(dispatcher);
        em.persist(r);
    }

    public List<Runner> getRunners(){
        return (List<Runner>)em.createQuery("SELECT a FROM Runner a").getResultList();
    }


}
