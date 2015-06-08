package action;

import java.sql.SQLException;
import java.util.Map;

import Dao.Dao;

import com.opensymphony.xwork2.ActionContext;

public class backhostAction {
	
	public String execute() throws SQLException{
		Map<String, Object> session = ActionContext.getContext().getSession();
        String usrid = session.get("USRID").toString(); 
        
        Dao dao=new Dao();
        String authority=dao.getAuthority(usrid);
        
		return authority;
	}
}
