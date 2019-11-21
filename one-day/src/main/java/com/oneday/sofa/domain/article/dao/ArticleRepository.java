package com.oneday.sofa.domain.article.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oneday.sofa.domain.article.domain.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long>{

}
