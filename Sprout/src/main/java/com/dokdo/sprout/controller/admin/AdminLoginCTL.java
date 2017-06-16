package com.dokdo.sprout.controller.admin;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.dokdo.sprout.common.def.DefConst;
import com.dokdo.sprout.common.def.EnError;
import com.dokdo.sprout.common.exception.ParamException;
import com.dokdo.sprout.common.exception.SessionException;
import com.dokdo.sprout.common.exception.SqlException;
import com.dokdo.sprout.common.service.SproutServiceCTL;
import com.dokdo.sprout.common.service.SproutServiceImpl;
import com.dokdo.sprout.common.util.CUtil;
import com.dokdo.sprout.common.util.DaoMap;
import com.dokdo.sprout.common.util.ResultMap;

public class AdminLoginCTL extends SproutServiceCTL{
	Logger log = Logger.getLogger(this.getClass());
    
	private static String[] PARAM = 
		{ "id", "pw", "domain" };
	

	@Override
	public Map<String, Object> process(HttpServletRequest request, SproutServiceImpl service)
			throws ParamException, SqlException, SessionException {
		
    	DaoMap map;
    	
    	/* Param check */
		map = CUtil.ParamToMap(PARAM, request);
	
		map = service.selectLogin(map);
		
		if( map == null || map.isEmpty() ){
			resultMap.putResult(EnError.ERROR_AUTH);
			return resultMap;
		}
		
		map.remove("PASS");
		
		CUtil.printMap(log, map);
		
		String use_data = map.getString("USE_DATA");
		
		if( use_data == null || DefConst.USE_NO.equals(use_data) )
			resultMap.putResult(EnError.ERROR_USER_NO_USE);
		else{
			
			map.put("session", CUtil.GetSessionKey());
			map.put("uuid", map.get("UUID"));
			int iRet = service.updateSessionKey(map);
			
			if( iRet == 1 ){
				map.put("SESSION", map.get("session"));
				map.put("UUID", map.get("uuid"));
				map.remove("session");
				map.remove("uuid");
				resultMap.put(ResultMap.RET_DATA, map);
    			resultMap.putResult(EnError.ERROR_SUCCESS);
    		}
			else
				resultMap.putResult(EnError.ERROR_DB);
		}
			
		
		log.debug("session : " + request.getSession().getId());
    	
    	
    	return resultMap;
	}
    
}
