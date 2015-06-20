package action;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.xml.sax.SAXException;

import Dao.Dao;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class LoginAction extends ActionSupport implements SessionAware{

	/**
	 * @return
	 */
	private String name;
	private String authority;
	public Map getSession() {
		return session;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private Map session;
    public void setSession(Map session) {
        this.session = session;
    }
	
	public String execute() throws SQLException, ParseException, ParserConfigurationException, SAXException, IOException, TransformerException {
		HttpServletRequest request=ServletActionContext.getRequest();
		String usr=request.getParameter("usr");
		String psd=request.getParameter("psd");
		Dao dao=new Dao();
		name=dao.getUsrName(usr);
		String r=dao.login(usr,psd);
		authority=dao.getAuthority(usr);
		String result="";
		if(r=="success"){
			this.session.put("USRID",usr);
			result="success";
		}else if(r=="psd_error"){
			result="psd_error";
		}else{
			result="id_error";
		}
		
		if(authority.equals("s")){
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date now=new Date();
			String now_time=format.format(now);
			String old_signin_time=dao.getSigninTime(usr);
			dao.compareAndRestoreIsSignin(now_time, old_signin_time, usr);
			dao.compareAndModifySigninTime(now_time, old_signin_time, usr);
		}else if(authority.equals("t")){
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date now=new Date();
			String now_time=format.format(now);
			String old_login_time=dao.getLoginTime(usr);
			String[] courses=dao.getTeacherCourses(usr);
			dao.compareAndModifyLoginTimeAndXML(now_time, old_login_time, usr,courses);
		}
		
		return result;
	}
}