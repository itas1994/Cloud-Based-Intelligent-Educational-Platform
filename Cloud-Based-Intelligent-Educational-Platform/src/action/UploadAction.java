package action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import Dao.Dao;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class UploadAction extends ActionSupport {
	private static final int BUFFER_SIZE = 16 * 1024;
    private String title;
    private File upload;
    private String uploadFileName;
    private String uploadContentType;
    private String savePath;
    
    public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public String getUploadContentType() {
		return uploadContentType;
	}
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	public String getSavePath() {
		return savePath;
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
    
    @Override
    public String execute() throws Exception {
    	String uploadPath = ServletActionContext.getServletContext().getRealPath(getSavePath());
        FileInputStream fi=new FileInputStream(this.upload);
        String name=this.getUploadFileName();
        String target=uploadPath+"\\"+name;
        File dst = new File(target);  
        FileOutputStream os = new FileOutputStream(dst);  
        byte[] buffer = new byte[1024];
        int length = 0;  
        while ((length = fi.read(buffer)) > 0) {
            os.write(buffer, 0, length);  
        }  
        fi.close();  
        os.close();  
        //Êý¾Ý¿â²Ù×÷
        String size=null;
        int s=0;
        HttpServletRequest request = ServletActionContext.getRequest();
        String content=request.getParameter("content");
        String title=request.getParameter("title");
        String location="usr/"+name;
        s=(int) this.upload.length();
        if(s<1024){
        	size=s+"B";
        }else if(s>=1024 && s<1048576){
        	s/=1024;
        	size=s+"KB";
        }else if(s>=1048576 && s<1073741824){
        	s/=1048576;
        	size=s+"M";
        }
        Dao dao=new Dao();
        dao.upload(title,content,size,location,name);
        return SUCCESS;
    }
}