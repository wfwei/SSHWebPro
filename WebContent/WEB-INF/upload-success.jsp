<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>上传成功</title>
<link href="<s:url value="/css/main.css"/>" rel="stylesheet"
	type="text/css" />
</head>

<body>

	<table class="wwFormTable">
		<tr>
			<td colspan="2">
				<h1>上传成功</h1>
			</td>
		</tr>

		<tr>
			<td class="tdLabel"><label for="doUpload_upload" class="label">
					内容类型: </label></td>
			<td><s:property value="uploadContentType" /></td>
		</tr>

		<tr>
			<td class="tdLabel"><label for="doUpload_upload" class="label">
					文件路径: </label></td>
			<td><s:property value="uploadFileName" /></td>
		</tr>

		<tr>
			<td class="tdLabel"><label for="doUpload_upload" class="label">
					文件: </label></td>
			<td><s:property value="upload" /></td>
		</tr>
	</table>

	<a href="listFilesAction.action">返回文件列表</a>

</body>
</html>
