package com.web.httpConn.dao.impl;

import java.util.List;
import java.util.Map;

public interface KoreaApiDaoImpl {

	public List<Map<String, Object>> selectKoreaApiList() throws Exception;
	
	public void insertKoreaApiList(Map<String, Object> param) throws Exception;
	
	public int selectKoreaApiIdCnt(Map<String, Object> param) throws Exception;
}
