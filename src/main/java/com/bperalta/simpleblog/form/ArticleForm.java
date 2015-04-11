package com.bperalta.simpleblog.form;

import java.sql.Date;

public class ArticleForm {
	private long articleId;
	private String title;
	private String imageUrl;
	private String description;
	private String content;//content will be saved in a no sequel
	private String type;
	private String isPublished;
	private Date dateCreated;
	private Date dateModified;
	private Date datePublished;
	private AuthorForm author;
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public Date getDateModified() {
		return dateModified;
	}
	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}
	public Date getDatePublished() {
		return datePublished;
	}
	public void setDatePublished(Date datePublished) {
		this.datePublished = datePublished;
	}


	public String getIsPublished() {
		return isPublished;
	}
	public void setIsPublished(String isPublished) {
		this.isPublished = isPublished;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}



	public AuthorForm getAuthor() {
		return author;
	}
	public void setAuthor(AuthorForm author) {
		this.author = author;
	}
	public long getArticleId() {
		return articleId;
	}
	public void setArticleId(long articleId) {
		this.articleId = articleId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
	
