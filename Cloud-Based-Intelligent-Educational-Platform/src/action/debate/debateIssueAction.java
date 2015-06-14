package action.debate;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import Dao.Dao;

public class debateIssueAction {
	
	public String execute()throws SQLException{
		HttpServletRequest rq=ServletActionContext.getRequest();
		String title=rq.getParameter("title");
		String content=rq.getParameter("content");
		
		Map<String, Object> session = ActionContext.getContext().getSession();
        String issueusr = session.get("USRID").toString(); 
		
		Dao dao=new Dao();
		dao.db4issue(title, content, issueusr);
		dao.dom4issue(title, content, issueusr);
		return "success";
	}
}
