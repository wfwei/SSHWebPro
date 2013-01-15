package cn.edu.zju.plex.wp.action;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.edu.zju.plex.wp.bean.UserBean;
import cn.edu.zju.plex.wp.dao.UserDAO;
import cn.edu.zju.plex.wp.util.EncryptPasswd;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class LoginAction extends ActionSupport implements ModelDriven<UserBean> {
	private static final long serialVersionUID = 8716953544745282211L;
	private UserBean user = new UserBean();

	public UserBean getModel() {
		return user;
	}

	public String execute() throws Exception {
		try {
			user.setPassword(EncryptPasswd.createPassword(user.getPassword())); // 对用户输入的密码进行加密
			UserBean validUser = UserDAO.loginValidate(user);
			if (validUser != null) {
				UserDAO.loginUser(validUser);
				addToSession("user", validUser);
				System.out.println("Login as:" + validUser);
				return SUCCESS;
			} else {
				addFieldError("username", "用户名和密码不一致");
				return LOGIN;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return LOGIN;
	}

	private void addToSession(String attribute, Object value) {
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.setAttribute(attribute, value);
		session.setMaxInactiveInterval(60 * 60 * 3);
	}

}
