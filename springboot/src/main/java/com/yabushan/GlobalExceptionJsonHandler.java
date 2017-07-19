package com.yabushan;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yabushan.exceptionjson.ErrorInfo;
import com.yabushan.exceptionjson.MyException;
@ControllerAdvice
public class GlobalExceptionJsonHandler {
	//自定义的异常类MyException
	  @ExceptionHandler(value = MyException.class)
	    @ResponseBody
	    public ErrorInfo<String> jsonErrorHandler(HttpServletRequest req, MyException e) throws Exception {
	        ErrorInfo<String> r = new ErrorInfo<>();
	        r.setMessage(e.getMessage());
	        r.setCode(ErrorInfo.ERROR);
	        r.setData("Some Data");
	        r.setUrl(req.getRequestURL().toString());
	        return r;
	    }
}
