<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>登录</title>
</head>
<body>

	<s:form namespace="/" action="loginAction" theme="simple" method="post"
		enctype="multipart/form-data">

		<table align="center" width="50%" border="1">
			<caption>用户登录</caption>
			<tr>
				<td>用户名:</td>
				<td><input name="username" type="text"></td>
			</tr>
			<tr>
				<td>密 码:</td>
				<td><input name="password" type="password"></td>
			</tr>
			<tr>
				<td><s:submit value=" 登录 "></s:submit></td>
				<td><s:reset value=" 重置 "></s:reset></td>
			</tr>

		</table>

	</s:form>

</body>
</html>