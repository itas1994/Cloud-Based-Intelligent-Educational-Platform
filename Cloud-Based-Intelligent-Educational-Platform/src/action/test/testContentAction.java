package action.test;

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

public class testContentAction {
	
	private int id;
	private String title;
	private int hasMine;
	private String content;
	private ArrayList stelist;
	private ArrayList ttelist;
	private String name;
	private int isExpired;
	private String limittime;
	private float timespan;

	public float getTimespan() {
		return timespan;
	}


	public void setTimespan(float timespan) {
		this.timespan = timespan;
	}


	public String getLimittime() {
		return limittime;
	}


	public void setLimittime(String limittime) {
		this.limittime = limittime;
	}


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

	
	public int getHasMine() {
		return hasMine;
	}

	public void setHasMine(int hasMine) {
		this.hasMine = hasMine;
	}

	
	
	public ArrayList getStelist() {
		return stelist;
	}

	public void setStelist(ArrayList stelist) {
		this.stelist = stelist;
	}

	public ArrayList getTtelist() {
		return ttelist;
	}

	public void setTtelist(ArrayList ttelist) {
		this.ttelist = ttelist;
	}

	public String execute() throws SQLException, ParserConfigurationException, SAXException, IOException, ParseException{
		HttpServletRequest request=ServletActionContext.getRequest();
		id=Integer.parseInt(request.getParameter("id"));
		
		Dao dao=new Dao();
		
		Map<String, Object> session = ActionContext.getContext().getSession();
        String usrid = session.get("USRID").toString();
        name=dao.getUsrName(usrid);
		
        String issueteacher=dao.getTestIssueTeacher(id);
        
        title=dao.getTestTitle(id);
		hasMine=dao.isMyAnswer4Test(id, issueteacher, usrid);
		content=dao.getTestContent(id, issueteacher);
		stelist=(ArrayList) dao.answer4Test4Student(id, issueteacher,usrid);
		ttelist=(ArrayList) dao.answer4Test4Teacher(id, issueteacher, usrid);
		isExpired=dao.isExpired4Test(id);
		limittime=dao.getLimitTimeInDate(id);
		timespan=dao.getLimitTimeInMinutes(id);
		
		String authority=dao.getAuthority(usrid);
		
		return authority;
	}
}
