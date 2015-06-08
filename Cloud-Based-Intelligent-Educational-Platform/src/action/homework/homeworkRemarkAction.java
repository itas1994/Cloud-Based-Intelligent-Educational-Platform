package action.homework;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.struts2.ServletActionContext;
import org.xml.sax.SAXException;

import Dao.Dao;

public class homeworkRemarkAction {
	
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

	public String execute() throws SQLException, ParserConfigurationException, SAXException, IOException{
		HttpServletRequest rq=ServletActionContext.getRequest();
		id=Integer.parseInt(rq.getParameter("id"));
		String ausr=rq.getParameter("ausr");
		String aremark=rq.getParameter("aremark");
		Dao dao=new Dao();
		String datatable="homework";
		title=dao.getHOandTETitle(datatable, id);
		String issueteacher=dao.getIssueusr(id);
		dao.insertTeacherRemark(id, issueteacher,ausr,aremark);
		
		content=dao.getHomeworkContent(id, issueteacher);
		holist=(ArrayList) dao.answer4Homework(id, issueteacher);
		return "success";
	}

}
