package org.edupoll.repository;

import org.edupoll.model.User;

public interface AuthInterface {
	
	public abstract int join(User user);
	
	public abstract User select(String id);
	
	

}
