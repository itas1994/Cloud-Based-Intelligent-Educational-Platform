package action.resource;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import Dao.Dao;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class ContentAction extends ActionSupport {

	/**
	 * @return
	 */
	private ArrayList relist=new ArrayList();
	public ArrayList getRelist(){
		return relist;
	}
	public void setRelist(ArrayList relist){ 
		this.relist=relist;
	}

	public String execute() throws SQLException {
		HttpServletRequest request=ServletActionContext.getRequest();
		int id=Integer.parseInt(request.getParameter("id"));
		Dao dao = new Dao();
		relist = (ArrayList) dao.content(id);
		dao.addhot(id);
		return "success";
	}
}