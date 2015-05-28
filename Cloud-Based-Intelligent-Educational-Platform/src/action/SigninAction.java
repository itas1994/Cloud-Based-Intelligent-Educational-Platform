package action;

import java.sql.SQLException;
import java.util.ArrayList;

import bean.classes;
import antlr.collections.List;
import Dao.Dao;

public class SigninAction {

	private java.util.List<classes> classes1;
	public java.util.List<classes> getClasses1() {
		return classes1;
	}
	public void setClasses1(java.util.List<classes> classes1) {
		this.classes1 = classes1;
	}
	
	public String execute() throws SQLException{
		Dao da = new Dao();
		setClasses1(da.getClasses());
		return "dmsuc";
	}
}
