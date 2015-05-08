package com.bperalta.simpleblog.data.dao;

import java.util.List;

import com.bperalta.simpleblog.transfer.CategoryTransfer;

public interface UtilDao {

	
	public List<CategoryTransfer> getCategoriesByType(String type);
}
