<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Home Page</title>
</head>
<body>
	<p>
		<s:if test="%{#session.user==null}">游客请：<a
				href="<s:url action='registerAction' namespace='/' />">注册</a> 或者 
			<a href="<s:url action='loginAction' namespace='/' />">登录</a>
		</s:if>
		<s:else>欢迎：${user.username} <a
				href="<s:url action='logoutAction' namespace='/' />">登出</a>
		</s:else>
	</p>
	<hr />
	<p />
	<s:url id="url" action="listFilesAction" namespace='/'>
	</s:url>
	<s:a href="%{url}">文件FTP</s:a>
	<p />
	<hr />
	<p>
		<a href="<s:url action='test/!executeA'/>">TestA</a> <a
			href="<s:url action='test/!executeB'/>">TestB</a>
	</p>

</body>
</html>