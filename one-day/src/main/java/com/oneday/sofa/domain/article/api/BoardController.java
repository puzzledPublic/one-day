package com.oneday.sofa.domain.article.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oneday.sofa.domain.article.dto.BoardResponse;
import com.oneday.sofa.domain.article.service.BoardService;

@RestController
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	//TODO:: 해당 게시판 글 목록 조회, Pageable 설정 고민
	@GetMapping("/{boardName}")
	public List<BoardResponse> getArticleList(@PathVariable String boardName, @PageableDefault Pageable pageable) {
		return boardService.pagingBoard(boardName, pageable);
	}
}
