package action.debate;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.xml.sax.SAXException;

import com.opensymphony.xwork2.ActionContext;

import bean.debateReplyBean;
import Dao.Dao;

public class debateReplyAction {

	private String title;
	private int id;
	private List<debateReplyBean> delist=new ArrayList<debateReplyBean>();
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public List<debateReplyBean> getDelist() {
		return delist;
	}

	public void setDelist(List<debateReplyBean> delist) {
		this.delist = delist;
	}
	
	public String execute()throws SQLException, SAXException, IOException{
		HttpServletRequest rq=ServletActionContext.getRequest();
		title=rq.getParameter("title");
		id=Integer.parseInt(rq.getParameter("id"));
		String replycontent=rq.getParameter("replycontent");
		Dao dao=new Dao();
		
		Map<String, Object> session = ActionContext.getContext().getSession();
        String replyusr = session.get("USRID").toString(); 
        name=dao.getUsrName(replyusr);
        
		Date now = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String replytime = df.format(now);
		
		String issueusr=dao.getIssueusr(id);
		dao.insertReply(id, title, replycontent, name, replytime, issueusr);
		delist=dao.reply(id, title, issueusr);
		return "success";
	}
}
