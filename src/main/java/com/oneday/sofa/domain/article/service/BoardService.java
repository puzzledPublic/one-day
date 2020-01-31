package com.oneday.sofa.domain.article.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.oneday.sofa.domain.article.dao.ArticleRepository;
import com.oneday.sofa.domain.article.dao.BoardRepository;
import com.oneday.sofa.domain.article.domain.Article;
import com.oneday.sofa.domain.article.domain.Board;
import com.oneday.sofa.domain.article.dto.BoardResponse;
import com.oneday.sofa.global.error.exception.EntityNotFoundException;

@Service
public class BoardService {
	
	private static final Logger log = LoggerFactory.getLogger(BoardService.class);

	@Autowired
	BoardRepository boardRepository;
	
	@Autowired
	ArticleRepository articleRepository;
	
	//글 목록 조회
	public List<BoardResponse> pagingBoard(String boardName, Pageable pageable) {
		Board board = boardRepository.findByName(boardName).orElseThrow(EntityNotFoundException::new);
		List<Object[]> list = articleRepository.findByBoardAndRemovedFalse(board, pageable);
		
		log.info("searched article list size at " + boardName + " : " + list.size());
		
		List<BoardResponse> articleList = new ArrayList<>();
		for(Object[] objArr : list) {
			articleList.add(new BoardResponse((Article)objArr[0], (long)objArr[1]));
		}
		
		return articleList;
	}
}
