package action.debate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import Dao.Dao;

public class debateContentAction {
	private List delist=new ArrayList();
	private String title;
	private int id;
	public List getDelist() {
		return delist;
	}

	public void setDelist(List delist) {
		this.delist = delist;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String execute() throws SQLException{
		HttpServletRequest rq=ServletActionContext.getRequest();
		id=Integer.parseInt(rq.getParameter("id"));
		String issueusr="itas1994";
		Dao dao=new Dao();
		delist=dao.getDebateContent(id, issueusr);
		title=dao.getDebateTitle(id);
		return "success";
	}
}