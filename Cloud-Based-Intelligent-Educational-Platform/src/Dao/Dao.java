package Dao;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import bean.User;
import bean.timeTableBean;
import bean.debateBean;
import bean.debateReplyBean;
import bean.homeworkAnswerBean;
import bean.homeworkBean;
import bean.preBean;
import bean.resourceBean;
import bean.testAnswerBean;
import bean.testBean;

public class Dao {
		
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
	
		public void con() throws SQLException{//����mysql����
			String url="jdbc:mysql://127.0.0.1:3306/project";
			String usr="root";
			String psd="lzw0201";
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con=DriverManager.getConnection(url,usr,psd);
				st=con.createStatement();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		public void destroyQuery(){//�Ͽ�mysql����
			try {
				rs.close();
				st.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void destroyUpdate(){//�Ͽ�mysql����
			try {
				st.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public List<resourceBean> search(String title) throws SQLException{
			this.con();
			List<resourceBean> relist=new ArrayList<resourceBean>();
			String sql_search="select * from resourceinfo where title = '"+title+"'";
			rs=st.executeQuery(sql_search);
				while(rs.next()){
					resourceBean r=new resourceBean();
					r.setId(rs.getInt("id"));
					r.setTitle(rs.getString("title"));
					r.setSize(rs.getString("size"));
					r.setDate(rs.getString("date"));
					r.setContent(rs.getString("content"));
					r.setLocation(rs.getString("location"));
					r.setHot(rs.getInt("hot"));
					r.setUsr(rs.getString("uploadusr"));
					r.setName(rs.getString("name"));
					relist.add(r);
				}
			this.destroyQuery();
			return relist;
		}
		
		public void upload(String title,String content,String size,String location,String uploadusr,String name) throws SQLException{
			this.con();
			String sql_upload="insert into resourceinfo values(null,'"
									+title+"','"+size+"',now(),'"+location+"','"+content+"',0,'"+uploadusr+"','"+name+"')";
			st.executeUpdate(sql_upload);
			this.destroyUpdate();
		}
		
		public List<resourceBean> hotresource() throws SQLException{
			this.con();
			List<resourceBean> relist=new ArrayList<resourceBean>();
			String sql_hot="select * from resourceinfo order by hot DESC";
			rs=st.executeQuery(sql_hot);
			while(rs.next()){
				resourceBean r=new resourceBean();
				r.setId(rs.getInt("id"));
				r.setTitle(rs.getString("title"));
				r.setSize(rs.getString("size"));
				r.setDate(rs.getString("date"));
				r.setContent(rs.getString("content"));
				r.setLocation(rs.getString("location"));
				r.setHot(rs.getInt("hot"));
				r.setUsr(rs.getString("uploadusr"));
				r.setName(rs.getString("name"));
				relist.add(r);
			}
			this.destroyQuery();
			return relist;
		}
		
		public List<resourceBean> content(int id) throws SQLException{
			this.con();
			List<resourceBean> relist=new ArrayList<resourceBean>();
			String sql_search="select * from resourceinfo where id="+id+"";
			rs=st.executeQuery(sql_search);
				while(rs.next()){
					resourceBean r=new resourceBean();
					r.setId(rs.getInt("id"));
					r.setTitle(rs.getString("title"));
					r.setSize(rs.getString("size"));
					r.setDate(rs.getString("date"));
					r.setContent(rs.getString("content"));
					r.setLocation(rs.getString("location"));
					r.setHot(rs.getInt("hot"));
					r.setUsr(rs.getString("uploadusr"));
					r.setName(rs.getString("name"));
					relist.add(r);
				}
			this.destroyQuery();
			return relist;
		}
		
		public void addhot(int id) throws SQLException{
			this.con();
			String sql_hot="update resourceinfo set hot=hot+1 where id="+id+"";
			st.executeUpdate(sql_hot);
			this.destroyUpdate();
		}
		
		public String login(String usr,String psd) throws SQLException{
			this.con();
			String result="";
			String sql_login="select psd from usr where id='"+usr+"'";
		    rs=st.executeQuery(sql_login);
		    while(rs.next()){
				if (rs.getString("psd").equals(psd)) {
					result = "success";
					break;
				} else {
					result = "psd_error";
					break;
				}
		    }
		    if(rs.equals(null)){
		    	result="id_error";
		    }
		    this.destroyQuery();
		    
		    return result;
		}
		
		public String getUsrName(String usr) throws SQLException{
			this.con();
			String sql_name="select name from usr where id = '"+usr+"'";
			String name="";
			rs=st.executeQuery(sql_name);
			while(rs.next()){
				name=rs.getString("name");
			}
			return name;
		}
		
		public List<debateBean> getDebate() throws SQLException{
			this.con();
			List<debateBean> delist=new ArrayList<debateBean>();
			String sql_debate="select * from debate";
			rs=st.executeQuery(sql_debate);
			while(rs.next()){
				debateBean d=new debateBean();
				d.setId(rs.getInt("id"));
				d.setContentpath(rs.getString("contentpath"));
				d.setTitle(rs.getString("title"));
				d.setIssuetime(rs.getString("issuetime"));
				d.setIssueusr(rs.getString("issueusr"));
				d.setReplynum(rs.getInt("replynum"));
				delist.add(d);
			}
			this.destroyQuery();
			return delist;
		}
		
		public List<debateReplyBean> getDebateReply(int id,String issueusr) throws SQLException{
			this.con();
			List<debateReplyBean> delist=new ArrayList<debateReplyBean>();
			String title="";
			String _id=id+"";
			String sql_debate_title="select title from debate where id="+_id;
			rs=st.executeQuery(sql_debate_title);
			while(rs.next()){
				title=rs.getString("title");
			}
			this.destroyQuery();
			
			//dom����
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	    	try {
	            DocumentBuilder db = dbf.newDocumentBuilder();
	            Document doc = db.parse("D://apache-tomcat-6.0.29//webapps//Cloud-Based-Intelligent-Educational-Platform//"
	            		+ "usr//debate_reply_content//"+issueusr+".xml");
	            NodeList debates = doc.getElementsByTagName("debate");
	            for(int i=0;i<debates.getLength();i++){
	            	Node _title=debates.item(i).getChildNodes().item(0);
	            	if(_title.getTextContent().equals(title) && 
	            				_title.getAttributes().getNamedItem("id")
	            								.getNodeValue().toString().equals(_id)){
	            		Node Replies=debates.item(i).getChildNodes().item(2);
	            		NodeList replies=Replies.getChildNodes();
	            		for(int k=0;k<replies.getLength();k++){
	            			Node reply=replies.item(k);
	            			debateReplyBean dr=new debateReplyBean();
	            			dr.setReplyusr(reply.getChildNodes().item(0).getTextContent());
	            			dr.setReplytime(reply.getChildNodes().item(1).getTextContent());
	            			dr.setReplycontent(reply.getChildNodes().item(2).getTextContent());
	            			delist.add(dr);
	            		}
	            	}
	            }
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}
			return delist;
		}
		
		public String getDebateContent(int id,String issueusr) throws 
				SQLException, ParserConfigurationException, SAXException, IOException{
			this.con();
			String content="";
			String title="";
			String _id=id+"";
			String sql_debate_title="select title from debate where id="+_id;
			rs=st.executeQuery(sql_debate_title);
			while(rs.next()){
				title=rs.getString("title");
			}
			this.destroyQuery();
			
			//dom����
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse("D://apache-tomcat-6.0.29//webapps//Cloud-Based-Intelligent-Educational-Platform//"
            		+ "usr//debate_reply_content//"+issueusr+".xml");
            NodeList debates = doc.getElementsByTagName("debate");
            for(int i=0;i<debates.getLength();i++){
            	Node _title=debates.item(i).getChildNodes().item(0);
            	if(_title.getTextContent().equals(title) && 
            			_title.getAttributes().getNamedItem("id")
            				.getNodeValue().toString().equals(_id)){
            		content=debates.item(i).getChildNodes().item(1).getTextContent();
            	}
            }    
			return content;
		}
		
		public String getDebateTitle(int id) throws SQLException{
			this.con();
			String title="";
			String sql_debate_title="select title from debate where id="+id;
			rs=st.executeQuery(sql_debate_title);
			while(rs.next()){
				title=rs.getString("title");
			}
			this.destroyQuery();
			return title;
		}
		
		/*begin for issue*/
		public void creatNewXml(String usrid) throws ParserConfigurationException, TransformerException, SAXException, IOException{
			String filepath="D://apache-tomcat-6.0.29/webapps//Cloud-Based-Intelligent-Educational-Platform"
					+ "//usr//debate_reply_content//"+usrid+".xml";
			DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
			DocumentBuilder builder=factory.newDocumentBuilder();
			Document doc=builder.newDocument();
			Element Debates=doc.createElement("Debates");
			doc.appendChild(Debates);
			
			Transformer transformer=TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8"); 
			StreamResult fileResult=new StreamResult(new File(filepath));
			DOMSource source=new DOMSource(doc);
			transformer.transform(source,fileResult);
		}
		
		public void db4issue(String title,String content,String usrid) throws SQLException{
			this.con();
			String sql_issue="insert into debate values(null,'"+title+"','usr/debate_reply_content/"+usrid+".xml',"
					+ "now(),0,'"+usrid+"')";
			st.executeUpdate(sql_issue);
			this.destroyUpdate();
		}
		
		public void dom4issue(String title,String content,String usrid) throws SQLException{
			this.con();
			int id = 0;
			String _id="";
			String sql_id="select id from debate where title = '"+title+"' order by issuetime DESC LIMIT 1";
			rs=st.executeQuery(sql_id);
			while(rs.next()){
				id=rs.getInt("id");
			}
			_id=id+"";
			this.destroyQuery();
			
			//dom�޸�xml
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			String fileName="D://apache-tomcat-6.0.29//webapps//Cloud-Based-Intelligent-Educational-Platform//"
            		+ "usr//debate_reply_content//"+usrid+".xml";
	    	try {
	            DocumentBuilder db = dbf.newDocumentBuilder();
	            File xml=new File(fileName);
	            if(false==xml.exists()){
	            	this.creatNewXml(usrid);
	            }
	            Document doc = db.parse(fileName);
	            Node debates=doc.getElementsByTagName("Debates").item(0);
	            Element debate=doc.createElement("debate");
	            Element ti=doc.createElement("title");//title
	            ti.setAttribute("id", _id);
	            ti.setTextContent(title);
	            Element cont=doc.createElement("content");//title--content
	            cont.setTextContent(content);
	            Element Replies=doc.createElement("Replies");
	            debate.appendChild(ti);//content->title
	            debate.appendChild(cont);//title->Debates
	            debate.appendChild(Replies);
	            debates.appendChild(debate);
	            this.doc2XmlFile(doc, fileName);
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}
		}
		/*end of issue*/
		
		/*begin of reply*/
		public String getIssueusr(int id) throws SQLException{
			this.con();
			String _id=id+"";
			String issueusr="";
			String sql_debate_issueusr="select issueusr from debate where id="+_id;
			rs=st.executeQuery(sql_debate_issueusr);
			while(rs.next()){
				issueusr=rs.getString("issueusr");
			}
			this.destroyQuery();
			return issueusr;
		}
		
		public void insertReply(int id,String title,String replycontent
				,String replyusr,String replytime,String issueusr) throws SAXException, IOException{
			String _id=id+"";
			
			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
			try {
				DocumentBuilder db=dbf.newDocumentBuilder();
				Document doc=db.parse("D://apache-tomcat-6.0.29//webapps//Cloud-Based-Intelligent-Educational-Platform//"
	            		+ "usr//debate_reply_content//"+issueusr+".xml");
				NodeList debates=doc.getElementsByTagName("debate");
				for(int i=0;i<debates.getLength();i++){
					Node ti=debates.item(i).getChildNodes().item(0);
					if(ti.getAttributes().getNamedItem("id")
								.getNodeValue().toString().equals(_id)){
						Node Replies=debates.item(i).getChildNodes().item(2);
						Element _replyusr=doc.createElement("replyusr");
						_replyusr.setTextContent(replyusr);
						Element _replytime=doc.createElement("replytime");
						_replytime.setTextContent(replytime);
						Element _replycontent=doc.createElement("replycontent");
						_replycontent.setTextContent(replycontent);
						Element reply=doc.createElement("reply");
						reply.appendChild(_replyusr);
						reply.appendChild(_replytime);
						reply.appendChild(_replycontent);
						Replies.appendChild(reply);
						this.doc2XmlFile(doc, "D://apache-tomcat-6.0.29//webapps//Cloud-Based-Intelligent-Educational-Platform//"
			            		+ "usr//debate_reply_content//"+issueusr+".xml");
					}
				}
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			}
		}
		
		public List<debateReplyBean> reply(int id,String title,String issueusr) throws SQLException{
			String _id=id+"";
			List<debateReplyBean> delist=new ArrayList<debateReplyBean>();

			//dom����
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	    	try {
	            DocumentBuilder db = dbf.newDocumentBuilder();
	            Document doc = db.parse("D://apache-tomcat-6.0.29//webapps//Cloud-Based-Intelligent-Educational-Platform//"
	            		+ "usr//debate_reply_content//"+issueusr+".xml");
	            NodeList debates = doc.getElementsByTagName("debate");
	            for(int i=0;i<debates.getLength();i++){
	            	Node _title=debates.item(i).getChildNodes().item(0);
	            	if(_title.getAttributes().getNamedItem("id")
	            				.getNodeValue().toString().equals(_id)){
	            		Node Replies=debates.item(i).getChildNodes().item(2);
	            		NodeList replies=Replies.getChildNodes();
	            		for(int k=0;k<replies.getLength();k++){
	            			Node reply=replies.item(k);
	            			debateReplyBean dr=new debateReplyBean();
	            			dr.setReplyusr(reply.getChildNodes().item(0).getTextContent());
	            			dr.setReplytime(reply.getChildNodes().item(1).getTextContent());
	            			dr.setReplycontent(reply.getChildNodes().item(2).getTextContent());
	            			delist.add(dr);
	            		}
	            	}
	            }
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}
			return delist;
		}
		//end of reply
		
		//����
		public ArrayList<User> allStudent() throws SQLException{
			this.con();
			ArrayList<User> list=new ArrayList<User>();
			String sql_search="select * from usr where authority not in('t')";
			rs=st.executeQuery(sql_search);
				while(rs.next()){
					User r=new User();
					r.setID(rs.getString("id"));
					r.setName(rs.getString("name"));
					list.add(r);
				}
			this.destroyQuery();
			return list;
		}
		
		public int numbers() throws SQLException{
			this.con();
			int num=0;
			ArrayList<User> list=new ArrayList<User>();
			String sql_search="select * from usr";
			rs=st.executeQuery(sql_search);
				while(rs.next()){
					User r=new User();
					r.setID(rs.getString("id"));
					r.setName(rs.getString("name"));
					list.add(r);
					num++;
				}
			this.destroyQuery();
			return num;
		}
		
		public boolean isInDates(String strDate,String strDateBegin,String strDateEnd){   
	        SimpleDateFormat sd = new SimpleDateFormat("HH:mm:ss");  
	        Date myDate = null;  
	        Date dateBegin = null;  
	        Date dateEnd = null;  
	        try {  
	            myDate = sd.parse(strDate);  
	            dateBegin = sd.parse(strDateBegin);  
	            dateEnd = sd.parse(strDateEnd);  
	        } catch (ParseException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        }  
	        strDate = String.valueOf(myDate);  
	        strDateBegin = String.valueOf(dateBegin);  
	        strDateEnd = String.valueOf(dateEnd);  
	          
	        int strDateH = Integer.parseInt(strDate.substring(11,13));
	        int strDateM = Integer.parseInt(strDate.substring(14,16));
	        int strDateS = Integer.parseInt(strDate.substring(17,19));
	        
	        int strDateBeginH = Integer.parseInt(strDateBegin.substring(11,13));  
	        int strDateBeginM = Integer.parseInt(strDateBegin.substring(14,16));  
	        int strDateBeginS = Integer.parseInt(strDateBegin.substring(17,19));  
	          
	        int strDateEndH = Integer.parseInt(strDateEnd.substring(11,13));  
	        int strDateEndM = Integer.parseInt(strDateEnd.substring(14,16));  
	        int strDateEndS = Integer.parseInt(strDateEnd.substring(17,19));  
	          
	        if((strDateH>=strDateBeginH && strDateH<=strDateEndH)){  
	            if(strDateH>strDateBeginH && strDateH<strDateEndH){  
	                return true;  
	            }else if(strDateH==strDateBeginH && strDateM>strDateBeginM && strDateH<strDateEndH){  
	                return true;  
	            }else if(strDateH==strDateBeginH && strDateM==strDateBeginM && strDateS>strDateBeginS && strDateH<strDateEndH){  
	                return true;  
	            }else if(strDateH==strDateBeginH && strDateM==strDateBeginM && strDateS==strDateBeginS && strDateH<strDateEndH){  
	                return true;  
	            }else if(strDateH>strDateBeginH && strDateH==strDateEndH && strDateM<strDateEndM){  
	                return true;  
	            }else if(strDateH>strDateBeginH && strDateH==strDateEndH && strDateM==strDateEndM && strDateS<strDateEndS){  
	                return true;  
	            }else if(strDateH>strDateBeginH && strDateH==strDateEndH && strDateM==strDateEndM && strDateS==strDateEndS){  
	                return true;  
	            }else{  
	                return false;  
	            }  
	        }else{  
	            return false;  
	        }  
	    }  
		
		
		public void modifySigninTime(String now,String usrid) throws SQLException{
			this.con();
			String sql_modify_signin_time="update usr set "
					+ "signinTime='"+now+"' where id='"+usrid+"'";
			st.executeUpdate(sql_modify_signin_time);
			this.destroyUpdate();
		}
		
		public void compareAndModifySigninTime(String now,
				String oldSigninTime,String usrid) throws ParseException, SQLException{
			int oldDay=0;
			int newDay=0;
			
			if(oldSigninTime.substring(8).equals("0")){
				oldDay=Integer.parseInt(oldSigninTime.substring(9,10));
			}else{
				oldDay=Integer.parseInt(oldSigninTime.substring(8,10));
			}
			
			if(now.substring(8).equals("0")){
				newDay=Integer.parseInt(now.substring(9,10));
			}else{
				newDay=Integer.parseInt(now.substring(8,10));
			}
			
			if((newDay-oldDay)>=1)
				this.modifySigninTime(now,usrid);
		}
		
		public void restoreIsSignin(String usrid) throws SQLException{
			this.con();
			String sql_restore_isSignin="update usr set isSignin1=-1,isSignin2=-1,"
					+ "isSignin3=-1,isSignin4=-1,isSignin5=-1,isSignin6=-1 where "
					+ "id='"+usrid+"'";
			st.executeUpdate(sql_restore_isSignin);
			this.destroyUpdate();
		}
		
		public void compareAndRestoreIsSignin(String now,
				String oldSigninTime,String usrid) throws SQLException{
			int oldDay=0;
			int newDay=0;
			
			if(oldSigninTime.substring(8).equals("0")){
				oldDay=Integer.parseInt(oldSigninTime.substring(9,10));
			}else{
				oldDay=Integer.parseInt(oldSigninTime.substring(8,10));
			}
			
			if(now.substring(8).equals("0")){
				newDay=Integer.parseInt(now.substring(9,10));
			}else{
				newDay=Integer.parseInt(now.substring(8,10));
			}
			
			if((newDay-oldDay)>=1)
				this.restoreIsSignin(usrid);
		}
		
		public String getSigninTime(String usrid) throws SQLException{
			String signinTime="";
			
			this.con();
			String sql_signin_time="select signinTime from usr where id='"+usrid+"'";
			rs=st.executeQuery(sql_signin_time);
			while(rs.next()){
				signinTime=rs.getString("signinTime");
			}
			this.destroyQuery();
			
			return signinTime;
		}
		
		public void modifyIsSignin(String usrid) throws SQLException{
			int index=this.getCurrentCourseIndex();
			
			this.con();
			String sql_modify_isSignin="update usr set isSignin"+index+"=1 where id='"+usrid+"'";
			st.executeUpdate(sql_modify_isSignin);
			this.destroyUpdate();
		}
		
		public String[] getCurrentCourseStart() throws SQLException{
			String[] start_time=new String[6];
			int i=0;
			
			this.con();
			String sql_start_time="select start from timetable order by id";
			rs=st.executeQuery(sql_start_time);
			while(rs.next()){
				start_time[i]=rs.getString("start");
				i++;
			}
			
			return start_time;
		}
		
		public String[] getCurrentCourseEnd() throws SQLException{
			String[] end_time=new String[6];
			int k=0;
			
			this.con();
			String sql_end_time="select end from timetable order by id";
			rs=st.executeQuery(sql_end_time);
			while(rs.next()){
				end_time[k]=rs.getString("end");
				k++;
			}
			
			return end_time;
		}
		
		public String getNow(){
			SimpleDateFormat format=new SimpleDateFormat("HH:mm:ss");
			Date now=new Date();
			String strNow=format.format(now);
			
			return strNow;
		}
		
		public String[] getCurrentCourse() throws SQLException{
			String[] current_course=new String[3];
			boolean judge_time=false;
			
			String strNow=this.getNow();
			String[] start_time=this.getCurrentCourseStart();
			String[] end_time=this.getCurrentCourseEnd();
			
			for(int j=0;j<start_time.length;j++){
				judge_time=this.isInDates(strNow, start_time[j], end_time[j]);
				if(judge_time==true){
					String sql_course_name="select course from timetable where "
							+ "start='"+start_time[j]+"',end_time='"
									+end_time[j]+"'";
					rs=st.executeQuery(sql_course_name);
					current_course[0]=rs.getString("course");
					current_course[1]=start_time[j];
					current_course[2]=end_time[j];
				}
			}
			this.destroyQuery();
			
			return current_course;
		}
		
		public int getCurrentCourseIndex() throws SQLException{
			boolean judge_time=false;
			int index=0;
			
			String strNow=this.getNow();
			String[] start_time=this.getCurrentCourseStart();
			String[] end_time=this.getCurrentCourseEnd();
			
			for(int j=0;j<start_time.length;j++){
				judge_time=this.isInDates(strNow, start_time[j], end_time[j]);
				if(judge_time==true){
					index=j;
				}
			}
			
			return index;
		}
		
		public String getLoginTime(String usrid) throws SQLException{
			String old_login_time="";
			
			this.con();
			String sql_login_time="select loginTime from usr where id='"+usrid+"'";
			rs=st.executeQuery(sql_login_time);
			while(rs.next()){
				old_login_time=rs.getString("loginTime");
			}
			this.destroyQuery();
			
			return old_login_time;
		}
		
		public void modifyLoginTime(String now,String usrid) throws SQLException{
			this.con();
			String sql_modify_login_time="update usr set "
					+ "loginTime='"+now+"' where id='"+usrid+"'";
			st.executeUpdate(sql_modify_login_time);
			this.destroyUpdate();
		}
		
		public String[] getTeacherCourses(String usrid) throws SQLException{
			String[] courses=new String[6];
			String[] courses_temp=new String[6];
			int j=0;
			
			this.con();
			String sql_teacher_courses="select course1,course2,course3,course4,"
					+ "course5,course6 from usr where id='"+usrid+"'";
			rs=st.executeQuery(sql_teacher_courses);
			while(rs.next()){
				courses_temp[0]=rs.getString("course1");
				courses_temp[1]=rs.getString("course2");
				courses_temp[2]=rs.getString("course3");
				courses_temp[3]=rs.getString("course4");
				courses_temp[4]=rs.getString("course5");
				courses_temp[5]=rs.getString("course6");
			}
			for(int i=0;i<courses_temp.length;i++){
				if(courses_temp[i].equals(""))
					continue;
				else{
					courses[j]=courses_temp[i];
					j++;
				}
			}
			
			return courses;
		}
		
		public void createXML4signin(String teacher,String[] courses) throws ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException{
			String filepath="D://apache-tomcat-6.0.29/webapps//Cloud-Based-Intelligent-Educational-Platform"
					+ "//usr/signin//"+teacher+".xml";
			DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
			DocumentBuilder builder=factory.newDocumentBuilder();
			Document doc=builder.newDocument();
			Element Courses=doc.createElement("Courses");
			for(int i=0;i<courses.length;i++){
				Element course=doc.createElement("course");
				course.setAttribute("name",courses[i]);
				Courses.appendChild(course);
			}
			doc.appendChild(Courses);
			
			Transformer transformer=TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8"); 
			StreamResult fileResult=new StreamResult(new File(filepath));
			DOMSource source=new DOMSource(doc);
			transformer.transform(source,fileResult);
		}
		
		public void restoreXML4signin(String teacher,String[] courses) throws ParserConfigurationException, SAXException, IOException, TransformerFactoryConfigurationError, TransformerException{
			String filename="D://apache-tomcat-6.0.29//webapps//Cloud-Based-Intelligent-Educational-Platform//"
            		+ "usr//signin//"+teacher+".xml";
			File file=new File(filename);
			if(file.exists()==false)
				this.createXML4signin(teacher,courses);
			
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
	        Document doc = db.parse(filename);
	        NodeList courses_=doc.getElementsByTagName("course");
	        for(int i=0;i<courses_.getLength();i++){
	        	Node course=courses_.item(i);
	        	NodeList course_children=courses_.item(i).getChildNodes();
	        	for(int k=0;k<course_children.getLength();k++){
	        		course.removeChild(course_children.item(k));
	        	}
	        }
		}
		
		public void compareAndModifyLoginTimeAndXML(String now,
				String oldLoginTime,String usrid,String[] courses) throws SQLException, ParserConfigurationException, SAXException, IOException, TransformerFactoryConfigurationError, TransformerException{
			int oldDay=0;
			int newDay=0;
			
			if(oldLoginTime.substring(8).equals("0")){
				oldDay=Integer.parseInt(oldLoginTime.substring(9,10));
			}else{
				oldDay=Integer.parseInt(oldLoginTime.substring(8,10));
			}
			
			if(now.substring(8).equals("0")){
				newDay=Integer.parseInt(now.substring(9,10));
			}else{
				newDay=Integer.parseInt(now.substring(8,10));
			}
			
			if((newDay-oldDay)>=1){
				this.modifyLoginTime(now,usrid);
				this.restoreXML4signin(usrid, courses);
			}
		}
		
		public ArrayList<User> Groupinguser() throws SQLException{
			this.con();
			ArrayList<User> list=new ArrayList<User>();
			String sql_search="SELECT * FROM usr ORDER BY RAND()  LIMIT 40";
			rs=st.executeQuery(sql_search);
				while(rs.next()){
					User r=new User();
					r.setID(rs.getString("id"));
					r.setName(rs.getString("name"));
					list.add(r);
				}
			this.destroyQuery();
			return list;
		}
		
		public String getAuthority(String usrid) throws SQLException{
			String authority="";
			this.con();
			String sql_authority="select authority from usr where id = '"+usrid+"'";
			rs=st.executeQuery(sql_authority);
			while(rs.next()){
				authority=rs.getString("authority");
			}
			return authority;
		}
		
		public List<homeworkBean> allHomework() throws SQLException{
			this.con();
			List<homeworkBean> holist=new ArrayList<homeworkBean>();
			String sql_all="select * from homework";
			rs=st.executeQuery(sql_all);
				while(rs.next()){
					homeworkBean h=new homeworkBean();
					h.setId(rs.getInt("id"));
					h.setTitle(rs.getString("title"));
					h.setIssuetime(rs.getString("issuetime"));
					h.setDeadline(rs.getString("deadline"));
					h.setIssueteacher(rs.getString("issueteacher"));
					holist.add(h);
				}
			this.destroyQuery();
			return holist;
		}
		
		public List<homeworkBean> getOwnHomework(String usrid) throws SQLException{
			this.con();
			List<homeworkBean> holist=new ArrayList<homeworkBean>();
			String sql_own="select * from homework where issueteacher = '"+usrid+"'";
			rs=st.executeQuery(sql_own);
				while(rs.next()){
					homeworkBean h=new homeworkBean();
					h.setId(rs.getInt("id"));
					h.setTitle(rs.getString("title"));
					h.setIssuetime(rs.getString("issuetime"));
					h.setDeadline(rs.getString("deadline"));
					h.setIssueteacher(rs.getString("issueteacher"));
					holist.add(h);
				}
			this.destroyQuery();
			return holist;
		}
		
		public int isExpired4Homework(int id) throws SQLException, ParseException{
			int isExpired=-1;
			String deadline="";
			
			this.con();
			String sql_deadline="select deadline from homework where id = "+id+"";
			rs=st.executeQuery(sql_deadline);
			while(rs.next()){
				deadline=rs.getString("deadline");
			}
			this.destroyQuery();
			
			String _deadline=deadline+" 00:00:00";
			SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
			Date now_time=new Date();
			String now=format.format(now_time);
			long now_stamp=this.date2TimeStamp(now);
			
			long deadline_stamp=this.date2TimeStamp(_deadline);
			
			if(0<=(now_stamp-deadline_stamp))
				isExpired=1;
			
			return isExpired;
		}
		
		public long date2TimeStamp(String time) throws ParseException{
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = format.parse(time);
			long time_stamp=date.getTime();
			
			return time_stamp;
		}
		
		public String timeStamp2Date(long time_stamp){
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = format.format(time_stamp);
			
			return date;
		}
		
		//begin of homework content
		public List<homeworkAnswerBean> answer4Homework(int id,String issueteacher) throws SQLException{
			String _id=id+"";
			List<homeworkAnswerBean> holist=new ArrayList<homeworkAnswerBean>();

			//dom����
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	    	try {
	            DocumentBuilder db = dbf.newDocumentBuilder();
	            Document doc = db.parse("D://apache-tomcat-6.0.29//webapps//Cloud-Based-Intelligent-Educational-Platform//"
	            		+ "usr//homework_answer_content//"+issueteacher+".xml");
	            NodeList homeworks = doc.getElementsByTagName("homework");
	            for(int i=0;i<homeworks.getLength();i++){
	            	Node title=homeworks.item(i).getChildNodes().item(0);
	            	if(title.getAttributes().getNamedItem("id")
	            			.getNodeValue().toString().equals(_id)){
	            		Node Answers=homeworks.item(i).getChildNodes().item(2);
	            		if(Answers.getChildNodes().getLength()!=0){
	            			NodeList answers=Answers.getChildNodes();//all answer nodes
		            		for(int k=0;k<answers.getLength();k++){
		            			Node answer=answers.item(k);
		            			homeworkAnswerBean ha=new homeworkAnswerBean();
		            			ha.setAusr(answer.getChildNodes().item(0).getTextContent());
		            			ha.setAtime(answer.getChildNodes().item(1).getTextContent());
		            			ha.setAcontent(answer.getChildNodes().item(2).getTextContent());
		            			ha.setAremark(answer.getChildNodes().item(3).getTextContent());
		            			holist.add(ha);
		            		}
	            		}
	            	}
	            }
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}
	    	
			return holist;
		}
		
		public String getHOandTETitle(String datatable,int id) throws SQLException{
			this.con();
			String title="";
			String sql_ho_title="select title from "+datatable+" where id = "+id+"";
			rs=st.executeQuery(sql_ho_title);
			while(rs.next()){
				title=rs.getString("title");
			}
			this.destroyQuery();
			return title;
		}
		
		public String getHOandTEIssueTeacher(String datatable,int id) throws SQLException{
			this.con();
			String issueteacher="";
			String sql_ho_issueteacher="select issueteacher from "+datatable+" where id = "+id+"";
			rs=st.executeQuery(sql_ho_issueteacher);
			while(rs.next()){
				issueteacher=rs.getString("issueteacher");
			}
			this.destroyQuery();
			return issueteacher;
		}
		
		public String getHomeworkContent(int id,String issueteacher) 
				throws ParserConfigurationException, SAXException, IOException{
			String _id=id+"";
			String content="";

			//dom����
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse("D://apache-tomcat-6.0.29//webapps//Cloud-Based-Intelligent-Educational-Platform//"
            		+ "usr//homework_answer_content//"+issueteacher+".xml");
            NodeList homework = doc.getElementsByTagName("homework");
            for(int i=0;i<homework.getLength();i++){
            	Node title=homework.item(i).getChildNodes().item(0);
            	if(title.getAttributes().getNamedItem("id").getNodeValue().equals(_id)){
            		content=homework.item(i).getChildNodes().item(1).getTextContent();
            	}
            }    
	    	
            return content;
		}
		
		public boolean isMyAnswer4Homework(int id,String issueteacher,String ausr){
			String _id=id+"";
			boolean hasMine=true;
			
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	    	try {
	            DocumentBuilder db = dbf.newDocumentBuilder();
	            Document doc = db.parse("D://apache-tomcat-6.0.29//webapps//Cloud-Based-Intelligent-Educational-Platform//"
	            		+ "usr//homework_answer_content//"+issueteacher+".xml");
	            NodeList homework = doc.getElementsByTagName("homework");
	            for(int i=0;i<homework.getLength();i++){
	            	Node title=homework.item(i).getChildNodes().item(0);
	            	if(title.getAttributes().getNamedItem("id").getNodeValue()
	            			.toString().equals(_id)){
	            		Node answer=homework.item(i).getChildNodes().item(2);
	            		if(answer.getChildNodes().item(0).getTextContent().equals(ausr)
	            			&& answer.getChildNodes().item(2).getTextContent().equals(""))
	            				hasMine=false;
	            	}
	            }
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}
			return hasMine;
		}
		//end of homework content
		
		public void insertTeacherRemark(int id,String issueteacher
									,String ausr,String aremark,String atime){
			String _id=id+"";
			
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	    	try {
	            DocumentBuilder db = dbf.newDocumentBuilder();
	            Document doc = db.parse("D://apache-tomcat-6.0.29//webapps//Cloud-Based-Intelligent-Educational-Platform//"
	            		+ "usr//homework_answer_content//"+issueteacher+".xml");
	            NodeList homeworks = doc.getElementsByTagName("homework");
	            for(int i=0;i<homeworks.getLength();i++){
	            	Node title=homeworks.item(i).getChildNodes().item(0);
	            	if(title.getAttributes().getNamedItem("id")
	            			.getNodeValue().toString().equals(_id)){
	            		Node Answers=homeworks.item(i).getChildNodes().item(2);
	            		NodeList answers=Answers.getChildNodes();
	            		for(int k=0;k<answers.getLength();k++){
	            			Node answer=answers.item(k);
	            			if(answer.getChildNodes().item(0).getTextContent().equals(ausr)
	            					&& answer.getChildNodes().item(1).getTextContent().equals(atime)){
	            				answer.getChildNodes().item(3).setTextContent(aremark);
	            				doc2XmlFile(doc,"D://apache-tomcat-6.0.29//webapps//Cloud-Based-Intelligent-Educational-Platform//"
	            	            		+ "usr//homework_answer_content//t001.xml");
	            			}
	            		}
	            	}
	            }
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}
		}
		
		public void doc2XmlFile(Document document, String filename) {
	        try {
	            /** 将document中的内容写入文件中 */
	            TransformerFactory tFactory = TransformerFactory.newInstance();
	            Transformer transformer = tFactory.newTransformer();
	            /** 编码 */
	            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	            DOMSource source = new DOMSource(document);
	            StreamResult result = new StreamResult(new File(filename));
	            transformer.transform(source, result);
	        } catch (Exception ex) {
	            System.out.println("更新" + filename + "出错：" + ex);
	            ex.printStackTrace();
	        }
	    }
		
		/*begin for issue homework*/
		public void creatNewXml4homework(String issueteacher) throws ParserConfigurationException, TransformerException, SAXException, IOException{
			String filepath="D://apache-tomcat-6.0.29/webapps//Cloud-Based-Intelligent-Educational-Platform"
					+ "//usr/homework_answer_content//"+issueteacher+".xml";
			DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
			DocumentBuilder builder=factory.newDocumentBuilder();
			Document doc=builder.newDocument();
			Element Homeworks=doc.createElement("Homeworks");
			doc.appendChild(Homeworks);
			
			Transformer transformer=TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8"); 
			StreamResult fileResult=new StreamResult(new File(filepath));
			DOMSource source=new DOMSource(doc);
			transformer.transform(source,fileResult);
		}
		
		public void db4issueHomework(String title,String content,String issueteacher,String deadline) throws SQLException{
			this.con();
			String sql_issue="insert into homework values(null,'"+title+"','usr/homework_answer_content/"+issueteacher+".xml',"
					+ "now(),'"+deadline+"','"+issueteacher+"')";
			st.executeUpdate(sql_issue);
			this.destroyUpdate();
		}
		
		public void dom4issueHomework(String title,String content,String issueteacher) throws SQLException{
			this.con();
			int id = 0;
			String _id="";
			String sql_id="select id from homework where title = '"+title+"' order by issuetime DESC LIMIT 1";
			rs=st.executeQuery(sql_id);
			while(rs.next()){
				id=rs.getInt("id");
			}
			_id=id+"";
			this.destroyQuery();
			
			//dom�޸�xml
			String fileName="D://apache-tomcat-6.0.29//webapps//Cloud-Based-Intelligent-Educational-Platform//"
            		+ "usr//homework_answer_content//"+issueteacher+".xml";
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	    	try{
	            DocumentBuilder db = dbf.newDocumentBuilder();
	            File xml=new File(fileName);
	            if(false==xml.exists()){
	            	this.creatNewXml4homework(issueteacher);
	            }
	            Document doc = db.parse(fileName);
	            Node homeworks=doc.getElementsByTagName("Homeworks").item(0);
	            Element homework=doc.createElement("homework");
	            Element ti=doc.createElement("title");//title
	            ti.setTextContent(title);
	            ti.setAttribute("id", _id);
	            Element cont=doc.createElement("content");//title--content
	            cont.setTextContent(content);
	            Element Answers=doc.createElement("Answers");
	            homework.appendChild(ti);//title->Debates
	            homework.appendChild(cont);
	            homework.appendChild(Answers);
	            homeworks.appendChild(homework);
	            this.doc2XmlFile(doc, fileName);
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}
		}
		/*end of issue homework*/
		
		public void insertStudentAnswer4Homework(int id,String issueteacher,String ausr
				,String atime,String acontent){
			String _id=id+"";
			
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	    	try {
	            DocumentBuilder db = dbf.newDocumentBuilder();
	            Document doc = db.parse("D://apache-tomcat-6.0.29//webapps//Cloud-Based-Intelligent-Educational-Platform//"
	            		+ "usr//homework_answer_content//"+issueteacher+".xml");
	            NodeList homeworks = doc.getElementsByTagName("homework");
	            for(int i=0;i<homeworks.getLength();i++){
	            	Node title=homeworks.item(i).getChildNodes().item(0);
	            	if(title.getAttributes().getNamedItem("id")
	            			.getNodeValue().toString().equals(_id)){
	            		Node Answers=homeworks.item(i).getChildNodes().item(2);
	            		
	            		Element _answer=doc.createElement("answer");
            			Element _ausr=doc.createElement("ausr");
            			_ausr.setTextContent(ausr);
            			Element _atime=doc.createElement("atime");
            			_atime.setTextContent(atime);
            			Element _acontent=doc.createElement("acontent");
            			_acontent.setTextContent(acontent);
            			Element _aremark=doc.createElement("aremark");
            			_aremark.setTextContent("暂未评价");
            			_answer.appendChild(_ausr);
            			_answer.appendChild(_atime);
            			_answer.appendChild(_acontent);
            			_answer.appendChild(_aremark);
            			Answers.appendChild(_answer);
            			doc2XmlFile(doc, "D:/apache-tomcat-6.0.29/webapps/Cloud-Based-Intelligent-Educational-Platform/"
        	            		+ "usr/homework_answer_content/"+issueteacher+".xml");	
	            	}
	            }
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}
		}
		
		public List<testBean> allTest() throws SQLException{
			this.con();
			List<testBean> telist=new ArrayList<testBean>();
			String sql_all="select * from test";
			rs=st.executeQuery(sql_all);
				while(rs.next()){
					testBean t=new testBean();
					t.setId(rs.getInt("id"));
					t.setTitle(rs.getString("title"));
					t.setIssuetime(rs.getString("issuetime"));
					t.setLimittime(rs.getInt("limittime"));
					t.setIssueteacher(rs.getString("issueteacher"));
					telist.add(t);
				}
			this.destroyQuery();
			return telist;
		}
		
		public int getLimitTimeInMinutes(int id) throws SQLException{
			String limittime="";
			int timespan=0;
			
			this.con();
			String sql_limittime="select limittime from test where id = "+id+"";
			rs=st.executeQuery(sql_limittime);
			while(rs.next()){
				timespan=rs.getInt("limittime");
			}
			this.destroyQuery();
			
			return timespan;
		}
		
		public String getLimitTimeInDate(int id) throws SQLException, ParseException{
			String limittime="";
			int timespan=0;
			
			this.con();
			String sql_limittime="select limittime from test where id = "+id+"";
			rs=st.executeQuery(sql_limittime);
			while(rs.next()){
				timespan=rs.getInt("limittime");
			}
			this.destroyQuery();
			
			SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
			Date now_time=new Date();
			String now=format.format(now_time);
			long now_stamp=this.date2TimeStamp(now);
			
			int s_time=timespan*60*1000;
			long limit_stamp=now_stamp+s_time;
			limittime=this.timeStamp2Date(limit_stamp);
			
			return limittime;
		}
		
		public List<testBean> getOwnTest(String usrid) throws SQLException{
			this.con();
			List<testBean> telist=new ArrayList<testBean>();
			String sql_all="select * from test where issueteacher = '"+usrid+"'";
			rs=st.executeQuery(sql_all);
				while(rs.next()){
					testBean t=new testBean();
					t.setId(rs.getInt("id"));
					t.setTitle(rs.getString("title"));
					t.setIssuetime(rs.getString("issuetime"));
					t.setLimittime(rs.getInt("limittime"));
					t.setIssueteacher(rs.getString("issueteacher"));
					telist.add(t);
				}
			this.destroyQuery();
			return telist;
		}
		
		public String getTestTitle(int id) throws SQLException{
			this.con();
			String title="";
			String sql_te_title="select title from test where id = "+id+"";
			rs=st.executeQuery(sql_te_title);
			while(rs.next()){
				title=rs.getString("title");
			}
			this.destroyQuery();
			return title;
		}
		
		public String getTestIssueTeacher(int id) throws SQLException{
			this.con();
			String issueteacher="";
			String sql_te_issueteacher="select issueteacher from test where id = "+id+"";
			rs=st.executeQuery(sql_te_issueteacher);
			while(rs.next()){
				issueteacher=rs.getString("issueteacher");
			}
			this.destroyQuery();
			return issueteacher;
		}
		
		public void insertTeacherScore(int id,String issueteacher,String ausr,String ascore){
			String _id=id+"";
			
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	    	try {
	    		String filename="D://apache-tomcat-6.0.29//webapps//Cloud-Based-Intelligent-Educational-Platform//"
	            		+ "usr//test_answer_content//"+issueteacher+".xml";
	            DocumentBuilder db = dbf.newDocumentBuilder();
	            Document doc = db.parse(filename);
	            NodeList tests = doc.getElementsByTagName("test");
	            for(int i=0;i<tests.getLength();i++){
	            	Node title=tests.item(i).getChildNodes().item(0);
	            	if(title.getAttributes().getNamedItem("id")
	            			.getNodeValue().equals(_id)){
	            		Node Answers=tests.item(i).getChildNodes().item(2);
	            		NodeList answers=Answers.getChildNodes();
	            		for(int k=0;k<answers.getLength();k++){
	            			Node answer=answers.item(k);
	            			if(answer.getChildNodes().item(0).getTextContent().equals(ausr))
	            				answer.getChildNodes().item(3).setTextContent(ascore);
	            				this.doc2XmlFile(doc, filename);
	            		}
	            	}
	            }
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}
		}
		
		//begin of issue test
		public void creatNewXml4Test(String issueteacher) throws ParserConfigurationException, TransformerException, SAXException, IOException{
			String filepath="D://apache-tomcat-6.0.29/webapps//Cloud-Based-Intelligent-Educational-Platform"
					+ "//usr/test_answer_content//"+issueteacher+".xml";
			DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
			DocumentBuilder builder=factory.newDocumentBuilder();
			Document doc=builder.newDocument();
			Element Homeworks=doc.createElement("Tests");
			doc.appendChild(Homeworks);
			
			Transformer transformer=TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8"); 
			StreamResult fileResult=new StreamResult(new File(filepath));
			DOMSource source=new DOMSource(doc);
			transformer.transform(source,fileResult);
		}
		
		public void db4issueTest(String title,String content,String issueteacher,String limittime,String deadline) throws SQLException{
			this.con();
			String sql_issue="insert into test values(null,'"+title+"','usr/test_answer_content/"+issueteacher+".xml',"
					+ "now(),'"+deadline+"','"+limittime+"','"+issueteacher+"')";
			st.executeUpdate(sql_issue);
			this.destroyUpdate();
		}
		
		public void dom4issueTest(String title,String content,String issueteacher) throws SQLException{
			this.con();
			int id = 0;
			String _id="";
			String sql_id="select id from test where title = '"+title+"' order by issuetime DESC LIMIT 1";
			rs=st.executeQuery(sql_id);
			while(rs.next()){
				id=rs.getInt("id");
			}
			_id=id+"";
			this.destroyQuery();
			
			//dom�޸�xml
			String fileName="D://apache-tomcat-6.0.29//webapps//Cloud-Based-Intelligent-Educational-Platform//"
            		+ "usr//test_answer_content//"+issueteacher+".xml";
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	    	try{
	            DocumentBuilder db = dbf.newDocumentBuilder();
	            File xml=new File(fileName);
	            if(false==xml.exists()){
	            	this.creatNewXml4Test(issueteacher);
	            }
	            Document doc = db.parse(fileName);
	            Node tests=doc.getElementsByTagName("Tests").item(0);
	            Element test=doc.createElement("test");
	            Element ti=doc.createElement("title");//title
	            ti.setTextContent(title);
	            ti.setAttribute("id", _id);
	            Element cont=doc.createElement("content");//title--content
	            cont.setTextContent(content);
	            Element Answers=doc.createElement("Answers");
	            test.appendChild(ti);//title->Debates
	            test.appendChild(cont);
	            test.appendChild(Answers);
	            tests.appendChild(test);
	            this.doc2XmlFile(doc, fileName);
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}
		}
		//end of issue test
		
		public void insertStudentAnswer4Test(int id,String issueteacher,String ausr
				,String atime,String acontent){
			String _id=id+"";
			
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	    	try {
	            DocumentBuilder db = dbf.newDocumentBuilder();
	            Document doc = db.parse("D://apache-tomcat-6.0.29//webapps//Cloud-Based-Intelligent-Educational-Platform//"
	            		+ "usr//test_answer_content//"+issueteacher+".xml");
	            NodeList tests = doc.getElementsByTagName("test");
	            for(int i=0;i<tests.getLength();i++){
	            	Node title=tests.item(i).getChildNodes().item(0);
	            	if(title.getAttributes().getNamedItem("id")
	            			.getNodeValue().toString().equals(_id)){
	            		Node Answers=tests.item(i).getChildNodes().item(2);
	            		
	            		Element _answer=doc.createElement("answer");
            			Element _ausr=doc.createElement("ausr");
            			_ausr.setTextContent(ausr);
            			Element _atime=doc.createElement("atime");
            			_atime.setTextContent(atime);
            			Element _acontent=doc.createElement("acontent");
            			_acontent.setTextContent(acontent);
            			Element _ascore=doc.createElement("ascore");
            			_ascore.setTextContent("暂未打分");
            			_answer.appendChild(_ausr);
            			_answer.appendChild(_atime);
            			_answer.appendChild(_acontent);
            			_answer.appendChild(_ascore);
            			Answers.appendChild(_answer);
            			doc2XmlFile(doc, "D:/apache-tomcat-6.0.29/webapps/Cloud-Based-Intelligent-Educational-Platform/"
        	            		+ "usr/test_answer_content/"+issueteacher+".xml");	
	            	}
	            }
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}
		}
		
		public int isMyAnswer4Test(int id,String issueteacher,String ausr){
			String _id=id+"";
			int hasMine=1;
			
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	    	try {
	            DocumentBuilder db = dbf.newDocumentBuilder();
	            Document doc = db.parse("D://apache-tomcat-6.0.29//webapps//Cloud-Based-Intelligent-Educational-Platform//"
	            		+ "usr//test_answer_content//"+issueteacher+".xml");
	            NodeList tests = doc.getElementsByTagName("test");
	            for(int i=0;i<tests.getLength();i++){
	            	Node title=tests.item(i).getChildNodes().item(0);
	            	if(title.getAttributes().getNamedItem("id").getNodeValue().equals(_id)){
	            		Node Answers=tests.item(i).getChildNodes().item(2);
	            		NodeList answers=Answers.getChildNodes();
	            		if(answers.getLength()!=0){
	            			for(int k=0;k<answers.getLength();k++){
		            			Node answer=answers.item(k);
		            			if(answer.getChildNodes().item(0).getTextContent().equals(ausr)
		    	            			&& answer.getChildNodes().item(2).getTextContent().equals(""))
		    	            				hasMine=-1;
		            		}
	            		}else{
	            			hasMine=-1;
	            		}
	            	}
	            }
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}
	    	
			return hasMine;
		}
		
		public List<testAnswerBean> answer4Test4Student(int id,String issueteacher,String usrid) throws SQLException{
			String _id=id+"";
			List<testAnswerBean> telist=new ArrayList<testAnswerBean>();

			//dom����
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	    	try {
	            DocumentBuilder db = dbf.newDocumentBuilder();
	            Document doc = db.parse("D://apache-tomcat-6.0.29//webapps//Cloud-Based-Intelligent-Educational-Platform//"
	            		+ "usr//test_answer_content//"+issueteacher+".xml");
	            NodeList tests = doc.getElementsByTagName("test");
	            for(int i=0;i<tests.getLength();i++){
	            	Node title=tests.item(i).getChildNodes().item(0);
	            	if(title.getAttributes().getNamedItem("id")
	            			.getNodeValue().toString().equals(_id)){
	            		Node Answers=tests.item(i).getChildNodes().item(2);
	            		NodeList answers=Answers.getChildNodes();
	            		for(int k=0;k<answers.getLength();k++){
	            			if(answers.item(k).getChildNodes().item(0)
	            					.getTextContent().equals(usrid)){
	            				Node answer=answers.item(k);
		            			testAnswerBean te=new testAnswerBean();
		            			te.setAusr(answer.getChildNodes().item(0).getTextContent());
		            			te.setAtime(answer.getChildNodes().item(1).getTextContent());
		            			te.setAcontent(answer.getChildNodes().item(2).getTextContent());
		            			te.setAscore(answer.getChildNodes().item(3).getTextContent());
		            			telist.add(te);
	            			}
	            		}
	            	}
	            }
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}
			return telist;
		}
		
		public int isExpired4Test(int id) throws SQLException, ParseException{
			int isExpired=-1;
			String deadline="";
			
			this.con();
			String sql_deadline="select deadline from test where id = "+id+"";
			rs=st.executeQuery(sql_deadline);
			while(rs.next()){
				deadline=rs.getString("deadline");
			}
			this.destroyQuery();
			
			String _deadline=deadline+" 00:00:00";
			SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
			Date now_time=new Date();
			String now=format.format(now_time);
			long now_stamp=this.date2TimeStamp(now);
			
			long deadline_stamp=this.date2TimeStamp(_deadline);
			
			if(0<=(now_stamp-deadline_stamp))
				isExpired=1;
			
			return isExpired;
		}
		
		public List<testAnswerBean> answer4Test4Teacher(int id,String issueteacher,String usrid) throws SQLException{
			String _id=id+"";
			List<testAnswerBean> telist=new ArrayList<testAnswerBean>();

			//dom����
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	    	try {
	            DocumentBuilder db = dbf.newDocumentBuilder();
	            Document doc = db.parse("D://apache-tomcat-6.0.29//webapps//Cloud-Based-Intelligent-Educational-Platform//"
	            		+ "usr//test_answer_content//"+issueteacher+".xml");
	            NodeList tests = doc.getElementsByTagName("test");
	            for(int i=0;i<tests.getLength();i++){
	            	Node title=tests.item(i).getChildNodes().item(0);
	            	if(title.getAttributes().getNamedItem("id")
	            			.getNodeValue().toString().equals(_id)){
	            		Node Answers=tests.item(i).getChildNodes().item(2);
	            		NodeList answers=Answers.getChildNodes();
	            		for(int k=0;k<answers.getLength();k++){
	            			Node answer=answers.item(k);
	            			testAnswerBean te=new testAnswerBean();
	            			te.setAusr(answer.getChildNodes().item(0).getTextContent());
	            			te.setAtime(answer.getChildNodes().item(1).getTextContent());
	            			te.setAcontent(answer.getChildNodes().item(2).getTextContent());
	            			te.setAscore(answer.getChildNodes().item(3).getTextContent());
	            			telist.add(te);
	            		}
	            	}
	            }
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}
			return telist;
		}
		
		public String getTestContent(int id,String issueteacher) 
				throws ParserConfigurationException, SAXException, IOException{
			String _id=id+"";
			String content="";

			//dom����
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse("D://apache-tomcat-6.0.29//webapps//Cloud-Based-Intelligent-Educational-Platform//"
            		+ "usr//test_answer_content//"+issueteacher+".xml");
            NodeList tests = doc.getElementsByTagName("test");
            for(int i=0;i<tests.getLength();i++){
            	Node title=tests.item(i).getChildNodes().item(0);
            	if(title.getAttributes().getNamedItem("id").getNodeValue().equals(_id)){
            		content=tests.item(i).getChildNodes().item(1).getTextContent();
            	}
            }    
	    	
            return content;
		}
		
		/*begin of info*/
		public List<User> getInfo(String usrid) throws SQLException{
			List<User> ulist=new ArrayList<User>();
			
			this.con();
			String sql_usrinfo="select * from usr where id = '"+usrid+"'";
			rs=st.executeQuery(sql_usrinfo);
			while(rs.next()){
				User u=new User();
				u.setID(rs.getString("id"));
				u.setAuthority(rs.getString("authority"));
				u.setAge(rs.getInt("age"));
				u.setEmail(rs.getString("email"));
				u.setName(rs.getString("name"));
				u.setNation(rs.getString("nation"));
				u.setSex(rs.getString("sex"));
				u.setTel(rs.getString("tel"));
				ulist.add(u);
			}
			
			return ulist;
		}
		
		public String[] getFileName(String path){
			File f=new File(path);
			String[] f_array=f.list();
			
			return f_array;
		}
		
		public int[] getCountFromXML(String usrid,String[] f_ho,String[] f_te) throws SAXException, IOException, ParserConfigurationException{
			List<preBean> inlist=new ArrayList<preBean>();
			int[] temp=new int[2];
			int c_ho=0;
			int c_te=0;
			
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			for(int c1=0;c1<f_ho.length;c1++){
				Document doc = db.parse("D:\\apache-tomcat-6.0.29\\webapps\\Cloud-Based-Intelligent-Educational-Platform\\"
	            		+ "usr\\homework_answer_content\\"+f_ho[c1]);
	            NodeList homeworks = doc.getElementsByTagName("homework");
	            for(int i=0;i<homeworks.getLength();i++){
	            	Node Answers=homeworks.item(i).getChildNodes().item(2);
	            	NodeList answers=Answers.getChildNodes();
	            	for(int k=0;k<answers.getLength();k++){
	            		Node ausr=answers.item(k).getChildNodes().item(0);
	            		if(usrid==ausr.getTextContent())
	            			c_ho++;
	            	}
	            }    
			}
			for(int c2=0;c2<f_te.length;c2++){
				Document doc = db.parse("D:\\apache-tomcat-6.0.29\\webapps\\Cloud-Based-Intelligent-Educational-Platform\\"
	            		+ "usr\\test_answer_content\\"+f_te[c2]);
	            NodeList tests = doc.getElementsByTagName("test");
	            for(int i=0;i<tests.getLength();i++){
	            	Node Answers=tests.item(i).getChildNodes().item(2);
	            	NodeList answers=Answers.getChildNodes();
	            	for(int k=0;k<answers.getLength();k++){
	            		Node ausr=answers.item(k).getChildNodes().item(0);
	            		if(usrid==ausr.getTextContent())
	            			c_te++;
	            	}
	            }    
			}
			
			temp[0]=c_ho;
			temp[1]=c_te;
			
			return temp;
		}
		
		
		public List<preBean> getCount4Student(String usrid,String[] f_ho,String[] f_te) throws SQLException, SAXException, IOException, ParserConfigurationException{
			List<preBean> inlist=new ArrayList<preBean>();
			preBean pb=new preBean();
			int count_re=0;
			int count_de=0;
			
			this.con();
			String sql_info="select c_re.count_re,c_de.count_de from"
					+ "(select count(uploadusr) as count_re "
					+ "from resourceinfo where uploadusr = '"+usrid+"') as c_re,"
					+ "(select count(issueusr) as count_de "
					+ "from debate where issueusr = '"+usrid+"') as c_de";
			rs=st.executeQuery(sql_info);
			while(rs.next()){
				count_re=rs.getInt("count_re");
				count_de=rs.getInt("count_de");
				pb.setCount_re(count_re);
				pb.setCount_de(count_de);
			}
			this.destroyQuery();
			
			int[] temp=this.getCountFromXML(usrid, f_ho, f_te);
			pb.setCount_ho(temp[0]);
			pb.setCount_te(temp[1]);
			inlist.add(pb);
			
			return inlist;
		}
		
		public List<preBean> getCount4Teacher(String usrid) throws SQLException, SAXException, IOException, ParserConfigurationException{
			List<preBean> inlist=new ArrayList<preBean>();
			preBean pb=new preBean();
			int count_re=0;
			int count_de=0;
			int count_ho=0;
			int count_te=0;
			
			this.con();
			String sql_info="select c_re.count_re,c_de.count_de,"
					+ "c_ho.count_ho,c_te.count_te from "
					+ "(select count(uploadusr) as count_re "
					+ "from resourceinfo where uploadusr = '"+usrid+"') as c_re,"
					+ "(select count(issueusr) as count_de "
					+ "from debate where issueusr = '"+usrid+"') as c_de,"
					+ "(select count(issueteacher) as count_ho "
					+ "from homework where issueteacher = '"+usrid+"') as c_ho,"
					+ "(select count(issueteacher) as count_te "
					+ "from test where issueteacher = '"+usrid+"') as c_te";
			rs=st.executeQuery(sql_info);
			while(rs.next()){
				count_re=rs.getInt("count_re");
				count_de=rs.getInt("count_de");
				count_ho=rs.getInt("count_ho");
				count_te=rs.getInt("count_te");
				pb.setCount_re(count_re);
				pb.setCount_de(count_de);
				pb.setCount_ho(count_ho);
				pb.setCount_te(count_te);
				inlist.add(pb);
			}
			this.destroyQuery();
			
			return inlist;
		}
		
		public void modifyPsd(String usrid,String new_psd) throws SQLException{
			this.con();
			String sql_mo_psd="update usr set psd = '"+new_psd+"' "
					+ "where id = '"+usrid+"'";
			st.executeUpdate(sql_mo_psd);
			this.destroyUpdate();
		}
		/*end of info*/
}
