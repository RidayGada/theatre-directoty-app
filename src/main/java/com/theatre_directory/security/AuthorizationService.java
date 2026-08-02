package com.theatre_directory.security;

import org.springframework.stereotype.Component;

import com.theatre_directory.enums.Role;
import com.theatre_directory.exception.ForbiddenOperationException;

@Component
public class AuthorizationService {

    private final UserContext userContext;

    public AuthorizationService(UserContext userContext) {
        this.userContext = userContext;
    }

    public void requireManager() {
    	
    	System.out.println("UserContext Role = " + userContext.getCurrentUserRole());

        if (userContext.getCurrentUserRole() != Role.MANAGER) {
            throw new ForbiddenOperationException(
                    "Only managers can perform this operation.");
        }
    }

    public void requireUser() {
        if (userContext.getCurrentUserRole() != Role.USER) {
            throw new ForbiddenOperationException(
                    "Only users can perform this operation.");
        }
    }

}