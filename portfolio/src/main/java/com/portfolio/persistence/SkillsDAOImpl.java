package com.portfolio.persistence;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.portfolio.domain.Criteria;
import com.portfolio.domain.SkillsVO;


@Repository
public class SkillsDAOImpl implements SkillsDAO{

	@Resource(name = "sqlSession")
	private SqlSession session;
	
	private static String namespace = "com.portfolio.mapper.SkillsMapper";

	@Override
	public List<SkillsVO> SkillsSubCategoryListAll(Criteria cri) throws Exception {
		return session.selectList(namespace + ".SkillsSubCategoryListAll", cri);
	}

	@Override
	public List<SkillsVO> SkillsList(Criteria cri) throws Exception {
		return session.selectList(namespace + ".SkillsList", cri);
	}

	@Override
	public int SkillsListCount(Criteria cri) throws Exception {
		return session.selectOne(namespace + ".SkillsListCount", cri);
	}

	@Override
	public List<SkillsVO> SkillsListGroup(Criteria cri) throws Exception {
		return session.selectList(namespace + ".SkillsListGroup", cri);
	}

	@Override
	public int SkillsListGroupCount(Criteria cri) throws Exception {
		return session.selectOne(namespace + ".SkillsListGroupCount", cri);
	}
	
	@Override
	public List<SkillsVO> SkillsSubCategoryList(Map<String, Object> map) throws Exception {
		return session.selectList(namespace + ".SkillsSubCategoryList", map);
	}

	@Override
	public SkillsVO SkillsView(Integer skNumber) throws Exception {
		return session.selectOne(namespace + ".SkillsView", skNumber);
	}

	@Override
	public int SkillsRegister(SkillsVO vo) throws Exception {
		return session.insert(namespace + ".SkillsRegister", vo);
	}

	@Override
	public int SkillsModify(SkillsVO vo) throws Exception {
		return session.update(namespace + ".SkillsModify", vo);
	}

	@Override
	public int SkillsDelete(Integer skNumber) throws Exception {
		return session.delete(namespace + ".SkillsDelete", skNumber);
	}

	@Override
	public void SkillsViewCountUpdate(Integer skNumber) throws Exception {
		session.update(namespace + ".SkillsViewCountUpdate", skNumber);
	}

	@Override
	public List<SkillsVO> SkillsListGroupNew() throws Exception {
		return session.selectList(namespace + ".SkillsListGroupNew");
	}
	
}
