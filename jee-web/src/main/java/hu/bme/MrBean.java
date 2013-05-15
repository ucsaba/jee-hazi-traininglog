package hu.bme;

import hu.bme.entities.Person;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import lombok.Getter;
import lombok.Setter;

@ManagedBean
@SessionScoped
public class MrBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	@Getter	@Setter	private SessionBean sessionBean;

	@Getter	@Setter	private String id;
	@Getter	@Setter	private String userName;
	@Getter	@Setter	private String password;
	@Getter	@Setter	private String result;
	@Getter	@Setter	private boolean isLoggedIn;
	@Getter	@Setter	private boolean isAdmin;
	
	public String login() {
		System.out.println("login " + userName + " " + password);
		
		Person p = sessionBean.authPerson(userName, password);
		if(p != null) {
			id = p.getId().toString();
			userName = p.getName();
			password = p.getPwd();
			isAdmin = p.isAdmin();
			isLoggedIn = true;
			return "success";
		}
		
		isAdmin = false;
		isLoggedIn = false;
		return "fail";
	}
	
	public String logout() {
		System.out.println("logout " + userName + " " + password);

		id = null;
		userName = null;
		password = null;
		isAdmin = false;
		isLoggedIn = false;
		return "logout";
	}

	
}
