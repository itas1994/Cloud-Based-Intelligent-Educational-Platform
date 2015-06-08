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

public class testAnswerAction {
	
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

	public String execute() throws SQLException, ParserConfigurationException, SAXException, IOException{
		HttpServletRequest rq=ServletActionContext.getRequest();
		id=Integer.parseInt(rq.getParameter("id"));
		String acontent=rq.getParameter("acontent");
		Dao dao=new Dao();
		String datatable="test";
		title=dao.getHOandTETitle(datatable, id);
		String issueteacher=dao.getHOandTEIssueTeacher(datatable, id);
		
		Map<String, Object> session = ActionContext.getContext().getSession();
        String ausr = session.get("USRID").toString(); 
		
		String filename="test_answer_content";
		dao.insertStudentAnswer(filename, id, issueteacher, ausr, acontent);
		
		content=dao.getTestContent(id, issueteacher);
		telist=(ArrayList) dao.answer4Test(id, issueteacher);
		return "success";
	}

}
