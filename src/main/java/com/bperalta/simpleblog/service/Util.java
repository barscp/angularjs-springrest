package com.bperalta.simpleblog.service;

import com.bperalta.simpleblog.data.Article;
import com.bperalta.simpleblog.form.ArticleForm;

public class Util {
	public static ArticleForm convertToForm(Article dto) {
		ArticleForm form = new ArticleForm();
		form.setArticleId(dto.getArticleId());
		form.setContent(dto.getContent());
		form.setImageUrl(dto.getThumbnailUrl());
		form.setTitle(dto.getTitle());
		form.setType(dto.getType());
		form.setDescription(dto.getDescription());
		form.setIsPublished(dto.getIsPublished());
		form.setDateCreated(dto.getDateCreated());
		form.setDateModified(dto.getModifiedDate());
		form.setDatePublished(dto.getDatePublished());
		return form;
	}
	public static Article covertToDto(ArticleForm form){
		Article dto = new Article();
		dto.setArticleId(form.getArticleId());
		dto.setContent(form.getContent());
		dto.setThumbnailUrl(form.getImageUrl());
		dto.setTitle(form.getTitle());
		dto.setType(form.getType());
		dto.setDescription(form.getDescription());
		dto.setIsPublished(form.getIsPublished());
		dto.setDateCreated(form.getDateCreated());
		dto.setModifiedDate(form.getDateModified());
		dto.setDatePublished(form.getDatePublished());
		return dto;
	}
}
