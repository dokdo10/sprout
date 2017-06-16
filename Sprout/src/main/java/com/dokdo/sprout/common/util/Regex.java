package com.dokdo.sprout.common.util;

import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

import com.dokdo.sprout.common.def.DefConst;

public class Regex {
	/* 아이디 영어 대소문자, 4~12자 */
	private static final String REGEX_ID = "^[A-Za-z0-9]{4,12}";
	
	/* 패스워드 영어 대소문자, 6~16자, 특수문자( 자릿수는 수동 체크하자 ) */
	private static final String REGEX_PW = "^[a-zA-Z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"<>\\\\|,.\\/?~`]+$";
	
	/* 이름 영어, 한글, 2~16자리 */
	private static final String REGEX_NAME = "^[A-Za-z0-9가-힣]{2,16}";
	
	/* Email */
	private static final String REGEX_EMAIL = "(\\w+\\.)*\\w+@(\\w+\\.)+[A-Za-z]+";
	
	/* 생년월일 */
	private static final String REGEX_BRITH_DAY = "[12][0-9]{3}-[01][0-9]-[0-3][0-9]";
	
	
	/**
	 * 아이디 정규식 검사
	 * @param target
	 * @return
	 */
	public static boolean checkUserID( String target ){
		return Pattern.matches(REGEX_ID, target);
	}
	
	/**
	 * 패스워드 정규식 검사
	 * @param target
	 * @return
	 */
	public static boolean checkPassword( String target ){
		return target.matches(REGEX_PW) && target.length() >= 6 && target.length() <= 14;
	}
	
	/**
	 * 이름 정규식 검사
	 * @param target
	 * @return
	 */
	public static boolean checkName( String target ){
		return Pattern.matches(REGEX_NAME, target);
	}
	
	/**
	 * Email 정규식 검사
	 * @param targt
	 * @return
	 */
	public static boolean checkEmail( String target ){
		/* 값이 없을 경우 체크하지 않음 */
		if( StringUtils.isEmpty(target))
			return true;
		
		return Pattern.matches(REGEX_EMAIL, target);
	}
	
	/**
	 *  성별체크 문자 체크
	 * @param args
	 */
	public static boolean checkSex( String target ){
		return target.equals(DefConst.MALE) || target.equals(DefConst.FEMALE);
	}
	
	
	public static boolean checkBrithDay( String target ){
		/* 값이 없을 경우 체크하지 않음 */
		if( StringUtils.isEmpty(target))
			return true;
		
		return Pattern.matches(REGEX_BRITH_DAY, target);
	}
	
	public static void main(String[] args) {
		System.out.println(checkBrithDay("19882111"));
	}
}
