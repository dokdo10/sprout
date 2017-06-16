package com.dokdo.sprout.controller.member;

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

public class MemberListCTL extends SproutServiceCTL{
	Logger log = Logger.getLogger(this.getClass());
    
	private static int LIMIT = 50;
	private static String[] PARAM = 
		{"uuid", "page", "domain"};
	

	@Override
	public Map<String, Object> process(HttpServletRequest request, SproutServiceImpl service ) 
			throws ParamException, SqlException, SessionException {
    	DaoMap map;
    	
    	/* 세션 체크 */
    	service.isValidity(request.getParameter("session"), request.getParameter("uuid"));
		
    	/* Param check */
		map = CUtil.ParamToMap(PARAM, request);
		
		/* 유저 레벨 확인 */
		String level = getUserLevel(
					map.getString("uuid"),
					map.getString("domain"),
					service
				);
		
		if( level == null ){
			resultMap.putResult(EnError.ERROR_USER_NOT_EXISTS);
			return resultMap;
		}
		else if( Integer.parseInt(level) >= 100){
			resultMap.putResult(EnError.ERROR_PERMISSION);
			return resultMap;
		}
		
		int page = Integer.parseInt( map.getString("page") ) * LIMIT;
		int limit = LIMIT;
		
		map.put("page", page);
		map.put("limit", limit);
		map.put("root", Integer.parseInt(level) <= 1 ? "ROOT" : map.getString("uuid"));
		
		DaoMap countMap = service.selectMemeberCount(map);
		List<DaoMap> listMap = service.selectMemberList(map);
    	
		resultMap.putResult(EnError.ERROR_SUCCESS);
		resultMap.putAll(countMap);
		resultMap.put(ResultMap.RET_DATA, listMap);
		
    	return resultMap;
	} 
	
	public String getUserLevel( String uuid, String domain, SproutServiceImpl service ) throws SqlException{
    	DaoMap map = new DaoMap();
		map.put("uuid", uuid);
		map.put("domain", domain);
		
		map = service.selectCheckUUID(map);
		
		return map.getString("LEVEL");
    }
}
