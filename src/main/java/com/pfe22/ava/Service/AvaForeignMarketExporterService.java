package com.pfe22.ava.Service;

import com.pfe22.ava.entities.AppUsers.User;
import com.pfe22.ava.entities.ava.AvaFile.Ava;
import com.pfe22.ava.entities.ava.AvaFile.AvaForeignMarketExporter;
import com.pfe22.ava.entities.ava.AvaFile.AvaOtherActivities;
import com.pfe22.ava.entities.ava.AvaFile.Beneficiary;
import com.pfe22.ava.exception.AppUsers.ClientIdExistException;
import com.pfe22.ava.exception.AppUsers.StatutDossierException;
import com.pfe22.ava.exception.AppUsers.UsernameExistException;

import javax.mail.MessagingException;
import java.util.Date;
import java.util.List;

public interface AvaForeignMarketExporterService {
    AvaForeignMarketExporter saveAvaForMEx(
                                             String avaType,
                                             String idClient,
                                             String naturePiece,
                                             String numCin,
                                             String codeAgence,
                                             Date dateValidite,
                                             Date finValidite,
                                             Date dateCloture,
                                             String compteDebit,
                                             String activiteBct,
                                             String caht,
                                             Float dat,
                                             String observation,
                                             String statutDossier,
                                             String statutValidationDossier,
                                             Float montantBct,
                                             String titulaireMarche,
                                             String montantContrat,
                                             String devise,
                                             Integer numAutorBct,
                                             Date dataAutoBct,
                                             Date dateContrat ,
                                             User agent,
                                             List<Beneficiary> beneficiaries
                                             ) throws ClientIdExistException;
    Iterable<Ava> getAllAvaForMEx();
    AvaForeignMarketExporter getAvaForMexById(Long id);
    AvaForeignMarketExporter updateAva(String  currentIdClient,
                                 String idClient,
                                 String newCaht ,
                                 Integer newnumAutorBct,
                                 Date NewdataAutoBct ,
                                 Float newMontantBct ,
                                 String newObservation,
                                 String référenceDossierAVA,
                                 String avaType,
                                 String naturePiece,
                                 String numCin,
                                 String codeAgence,
                                 Date dateValidite,
                                 Date finValidite,
                                 Date dateCloture,
                                 String compteDebit,
                                 String activiteBct,
                                 String statutDossier,
                                 String statutValidationDossier,
                                 String titulaireMarche,
                                 String MontantContrat,
                                 String Devise,
                                 Date dateContrat

    ) throws ClientIdExistException, MessagingException;

    AvaForeignMarketExporter renewalAva(String  currentIdClient,
                                        String avaType,
                                  String idClient,
                                  String newCaht ,
                                  Integer newnumAutorBct,
                                  Date NewdataAutoBct ,
                                  Float newMontantBct ,
                                  Date dateValidite,
                                  Date finValidite,
                                        Float dat,
                                  String statutDossier,
                                  String statutValidationDossier
                                  ) throws ClientIdExistException, StatutDossierException;
    AvaForeignMarketExporter addBenef (List<Beneficiary> beneficiaries, String clientid) throws UsernameExistException;
    AvaForeignMarketExporter findbytype ( String ava , String idclient);
}
