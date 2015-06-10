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
					t.setLimittime(rs.getString("limittime"));
					t.setIssueteacher(rs.getString("issueteacher"));
					telist.add(t);
				}
			this.destroyQuery();
			return telist;
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
					t.setLimittime(rs.getString("limittime"));
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
		
		/*public List<testAnswerBean> getTestAnswer4Teacher(int id,String issueteacher) throws SQLException{
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
	            			.getNodeValue().equals(_id)){
	            		Node Answers=tests.item(i).getChildNodes().item(2);
	            		NodeList answers=Answers.getChildNodes();
	            		for(int k=0;k<answers.getLength();k++){
	            			Node answer=answers.item(k);
	            			testAnswerBean t=new testAnswerBean();
	            			t.setAusr(answer.getChildNodes().item(0).getTextContent());
	            			t.setAtime(answer.getChildNodes().item(1).getTextContent());
	            			t.setAcontent(answer.getChildNodes().item(2).getTextContent());
	            			t.setAscore(answer.getChildNodes().item(3).getTextContent());
	            			telist.add(t);
	            		}
	            	}
	            }
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}
			return telist;
		}*/
		
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
		
		public void db4issueTest(String title,String content,String issueteacher,String limittime) throws SQLException{
			this.con();
			String sql_issue="insert into test values(null,'"+title+"','usr/test_answer_content/"+issueteacher+".xml',"
					+ "now(),'"+limittime+"','"+issueteacher+"')";
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
	            		for(int k=0;k<answers.getLength();k++){
	            			Node answer=answers.item(k);
	            			if(answer.getChildNodes().item(0).getTextContent().equals(ausr)
	    	            			&& answer.getChildNodes().item(2).getTextContent().equals(""))
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
}
