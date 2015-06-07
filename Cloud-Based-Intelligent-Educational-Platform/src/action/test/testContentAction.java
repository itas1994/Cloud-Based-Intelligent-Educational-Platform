package action.test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.struts2.ServletActionContext;
import org.xml.sax.SAXException;

import Dao.Dao;

public class testContentAction {
	
	private int id;
	private String title;
	private boolean hasMine;
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

	public boolean isHasMine() {
		return hasMine;
	}

	public void setHasMine(boolean hasMine) {
		this.hasMine = hasMine;
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
		Dao dao=new Dao();
		title=dao.getTestTitle(id);
		String issueteacher=dao.getTestIssueTeacher(id);
		String ausr="itas1994";
		String filename="test_answer_content";
		hasMine=dao.isMyAnswer4Test(filename,id, issueteacher, ausr);
		content=dao.getTestContent(id, issueteacher);
		telist=(ArrayList) dao.answer4Test(id, issueteacher);
		
		String usrid="t001";
		String result="";
		String authority=dao.getAuthority(usrid);
		
		return authority;
	}
}
