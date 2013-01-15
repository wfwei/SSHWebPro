<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>files</title>
</head>
<body>

	<script type="text/javascript">
	<!--addMore函数可以提供上传多个文件上传-->
		function addMore() {

			var td = document.getElementById("more");

			var br = document.createElement("br");
			var input = document.createElement("input");
			var button = document.createElement("input");

			input.type = "file";
			input.name = "upload";

			button.type = "button";
			button.value = "   删除    ";

			button.onclick = function() {
				td.removeChild(br);
				td.removeChild(input);
				td.removeChild(button);
			}

			td.appendChild(br);
			td.appendChild(input);
			td.appendChild(button);
		}
	</script>

	<a href="index.jsp">返回</a>

	<table align="center" width="70%" border="1">
		<tr>
			<td align="center">文件</td>
		</tr>
		<c:forEach var="uploadFiles" items="${uploadFiles}">
			<tr>
				<td><a
					href="downloadAction.action?fileName=${uploadFiles.name }&fileLocation=${uploadFiles.location }">${uploadFiles.name
						}</a></td>
			</tr>
		</c:forEach>
	</table>

	<table align="center" width="50%">
		<tr>
			<td><s:fielderror cssStyle="color:red" /></td>
		</tr>
	</table>

	<s:form namespace="/" action="uploadAction" theme="simple"
		method="post" enctype="multipart/form-data">

		<table align="center" width="50%" border="1">
			<tr>
				<td>上传文件</td>
				<td id="more"><s:file name="upload"></s:file> <input
					type="button" value="上传更多..." onclick="addMore()" /></td>
			</tr>
			<tr>
				<td></td>
				<td><s:submit value=" 确认 "></s:submit> <s:reset value=" 重置 "></s:reset>
				</td>
			</tr>

		</table>

	</s:form>

</body>
</html>