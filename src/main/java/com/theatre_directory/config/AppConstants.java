package com.theatre_directory.config;

public final class AppConstants {

    private AppConstants() {
        // Prevent instantiation
    }

    public static final String JWT_SECRET =
            "ThisIsMyVerySecretKeyForTheatreDirectoryManagementSystem2026";

    public static final long JWT_EXPIRATION =
            24 * 60 * 60 * 1000; // 24 hours

    public static final String TOKEN_PREFIX = "Bearer ";

    public static final String AUTH_HEADER = "Authorization";
    
    public static final String MANAGER_CODE = "m-a-n-a-g-e-r";
}