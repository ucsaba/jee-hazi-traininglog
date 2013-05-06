package hu.bme;

import hu.bme.entities.Customer;
import hu.bme.entities.Runner;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class TestManagedBean {
    
    @EJB
    private TestSessionBean testSessionBean;
    
    public TestSessionBean getTestSessionBean() {
        return testSessionBean;
    }
//--------------------------------- Customer & Runner -----------------------------------------------
    private String name;
	private String addr;
	private String tel;
	
	private String uname;
	private String pwd;
	private Boolean dispatcher;
	
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

	public Boolean getDispatcher() {
		return dispatcher;
	}

	public void setDispatcher(Boolean dispatcher) {
		this.dispatcher = dispatcher;
	}

	public void setTestSessionBean(TestSessionBean testSessionBean) {
		this.testSessionBean = testSessionBean;
	}

	public String doRunner(){
        testSessionBean.addRunner(name, uname, pwd, tel, dispatcher);
        return null;
    }
	
	public String doCustomer(){
        testSessionBean.addCustomer(name, addr, tel);
        return null;
    }


//--------------------------------- Delivery -----------------------------------------------
    private String item;
    private Customer sender;
    private Customer receiver;
    private Runner runner;
    

    public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public Customer getSender() {
		return sender;
	}

	public void setSender(Customer sender) {
		this.sender = sender;
	}

	public Customer getReceiver() {
		return receiver;
	}

	public void setReceiver(Customer receiver) {
		this.receiver = receiver;
	}

	public Runner getRunner() {
		return runner;
	}

	public void setRunner(Runner runner) {
		this.runner = runner;
	}
	
	public String doDelivery(){
        testSessionBean.addDelivery(item,sender,receiver,runner);
        return null;
    }
	
}
