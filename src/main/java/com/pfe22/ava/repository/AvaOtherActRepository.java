package com.pfe22.ava.repository;

import com.pfe22.ava.entities.ava.AvaFile.Ava;
import com.pfe22.ava.entities.ava.AvaFile.AvaOtherActivities;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvaOtherActRepository extends AvaRepository {
    AvaOtherActivities findAvaById(Long id);
    int countAvaByIdClient(String id);
    //AvaOtherActivities findAvasByIdClient(String id);

}
