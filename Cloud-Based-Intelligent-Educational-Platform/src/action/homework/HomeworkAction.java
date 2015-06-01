package action.homework;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.homeworkBean;
import Dao.Dao;

public class HomeworkAction {
		
	private ArrayList holist;
	
	public ArrayList getHolist() {
		return holist;
	}

	public void setHolist(ArrayList holist) {
		this.holist = holist;
	}

	public String execute() throws SQLException{
		String usrid="t001";
		String result="";
		Dao dao=new Dao();
		String authority=dao.getAuthority(usrid);
		if("s"==authority){
			holist=(ArrayList) dao.allHomework();
			result="student";
		}
		else if("t" == authority){
			holist=(ArrayList) dao.getOwnHomework(usrid);
			result="teacher";
		}
		return result;
	}
}
