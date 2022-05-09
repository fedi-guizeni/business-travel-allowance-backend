package com.pfe22.ava.batch;

import com.pfe22.ava.entities.ava.AvaFile.Ava;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class AvaReader extends JdbcCursorItemReader<Ava> implements ItemReader<Ava> {

    public AvaReader(@Autowired DataSource dataSource){
        setDataSource(dataSource);
        setSql("UPDATE ava SET statut_dossier=? WHERE id_client=?");

        setFetchSize(100);
        setRowMapper(new AvaRowMapper());
    }
}
