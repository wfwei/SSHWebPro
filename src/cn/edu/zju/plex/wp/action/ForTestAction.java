package cn.edu.zju.plex.wp.action;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ForTestAction extends ActionSupport {
	private static final long serialVersionUID = -4169059689211536315L;
	ActionContext actionContext = ActionContext.getContext();
	ServletContext sActionContext = ServletActionContext.getServletContext();
	
	public String executeA() {
		actionContext.get("request");
		System.out.println("execute A");
		return "executeA";
	}

	public String executeB() {
		System.out.println("execute B");
		return "executeB";
	}

	@Override
	public String execute() throws Exception {
		System.out.println("Default Execute");
		return "success";
	}

}
