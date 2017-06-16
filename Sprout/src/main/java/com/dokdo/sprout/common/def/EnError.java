package com.dokdo.sprout.common.def;

public enum EnError {
	ERROR_SUCCESS			(0, 	"성공"),
	ERROR_PARAM				(1,		"파라미터 누락"),
	ERROR_DB				(2,		"DB Error"),
	ERROR_SYSTEM			(3, 	""),
	ERROR_PERMISSION		(4, 	"권한 없음"),
	ERROR_LICENSE_EXPIRE	(100,	"라이선스 만료"),
	ERROR_LICENSE_NO_USE	(101,	"라이선스 사용 불가"),
	ERROR_SESSION_EXPIRE	(102,	"세션키 만료"),
	ERROR_USER_NO_USE		(103,	"유저차단됨"),
	ERROR_USER_NOT_EXISTS	(104,	"유저 없음"),
	ERROR_AUTH				(105,	"인증실패"),
	
	ERROR_EXISTS_ID			(201,	"이미 존재하는 아이디"),
	ERROR_DOMAIN_NOT_EXISTS	(202, 	"존재하지 않는 도메인"),
	ERROR_REGEX_ID			(213,	"아이디 영문 대소문자 숫자, 6~12자리"),
	ERROR_REGEX_PASSWORD	(214,	"비밀번호 대소문자 특수문자, 6~14자리"),
	ERROR_REGEX_NAME		(215,	"이름 대소문자 숫자, 2~16자리"),
	ERROR_REGEX_EMAIL		(216, 	"Email 형식오류"),
	ERROR_REGEX_SEX			(217, 	"알 수 없는 성별"),
	ERROR_REGEX_LEVEL		(218, 	"알 수 없는 레벨"),
	ERROR_REGEX_BRITH_DAY	(219, 	"잘못된 생년월일"),
	
	ERROR_LICENSE_LIMIT		(220, 	"라이선스 사용 초과"),
	ERROR_LICENSE_WRONG		(221, 	"잘못된 라이선스"),
	ERROR_PARENT_UUID		(222, 	"잘못된 부모");
	
	static public String GetMessage( int code ){
		EnError[] list = EnError.values();
		for( EnError e : list ){
			if( e.getCode() == code )
				return e.getMsg();
		}
		
		return "not found msg";
	}
	
	public int getCode(){
		return code;
	}
	
	public String getMsg(){
		return msg;
	}
	
	int code;
	String msg;
	EnError( int code, String msg ){
		this.code = code;
		this.msg = msg;
	}
}
