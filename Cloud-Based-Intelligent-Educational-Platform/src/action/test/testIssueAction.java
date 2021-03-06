package action.test;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import Dao.Dao;

public class testIssueAction {
	
	private String name;

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	public String execute() throws SQLException{
		HttpServletRequest rq=ServletActionContext.getRequest();
		String title=rq.getParameter("title");
		String content=rq.getParameter("content");
		String limittime=rq.getParameter("limittime");//
		//limittime的输入方式为输入分钟
		String year=rq.getParameter("year");
		String month=rq.getParameter("month");
		String day=rq.getParameter("day");
		
		String deadline=year+"-"+month+"-"+day;
		
		Dao dao=new Dao();
		
		Map<String, Object> session = ActionContext.getContext().getSession();
        String issueteacher = session.get("USRID").toString(); 
        name=dao.getUsrName(issueteacher);
        
		dao.db4issueTest(title, content, issueteacher, limittime,deadline);
		dao.dom4issueTest(title, content, issueteacher);
		return "success";
	}
}
