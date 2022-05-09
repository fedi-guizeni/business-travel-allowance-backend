package com.pfe22.ava.constant;

public class Authority {
    public static final String[] USER_AUTHORITIES = {"READ"};
    public static final String[] AGENT_AUTHORITIES = {"READ","UPDATE","CREATE"};
    public static final String[] ADMIN_AUTHORITIES = {"READ","UPDATE","CREATE","DELETE"};

    public static final String[] CREATEUR_SUCCURSALE_AUTHORITIES = {"READ","UPDATE","CREATE"};
    public static final String[] VÉRIFICATEUR_SUCCURSALE_AUTHORITIES = {"READ","UPDATE","CREATE","VALIDATE"};
    public static final String[] CREATEUR_RÉGLEMENTAIRE_AUTHORITIES = {"READ","UPDATE","CREATE","VALIDATE"};
    public static final String[] VÉRIFICATEUR_RÉGLEMENTAIRE_AUTHORITIES = {"READ","UPDATE","CREATE","DELETE","APPROVE"};


}
