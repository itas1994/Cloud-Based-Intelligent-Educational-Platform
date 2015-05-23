package action.resource;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import Dao.Dao;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class ResourceAction extends ActionSupport {

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
		Dao dao = new Dao();
		relist = (ArrayList) dao.hotresource();
		return "success";
	}
}