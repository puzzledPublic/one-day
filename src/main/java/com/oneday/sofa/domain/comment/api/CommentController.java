package com.oneday.sofa.domain.comment.api;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oneday.sofa.domain.comment.dto.CommentRequest;
import com.oneday.sofa.domain.comment.service.CommentService;
import com.oneday.sofa.domain.member.dto.JWTMember;
import com.oneday.sofa.global.config.CheckJWT;

@RestController
@RequestMapping("/comment")
@Validated
public class CommentController {
	
	private static final Logger log = LoggerFactory.getLogger(CommentController.class);

	@Autowired
	CommentService commentService;
	
	//댓글 등록
	@CheckJWT
	@PostMapping
	public void createComment(JWTMember jwtMember, @RequestBody @Valid CommentRequest commentRequest) {
		log.info("regist comment: " + commentRequest.getArticleId() + " " + commentRequest.getParentId() + " " + commentRequest.getContent());
		
		commentService.saveComment(jwtMember, commentRequest);
	}
	
	//댓글 삭제
	@CheckJWT
	@DeleteMapping("/{commentId}")
	public void deleteComment(JWTMember jwtMember, @PathVariable @Min(1) long commentId) {
		log.info("delete comment: comment id -> " + commentId);
		
		commentService.removeComment(jwtMember, commentId);
	}
	
	//댓글 수정
	@CheckJWT
	@PutMapping("/{commentId}")
	public void updateComment(JWTMember jwtMember, @PathVariable @Min(1) long commentId, @RequestBody @Valid CommentRequest content) {
		
		commentService.modifyComment(jwtMember, commentId, content);
	}
	
}
