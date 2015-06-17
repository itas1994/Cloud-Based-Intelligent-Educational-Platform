package action.resource;

import java.sql.SQLException;
import java.util.Map;

import Dao.Dao;

import com.opensymphony.xwork2.ActionContext;

public class conveyName4ResourceAction {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String execute() throws SQLException{
		Dao dao=new Dao();
		
		Map<String, Object> session = ActionContext.getContext().getSession();
        String usrid = session.get("USRID").toString(); 
        name=dao.getUsrName(usrid);
        
        return "success";
	}
}
