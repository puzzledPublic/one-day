package com.oneday.sofa.domain.comment.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oneday.sofa.domain.comment.domain.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{
	
	List<Comment> findByArticleId(long articleId);
}
