package com.pfe22.ava.Service;

import com.pfe22.ava.entities.AppUsers.User;
import com.pfe22.ava.entities.ava.AvaFile.*;
import com.pfe22.ava.exception.AppUsers.*;

import javax.mail.MessagingException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface AvaOtherActService {
    AvaOtherActivities saveAvaOtherAct(
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
            Float caht,
            Float dat,
            String observation,
            String statutDossier,
            String statutValidationDossier,
            Integer numAutorBct,
            Date dataAutoBct,
            Float montantBct
                                       , String nompromoteurProjet,
            Date dateDeclarationFiscale,
            String referenceFiscale ,
            User agent ,
            List<Beneficiary> beneficiaries,
            List<HistoriqueAva> historiqueAvas
                                        ) throws ClientIdExistException;
    Iterable<Ava> getAllAvaOtherAct();
    AvaOtherActivities getAvaOtherActById(Long id);
    AvaOtherActivities updateAva(String  currentIdClient,
                                   String idClient,
                                   Float newCaht ,
                                   Integer newnumAutorBct,
                                   Date NewdataAutoBct ,
                                   Float newMontantBct ,
                                   Date newDateDeclarationFiscale ,
                                   String newReferenceFiscale,
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
                                   String nompromoteurProjet,
                                   String AgentId

    ) throws ClientIdExistException, MessagingException;

    AvaOtherActivities validateAva(String  currentIdClient,
                                   String idClient,
                                   Float newCaht ,
                                   Integer newnumAutorBct,
                                   Date NewdataAutoBct ,
                                   Float newMontantBct ,
                                   Date newDateDeclarationFiscale ,
                                   String newReferenceFiscale,
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
                                   String nompromoteurProjet,
                                   String AgentId

                                ) throws ClientIdExistException, MessagingException;

    AvaOtherActivities renewalAva(String  currentIdClient,
                                  String avaType,
                                  String référenceDossierAVA ,
                                  Float caht,
                                  Date dateValidite,
                                  Date finValidite,
                                  Integer numAutoBct,
                                  Date dateAutorBct,
                                  Float MontantBct,
                                  String idClient,
                                  Date dateDeclarationFiscale,
                                  Float dat,
                                  String observation,
                                  String statutDossier,
                                  String AgentId
                                  ) throws ClientIdExistException, StatutDossierException;

    AvaOtherActivities addBenef (List<Beneficiary> beneficiaries,String clientid ,String typeAva) throws UsernameExistException;
    AvaOtherActivities findbytype (String ava , String idclient);
    List<Ava> avalistRgChecker();
    List<Ava> avalistForRgMaker();
    List<Ava> avalistForBranch();
    List<Ava> avalistForAmandment();
    List<Ava> avalistForRenewel();
    List<Ava> avaMonthlyList() ;
    List<Ava> avaMonthlyCustomList(String date) throws ParseException;


}

