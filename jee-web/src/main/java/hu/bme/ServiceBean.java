package hu.bme;

import hu.bme.entities.Run;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class ServiceBean {
	@EJB
	private SessionBean sessionBean;

	@WebMethod
	public List<Run> getRun(String personId, String date) throws Exception {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			df.parse(date);
			List<Run> list = sessionBean.getRunsByPersonIdAndDate(personId,
					date);

			System.out.println("getRun list.size " + list.size());
			if (list.isEmpty()) {
				throw new Exception("There is no run with personid " + personId
						+ " and date " + date);
			}
			return list;
		} catch (ParseException e) {
			throw new Exception("Invalid date format. Valid format: yyyy-mm-dd");
		}

	}

	@WebMethod(exclude = true)
	public SessionBean getSessionBean() {
		return sessionBean;
	}

	@WebMethod(exclude = true)
	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

}
