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
import com.dokdo.sprout.common.util.Regex;

public class AdminUpdateCTL extends SproutServiceCTL{
	Logger log = Logger.getLogger(this.getClass());
    
	private static String[] PARAM = 
		{ "uuid", "pw", "name", "phone", "email", "brith_date", "sex" };
	
    

	@Override
	public Map<String, Object> process(HttpServletRequest request, SproutServiceImpl service)
			throws ParamException, SqlException, SessionException {
    	DaoMap map;
    	
    	/* 세션 체크 */
    	service.isValidity(request.getParameter("session"), request.getParameter("uuid"));
		
		/* Param check */
		map = CUtil.ParamToMap(PARAM, request);
	
		/* 패스워드 정규식 체크 */
		if( !Regex.checkPassword(map.getString("pw")) ){
			resultMap.putResult(EnError.ERROR_REGEX_PASSWORD);
			return resultMap;
		}
		
		/* 이름 정규식 체크 */
		if( !Regex.checkName(map.getString("name")) ){
			resultMap.putResult(EnError.ERROR_REGEX_NAME);
			return resultMap;
		}
		
		/* Email 정규식 체크 */
		if( !Regex.checkEmail(map.getString("email")) ){
			resultMap.putResult(EnError.ERROR_REGEX_EMAIL);
			return resultMap;
		}
		
		/* 핸드폰 번호 정규식 체크는 하지말자 */
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
		
		int iRet = service.updateUser(map);
		
		if( iRet == 1 )
			resultMap.putResult(EnError.ERROR_SUCCESS);
		else 
			resultMap.putResult(EnError.ERROR_DB);
    	
    	return resultMap;
	}
   
}
