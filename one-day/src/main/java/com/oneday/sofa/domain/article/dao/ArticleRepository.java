package com.oneday.sofa.domain.article.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.oneday.sofa.domain.article.domain.Article;
import com.oneday.sofa.domain.article.domain.Board;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long>{
	
	Optional<Article> findByIdAndRemovedFalse(long id);

	@Query("SELECT a, a.comments.size FROM Article a join fetch a.member WHERE a.board = :board AND a.removed = false")
	List<Object[]> findByBoardAndRemovedFalse(@Param("board") Board board, Pageable pageable);
}
