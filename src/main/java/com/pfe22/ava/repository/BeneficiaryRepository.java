package com.pfe22.ava.repository;

import com.pfe22.ava.entities.ava.AvaFile.Beneficiary;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeneficiaryRepository extends CrudRepository<Beneficiary , Long> {
    Beneficiary findBeneficiaryByIdBenef(String idBenef);
}
