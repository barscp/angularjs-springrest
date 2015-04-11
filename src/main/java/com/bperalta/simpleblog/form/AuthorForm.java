package com.bperalta.simpleblog.form;

import java.util.List;

public class AuthorForm {
  private String authorId;
  private String username;
  private String email;
  private String firstName;
  private String lastName;
  private String profileUrl;
  private String signature;
  public String getAuthorId() {
	return authorId;
}
public void setAuthorId(String authorId) {
	this.authorId = authorId;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
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
public String getProfileUrl() {
	return profileUrl;
}
public void setProfileUrl(String profileUrl) {
	this.profileUrl = profileUrl;
}
public String getSignature() {
	return signature;
}
public void setSignature(String signature) {
	this.signature = signature;
}
public List<ArticleForm> getArticleList() {
	return articleList;
}
public void setArticleList(List<ArticleForm> articleList) {
	this.articleList = articleList;
}
List<ArticleForm> articleList;
}
