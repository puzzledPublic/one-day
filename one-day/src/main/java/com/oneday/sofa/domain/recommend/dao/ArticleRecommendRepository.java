package com.oneday.sofa.domain.recommend.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oneday.sofa.domain.recommend.domain.ArticleRecommend;

@Repository
public interface ArticleRecommendRepository extends JpaRepository<ArticleRecommend, Long>{
	
	Optional<ArticleRecommend> findByArticleIdAndMemberId(Long articleId, Long memberId);
}
