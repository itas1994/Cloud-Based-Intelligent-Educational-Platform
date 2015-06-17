package action.debate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

import Dao.Dao;
import bean.debateBean;

public class DebateAction {
	private ArrayList delist=new ArrayList();
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList getDelist() {
		return delist;
	}

	public void setDelist(ArrayList delist) {
		this.delist = delist;
	}
	
	public String execute() throws SQLException{
		Dao dao=new Dao();
		
		Map<String, Object> session = ActionContext.getContext().getSession();
        String usrid = session.get("USRID").toString(); 
        name=dao.getUsrName(usrid);
        
		delist=(ArrayList) dao.getDebate();
		return "success";
	}
}
