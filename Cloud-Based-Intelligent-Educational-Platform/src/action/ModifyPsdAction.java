package action;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import Dao.Dao;

import com.opensymphony.xwork2.ActionContext;

public class ModifyPsdAction {

	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String execute() throws SQLException{
		Map<String, Object> session = ActionContext.getContext().getSession();
        String usrid = session.get("USRID").toString(); 
        
        Dao dao=new Dao();
        name=dao.getUsrName(usrid);
		
		HttpServletRequest rq=ServletActionContext.getRequest();
		String new_psd=rq.getParameter("new_psd");
		dao.modifyPsd(usrid, new_psd);
		
		return "success";
	}
}
