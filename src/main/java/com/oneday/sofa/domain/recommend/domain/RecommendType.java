package com.oneday.sofa.domain.recommend.domain;

//public enum RecommendType {
//	ARTICLE(Values.ARTICLE),
//	COMMENT(Values.COMMENT)
//	;
//	
//	private String typeName;
//	
//	private RecommendType(String typeName) {
//		if(!this.name().equals(typeName.toUpperCase())) {
//			throw new IllegalArgumentException("Incorrect RecommendType");
//		}
//	}
//	
//	public static class Values {
//		public static final String ARTICLE = "ARTICLE";
//		public static final String COMMENT = "COMMENT";
//	}
//	
//	public Recommend getRecommend(String id, long memberId) {
//		if(this == ARTICLE) {
//			return new ArticleRecommend(Long.parseLong(id), memberId);
//		}else {
//			return new CommentRecommend(Long.parseLong(id), memberId);
//		}
//	}
//}
