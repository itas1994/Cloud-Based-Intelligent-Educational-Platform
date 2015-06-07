package action.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.homeworkBean;
import Dao.Dao;

public class TestAction {
		
	private ArrayList stelist;
	private ArrayList ttelist;

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
		String usrid="t001";
		String result="";
		Dao dao=new Dao();
		String authority=dao.getAuthority(usrid);
		stelist=(ArrayList) dao.allTest();
		ttelist=(ArrayList) dao.getOwnHomework(usrid);
		return authority;
	}
}
