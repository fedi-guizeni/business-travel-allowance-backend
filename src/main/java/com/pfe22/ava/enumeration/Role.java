package com.pfe22.ava.enumeration;


import static com.pfe22.ava.constant.Authority.*;

public enum Role {
    ROLE_USER(USER_AUTHORITIES),
    AGENT_USER(AGENT_AUTHORITIES),
    ADMIN_USER(ADMIN_AUTHORITIES),

    CREATEUR_SUCCURSALE(CREATEUR_SUCCURSALE_AUTHORITIES),
    VÉRIFICATEUR_SUCCURSALE(VÉRIFICATEUR_SUCCURSALE_AUTHORITIES),
    CREATEUR_RÉGLEMENTAIRE(CREATEUR_RÉGLEMENTAIRE_AUTHORITIES),
    VÉRIFICATEUR_RÉGLEMENTAIRE(VÉRIFICATEUR_RÉGLEMENTAIRE_AUTHORITIES);

    private String[] authorities ;

    Role(String... authorities) {
        this.authorities=authorities;
    }
    public  String[] getAuthorities (){
        return authorities;
    }

}
