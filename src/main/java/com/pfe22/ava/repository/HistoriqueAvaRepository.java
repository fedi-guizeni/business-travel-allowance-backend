package com.pfe22.ava.repository;


import com.pfe22.ava.entities.ava.AvaFile.Ava;
import com.pfe22.ava.entities.ava.AvaFile.HistoriqueAva;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoriqueAvaRepository extends CrudRepository<HistoriqueAva,Long> {

}
