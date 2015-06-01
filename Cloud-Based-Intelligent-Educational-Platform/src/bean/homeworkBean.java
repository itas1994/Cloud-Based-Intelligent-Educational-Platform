package bean;

public class homeworkBean {
	private int id;
	private String title;
	private String contentpath;
	private String issuetime;
	private String deadline;
	private String issueteacher;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIssuetime() {
		return issuetime;
	}
	public void setIssuetime(String issuetime) {
		this.issuetime = issuetime;
	}
	public String getDeadline() {
		return deadline;
	}
	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}
	public String getIssueteacher() {
		return issueteacher;
	}
	public void setIssueteacher(String issueteacher) {
		this.issueteacher = issueteacher;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContentpath() {
		return contentpath;
	}
	public void setContentpath(String contentpath) {
		this.contentpath = contentpath;
	}
}
