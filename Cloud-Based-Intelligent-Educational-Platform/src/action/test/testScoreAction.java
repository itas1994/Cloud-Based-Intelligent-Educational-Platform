package action.test;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import Dao.Dao;

public class testScoreAction {
	
	private int id;
	private String title;
	private String content;
	private ArrayList telist;

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

	public ArrayList getTelist() {
		return telist;
	}

	public void setTelist(ArrayList telist) {
		this.telist = telist;
	}
	
	public String execute() throws SQLException{
		HttpServletRequest rq=ServletActionContext.getRequest();
		id=Integer.parseInt(rq.getParameter("id"));
		String ausr=rq.getParameter("ausr");
		String ascore=rq.getParameter("ascore");
		Dao dao=new Dao();
		String datatable="test";
		title=dao.getHOandTETitle(datatable, id);
		String issueteacher=dao.getIssueusr(id);
		String filename="homework_answer_content";
		dao.insertTeacherRemark(id, issueteacher,ausr,ascore);
		
		content=dao.getHOandTEContent(filename, id, issueteacher);
		telist=(ArrayList) dao.dotest(id, issueteacher);
		return "success";
	}
	
}
