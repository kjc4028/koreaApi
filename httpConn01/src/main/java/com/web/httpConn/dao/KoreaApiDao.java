package com.web.httpConn.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.httpConn.dao.impl.KoreaApiDaoImpl;

@Repository
public class KoreaApiDao implements KoreaApiDaoImpl {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public List<Map<String, Object>> selectKoreaApiList() throws Exception {
		return sqlSession.selectList("koreaApi.selectKoreaApiList");
	}

	@Override
	public void insertKoreaApiList(Map<String, Object> param) throws Exception {
		 sqlSession.insert("koreaApi.insertKoreaApi", param);
	}

	@Override
	public int selectKoreaApiIdCnt(Map<String, Object> param) throws Exception {
		return sqlSession.selectOne("koreaApi.selectKoreaApiIdCnt",param);
	}

}
