package action;

import java.sql.SQLException;
import java.util.Map;

import Dao.Dao;

import com.opensymphony.xwork2.ActionContext;

public class backhostAction {
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
        
		return "success";
	}
}
