package action;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import Dao.Dao;
import bean.User;

public class DmAction {
	private ArrayList<User> ulist;
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
		Dao udao=new Dao();
	
		ulist=udao.alluser();
		num=udao.numbers()-1;
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
		return "dmsuc";
	}
}

