package com.yabushan.exceptionjson;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JsonExceptionController {
	@RequestMapping("/json")
    public String json() throws MyException {
        throw new MyException("发生错误2");
    }
}
