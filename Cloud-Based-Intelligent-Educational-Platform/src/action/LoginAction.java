package action;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import Dao.Dao;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class LoginAction extends ActionSupport implements SessionAware{

	/**
	 * @return
	 */
	private Map session;
    public void setSession(Map session) {
        this.session = session;
    }
	
	public String execute() throws SQLException {
		HttpServletRequest request=ServletActionContext.getRequest();
		String usr=request.getParameter("usr");
		String psd=request.getParameter("psd");
		Dao dao=new Dao();
		String r=dao.login(usr,psd);
		if(r=="success"){
			this.session.put(usr,psd);
			return "success";
		}else if(r=="psd_error"){
			return "psd_error";
		}else{
			return "id_error";
		}
	}
}