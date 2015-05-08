package com.bperalta.simpleblog.data.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Author {
 
	@Id
	@Column(name="author_id", nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long authorId;
	
	@Column(name="first_name", nullable= false, length=100)
	private String firstName;
	
	@Column(name="last_name", length=100)
	private String lastName;
	
	@Column(name="email", length=100)
	private String email;
	
	@Column(name="description", length=1000)
	private String description;
	
	@Column(name="website_url", length=100)
	private String websiteUrl;
	
	public String getWebsiteUrl() {
		return websiteUrl;
	}

	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}

	@Column(name="profile_img_url",length=200)
	private String profileImgUrl;



    @JsonIgnore
	@OneToOne(optional=true,fetch=FetchType.LAZY)
	@JoinColumn(name="login_id")
	private Login userLogin;
	
    @JsonIgnore
	@OneToMany(mappedBy="author", targetEntity=Article.class, fetch=FetchType.LAZY)
	private List<Article> articleList;	
	

	public long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(long authorId) {
		this.authorId = authorId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProfileImgUrl() {
		return profileImgUrl;
	}

	public void setProfileImgUrl(String profileImgUrl) {
		this.profileImgUrl = profileImgUrl;
	}


	public Login getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(Login userLogin) {
		this.userLogin = userLogin;
	}

	public List<Article> getArticleList() {
		return articleList;
	}

	public void setArticleList(List<Article> articleList) {
		this.articleList = articleList;
	}

 
}
