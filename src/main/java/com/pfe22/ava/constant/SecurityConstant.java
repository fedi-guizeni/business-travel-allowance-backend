package com.pfe22.ava.constant;

public class SecurityConstant {
    public static final long EXPIRATION_TIME = 432_000_000; // expiration du token  : 5 jours
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String JWT_TOKEN_HEADER = "Jwt-Token";
    public static final String TOKEN_CANNOT_BE_VERIDIED = "Token cannot be verifies";
    public static final String GET_ATB = "Arab Tunisian Bank";
    public static final String GET_ADMINISTRATION = "AVA app user";
    public static final String FORBIDDEN_MESSAGE = "YOU NEED TO LOG IN TO ACCESS THIS PAGE";
    public static final String ACCESS_DENIED_MESSAGE = "YOU DO NOT HAVE PERMISIION TO ACCESS THIS PAGE";
    public static final String AUTHORITIES = "authorities";
    public static final String OPTIONS_HTTP_METHOD = "OPTIONS";
   public static final String[] PUBLIC_URLS = {"/user/login", "/user/register", "/user/resetpassword/**"};
  // public static final String[] PUBLIC_URLS = {"**"};

}
