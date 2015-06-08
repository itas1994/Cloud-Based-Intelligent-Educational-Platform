package action.test;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import Dao.Dao;

public class testIssueAction {
	
	
	public String execute() throws SQLException{
		HttpServletRequest rq=ServletActionContext.getRequest();
		String title=rq.getParameter("title");
		String content=rq.getParameter("content");
		String limittime=rq.getParameter("limittime");//
		//limittime的输入方式为输入分钟
		
		Map<String, Object> session = ActionContext.getContext().getSession();
        String issueteacher = session.get("USRID").toString(); 
		
		Dao dao=new Dao();
		dao.db4issueTest(title, content, issueteacher, limittime);
		dao.dom4issueTest(title, content, issueteacher);
		return "success";
	}
}
