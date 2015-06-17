package action.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

import bean.homeworkBean;
import Dao.Dao;

public class TestAction {
		
	private ArrayList stelist;
	private ArrayList ttelist;
	private String name;

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	public ArrayList getStelist() {
		return stelist;
	}

	public void setStelist(ArrayList stelist) {
		this.stelist = stelist;
	}

	public ArrayList getTtelist() {
		return ttelist;
	}

	public void setTtelist(ArrayList ttelist) {
		this.ttelist = ttelist;
	}

	public String execute() throws SQLException{
		Dao dao=new Dao();
		
		Map<String, Object> session = ActionContext.getContext().getSession();
        String usrid = session.get("USRID").toString(); 
        name=dao.getUsrName(usrid);
		
		String authority=dao.getAuthority(usrid);
		stelist=(ArrayList) dao.allTest();
		ttelist=(ArrayList) dao.getOwnHomework(usrid);
		return authority;
	}
}
