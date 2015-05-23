package action.debate;

import java.sql.SQLException;
import java.util.ArrayList;

import Dao.Dao;
import bean.debateBean;

public class DebateAction {
	private ArrayList delist=new ArrayList();

	public ArrayList getDelist() {
		return delist;
	}

	public void setDelist(ArrayList delist) {
		this.delist = delist;
	}
	
	public String execute() throws SQLException{
		Dao dao=new Dao();
		delist=(ArrayList) dao.getDebate();
		return "success";
	}
}
