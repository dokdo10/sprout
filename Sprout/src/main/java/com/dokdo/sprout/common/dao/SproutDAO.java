package com.dokdo.sprout.common.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dokdo.sprout.common.exception.SqlException;
import com.dokdo.sprout.common.util.DaoMap;

@Repository("sproutDAO")
public class SproutDAO extends AbstractDAO{
	@SuppressWarnings("unchecked")
    public DaoMap selectTest(Map<String, Object> map) throws SqlException{
		try{
			return DaoMap.convertMap( (Map<String, Object>)selectOne("common.selectValidityCheck", map) );
		}catch(Exception e){
			throw new SqlException(e.getMessage());
		}
    }
	
	@SuppressWarnings("unchecked")
    public DaoMap selectCheckID(Map<String, Object> map) throws SqlException{
		try{
			return DaoMap.convertMap( (Map<String, Object>)selectOne("admin.selectCheckID", map) );
		}catch(Exception e){
			throw new SqlException(e.getMessage());
		}
    }
	
	
	@SuppressWarnings("unchecked")
	public DaoMap selectCheckLicense(Map<String, Object> map) throws SqlException{
		try{
			return DaoMap.convertMap( (Map<String, Object>)selectOne("common.selectValidityLicense", map) );
		}catch(Exception e){
			throw new SqlException(e.getMessage());
		}
	}
	
	@SuppressWarnings("unchecked")
	public DaoMap selectCheckUUID(Map<String, Object> map) throws SqlException{
		try{
			return DaoMap.convertMap( (Map<String, Object>)selectOne("admin.selectCheckUUID", map) );
		}catch(Exception e){
			throw new SqlException(e.getMessage());
		}
	}
	
	@SuppressWarnings("unchecked")
	public DaoMap selectLogin(Map<String, Object> map) throws SqlException{
		try{
			return DaoMap.convertMap( (Map<String, Object>)selectOne("admin.selectLogin", map) );
		}catch(Exception e){
			throw new SqlException(e.getMessage());
		}
	}
	
	@SuppressWarnings("unchecked")
	public DaoMap selectMemberCount(Map<String, Object> map) throws SqlException{
		try{
			return DaoMap.convertMap( (Map<String, Object>)selectOne("member.selectMemberCount", map) );
		}catch(Exception e){
			throw new SqlException(e.getMessage());
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<DaoMap> selectMemberList(Map<String, Object> map) throws SqlException{
		try{
			return DaoMap.convertMap( (List<Map<String, Object>>)selectList("member.selectMemberList", map) );
		}catch(Exception e){
			throw new SqlException(e.getMessage());
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<DaoMap> selectFindUser(Map<String, Object> map) throws SqlException{
		try{
			return DaoMap.convertMap( (List<Map<String, Object>>)selectList("admin.selectFindUser", map) );
		}catch(Exception e){
			throw new SqlException(e.getMessage());
		}
	}
	
	public Integer insertUser(Map<String, Object> map) throws SqlException{
		try{
			return (Integer)insert("admin.insertUser", map);
		}catch(Exception e){
			throw new SqlException(e.getMessage());
		}
	}
	
	public Integer updatetUser(Map<String, Object> map) throws SqlException{
		try{
			return (Integer)update("admin.updateUser", map);
		}catch(Exception e){
			throw new SqlException(e.getMessage());
		}
	}
	
	public Integer updatetActiveUser(Map<String, Object> map) throws SqlException{
		try{
			return (Integer)update("admin.updateActive", map);
		}catch(Exception e){
			throw new SqlException(e.getMessage());
		}
	}
	
	public Integer updateSessionKey(Map<String, Object> map) throws SqlException{
		try{
			return (Integer)update("admin.updateSession", map);
		}catch(Exception e){
			throw new SqlException(e.getMessage());
		}
	}
}
