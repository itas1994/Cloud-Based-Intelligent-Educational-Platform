package action.homework;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

import bean.homeworkBean;
import Dao.Dao;

public class HomeworkAction {
		
	private ArrayList<homeworkBean> sholist;
	private ArrayList<homeworkBean> tholist;
	private String name;

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public ArrayList<homeworkBean> getSholist() {
		return sholist;
	}


	public void setSholist(ArrayList<homeworkBean> sholist) {
		this.sholist = sholist;
	}


	public ArrayList<homeworkBean> getTholist() {
		return tholist;
	}


	public void setTholist(ArrayList<homeworkBean> tholist) {
		this.tholist = tholist;
	}


	public String execute() throws SQLException{
		Dao dao=new Dao();

		Map<String, Object> session = ActionContext.getContext().getSession();
        String usrid = session.get("USRID").toString(); 
        name=dao.getUsrName(usrid);
		
		String authority=dao.getAuthority(usrid);
		sholist=(ArrayList<homeworkBean>) dao.allHomework();
		tholist=(ArrayList<homeworkBean>) dao.getOwnHomework(usrid);
		
		return authority;
	}
}
