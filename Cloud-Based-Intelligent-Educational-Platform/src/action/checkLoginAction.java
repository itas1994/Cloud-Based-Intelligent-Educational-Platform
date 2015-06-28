package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.struts2.ServletActionContext;
import org.xml.sax.SAXException;

import Dao.Dao;

public class checkLoginAction {

	private String name;
	private String authority;

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

	public String execute() throws SQLException, ParseException, ParserConfigurationException, SAXException, IOException, TransformerException {
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		PrintWriter out=response.getWriter();
		String usr=request.getParameter("usr");
		String psd=request.getParameter("psd");
		Dao dao=new Dao();
		String r=dao.login(usr,psd);
		if(r=="success"){
			out.print("1");//成功
			out.flush();
			out.close();
		}else if(r=="psd_error"){
			out.print("-1");//psd错误
			out.flush();
			out.close();
		}else{
			out.print("-2");//usr错误
			out.flush();
			out.close();
		}
		
		return null;
	}
}
