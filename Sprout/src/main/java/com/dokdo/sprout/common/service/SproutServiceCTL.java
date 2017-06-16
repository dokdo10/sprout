package com.dokdo.sprout.common.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.dokdo.sprout.common.exception.ParamException;
import com.dokdo.sprout.common.exception.SessionException;
import com.dokdo.sprout.common.exception.SqlException;
import com.dokdo.sprout.common.util.ResultMap;
import com.dokdo.sprout.controller.admin.AdminActiveCTL;
import com.dokdo.sprout.controller.admin.AdminCheckIDCTL;
import com.dokdo.sprout.controller.admin.AdminFindCTL;
import com.dokdo.sprout.controller.admin.AdminLoginCTL;
import com.dokdo.sprout.controller.admin.AdminRegCTL;
import com.dokdo.sprout.controller.admin.AdminUpdateCTL;
import com.dokdo.sprout.controller.member.MemberListCTL;
import com.dokdo.sprout.controller.member.MemberRegCTL;

public abstract class SproutServiceCTL{
	protected ResultMap resultMap = new ResultMap();
	
	abstract public Map<String, Object> process(HttpServletRequest request, SproutServiceImpl service) 
			throws ParamException, SqlException, SessionException;
	
	
	public static SproutServiceCTL GetServiceCTL( String url ){
		SproutServiceCTL ctl = null;
		
		String[] tmp = url.split("/");	
		String path = tmp[ tmp.length-2 ];
		String name = tmp[ tmp.length-1 ];
		
		switch (path) {
		/* 매니저 관련 처리 */
		case "admin":	
			ctl = GetAdminCTL( name );
			break;
		case "member":
			ctl = GetMemberCTL( name );
		default:
			break;
		}
		
		return ctl;
	}
	
	private static SproutServiceCTL GetAdminCTL( String url ){
		SproutServiceCTL ctl = null;
		
		switch (url) {
		case "Find.do":
			ctl = new AdminFindCTL(); break;
		case "Active.do":
			ctl = new AdminActiveCTL(); break;
		case "CheckID.do":
			ctl = new AdminCheckIDCTL(); break;
		case "Reg.do":
			ctl = new AdminRegCTL(); break;
		case "Update.do":
			ctl = new AdminUpdateCTL(); break;
		case "Login.do":
			ctl = new AdminLoginCTL(); break;
		
		default:
			break;
		}
		
		return ctl;
	}
	
	private static SproutServiceCTL GetMemberCTL( String url ){
		SproutServiceCTL ctl = null;
		
		switch (url) {
		case "List.do":
			ctl = new MemberListCTL(); break;
		case "Reg.do":
			ctl = new MemberRegCTL(); break;
		
		default:
			break;
		}
		
		return ctl;
	}
}
