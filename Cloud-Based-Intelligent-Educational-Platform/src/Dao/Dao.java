package Dao;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
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
import bean.classes;
import bean.debateBean;
import bean.debateReplyBean;
import bean.homeworkAnswerBean;
import bean.homeworkBean;
import bean.resourceBean;

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
		
		public void upload(String title,String content,String size,String location,String name) throws SQLException{
			this.con();
			String sql_upload="insert into resourceinfo values(null,'"
									+title+"','"+size+"',now(),'"+location+"','"+content+"',0,'lizhiwei','"+name+"')";
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
			String result=null;
			String sql_login="select psd from usr where id="+usr+"";
		    rs=st.executeQuery(sql_login);
		    while(rs.next()){
				if (rs.getString("psd").equals(psd)) {
					result = "success";
					System.out.println("a");
					break;
				} else {
					result = "psd_error";
					System.out.println("b");
					break;
				}
		    }
		    if(!rs.next()){
		    	result="id_error";
		    	System.out.println("c");
		    }
		    this.destroyQuery();
		    return result;
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
		
		public List<debateReplyBean> getDebateContent(int id,String issueusr) throws SQLException{
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
	            NodeList titles = doc.getElementsByTagName("title");
	            for(int i=0;i<titles.getLength();i++){
	            	if(titles.item(i).getNodeValue()==title && 
	            			titles.item(i).getAttributes().toString()==_id){
	            		NodeList replies=doc.getElementsByTagName("reply");
	            		for(int k=0;k<replies.getLength();k++){
	            			Node reply=replies.item(k);
	            			debateReplyBean dr=new debateReplyBean();
	            			dr.setReplyusr(reply.getChildNodes().item(0).toString());
	            			dr.setReplytime(reply.getChildNodes().item(1).toString());
	            			dr.setReplycontent(reply.getChildNodes().item(2).toString());
	            			delist.add(dr);
	            		}
	            	}
	            }
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}
			return delist;
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
			String filepath="./usr/debate_reply_content/"+usrid+".xml";
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
			String sql_issue="insert debate values(null,'"+title+"','usr/debate_reply_content/"+usrid+".xml',"
					+ "now(),0,'"+usrid+"')";
			st.executeUpdate(sql_issue);
			this.destroyUpdate();
		}
		
		public void dom4issue(String title,String content,String usrid) throws SQLException{
			this.con();
			int id = 0;
			String _id="";
			String sql_id="select id from debate where title = '"+title+"'";
			rs=st.executeQuery(sql_id);
			while(rs.next()){
				id=rs.getInt("id");
			}
			_id=id+"";
			this.destroyQuery();
			
			//dom�޸�xml
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	    	try {
	            DocumentBuilder db = dbf.newDocumentBuilder();
	            Document doc = db.parse("D://apache-tomcat-6.0.29//webapps//Cloud-Based-Intelligent-Educational-Platform//"
	            		+ "usr//debate_reply_content//"+usrid+".xml");
	            if(null==doc){
	            	creatNewXml(usrid);
	            }
	            Node debates=doc.getElementsByTagName("Debates").item(0);
	            Element ti=doc.createElement("title");//title
	            ti.appendChild(doc.createTextNode(title));
	            ti.setAttribute("id", _id);
	            Element cont=doc.createElement("content");//title--content
	            cont.appendChild(doc.createTextNode(content));
	            ti.appendChild(cont);//content->title
	            debates.appendChild(ti);//title->Debates
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
				Node debates=doc.getElementsByTagName("Debates").item(0);
				NodeList ti=debates.getChildNodes();
				for(int i=0;i<ti.getLength();i++){
					Node tit=ti.item(i);
					if(tit.getAttributes().toString().equals(_id)
							&& tit.getNodeValue().toString().equals(title)){
						Node _replyusr=doc.createElement("replyusr");
						_replyusr.appendChild(doc.createTextNode(replyusr));
						Node _replytime=doc.createElement("replytime");
						_replytime.appendChild(doc.createTextNode(replytime));
						Node _replycontent=doc.createElement("replycontent");
						_replycontent.appendChild(doc.createTextNode(replycontent));
						
						Node reply=doc.createElement("reply");
						reply.appendChild(_replyusr);
						reply.appendChild(_replytime);
						reply.appendChild(_replycontent);
						
						tit.appendChild(reply);
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
	            NodeList titles = doc.getElementsByTagName("title");
	            for(int i=0;i<titles.getLength();i++){
	            	if(titles.item(i).getNodeValue()==title && 
	            			titles.item(i).getAttributes().toString()==_id){
	            		NodeList replies=doc.getElementsByTagName("reply");
	            		for(int k=0;k<replies.getLength();k++){
	            			Node reply=replies.item(k);
	            			debateReplyBean dr=new debateReplyBean();
	            			dr.setReplyusr(reply.getChildNodes().item(0).toString());
	            			dr.setReplytime(reply.getChildNodes().item(1).toString());
	            			dr.setReplycontent(reply.getChildNodes().item(2).toString());
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
		public ArrayList<User> alluser() throws SQLException{
			this.con();
			ArrayList<User> list=new ArrayList<User>();
			String sql_search="select * from usr";
			rs=st.executeQuery(sql_search);
				while(rs.next()){
					User r=new User();
					r.setID(rs.getString("id"));
					r.setName(rs.getString("name"));
					r.setAge(rs.getString("age"));
					r.setSex(rs.getString("sex"));
					r.setEmail(rs.getString("email"));
					r.setNation(rs.getString("nation"));
					r.setTel(rs.getString("tel"));
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
					r.setAge(rs.getString("age"));
					r.setSex(rs.getString("sex"));
					r.setEmail(rs.getString("email"));
					r.setNation(rs.getString("nation"));
					r.setTel(rs.getString("tel"));
					list.add(r);
					num++;
				}
			this.destroyQuery();
			return num;
		}
		
		public List<classes> getClasses() throws SQLException{
			this.con();
			List<classes> delist=new ArrayList<classes>();
			String sql_debate="select * from classes";
			rs=st.executeQuery(sql_debate);
			while(rs.next()){
				classes ca = new classes();
				ca.setS_class1(rs.getString("subject1"));
				ca.setS_class2(rs.getString("subject2"));
				ca.setS_class3(rs.getString("subject3"));
				delist.add(ca);
			}
			this.destroyQuery();
			return delist;
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
					r.setAge(rs.getString("age"));
					r.setSex(rs.getString("sex"));
					r.setEmail(rs.getString("email"));
					r.setNation(rs.getString("nation"));
					r.setTel(rs.getString("tel"));
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
			String sql_own="select * from homework where id = '"+usrid+"'";
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
		
		//begin of homework content
		public List<homeworkAnswerBean> answer(int id,String issueteacher) throws SQLException{
			String _id=id+"";
			List<homeworkAnswerBean> holist=new ArrayList<homeworkAnswerBean>();

			//dom����
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	    	try {
	            DocumentBuilder db = dbf.newDocumentBuilder();
	            Document doc = db.parse("D://apache-tomcat-6.0.29//webapps//Cloud-Based-Intelligent-Educational-Platform//"
	            		+ "usr//homework_answer_content//"+issueteacher+".xml");
	            NodeList titles = doc.getElementsByTagName("title");
	            for(int i=0;i<titles.getLength();i++){
	            	if(titles.item(i).getAttributes().toString()==_id){
	            		NodeList answers=doc.getElementsByTagName("answer");
	            		for(int k=0;k<answers.getLength();k++){
	            			Node answer=answers.item(k);
	            			homeworkAnswerBean ha=new homeworkAnswerBean();
	            			ha.setAusr(answer.getChildNodes().item(0).toString());
	            			ha.setAtime(answer.getChildNodes().item(1).toString());
	            			ha.setAcontent(answer.getChildNodes().item(2).toString());
	            			ha.setAremark(answer.getChildNodes().item(4).toString());
	            			holist.add(ha);
	            		}
	            	}
	            }
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}
			return holist;
		}
		
		public String getHomeworkTitle(int id) throws SQLException{
			this.con();
			String title="";
			String sql_ho_title="select title from homework where id = "+id+"";
			rs=st.executeQuery(sql_ho_title);
			while(rs.next()){
				title=rs.getString("title");
			}
			this.destroyQuery();
			return title;
		}
		
		public String getHomeworkIssueTeacher(int id) throws SQLException{
			this.con();
			String issueteacher="";
			String sql_ho_issueteacher="select issueteacher from homework where id = "+id+"";
			rs=st.executeQuery(sql_ho_issueteacher);
			while(rs.next()){
				issueteacher=rs.getString("issueteacher");
			}
			this.destroyQuery();
			return issueteacher;
		}
		
		public String getHomeworkContent(int id,String issueteacher){
			String _id=id+"";
			String content_="";

			//dom����
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	    	try {
	            DocumentBuilder db = dbf.newDocumentBuilder();
	            Document doc = db.parse("D://apache-tomcat-6.0.29//webapps//Cloud-Based-Intelligent-Educational-Platform//"
	            		+ "usr//homework_answer_content//"+issueteacher+".xml");
	            NodeList titles = doc.getElementsByTagName("title");
	            for(int i=0;i<titles.getLength();i++){
	            	if(titles.item(i).getAttributes().toString()==_id){
	            		Node content=titles.item(i).getChildNodes().item(0);
	            		content_=content.getTextContent().toString();
	            	}
	            }
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}
			return content_;
		}
		//end of homework content
		
		public void insertTeacherRemark(){
			
		}
}
