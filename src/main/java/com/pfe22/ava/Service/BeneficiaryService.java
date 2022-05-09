package com.pfe22.ava.Service;

import com.pfe22.ava.entities.ava.AvaFile.Ava;
import com.pfe22.ava.entities.ava.AvaFile.Beneficiary;

import java.util.Date;

public interface BeneficiaryService {
    Beneficiary add(String idBenef,
                    Integer benefCardId,
                    String benefCardType,
                    String nomPrenomBenef,
                    String adressBenef,
                    String qualite,
                    Integer tel,
                    String statutBenef,
                    Date dateCreation
                   );
    void deleteBenef( Long id);
    Beneficiary update ( String currentidBenef,
                        String idBenef,
                        Integer benefCardId,
                        String benefCardType,
                        String nomPrenomBenef,
                        String adressBenef,
                        String qualite,
                        Integer tel,
                        String statutBenef);
}
