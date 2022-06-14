package com.pfe22.ava.repository;


import com.pfe22.ava.entities.ava.AvaFile.Ava;
import com.pfe22.ava.entities.ava.AvaFile.AvaForeignMarketExporter;
import com.pfe22.ava.entities.ava.AvaFile.OutputDateStat;
import com.pfe22.ava.entities.ava.AvaFile.OutputStat;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.criteria.Expression;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@NoRepositoryBean
public interface AvaRepository extends CrudRepository<Ava , Long> {


    Ava findAvaByIdClient(String id);
    //Ava findAvaByIdClientAndAvaType(String id,String avaType);
    @Query(" FROM Ava   where AvaType=?1 and idClient=?2")
    Ava findAvaByAvaTypeandclientid(String ava , String idclient);

    List<Ava> findAllByStatutValidationDossier(String status);
    List<Ava> findAllByStatutDossier(String status);
    List<Ava> findAllByStatutDossierOrStatutValidationDossier(String status ,String status2);

    @Query("FROM Ava where dateCreation >=?1 and dateCreation <=?2 order by dateCreation")
    List<Ava>findAvasDuMois(Date d1, Date d2);

    @Query(value = "select new com.pfe22.ava.entities.ava.AvaFile.OutputStat( count(id) , AvaType) from Ava group by AvaType")
    List<OutputStat> findcountgroupbytype();

    @Query(value = "select new com.pfe22.ava.entities.ava.AvaFile.OutputStat(100* count(id)/(select count(id) from Ava)  , AvaType) from Ava group by AvaType")
    List<OutputStat> findcountgroupbytype2();

    @Query(value = "select new com.pfe22.ava.entities.ava.AvaFile.OutputStat( count(id)  , statutValidationDossier) from Ava group by statutValidationDossier")
    List<OutputStat> findcountgroupbytype3();

    @Query(value = "select new com.pfe22.ava.entities.ava.AvaFile.OutputStat( count(id)  , statutDossier) from Ava group by statutDossier")
    List<OutputStat> findcountgroupbytype4();

    @Query(value = "select new com.pfe22.ava.entities.ava.AvaFile.OutputStat( count(id)  , statutValidationDossier) from Ava where AvaType=?1 group by statutValidationDossier")
    List<OutputStat> findcountgroupbytype5(String avaType);

    @Query(value = "select new com.pfe22.ava.entities.ava.AvaFile.OutputStat( count(id)  , statutDossier) from Ava  group by statutDossier")
    List<OutputStat> findcountgroupbytype6();

    @Query(value = "select new com.pfe22.ava.entities.ava.AvaFile.OutputStat(100* count(id)/(select count(id) from Ava)  , statutValidationDossier) from Ava group by statutValidationDossier")
    List<OutputStat> findcountgroupbytype7();

    @Query(value = "select new com.pfe22.ava.entities.ava.AvaFile.OutputDateStat( count(id)  , dateCreation) from Ava  group by dateCreation")
    List<OutputDateStat> findcountgroupbytype8();
}
