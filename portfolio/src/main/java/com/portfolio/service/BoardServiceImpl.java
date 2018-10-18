package com.portfolio.service;


import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.portfolio.domain.BoardVO;
import com.portfolio.domain.Criteria;
import com.portfolio.persistence.BoardDAO;


@Service
public class BoardServiceImpl implements BoardService{

	@Inject private BoardDAO dao;

	@Override
	public int BoardRegister(BoardVO vo) throws Exception {
		return dao.BoardRegister(vo);
	}

	@Override
	public List<BoardVO> BoardList(Criteria cri) throws Exception {
		return dao.BoardList(cri);
	}

	@Override
	public int BoardListCount(Criteria cri) throws Exception {
		return dao.BoardListCount(cri);
	}

	@Override
	public BoardVO BoardView(Integer bNumber) throws Exception {
		return dao.BoardView(bNumber);
	}

	@Override
	public int BoardModify(BoardVO vo) throws Exception {
		return dao.BoardModify(vo);
	}
	
}
