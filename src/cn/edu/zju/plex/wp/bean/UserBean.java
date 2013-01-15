package cn.edu.zju.plex.wp.bean;

import java.util.Date;

public class UserBean {

	private Long id; // 用户id

	private String username; // 用户名

	private String realname; // 姓名

	private String email; // 邮件

	private String password; // 密码

	private boolean isActive; // 是否存活

	private Date lastLogin; // 最近登入时间

	private Date timeJoined; // 注册时间

	public String toString() {
		return String
				.format("{id:%d, username:%s, email:%s}", id, username, email);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public Date getTimeJoined() {
		return timeJoined;
	}

	public void setTimeJoined(Date timeJoined) {
		this.timeJoined = timeJoined;
	}
}
