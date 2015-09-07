package action.group;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import bean.User;
import Dao.Dao;


public class GroupAction {

	private List<User> list;
	private int memNum;
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMemNum() {
		return memNum;
	}
	public void setMemNum(int memNum) {
		this.memNum = memNum;
	}
	public List<User> getList() {
		return list;
	}
	public void setList(List<User> list) {
		this.list = list;
	}
	public String execute() throws SQLException{
		HttpServletRequest request = ServletActionContext.getRequest();
		int groupNum = Integer.parseInt(request.getParameter("num"));
		memNum=(int) Math.ceil(5.0/(float)groupNum);

		Map<String, Object> session = ActionContext.getContext().getSession();
        String usrid = session.get("USRID").toString();
		
		Dao dao = new Dao();
		list=dao.Groupinguser();
		name=dao.getUsrName(usrid);
		
		return "success";
	}
}
