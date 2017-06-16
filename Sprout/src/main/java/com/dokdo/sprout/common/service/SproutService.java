package com.dokdo.sprout.common.service;

import java.util.List;

import com.dokdo.sprout.common.exception.SqlException;
import com.dokdo.sprout.common.util.DaoMap;

public interface SproutService {
	DaoMap selectValidityCheck(DaoMap map) throws SqlException;
	
	DaoMap selectCheckID(DaoMap map) throws SqlException;
	
	DaoMap selectCheckLicense(DaoMap map) throws SqlException;
	
	DaoMap selectCheckUUID(DaoMap map) throws SqlException;
	
	DaoMap selectLogin(DaoMap map) throws SqlException;
	
	DaoMap selectMemeberCount(DaoMap map) throws SqlException;
	
	List<DaoMap> selectMemberList(DaoMap map) throws SqlException;
	
	List<DaoMap> selectFindUser(DaoMap map) throws SqlException;
	
	Integer insertUser(DaoMap map) throws SqlException;
	
	Integer updateUser(DaoMap map) throws SqlException;
	
	Integer updateActiveUser(DaoMap map) throws SqlException;
	
	Integer updateSessionKey(DaoMap map) throws SqlException;
}
