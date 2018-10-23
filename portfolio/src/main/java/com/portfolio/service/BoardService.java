package com.portfolio.service;

import java.util.List;

import com.portfolio.domain.BoardVO;
import com.portfolio.domain.Criteria;


public interface BoardService {

	/* 게시글 등록 */
	public int BoardRegister(BoardVO vo) throws Exception;

	/* 게시글 리스트 */
	public List<BoardVO> BoardList(Criteria cri) throws Exception;
	
	/* 게시글 총 수 */
	public int BoardListCount(Criteria cri) throws Exception;
	
	/* 게시글 상세 */
	public BoardVO BoardView(Integer bNumber) throws Exception;
	
	/* 게시글 수정 */
	public int BoardModify(BoardVO vo) throws Exception;
	
	/* 게시글 삭제 */
	public int BoardDelete(Integer bNumber) throws Exception;
	
	/* 게시글 리스트 메인출력용*/
	public List<BoardVO> BoardListNew() throws Exception;
	
}
