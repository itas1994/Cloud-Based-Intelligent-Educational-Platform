package bean;

import java.util.Date;

public class debateBean {
	private int id;
	private String title;
	private String contentpath;
	private String issuetime;
	private int replynum;
	private String issueusr;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContentpath() {
		return contentpath;
	}
	public void setContentpath(String contentpath) {
		this.contentpath = contentpath;
	}
	public String getIssuetime() {
		return issuetime;
	}
	public void setIssuetime(String issuetime) {
		this.issuetime = issuetime;
	}
	public int getReplynum() {
		return replynum;
	}
	public void setReplynum(int replynum) {
		this.replynum = replynum;
	}
	public String getIssueusr() {
		return issueusr;
	}
	public void setIssueusr(String issueusr) {
		this.issueusr = issueusr;
	}
}
