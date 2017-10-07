package com.san.model;

public class User {
	 private static final long serialVersionUID = 1L;
	private int id;
	private String userName;
	private String pwd;
	private String mailbox;
	private int phone;
	private int grade;
	private String image;
	
	public User() {
		super();
	}
	public User(int id, String userName, String pwd, String mailbox, int phone,
			int grade) {
		super();
		this.id = id;
		this.userName = userName;
		this.pwd = pwd;
		this.mailbox = mailbox;
		this.phone = phone;
		this.grade = grade;
	}
	public User(String userName, String image) {
		super();
		this.userName = userName;
		this.image = image;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}

	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getMailbox() {
		return mailbox;
	}
	public void setMailbox(String mailbox) {
		this.mailbox = mailbox;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "User [grade=" + grade + ", id=" + id + ", image=" + image
				+ ", mailbox=" + mailbox + ", phone=" + phone + ", pwd=" + pwd
				+ ", userName=" + userName + "]";
	}
	
}
