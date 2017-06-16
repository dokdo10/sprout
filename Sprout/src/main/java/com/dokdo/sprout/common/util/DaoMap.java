package com.dokdo.sprout.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DaoMap extends HashMap<String, Object>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	synchronized public static DaoMap convertMap( Map<String, Object> map ){
		DaoMap tmp = new DaoMap();
		if( map != null)
			tmp.putAll( map );
		return tmp;
	}
	
	synchronized public static List<DaoMap> convertMap( List<Map<String, Object>> list ){
		List<DaoMap> tmp = new ArrayList<DaoMap>();
		
		for( Map<String, Object> m : list ){
			DaoMap t = new DaoMap();
			if( m != null){
				t.putAll( m );
				tmp.add(t);
			}
		}
		
		return tmp;
	}
	
	public String getStringUP( String key ){
		return get(key.toUpperCase()) + "";
	}
	
	public String getString( String key ){
		return get(key) + "";
	}
	
	/**
	 * 키를 모두 소문자로 바꿈..
	 */
//	public void toLower(){
//		DaoMap tmp = new DaoMap();
//		for( String key : keySet() ){
//			tmp.put(key.toLowerCase(), get(key) );
//		}
//		
//		clear();
//		putAll(tmp);
//	}
}
