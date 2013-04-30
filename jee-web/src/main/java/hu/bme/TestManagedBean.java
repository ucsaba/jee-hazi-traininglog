package hu.bme;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class TestManagedBean {
    
    @EJB
    private TestSessionBean testSessionBean;

    private String testProperty;

    public String getTestProperty() {
        return testProperty;
    }

    public void setTestProperty(String testProperty) {
        this.testProperty = testProperty;
    }
    
    public String doTest(){
        testSessionBean.addTestEntity(testProperty);
        return null;
    }

    public TestSessionBean getTestSessionBean() {
        return testSessionBean;
    }

    private String item;
    private Long sender;
    private Long receiver;
    private Long runner;

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public void setSender(Long sender) {
        this.sender = sender;
    }

    public Long getSender() {
        return sender;
    }

    public Long getReceiver() {
        return receiver;
    }

    public void setReceiver(Long receiver) {
        this.receiver = receiver;
    }

    public Long getRunner() {
        return runner;
    }

    public void setRunner(Long runner) {
        this.runner = runner;
    }

    public String doDelivery(){
        testSessionBean.addDelivery(item,sender,receiver,runner);
        return null;
    }
}
