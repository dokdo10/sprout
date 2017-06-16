package com.dokdo.sprout.controller.admin;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;

import com.dokdo.sprout.common.def.EnError;
import com.dokdo.sprout.common.exception.ParamException;
import com.dokdo.sprout.common.exception.SessionException;
import com.dokdo.sprout.common.exception.SqlException;
import com.dokdo.sprout.common.service.SproutServiceCTL;
import com.dokdo.sprout.common.service.SproutServiceImpl;
import com.dokdo.sprout.common.util.CUtil;
import com.dokdo.sprout.common.util.DaoMap;
import com.dokdo.sprout.common.util.Regex;

@Controller
public class AdminCheckIDCTL extends SproutServiceCTL{
	Logger log = Logger.getLogger(this.getClass());
    
	private static String[] PARAM = 
		{"id", "domain"};
	

	@Override
	public Map<String, Object> process(HttpServletRequest request, SproutServiceImpl service)
			throws ParamException, SqlException, SessionException {
    	DaoMap map;
    	
    	/* Param check */
		map = CUtil.ParamToMap(PARAM, request);
		
		/* 아이디 정규식 체크 */
		if( !Regex.checkUserID(map.getStringUP("id")) ){
			resultMap.putResult(EnError.ERROR_REGEX_ID);
			return resultMap;
		}
		
		/* 아이디 및 도메인 검사 */
		map = service.selectCheckID(map);
		
		String id_exists 		= map.getStringUP("ID_EXISTS");
		String domain_exists 	= map.getStringUP("DOMAIN_EXISTS");
		
		if( "1".equals(id_exists) )
			resultMap.putResult(EnError.ERROR_EXISTS_ID);
		
		else if( "0".equals(domain_exists) )
			resultMap.putResult(EnError.ERROR_DOMAIN_NOT_EXISTS);
		
		else
			resultMap.putResult(EnError.ERROR_SUCCESS);
    	
    	
    	return resultMap;
	}
    
}
