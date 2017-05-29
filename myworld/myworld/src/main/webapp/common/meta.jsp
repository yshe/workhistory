<meta http-equiv="Cache-Control" content="no-cache" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+ ":"+ request.getServerPort()+ path + "/";
%>
<base href="<%=basePath%>" target="_self"/>
<script>
	var absBasePath = "<%=basePath%>";
	var absPath = "<%=path%>";
</script>
