package cn.edu.zju.plex.wp.util;

import java.util.Map;

import cn.edu.zju.plex.wp.bean.UserBean;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthorizationInterceptor extends AbstractInterceptor {
	private String ignoreActions;

	public String getIgnoreActios() {
		return ignoreActions;
	}

	public void setIgnoreActions(String ignoreActions) {
		this.ignoreActions = ignoreActions;
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext ctx = invocation.getInvocationContext();
		UserBean user = (UserBean) ctx.getSession().get("user");
		boolean ignore = false;
		String currentAction = invocation.getProxy().getActionName();
		String[] actions = ignoreActions.split(",");

		for (String action : actions) {
			if (action.trim().equals(currentAction)) {
				ignore = true;
				break;
			}
		}
		if (user != null || ignore == true) {
			return invocation.invoke();
		} else {
			return Action.LOGIN;
		}

	}
}
