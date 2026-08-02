package com.theatre_directory.security;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import com.theatre_directory.enums.Role;

//to store the user ids for whole app, because in authfilter, it extracts user id but the variable 
//is killed as soon as the doFilter ends, so user context saves the user's id and acts as a bag.

@Component
@RequestScope // every req gets its own object
public class UserContext {


	 private Long currentUserId;
	 
	 private Role currentUserRole;

	 public Long getCurrentUserId() {
		 return currentUserId;
	 }

	 public void setCurrentUserId(Long currentUserId) {
		 this.currentUserId = currentUserId;
	 }
	 
	 public Role getCurrentUserRole() {
         return currentUserRole;
	 }

	 public void setCurrentUserRole(Role currentUserRole) {
	     this.currentUserRole = currentUserRole;
	 }
	    
}
