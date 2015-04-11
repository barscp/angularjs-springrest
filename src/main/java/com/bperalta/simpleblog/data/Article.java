package com.bperalta.simpleblog.data;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="article")
public class Article {

	@Id
	@Column(name="article_id", nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long articleId;
	
	@Column(name="title", nullable=false, length=300)
	private String title;
	
	@Column(name="type", nullable=false, length=50)
	private String type;
	
	@Column(name="tag", length=200)
	private String tag;
	
	@Column(name="description", length=1000)
	private String description;

	@Column(name="content", length=4000)
	private String content;
	
	@Column(name="is_published", length=1)
	private String isPublished;
	
	@Column(name="thumbnail_url",length=200)
	private String thumbnailUrl;
	
	@Column(name="date_created")
	private Date dateCreated;
	
	@Column(name="date_published")
	private Date datePublished;
	
	@Column(name="modified_date")
	private Date modifiedDate;
	
	
	@ManyToOne(optional=false, fetch=FetchType.LAZY)// 
	@JoinColumn(name="author_id")
	private Author author;
	
	
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

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
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

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
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
