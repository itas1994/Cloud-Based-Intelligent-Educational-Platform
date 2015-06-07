package action.debate;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.struts2.ServletActionContext;
import org.xml.sax.SAXException;

import Dao.Dao;

public class debateContentAction {
	private List delist=new ArrayList();
	private String title;
	private String content;
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	private int id;
	public List getDelist() {
		return delist;
	}

	public void setDelist(List delist) {
		this.delist = delist;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String execute() throws SQLException, ParserConfigurationException, SAXException, IOException{
		HttpServletRequest rq=ServletActionContext.getRequest();
		id=Integer.parseInt(rq.getParameter("id"));
		String issueusr="itas1994";
		Dao dao=new Dao();
		delist=dao.getDebateReply(id, issueusr);
		title=dao.getDebateTitle(id);
		content=dao.getDebateContent(id, issueusr);
		return "success";
	}
}