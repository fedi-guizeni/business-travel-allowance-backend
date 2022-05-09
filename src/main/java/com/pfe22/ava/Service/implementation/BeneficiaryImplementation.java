package com.pfe22.ava.Service.implementation;

import com.pfe22.ava.Service.BeneficiaryService;
import com.pfe22.ava.entities.ava.AvaFile.Ava;
import com.pfe22.ava.entities.ava.AvaFile.Beneficiary;
import com.pfe22.ava.repository.BeneficiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class BeneficiaryImplementation implements BeneficiaryService {

    private BeneficiaryRepository beneficiaryRepository ;

    @Autowired
    public BeneficiaryImplementation(BeneficiaryRepository beneficiaryRepository) {
        this.beneficiaryRepository = beneficiaryRepository;
    }

    @Override
    public Beneficiary add(String idBenef,
                           Integer benefCardId,
                           String benefCardType,
                           String nomPrenomBenef,
                           String adressBenef,
                           String qualite,
                           Integer tel,
                           String statutBenef,
                           Date dateCreation
                          ) {
        Beneficiary beneficiary = new Beneficiary() ;
        beneficiary.setIdBenef(idBenef);
        beneficiary.setBenefCardId(benefCardId);
        beneficiary.setBenefCardType(benefCardType);
        beneficiary.setNomPrenomBenef(nomPrenomBenef);
        beneficiary.setAdressBenef(adressBenef);
        beneficiary.setQualite(qualite);
        beneficiary.setTel(tel);
        beneficiary.setStatutBenef(statutBenef);
        beneficiary.setDateCreation(new Date());

        beneficiaryRepository.save(beneficiary);

        return beneficiary;
    }

    @Override
    public void deleteBenef(Long id) {
        beneficiaryRepository.deleteById(id);
    }

    @Override
    public Beneficiary update(String currentidBenef,
                              String idBenef,
                              Integer benefCardId,
                              String benefCardType,
                              String nomPrenomBenef,
                              String adressBenef,
                              String qualite,
                              Integer tel,
                              String statutBenef) {
        Beneficiary beneficiary = findBeneficiaryByIdBenef(currentidBenef);
        beneficiary.setIdBenef(idBenef);
        beneficiary.setBenefCardId(benefCardId);
        beneficiary.setBenefCardType(benefCardType);
        beneficiary.setNomPrenomBenef(nomPrenomBenef);
        beneficiary.setAdressBenef(adressBenef);
        beneficiary.setQualite(qualite);
        beneficiary.setTel(tel);
        beneficiary.setStatutBenef(statutBenef);
        beneficiaryRepository.save(beneficiary);
        return beneficiary;
    }
    private Beneficiary findBeneficiaryByIdBenef ( String idBenef){
        return beneficiaryRepository.findBeneficiaryByIdBenef(idBenef);
    }
}
