package action.homework;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import Dao.Dao;

public class homeworkIssueAction {
	
	
	public String execute() throws SQLException{
		HttpServletRequest rq=ServletActionContext.getRequest();
		String title=rq.getParameter("title");
		String content=rq.getParameter("content");
		String deadline=rq.getParameter("deadline");

		Map<String, Object> session = ActionContext.getContext().getSession();
        String issueteacher = session.get("USRID").toString();
		
		Dao dao=new Dao();
		dao.db4issueHomework(title, content, issueteacher, deadline);
		dao.dom4issueHomework(title, content, issueteacher);
		return "success";
	}
}
