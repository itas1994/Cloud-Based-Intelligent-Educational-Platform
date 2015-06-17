package action.test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.struts2.ServletActionContext;
import org.xml.sax.SAXException;

import com.opensymphony.xwork2.ActionContext;

import Dao.Dao;

public class testScoreAction {
	
	private int id;
	private String title;
	private String content;
	private ArrayList ttelist;
	private String name;

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

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

	public ArrayList getTtelist() {
		return ttelist;
	}

	public void setTelist(ArrayList ttelist) {
		this.ttelist = ttelist;
	}
	
	public String execute() throws SQLException, ParserConfigurationException, SAXException, IOException{
		HttpServletRequest rq=ServletActionContext.getRequest();
		id=Integer.parseInt(rq.getParameter("id"));
		String ausr=rq.getParameter("ausr");
		String ascore=rq.getParameter("ascore");
		Dao dao=new Dao();
		String datatable="test";
		title=dao.getHOandTETitle(datatable, id);
		String issueteacher=dao.getHOandTEIssueTeacher(datatable, id);
		dao.insertTeacherScore(id, issueteacher,ausr,ascore);
		
		Map<String, Object> session = ActionContext.getContext().getSession();
        String usrid = session.get("USRID").toString(); 
        name=dao.getUsrName(usrid);
		
		content=dao.getTestContent(id, issueteacher);
		ttelist=(ArrayList) dao.answer4Test4Teacher(id, issueteacher,ausr);
		return "success";
	}
	
}
