package action.test;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
	private String name;
	private ArrayList stelist;

	public ArrayList getStelist() {
		return stelist;
	}


	public void setStelist(ArrayList stelist) {
		this.stelist = stelist;
	}


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
        name=dao.getUsrName(ausr);
		
        Date now = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String atime = df.format(now);
        
		dao.insertStudentAnswer4Test(id, issueteacher, name,atime,acontent);
		
		content=dao.getTestContent(id, issueteacher);
		stelist=(ArrayList) dao.answer4Test4Student(id, issueteacher,name);
		telist=(ArrayList) dao.answer4Test4Student(id, issueteacher,name);
		return "success";
	}

}
