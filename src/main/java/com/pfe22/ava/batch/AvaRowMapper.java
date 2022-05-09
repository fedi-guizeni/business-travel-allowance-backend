package com.pfe22.ava.batch;

import com.pfe22.ava.entities.ava.AvaFile.Ava;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AvaRowMapper implements RowMapper<Ava> {


    @Override
    public Ava mapRow(ResultSet rs, int rowNum) throws SQLException {
        Ava ava = new Ava();
        ava.setStatutDossier(rs.getString("statut_dossier"));
        ava.setIdClient(rs.getString("id_client"));

        return ava;
    }
}
