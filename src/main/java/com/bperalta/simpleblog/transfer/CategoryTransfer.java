package com.bperalta.simpleblog.transfer;

public class CategoryTransfer {
	private String category;
	private String count;
	public CategoryTransfer(){}
	public CategoryTransfer(String category, String count){
		this.category=category;
		this.count=count;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
}
