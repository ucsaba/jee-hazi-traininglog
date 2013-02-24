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
}
