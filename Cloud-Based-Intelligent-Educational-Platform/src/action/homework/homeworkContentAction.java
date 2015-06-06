package action.homework;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.struts2.ServletActionContext;
import org.xml.sax.SAXException;

import Dao.Dao;

public class homeworkContentAction {
	
	private int id;
	private String title;
	private boolean hasMine;
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
	

	public boolean isHasMine() {
		return hasMine;
	}

	public void setHasMine(boolean hasMine) {
		this.hasMine = hasMine;
	}
	
	public String execute() throws SQLException, ParserConfigurationException, SAXException, IOException{
		HttpServletRequest rq=ServletActionContext.getRequest();
		id=Integer.parseInt(rq.getParameter("id"));
		Dao dao=new Dao();
		String datatable="homework";
		title=dao.getHOandTETitle(datatable, id);
		String issueteacher=dao.getHOandTEIssueTeacher(datatable, id);
		String ausr="itas1994";
		String filename="homework_answer_content";
		hasMine=dao.isMyAnswer(filename,id, issueteacher, ausr);
		content=dao.getHOandTEContent(filename, id, issueteacher);
		holist=(ArrayList) dao.answer(id, issueteacher);
		
		String usrid="t001";
		String result="";
		String authority=dao.getAuthority(usrid);
		
		return authority;
	}
}
