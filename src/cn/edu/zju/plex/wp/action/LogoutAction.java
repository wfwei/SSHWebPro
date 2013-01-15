package cn.edu.zju.plex.wp.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class LogoutAction extends ActionSupport {
	public String execute() {
		ActionContext.getContext().getSession().remove("user");
		return SUCCESS;
	}
}
