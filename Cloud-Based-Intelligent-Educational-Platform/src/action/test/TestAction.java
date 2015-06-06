package action.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.homeworkBean;
import Dao.Dao;

public class TestAction {
		
	private ArrayList telist;
	
	public ArrayList getTelist() {
		return telist;
	}

	public void setTelist(ArrayList telist) {
		this.telist = telist;
	}

	public String execute() throws SQLException{
		String usrid="t001";
		String result="";
		Dao dao=new Dao();
		String authority=dao.getAuthority(usrid);
		if("s"==authority){
			telist=(ArrayList) dao.allTest();
			result="student";
		}
		else if("t" == authority){
			telist=(ArrayList) dao.getOwnHomework(usrid);
			result="teacher";
		}
		return result;
	}
}
