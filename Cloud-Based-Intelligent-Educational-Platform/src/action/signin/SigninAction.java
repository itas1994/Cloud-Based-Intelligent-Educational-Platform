package action.signin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.opensymphony.xwork2.ActionContext;

import bean.timeTableBean;
import antlr.collections.List;
import Dao.Dao;

public class SigninAction {

	private String course_name;
	private String start;
	private String end;
	private String name;
	private int in_class_now;

	public int getIn_class_now() {
		return in_class_now;
	}

	public void setIn_class_now(int in_class_now) {
		this.in_class_now = in_class_now;
	}

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

	public String execute() throws SQLException, ParserConfigurationException, SAXException, IOException{
		Map<String, Object> session = ActionContext.getContext().getSession();
        String usrid = session.get("USRID").toString();
        
        Dao dao=new Dao();
        name=dao.getUsrName(usrid);
		
        dao.modifyIsSignin(usrid);
        
		String[] current_course=dao.getCurrentCourse();
		course_name=current_course[0];
		start=current_course[1];
		end=current_course[2];
		
		int index=dao.getCurrentCourseIndex();
		if(index!=0){
			dao.db4signin(usrid);
			String current_teacher=dao.getCurrentCourseTeaher(course_name);
			dao.xml4signin(current_teacher, course_name, usrid);
			in_class_now=1;
		}else{
			in_class_now=-1;
		}
		
		return "success";
	}
}
