package com.recipe.comment.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
	
		
	public abstract void execute(HttpServletRequest request, HttpServletResponse response) throws Exception;


}
