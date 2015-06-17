package action.resource;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import Dao.Dao;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class ResourceAction extends ActionSupport {

	/**
	 * @return
	 */
	private ArrayList relist=new ArrayList();
	private String name;

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	public ArrayList getRelist(){
		return relist;
	}
	public void setRelist(ArrayList relist){ 
		this.relist=relist;
	}

	public String execute() throws SQLException {
		Dao dao = new Dao();
		
		Map<String, Object> session = ActionContext.getContext().getSession();
        String usrid = session.get("USRID").toString(); 
        name=dao.getUsrName(usrid);
		
		relist = (ArrayList) dao.hotresource();
		return "success";
	}
}