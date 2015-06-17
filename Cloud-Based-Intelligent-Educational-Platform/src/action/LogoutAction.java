package action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

public class LogoutAction {
	
	public String execute(){
		
		Map<String, Object> session = ActionContext.getContext().getSession();
		session.remove("USRID");
		
		return "success";
	}
}
