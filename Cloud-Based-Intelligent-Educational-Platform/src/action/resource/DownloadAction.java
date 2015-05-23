package action.resource;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class DownloadAction extends ActionSupport
{
	private String fileName;
	
	public String getDownloadFileName() {//×ªÂë£¬·ÀÖ¹ÖÐÎÄÂÒÂë
		 String fileName=ServletActionContext.getRequest().getParameter("fileName");
		 String downFileName = fileName;
		 try {
		    downFileName = new String(downFileName.getBytes(), "ISO8859-1");
	     } catch (Exception e) {
		    e.printStackTrace();
	     }
		 return downFileName;
	}
	
	public InputStream getDownloadFile() throws Exception  
    {
		String name=this.getDownloadFileName();
		String realPath="/usr/resource_file/"+name;
		InputStream in=ServletActionContext.getServletContext().getResourceAsStream(realPath);
		return ServletActionContext.getServletContext().getResourceAsStream(realPath);
    }  
	
	@Override
    public String execute() throws Exception
    {
        return SUCCESS;
    }
}