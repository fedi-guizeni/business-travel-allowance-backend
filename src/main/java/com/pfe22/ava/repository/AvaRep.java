package com.pfe22.ava.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository
public class AvaRep {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public  int updateNonrenouvele(String status){
        String sql="UPDATE ava SET statut_dossier=? where fin_validite < current_date ";
        int numberOfRowUpdated=jdbcTemplate.update(sql,status);
        return numberOfRowUpdated;

    }

    @Transactional
    public  int updateAutoRenewal(){
        String sql="UPDATE ava SET fin_validite=(fin_validite + INTERVAL '1 year' ) ," +
                "date_validite=(date_validite + INTERVAL '1 year' )\n" +
                "where fin_validite < current_date and ava_type='exportateur' and statut_dossier='Active'";
        int numberOfRowUpdated=jdbcTemplate.update(sql);
        return numberOfRowUpdated;

    }


}
