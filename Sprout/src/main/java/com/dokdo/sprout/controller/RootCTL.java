package com.dokdo.sprout.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dokdo.sprout.common.def.EnError;
import com.dokdo.sprout.common.exception.ParamException;
import com.dokdo.sprout.common.exception.SessionException;
import com.dokdo.sprout.common.exception.SqlException;
import com.dokdo.sprout.common.service.SproutServiceCTL;
import com.dokdo.sprout.common.service.SproutServiceImpl;

@Controller
public class RootCTL extends SproutServiceImpl{
	Logger log = Logger.getLogger(this.getClass());
    
    @RequestMapping(value= {
    		"/test2.do", 
    		"/admin/Active.do", 
    		"/admin/CheckID.do", 
    		"/admin/Find.do",
    		"/admin/Login.do",
    		"/admin/Reg.do",
    		"/admin/Update.do",
    		"/member/List.do",
    		"/member/Reg.do"} )
    public @ResponseBody Map<String, Object> root(HttpServletRequest request) throws Exception{
    	try{
    		return SproutServiceCTL.GetServiceCTL(request.getRequestURL().toString()).process(request, this);
    	}
    	/* 공통 파라메터 에러 */
    	catch( ParamException e ){
    		log.error("ParamException", e);
    		resultMap.putResult(EnError.ERROR_PARAM, e.getMessage());
    	}
    	/* 공통 DB 에러 */
    	catch( SqlException e ){
    		log.error("SqlException", e);
    		resultMap.putResult(EnError.ERROR_DB, e.getMessage());
    	}
    	/* 세션 에러 */
    	catch( SessionException e ){
    		log.error("SessionException", e);
    		resultMap.putResult(e.getError() );
    	}
    	/* 소스 에러겠지.. */
    	catch( Exception e ){
    		log.error("Exception", e);
    		resultMap.putResult(EnError.ERROR_SYSTEM, e.getMessage());
    	}
    	
    	return resultMap;
    }
}
