package com.yabushan.springboot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TestPropeties {
	 @Value("${com.yabushan.sex}")
	    private String sex;
	    @Value("${com.yabushan.name}")
	    private String name;
		public String getSex() {
			return sex;
		}
		public void setSex(String sex) {
			this.sex = sex;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	    
}
