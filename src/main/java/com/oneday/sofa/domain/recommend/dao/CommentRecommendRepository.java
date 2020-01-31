package com.oneday.sofa.domain.recommend.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oneday.sofa.domain.recommend.domain.CommentRecommend;

@Repository
public interface CommentRecommendRepository extends JpaRepository<CommentRecommend, Long>{
	
	Optional<CommentRecommend> findByCommentIdAndMemberId(Long commentId, Long memberId);
}
