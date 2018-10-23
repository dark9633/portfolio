package com.portfolio.service;


import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.portfolio.domain.Criteria;
import com.portfolio.domain.SkillsVO;
import com.portfolio.persistence.SkillsDAO;


@Service
public class SkillsServiceImpl implements SkillsService{

	@Inject private SkillsDAO dao;

	@Override
	public List<SkillsVO> SkillsSubCategoryListAll(Criteria cri) throws Exception {
		return dao.SkillsSubCategoryListAll(cri);
	}

	@Override
	public List<SkillsVO> SkillsList(Criteria cri) throws Exception {
		return dao.SkillsList(cri);
	}

	@Override
	public int SkillsListCount(Criteria cri) throws Exception {
		return dao.SkillsListCount(cri);
	}

	@Override
	public List<SkillsVO> SkillsListGroup(Criteria cri) throws Exception {
		return dao.SkillsListGroup(cri);
	}

	@Override
	public int SkillsListGroupCount(Criteria cri) throws Exception {
		return dao.SkillsListGroupCount(cri);
	}
	
	@Override
	public List<SkillsVO> SkillsSubCategoryList(Map<String, Object> map) throws Exception {
		return dao.SkillsSubCategoryList(map);
	}

	@Override
	public SkillsVO SkillsView(Integer skNumber) throws Exception {
		dao.SkillsViewCountUpdate(skNumber);
		return dao.SkillsView(skNumber);
	}

	@Override
	public int SkillsRegister(SkillsVO vo) throws Exception {
		return dao.SkillsRegister(vo);
	}

	@Override
	public int SkillsModify(SkillsVO vo) throws Exception {
		return dao.SkillsModify(vo);
	}

	@Override
	public int SkillsDelete(Integer skNumber) throws Exception {
		return dao.SkillsDelete(skNumber);
	}

	@Override
	public List<SkillsVO> SkillsListGroupNew() throws Exception {
		return dao.SkillsListGroupNew();
	}

}
