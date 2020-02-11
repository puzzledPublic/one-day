package com.oneday.sofa.domain.member.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.oneday.sofa.domain.article.domain.Article;
import com.oneday.sofa.domain.common.EntityDate;
import com.oneday.sofa.domain.common.Role;

@Entity
public class Member {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable=false, unique=true)
	private String userName;
	
	private String nickName;
	
	@Column(nullable=false)
	private String password;
	
	@Column(nullable=true, unique=true)
	private String email;
	
	@OneToMany(mappedBy="member")
	@JsonManagedReference
	private List<Article> articles = new ArrayList<>();
	
	@Enumerated(EnumType.STRING)
	Role role;
	
	@Embedded
	private EntityDate dateInfo;
	
	boolean removed;
	
	protected Member() { }

	public Member(String userName, String nickName, String password, String email, Role role, EntityDate dateInfo) {
		this.userName = userName;
		this.nickName = nickName;
		this.password = password;
		this.email = email;
		this.role = role;
		this.dateInfo = dateInfo;
		this.removed = false;
	}
	
	public Member(String userName, String nickName, String password, String email, EntityDate dateInfo) {
		this(userName, nickName, password, email, Role.USER, dateInfo);
	}

	public Long getId() {
		return id;
	}

	public String getUserName() {
		return userName;
	}

	public String getNickName() {
		return nickName;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public List<Article> getArticles() {
		return articles;
	}
	
	public Role getRole() {
		return role;
	}

	public EntityDate getDateInfo() {
		return dateInfo;
	}
	
	public boolean isRemoved() {
		return removed;
	}
	
	public void remove() {
		removed = true;
	}
	
	@JsonIgnore
	public Map<String, Object> getJWTClaims() {
		Map<String, Object> claims = new HashMap<>();
		claims.put("id", this.getId());
		claims.put("username", this.getUserName());
		claims.put("nickname", this.getNickName());
		claims.put("role", this.getRole());
		return claims;
	}
	
	public boolean matchPassword(PasswordEncoder passwordEncoder, String rawPassword) {
		return passwordEncoder.matches(rawPassword, this.getPassword());
	}

}
