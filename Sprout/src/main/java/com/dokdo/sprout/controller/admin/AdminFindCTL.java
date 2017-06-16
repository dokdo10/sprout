package com.dokdo.sprout.controller.admin;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.dokdo.sprout.common.def.EnError;
import com.dokdo.sprout.common.exception.ParamException;
import com.dokdo.sprout.common.exception.SessionException;
import com.dokdo.sprout.common.exception.SqlException;
import com.dokdo.sprout.common.service.SproutServiceCTL;
import com.dokdo.sprout.common.service.SproutServiceImpl;
import com.dokdo.sprout.common.util.CUtil;
import com.dokdo.sprout.common.util.DaoMap;
import com.dokdo.sprout.common.util.ResultMap;

public class AdminFindCTL extends SproutServiceCTL{
	Logger log = Logger.getLogger(this.getClass());
    
	private static String[] PARAM = 
		{"name", "domain"};
	

	@Override
	public Map<String, Object> process(HttpServletRequest request, SproutServiceImpl service ) 
			throws ParamException, SqlException, SessionException {
    	DaoMap map;
    	
    	/* Param check */
		map = CUtil.ParamToMap(PARAM, request);
		
		
		/* 아이디 및 도메인 검사 */
		List<DaoMap> ret = service.selectFindUser(map);
		
		if( ret == null || ret.isEmpty() )
			resultMap.putResult(EnError.ERROR_USER_NOT_EXISTS);
		else{
			resultMap.putResult(EnError.ERROR_SUCCESS);
			resultMap.put(ResultMap.RET_DATA, ret);
		}
    	
    	return resultMap;
	}
}
