package com.dokdo.sprout.common.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import com.dokdo.sprout.common.dao.SproutDAO;
import com.dokdo.sprout.common.def.DefConst;
import com.dokdo.sprout.common.def.EnError;
import com.dokdo.sprout.common.exception.SessionException;
import com.dokdo.sprout.common.exception.SqlException;
import com.dokdo.sprout.common.util.CUtil;
import com.dokdo.sprout.common.util.DaoMap;
import com.dokdo.sprout.common.util.ResultMap;

public class SproutServiceImpl implements SproutService{
	protected Logger log = Logger.getLogger(this.getClass());
    protected ResultMap resultMap = new ResultMap();
	
    @Resource(name="sproutDAO")
    private SproutDAO sproutDAO;


	@Override
	public DaoMap selectValidityCheck(DaoMap map) throws SqlException {
		return sproutDAO.selectTest(map);
	}

	@Override
	public DaoMap selectCheckID(DaoMap map) throws SqlException {
		return sproutDAO.selectCheckID(map);
	}
	
	@Override
	public DaoMap selectCheckLicense(DaoMap map) throws SqlException {
		return sproutDAO.selectCheckLicense(map);
	}
	
	@Override
	public DaoMap selectCheckUUID(DaoMap map) throws SqlException {
		return sproutDAO.selectCheckUUID(map);
	}
	
	@Override
	public DaoMap selectLogin(DaoMap map) throws SqlException{
		return sproutDAO.selectLogin(map);
	}
	
	@Override
	public DaoMap selectMemeberCount(DaoMap map) throws SqlException{
		return sproutDAO.selectMemberCount(map);
	}
	
	@Override
	public List<DaoMap> selectMemberList(DaoMap map) throws SqlException{
		return sproutDAO.selectMemberList(map);
	}
	
	@Override
	public List<DaoMap> selectFindUser(DaoMap map) throws SqlException{
		return sproutDAO.selectFindUser(map);
	}
	
	@Override
	public Integer insertUser(DaoMap map) throws SqlException {
		return sproutDAO.insertUser(map);
	}
	
	@Override
	public Integer updateUser(DaoMap map) throws SqlException{
		return sproutDAO.updatetUser(map);
	}
	
	@Override
	public Integer updateActiveUser(DaoMap map) throws SqlException{
		return sproutDAO.updatetActiveUser(map);
	}
	
	@Override
	public Integer updateSessionKey(DaoMap map) throws SqlException{
		return sproutDAO.updateSessionKey(map);
	}
	
	/**
	 * 접속 정보가 유효한 사용자인지 체크한다.
	 * @param session
	 * @param uuid
	 * @return
	 * @throws Exception
	 */
	synchronized public void isValidity( String session, String uuid ) throws SessionException, SqlException{
		if( StringUtils.isEmpty(session) || StringUtils.isEmpty(uuid) ){
			throw new SessionException( EnError.ERROR_USER_NOT_EXISTS );
		}
		
		DaoMap map = new DaoMap();
		map.put("session", session);
		map.put("uuid", uuid);
		
		map = selectValidityCheck(map);
		
		CUtil.printMap(log, map);
		
		if( map == null || map.isEmpty() )
			throw new SessionException( EnError.ERROR_USER_NOT_EXISTS );
		
		else if( map.get("LICENSE_USE").equals(DefConst.USE_NO) )
			throw new SessionException( EnError.ERROR_LICENSE_NO_USE );
		
		else if( map.get("USER_USE").equals(DefConst.USE_NO) )
			throw new SessionException( EnError.ERROR_USER_NO_USE );
		
		else if( map.get("EXPIRE").equals("false") )
			throw new SessionException( EnError.ERROR_LICENSE_EXPIRE );
		
		else if( map.get("SESSION").equals("false") )
			throw new SessionException( EnError.ERROR_SESSION_EXPIRE );
	}
	
	/**
	 * 등록되었고 유효한 UUID 인가?
	 * @param uuid
	 * @param domain
	 * @param service
	 * @return
	 * @throws SqlException
	 */
	synchronized public boolean checkUUID( String uuid, String domain ) throws SqlException{
    	DaoMap map = new DaoMap();
		map.put("uuid", uuid);
		map.put("domain", domain);
		
		map = selectCheckUUID(map);
		
		CUtil.printMap(log, map);
		
		int is_exists = Integer.parseInt( map.getString("IS_EXISTS") );
		
		if( is_exists == 0 )
			return false;
		
		return true;
    }
}
