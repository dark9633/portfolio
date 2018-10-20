package com.portfolio.service;


import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.portfolio.domain.BoardVO;
import com.portfolio.domain.ReplyVO;
import com.portfolio.persistence.BoardDAO;
import com.portfolio.persistence.ReplyDAO;


@Service
public class ReplyServiceImpl implements ReplyService{

	@Inject private ReplyDAO dao;
	@Inject private BoardDAO Bdao;
	
	@Override
	public List<ReplyVO> ReplyList(Integer bNumber) throws Exception {
		return dao.ReplyList(bNumber);
	}

	@Override
	public int ReplyRegister(ReplyVO vo) throws Exception {
		BoardVO board = new BoardVO();
		board.setbNumber(vo.getbNumber());
		board.setReCount(1);
		/* 댓글 카운트 업데이트, 중요도가 낮아서 트랜잭션 처리는 하지 않는다. */
		Bdao.BoardReCountUpdate(board);
		return dao.ReplyRegister(vo);
	}

	@Override
	public int ReplyDelete(Integer reNumber) throws Exception {
		BoardVO board = new BoardVO();
		board.setbNumber(dao.ReplyView(reNumber).getbNumber());
		board.setReCount(-1);
		/* 댓글 카운트 업데이트, 중요도가 낮아서 트랜잭션 처리는 하지 않는다. */
		Bdao.BoardReCountUpdate(board);
		return dao.ReplyDelete(reNumber);
	}

	@Override
	public int ReplyUpdate(ReplyVO vo) throws Exception {
		return dao.ReplyUpdate(vo);
	}

	@Override
	public ReplyVO ReplyView(Integer reNumber) throws Exception {
		return dao.ReplyView(reNumber);
	}
	
}
