package bean;

public class User {
private String ID;
private String authority;
private String psd;
private String name;
private int age;
private String sex;
private String nation;
private String tel;
private String email;
public String getID() {
	return ID;
}
public void setID(String iD) {
	ID = iD;
}
public String getAuthority() {
	return authority;
}
public void setAuthority(String authority) {
	this.authority = authority;
}
public String getPsd() {
	return psd;
}
public void setPsd(String psd) {
	this.psd = psd;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getSex() {
	return sex;
}
public int getAge() {
	return age;
}
public void setAge(int age) {
	this.age = age;
}
public void setSex(String sex) {
	this.sex = sex;
}
public String getNation() {
	return nation;
}
public void setNation(String nation) {
	this.nation = nation;
}
public String getTel() {
	return tel;
}
public void setTel(String tel) {
	this.tel = tel;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
}
