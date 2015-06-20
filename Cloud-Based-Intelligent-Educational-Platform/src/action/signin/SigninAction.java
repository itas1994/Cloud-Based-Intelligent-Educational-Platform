package action.signin;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

import bean.timeTableBean;
import antlr.collections.List;
import Dao.Dao;

public class SigninAction {

	private String course_name;
	private String start;
	private String end;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCourse_name() {
		return course_name;
	}

	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String execute() throws SQLException{
		Map<String, Object> session = ActionContext.getContext().getSession();
        String usrid = session.get("USRID").toString();
        
        Dao dao=new Dao();
        name=dao.getUsrName(usrid);
		
        dao.modifyIsSignin(usrid);
        
		String[] current_course=dao.getCurrentCourse();
		course_name=current_course[0];
		start=current_course[1];
		end=current_course[2];
		
		return "success";
	}
}
