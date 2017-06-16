package com.dokdo.sprout.controller.admin;

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


public class AdminActiveCTL extends SproutServiceCTL{
	Logger log = Logger.getLogger(this.getClass());
    
	private static String[] PARAM = 
		{ "target_uuid", "use_data" };
	
	@Override
	public Map<String, Object> process(HttpServletRequest request, SproutServiceImpl service)
			throws ParamException, SqlException, SessionException {
		DaoMap map;
		
		/* 세션 체크 */
		service.isValidity(request.getParameter("session"), request.getParameter("uuid"));
		
		/* Param check */
		map = CUtil.ParamToMap(PARAM, request);
	
		String use_data = map.getString("use_data");
		if( !"1".equals(use_data) && !"0".equals(use_data) ){
			resultMap.putResult(EnError.ERROR_PARAM);
			return resultMap;
		}
		
		int iRet = service.updateActiveUser(map);
		
		if( iRet == 1 )
			resultMap.putResult(EnError.ERROR_SUCCESS);
		else 
			resultMap.putResult(EnError.ERROR_DB);
    	
    	
    	return resultMap;
	}
   
}
