package com.pfe22.ava.repository;

import com.pfe22.ava.entities.ava.AvaFile.Ava;
import com.pfe22.ava.entities.ava.AvaFile.AvaForeignMarketExporter;
import org.springframework.stereotype.Repository;

@Repository
public interface AvaForeignExporterRepository extends AvaRepository  {
    AvaForeignMarketExporter findAvaById(Long id);

    int countAvaByIdClient(String id);

}
