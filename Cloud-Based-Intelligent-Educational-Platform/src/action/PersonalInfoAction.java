package action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import Dao.Dao;

import com.opensymphony.xwork2.ActionContext;

public class PersonalInfoAction {
	
	private String name;
	private String authority;
	private List inlist;
	private List ulist;
	
	public List getUlist() {
		return ulist;
	}

	public void setUlist(List ulist) {
		this.ulist = ulist;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public List getInlist() {
		return inlist;
	}

	public void setInlist(List inlist) {
		this.inlist = inlist;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String execute() throws SQLException, SAXException, IOException, ParserConfigurationException{
		Map<String, Object> session = ActionContext.getContext().getSession();
        String usrid = session.get("USRID").toString(); 
        
        Dao dao=new Dao();
        name=dao.getUsrName(usrid);
        
        authority=dao.getAuthority(usrid);
        String path_ho="D:\\apache-tomcat-6.0.29\\webapps\\"
        		+ "Cloud-Based-Intelligent-Educational-Platform\\usr\\homework_answer_content";
        String path_te="D:\\apache-tomcat-6.0.29\\webapps\\"
        		+ "Cloud-Based-Intelligent-Educational-Platform\\usr\\test_answer_content";
        String[] f_ho=dao.getFileName(path_ho);
        String[] f_te=dao.getFileName(path_te);
        if(authority.equals("t")){
        	inlist=dao.getCount4Teacher(usrid);
        }else if(authority.equals("s")){
        	inlist=dao.getCount4Student(usrid, f_ho, f_te);
        }
        
        ulist=dao.getInfo(usrid);
        
		return "success";
	}
}
