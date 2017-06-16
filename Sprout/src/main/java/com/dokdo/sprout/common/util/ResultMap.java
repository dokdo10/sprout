package com.dokdo.sprout.common.util;

import com.dokdo.sprout.common.def.EnError;

public class ResultMap extends DaoMap{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String RET_CODE 	= "result_code";
	public static final String RET_MESSAGE	= "resule_msg";
	public static final String RET_DATA		= "data";
	
	/**
	 * 결과 코드 및 메시지 세팅
	 * @param result
	 */
	public void putResult( EnError result ){
		put( RET_CODE, 		result.getCode() );
		put( RET_MESSAGE,	result.getMsg() );
	}
	
	/**
	 * 결과 코드 및 메시지 세팅, 커스텀 메시지
	 * @param result
	 * @param msg
	 */
	public void putResult( EnError result, String msg ){
		put( RET_CODE, 		result.getCode() );
		put( RET_MESSAGE,	msg );
	}
}
