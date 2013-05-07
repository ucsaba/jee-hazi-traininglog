package hu.bme;

import java.lang.ProcessBuilder.Redirect;

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
	private String dispatcher;
	
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

	public String getDispatcher() {
		return dispatcher;
	}

	public void setDispatcher(String dispatcher) {
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
	
	public void deleteCustomer(Customer c) {
		testSessionBean.deleteCustomer(c);
	}

	public void toEditCustomer(Customer c) {
//		Redirect redirect = Redirect.getInstance();
//		redirect.setViewId("/xxx.xhtml");
	}
	
//--------------------------------- Delivery -----------------------------------------------
    private String item;
    private String deliverySender;
    private String deliveryReceiver;
    private String deliveryRunner;

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getDeliverySender() {
		return deliverySender;
	}

	public void setDeliverySender(String deliverySender) {
		this.deliverySender = deliverySender;
	}

	public String getDeliveryReceiver() {
		return deliveryReceiver;
	}

	public void setDeliveryReceiver(String deliveryReceiver) {
		this.deliveryReceiver = deliveryReceiver;
	}

	public String getDeliveryRunner() {
		return deliveryRunner;
	}

	public void setDeliveryRunner(String deliveryRunner) {
		this.deliveryRunner = deliveryRunner;
	}

	public String doDelivery(){
        testSessionBean.addDelivery(item,deliverySender,deliveryReceiver,deliveryRunner);
        return null;
    }
	
}
