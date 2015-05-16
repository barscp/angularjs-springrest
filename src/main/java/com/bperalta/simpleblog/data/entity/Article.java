package com.bperalta.simpleblog.data.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Article {

	@Id
	@Column(name="article_id", nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long articleId;
	
	@Column(name="title", nullable=false, length=300)
	private String title;
	
	@Column(name="type", nullable=false, length=50)
	private String type;
	
	@Column(name="category", length=200)
	private String category;
	
	@Column(name="tag", length=200)
	private String tag;
	
	@Column(name="description", length=1000)
	private String description;

	@Column(name="content", length=4000)
	private String content;
	
	@Column(name="is_published", length=1)
	private String isPublished;
	
	@Column(name="image_url",length=200)
	private String imageUrl;
	
	@Column(name="date_created")
	private Date dateCreated;
	
	@Column(name="date_published")
	private Date datePublished;
	
	@Column(name="date_modified")
	private Date dateModified;
	
	
   
    
	@ManyToOne(optional=false, fetch=FetchType.EAGER)// 
	@JoinColumn(name="author_id")
	private Author author;
	
	

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}


	public Date getDateModified() {
		return dateModified;
	}

	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getIsPublished() {
		return isPublished;
	}

	public void setIsPublished(String isPublished) {
		this.isPublished = isPublished;
	}


	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getDatePublished() {
		return datePublished;
	}

	public void setDatePublished(Date datePublished) {
		this.datePublished = datePublished;
	}



	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	
}
