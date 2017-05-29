<%@page language="java"  import="java.io.*,java.net.*,org.springframework.util.ClassUtils" pageEncoding="UTF-8"%><%
    	String filePath = request.getParameter("filePath");
    	String fileName = request.getParameter("fileName");
    	//String filePath = new String(request.getParameter("filePath").getBytes("utf-8"),"ISO-8859-1"); 
    	//String fileName = new String(request.getParameter("fileName").getBytes("utf-8"),"ISO-8859-1"); 
		//fileName = java.net.URLEncoder.encode(fileName,"utf-8"); 	     	 
		
 	    ClassLoader loader = ClassUtils.getDefaultClassLoader();
 	    
 	    InputStream inStream = loader.getResourceAsStream(filePath);

        if(inStream != null)
        {
        	ServletOutputStream outStream = response.getOutputStream();
            response.reset();
			response.setHeader("Content-Type","application/octet-stream");
			
			response.setHeader("Content-Disposition","attachment;filename=" + fileName);
			response.setHeader("Connection","close");			
           // FileInputStream inStream      = new FileInputStream(is);

            try
            {
				byte[] bytes = new byte[1024];
				int n = -1 ;
				while(( n = inStream.read(bytes)) != -1)
				{
                    outStream.write(bytes,0,n);
                }
                return;
            }
            finally
            {
                try
                {
                   inStream.close(); 
                   outStream.flush();
                   outStream.close();        
                }
                catch(Throwable e){}
            }
        }
        else{
        	out.println("<script language=\"javascript\">");
        	out.println("alert('该文件不存在，请检查其正确性！');");
        	out.println("window.close();");
        	out.println("</script>");
        }
%>
