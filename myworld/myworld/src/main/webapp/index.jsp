<%@ page contentType="text/html; charset=UTF-8"%>
<html>


<body>
<h2>Hello World!我们都是好孩子</h2>
<%@ include file="/common/meta.jsp"%>
<form name="from1" action="user/showuser.do" method="post">
<table width="300" border="1">

<tr>
    <td colspan="2"> 登录窗口</td>
</tr>
<tr>
    <td > 用户名</td>
    <td > <input type="text" name="username" size="10"></td>
</tr>

<tr>
    <td > 密码</td>
    <td > <input type="password" name="password" size="10"></td>
</tr>

<tr>
     <td colspan ="2"> <input type="submit" name="submit" value="登录">
     <a href="user/register.do">注册新用户</a></td>
</tr>

 </table>

</form>


<form name="from2" action="user/register.do" method="post">
<table width="300" border="1">

<tr>
    <td colspan="2"> 登录窗口</td>
</tr>
<tr>
    <td > 用户名</td>
    <td > <input type="text" name="username" size="10"></td>
</tr>

<tr>
    <td > 密码</td>
    <td > <input type="password" name="password" size="10"></td>
</tr>

<tr>
     <td colspan ="2"> <input type="submit" name="submit" value="注册">
</tr>
<tr><td><input type="button" value="下载模板" onclick="download();"/></td></tr>

 </table>

</form>



</body>

<script type="text/javascript">
//下载模板
function download(){
	var url = encodeURI(absBasePath+"common/file_download.jsp?filePath=/template/docker.docx&fileName=发个docker.docx");
	alert(url);
		window.open(url,null,"left=4000;top=4000");
}
</script>
</html>
