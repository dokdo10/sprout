package com.dokdo.sprout.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dokdo.sprout.common.service.SproutServiceImpl;

@Controller
public class TestController extends SproutServiceImpl{
	Logger log = Logger.getLogger(this.getClass());
    
    @RequestMapping(value= {"/test2.do", "/test1.do" } )
    public @ResponseBody Map<String, Object> openSampleList(HttpServletRequest request) throws Exception{
    	
    	return resultMap;
    }
    
}
