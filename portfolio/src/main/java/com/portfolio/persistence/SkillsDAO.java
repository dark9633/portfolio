package com.portfolio.persistence;

import java.util.List;
import java.util.Map;

import com.portfolio.domain.Criteria;
import com.portfolio.domain.SkillsVO;


public interface SkillsDAO {

	/* 서브카테고리 네비게이션 */
	public List<SkillsVO> SkillsSubCategoryListAll(Criteria cri) throws Exception;
	
	/* 스킬 리스트  */
	public List<SkillsVO> SkillsList(Criteria cri) throws Exception;
	
	/* 스킬 리스트 카운트 */
	public int SkillsListCount(Criteria cri) throws Exception;
	
	/* 스킬 리스트 그룹 */
	public List<SkillsVO> SkillsListGroup(Criteria cri) throws Exception;

	/* 스킬 리스트 그룹 카운트 */
	public int SkillsListGroupCount(Criteria cri) throws Exception;

	/* 서브카테고리 리스트 */
	public List<SkillsVO> SkillsSubCategoryList(Map<String, Object> map) throws Exception;
	
	/* 스킬 상세 페이지 */
	public SkillsVO SkillsView(Integer skNumber) throws Exception;
	
	/* 스킬 등록 */
	public int SkillsRegister(SkillsVO vo) throws Exception;
	
	/* 스킬 수정 */
	public int SkillsModify(SkillsVO vo) throws Exception;
	
	/* 스킬 삭제 */
	public int SkillsDelete(Integer skNumber) throws Exception;
	
	/* 스킬 뷰 업데이트 */
	public void SkillsViewCountUpdate(Integer skNumber) throws Exception;
	
	/* 스킬 리스트 그룹 메인출력용 */
	public List<SkillsVO> SkillsListGroupNew() throws Exception;
	
}
