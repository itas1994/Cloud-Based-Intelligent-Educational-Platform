package action;

import java.sql.SQLException;
import java.util.List;

import bean.User;
import Dao.Dao;


public class groupedAction {

	private List<User> Users;
	public List<User> getUsers() {
		return Users;
	}
	public void setUsers(List<User> users) {
		Users = users;
	}
	
	public String execute() throws SQLException{
		Dao da = new Dao();
		setUsers(da.Groupinguser());
		return "suc";
	}
}
