package com.bperalta.simpleblog.transfer;

import java.util.Set;


public class UserTransfer
{

	private final String name;
	private final Long authorId;
	private final Set<String> roles;
    
	
	public UserTransfer(String userName, Long authorId, Set<String> roles)
	{
		this.name = userName;
		this.roles = roles;
		this.authorId = authorId;
	}


	public String getName()
	{
		return this.name;
	}


	public Set<String> getRoles()
	{
		return this.roles;
	}


	public Long getAuthorId() {
		return authorId;
	}

}