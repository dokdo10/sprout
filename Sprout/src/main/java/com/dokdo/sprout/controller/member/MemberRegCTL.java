package com.dokdo.sprout.controller.member;

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
import com.dokdo.sprout.common.util.Regex;

public class MemberRegCTL extends SproutServiceCTL{
	Logger log = Logger.getLogger(this.getClass());
    
	private static String[] PARAM = 
		{ "id", "pw", "name", "phone", "email", "brith_date", "sex", "license", "level", "domain", "parent_uuid" };
	

	@Override
	public Map<String, Object> process(HttpServletRequest request, SproutServiceImpl service)
			throws ParamException, SqlException, SessionException {
    	DaoMap map;
    	
    	/* 세션 체크 */
    	service.isValidity(request.getParameter("session"), request.getParameter("uuid"));
    	
		/* Param check */
		map = CUtil.ParamToMap(PARAM, request);
		
		String level = map.getString("level");
		
		/* 아이디 정규식 체크 */
		if( !Regex.checkUserID(map.getString("id")) ){
			resultMap.putResult(EnError.ERROR_REGEX_ID);
			return resultMap;
		}
		
		/* 패스워드 정규식 체크 */
		if( !Regex.checkPassword(map.getString("pw")) ){
			resultMap.putResult(EnError.ERROR_REGEX_PASSWORD);
			return resultMap;
		}
		
		/* 성별 정규식 체크 */
		if( !Regex.checkSex(map.getString("sex")) ){
			resultMap.putResult(EnError.ERROR_REGEX_SEX);
			return resultMap;
		}
		
		/* 생년월일 유효성 체크 */
		if( !Regex.checkBrithDay(map.getString("brith_date")) ){
			resultMap.putResult(EnError.ERROR_REGEX_BRITH_DAY);
			return resultMap;
		}
		
		/* Email 정규식 체크 */
		if( !Regex.checkEmail(map.getString("email")) ){
			resultMap.putResult(EnError.ERROR_REGEX_EMAIL);
			return resultMap;
		}
		
		/* 회원 일때 */
		if( level.equals("100") ){
			/* 이름 정규식 체크 */
			if( !Regex.checkName(map.getString("name")) ){
				resultMap.putResult(EnError.ERROR_REGEX_NAME);
				return resultMap;
			}
		}
		/* 비회원 일때 */
		else if( level.equals("200") ){
			
		}
		else{
			resultMap.putResult(EnError.ERROR_REGEX_LEVEL);
			return resultMap;
		}
		
		
		/* 부모 uuid 체크 */
		if( !service.checkUUID(
				map.getString("parent_uuid"), 
				map.getString("domain"))){
			resultMap.putResult(EnError.ERROR_PARENT_UUID);
			return resultMap;
		}
		
		/* 회원 등록! */
		map.put("uuid", CUtil.GetUUID());
		int iRet = service.insertUser(map);
		
		if( iRet == 1 )
			resultMap.putResult(EnError.ERROR_SUCCESS);
		else
			resultMap.putResult(EnError.ERROR_DB);
    	
    	
    	return resultMap;
	}
   
}
