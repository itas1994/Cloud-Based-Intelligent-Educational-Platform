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
	private String name;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private Map session;
    public void setSession(Map session) {
        this.session = session;
    }
	
	public String execute() throws SQLException {
		HttpServletRequest request=ServletActionContext.getRequest();
		String usr=request.getParameter("usr");
		String psd=request.getParameter("psd");
		Dao dao=new Dao();
		name=dao.getUsrName(usr);
		String r=dao.login(usr,psd);
		String result="";
		if(r=="success"){
			this.session.put("USRID",usr);
			String authority=dao.getAuthority(session.get("USRID").toString());
			if("t".equals(authority))
				result="t_success";
			else if("s".equals(authority))
				result="s_success";
		}else if(r=="psd_error"){
			result="psd_error";
		}else{
			result="id_error";
		}
		
		return result;
	}
}