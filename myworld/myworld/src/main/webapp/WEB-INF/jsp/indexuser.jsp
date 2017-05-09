<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<title>测试</title>
  </head>
<script type="text/javascript">
var user =<%=request.getAttribute("user")%>;
alert(user);
</script>
  <body>
  ${user}
    ${user.username}
  </body>
</html>