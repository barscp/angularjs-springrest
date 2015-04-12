package com.bperalta.simpleblog.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="login")
public class Login {

	@Id
	@Column(name="login_id", nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long loginId;
	
	@Column(name="username", length=20, nullable=false)
	private String username;
	

    @JsonIgnore
	@Column(name="password", length=20,nullable=false)
	private String password;
	
	@Column(name="is_active", length=1,nullable=false)
	private String isActive;
	
	@Column(name="is_banned", length=1,nullable=false)
	private String isBanned;
	

    @JsonIgnore
	@OneToOne(mappedBy="userLogin", targetEntity=Author.class, fetch=FetchType.LAZY)
	private Author author;
	
	
	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getIsBanned() {
		return isBanned;
	}

	public void setIsBanned(String isBanned) {
		this.isBanned = isBanned;
	}


	public long getLoginId() {
		return loginId;
	}

	public void setLoginId(long loginId) {
		this.loginId = loginId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

}
