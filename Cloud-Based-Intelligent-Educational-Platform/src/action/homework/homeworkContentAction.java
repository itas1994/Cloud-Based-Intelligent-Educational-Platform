package action.homework;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.struts2.ServletActionContext;
import org.xml.sax.SAXException;

import com.opensymphony.xwork2.ActionContext;

import Dao.Dao;

public class homeworkContentAction {
	
	private int id;
	private String title;
	private boolean hasMine;
	private String content;
	private ArrayList holist;
	private String name;
	private int isExpired;

	public int getIsExpired() {
		return isExpired;
	}


	public void setIsExpired(int isExpired) {
		this.isExpired = isExpired;
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
	
	public String execute() throws SQLException, ParserConfigurationException, SAXException, IOException, ParseException{
		HttpServletRequest rq=ServletActionContext.getRequest();
		id=Integer.parseInt(rq.getParameter("id"));
		Dao dao=new Dao();
		String datatable="homework";
		title=dao.getHOandTETitle(datatable, id);
		String issueteacher=dao.getHOandTEIssueTeacher(datatable, id);
		
		Map<String, Object> session = ActionContext.getContext().getSession();
        String usrid = session.get("USRID").toString();
        name=dao.getUsrName(usrid);
		
		content=dao.getHomeworkContent(id, issueteacher);
		holist=(ArrayList) dao.answer4Homework(id, issueteacher);
		
		isExpired=dao.isExpired4Homework(id);
		
		String authority=dao.getAuthority(usrid);
		return authority;
	}
}
