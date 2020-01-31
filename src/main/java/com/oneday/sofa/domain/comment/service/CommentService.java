package com.oneday.sofa.domain.comment.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oneday.sofa.domain.article.domain.Article;
import com.oneday.sofa.domain.article.service.ArticleService;
import com.oneday.sofa.domain.comment.dao.CommentRepository;
import com.oneday.sofa.domain.comment.domain.Comment;
import com.oneday.sofa.domain.comment.dto.CommentRequest;
import com.oneday.sofa.domain.comment.dto.CommentResponse;
import com.oneday.sofa.domain.comment.exception.NestedCommentException;
import com.oneday.sofa.domain.member.domain.Member;
import com.oneday.sofa.domain.member.dto.JWTMember;
import com.oneday.sofa.domain.member.service.MemberService;
import com.oneday.sofa.global.error.exception.EntityNotFoundException;
import com.oneday.sofa.global.error.exception.UnAuthorizationException;

@Service
public class CommentService {

	@Autowired
	MemberService memberService;
	
	@Autowired
	ArticleService articleService;
	
	@Autowired
	CommentRepository commentRepository;
	
	public Comment findComment(long commentId) {
		return commentRepository.findById(commentId).orElseThrow(() -> new EntityNotFoundException("Comment"));
	}
	
	public List<CommentResponse> getArticleComments(long articleId) {
		List<Comment> comments = commentRepository.findByArticleId(articleId);
		return CommentResponse.toList(comments);
	}
	
	@Transactional
	public void saveComment(JWTMember jwtMember, CommentRequest commentRequest) {
		long memberId = jwtMember.getId();
		long articleId = commentRequest.getArticleId();
		long parentId = commentRequest.getParentId();
		
		Member member = memberService.findMemberById(memberId);
		String content = commentRequest.getContent();
		Article article = articleService.findNotRemovedArticle(articleId);
		
		if(isNestedComment(parentId)) {
			throw new NestedCommentException();
		}
		
		Comment newComment = new Comment(article.getId(), content, member, parentId);
		commentRepository.save(newComment);
		
	}
	
	@Transactional
	public void removeComment(JWTMember jwtMember, long commentId) {
		Comment comment = this.findByCommentId(commentId);
		
		//TODO:: 본인의 것인지 확인하는 절차 중복되는 코드.. 중복제거 필요
		Member owner = comment.getMember();
		if(owner.getId() != jwtMember.getId()) {
			throw new UnAuthorizationException();
		}
		
		comment.setRemoved(true);
	}
	
	@Transactional
	public void modifyComment(JWTMember jwtMember, long commentId, CommentRequest commentRequest) {
		Comment comment = this.findByCommentId(commentId);
		
		Member owner = comment.getMember();
		if(owner.getId() != jwtMember.getId()) {
			throw new UnAuthorizationException();
		}
		
		comment.updateBy(commentRequest);
	}

	private boolean isNestedComment(long parentId) {
		if(parentId != Comment.PARENT_COMMENT) {
			Comment parentComment = this.findByCommentId(parentId);
			return parentComment.isNested();
		}
		return false;
	}
	
	
	private Comment findByCommentId(long commentId) {
		return commentRepository.findById(commentId).orElseThrow(() -> new EntityNotFoundException("Comment"));
	}
}
