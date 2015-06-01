package action.homework;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import Dao.Dao;

public class homeworkContentAction {
	
	private int id;
	private String title;
	private String content;
	private ArrayList holist;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public ArrayList getHolist() {
		return holist;
	}

	public void setHolist(ArrayList holist) {
		this.holist = holist;
	}

	public String execute() throws SQLException{
		HttpServletRequest rq=ServletActionContext.getRequest();
		id=Integer.parseInt(rq.getParameter("id"));
		Dao dao=new Dao();
		title=dao.getHomeworkTitle(id);
		String issueteacher=dao.getIssueusr(id);
		content=dao.getHomeworkContent(id, issueteacher);
		holist=(ArrayList) dao.answer(id, issueteacher);
		return null;
	}

}
