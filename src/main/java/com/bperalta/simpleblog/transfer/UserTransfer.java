package com.bperalta.simpleblog.transfer;

import java.io.Serializable;
import java.util.Set;


/**
 * @author barryperalta
 *
 */
public class UserTransfer implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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