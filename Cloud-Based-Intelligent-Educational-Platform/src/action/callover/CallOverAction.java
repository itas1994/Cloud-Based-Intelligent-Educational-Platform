package action.callover;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import Dao.Dao;
import bean.User;

public class CallOverAction {
	private ArrayList<User> ulist;
	private String name;
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private int num;
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public ArrayList<User> getUlist() {
		return ulist;
	}

	public void setUlist(ArrayList<User> ulist) {
		this.ulist = ulist;
	}

	public String execute() throws SQLException {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		Map<String, Object> session = ActionContext.getContext().getSession();
        String usrid = session.get("USRID").toString(); 
        
        Dao dao=new Dao();
        name=dao.getUsrName(usrid);
		
		ulist=dao.allStudent();
		num=dao.numbers()-1;
		int a=(int)Math.floor(Math.random() * ( num + 1));
		for(int i=0;i<ulist.size();i++){
		    	if(i==a){
		    		User user=new User();
		    		user=ulist.get(i);
		    		ulist.removeAll(ulist);
		    		ulist.add(user);
		    }
		}
		request.setAttribute("ulist", ulist);
		return "success";
	}
}

