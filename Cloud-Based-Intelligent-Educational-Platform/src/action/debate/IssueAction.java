package action.debate;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import Dao.Dao;

public class IssueAction {
	
	public String execute()throws SQLException{
		HttpServletRequest rq=ServletActionContext.getRequest();
		String title=rq.getParameter("title");
		String content=rq.getParameter("content");
		String usrid="itas1994";
		Dao dao=new Dao();
		dao.db4issue(title, content, usrid);
		dao.dom4issue(title, content, usrid);
		return "success";
	}
}
