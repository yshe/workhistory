package com.yabushan.exception;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExceptionController {
	@RequestMapping("/exception")
	public String hello() throws Exception {
	    throw new Exception("发生错误");
	}
}
