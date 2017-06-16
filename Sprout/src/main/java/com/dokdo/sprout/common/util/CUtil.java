package com.dokdo.sprout.common.util;

import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.dokdo.sprout.common.exception.ParamException;

public class CUtil {
	public static void printMap( Logger log, Map<?, ?> map ){
		if( map == null ){
			log.debug("printMap, map is null");
			return;
		}
		
		log.debug("------------ Print Map ------------");
		for( Object key : map.keySet() )
			log.debug("KEY : " + key + ", VAL : " + map.get(key));
		log.debug("-----------------------------------");
	}
	
	/**
	 * 파라미터를 Map형식으로 바꿔주고 필요 파라메터가 없을 경우 Exception!
	 * @param param
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static DaoMap ParamToMap( String[] param, HttpServletRequest request) throws ParamException{
		DaoMap map = new DaoMap();
		String val;
		for( String key : param ){
			val = request.getParameter(key);
			
			if( val == null )
				throw new ParamException("not found param - " + key);
			
			map.put(key,  val);
		}
		
		return map;
	}
	
	public static String GetUUID(){
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replaceAll("-", "");
	}
	
	public static String GetSessionKey(){
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replaceAll("-", "");
	}
}
