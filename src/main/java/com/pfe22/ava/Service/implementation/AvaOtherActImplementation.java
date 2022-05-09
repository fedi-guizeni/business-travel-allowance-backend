package com.pfe22.ava.Service.implementation;


import com.pfe22.ava.Service.EmailService;
import com.pfe22.ava.entities.AppUsers.Client;
import com.pfe22.ava.entities.AppUsers.User;
import com.pfe22.ava.entities.ava.AvaFile.Ava;
import com.pfe22.ava.entities.ava.AvaFile.AvaForeignMarketExporter;
import com.pfe22.ava.entities.ava.AvaFile.AvaOtherActivities;
import com.pfe22.ava.Service.AvaOtherActService;
import com.pfe22.ava.entities.ava.AvaFile.Beneficiary;
import com.pfe22.ava.exception.AppUsers.*;
import com.pfe22.ava.repository.AvaOtherActRepository;
import com.pfe22.ava.repository.BeneficiaryRepository;
import com.pfe22.ava.repository.ClientRepository;
import com.pfe22.ava.repository.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
public class AvaOtherActImplementation implements AvaOtherActService {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private AvaOtherActRepository avaOtherActRepository;
    private UserRepository userRepository;
    private ClientRepository clientRepository;
    private BeneficiaryRepository beneficiaryRepository;
    private EmailService emailService;

    @Autowired
    public AvaOtherActImplementation(AvaOtherActRepository avaOtherActRepository ,
                                     UserRepository userRepository ,
                                     ClientRepository clientRepository,
                                     BeneficiaryRepository beneficiaryRepository,
                                     EmailService emailService) {
        this.avaOtherActRepository = avaOtherActRepository;
        this.userRepository= userRepository;
        this.clientRepository=clientRepository;
        this.beneficiaryRepository=beneficiaryRepository;
        this.emailService= emailService;
    }


    @Override
    public AvaOtherActivities saveAvaOtherAct(
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
                                              Integer numAutorBct,
                                              Date dataAutoBct,
                                              Float montantBct,
                                              String nompromoteurProjet,
                                              Date dateDeclarationFiscale,
                                              String referenceFiscale ,
                                              User agent,
                                              List<Beneficiary> beneficiaries

                                             ) throws ClientIdExistException {
        validateClientId(StringUtils.EMPTY,idClient,avaType);
        AvaOtherActivities avaOtherActivities = new AvaOtherActivities();
        avaOtherActivities.setReferenceDossierAVA(generateUserId());
        avaOtherActivities.setAvaType(avaType);
        avaOtherActivities.setIdClient(idClient);
        avaOtherActivities.setNaturePiece(naturePiece);
        avaOtherActivities.setNumCin(numCin);
        avaOtherActivities.setCodeAgence(codeAgence);
        avaOtherActivities.setDateCreation(new Date());
        avaOtherActivities.setDateValidite(dateValidite);
        avaOtherActivities.setFinValidite(finValidite);
        avaOtherActivities.setDateCloture(dateCloture);
        avaOtherActivities.setCompteDebit(compteDebit);
        avaOtherActivities.setActiviteBct(activiteBct);
        avaOtherActivities.setCaht(caht);
        avaOtherActivities.setDat(dat);
        avaOtherActivities.setObservation(observation);
        avaOtherActivities.setStatutDossier(statutDossier);
        avaOtherActivities.setStatutValidationDossier(statutValidationDossier);
        avaOtherActivities.setNumAutorBct(numAutorBct);
        avaOtherActivities.setDataAutoBct(dataAutoBct);
        avaOtherActivities.setMontantBct(montantBct);
        avaOtherActivities.setNompromoteurProjet(nompromoteurProjet);
        avaOtherActivities.setDateDeclarationFiscale(dateDeclarationFiscale);
        avaOtherActivities.setReferenceFiscale(referenceFiscale);
        avaOtherActivities.setAgent(agent);
        avaOtherActivities.setClient(getClient(idClient));
        avaOtherActivities.setBeneficiaries(beneficiaries);
        avaOtherActRepository.save(avaOtherActivities);
        return avaOtherActivities;
    }

    @Override
    public Iterable<Ava> getAllAvaOtherAct() {
        return avaOtherActRepository.findAll();
    }

   public Client getClient(String clientId){
        return clientRepository.findClientByUserId(clientId);
   }

    @Override
    public AvaOtherActivities getAvaOtherActById(Long id) {
        return avaOtherActRepository.findAvaById(id);
    }

    @Override
    public AvaOtherActivities updateAva(String currentIdClient,
                                        String idClient,
                                         String newCaht,
                                         Integer newnumAutorBct,
                                         Date NewdataAutoBct,
                                         Float newMontantBct,
                                        Date newDateDeclarationFiscale,
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
                                        String nompromoteurProjet) throws ClientIdExistException, MessagingException {
        AvaOtherActivities currentAvaOtherActivities = validateClientId(currentIdClient,idClient,avaType);
        currentAvaOtherActivities.setIdClient(idClient);
        currentAvaOtherActivities.setCaht(newCaht);
        currentAvaOtherActivities.setNumAutorBct(newnumAutorBct);
        currentAvaOtherActivities.setDataAutoBct(NewdataAutoBct);
        currentAvaOtherActivities.setMontantBct(newMontantBct);
        currentAvaOtherActivities.setDateDeclarationFiscale(newDateDeclarationFiscale);
        currentAvaOtherActivities.setReferenceFiscale(newReferenceFiscale);
        currentAvaOtherActivities.setObservation(newObservation);
        currentAvaOtherActivities.setReferenceDossierAVA(référenceDossierAVA);
        currentAvaOtherActivities.setAvaType(avaType);
        currentAvaOtherActivities.setNaturePiece(naturePiece);
        currentAvaOtherActivities.setNumCin(numCin);
        currentAvaOtherActivities.setCodeAgence(codeAgence);
        currentAvaOtherActivities.setDateValidite(dateValidite);
        currentAvaOtherActivities.setFinValidite(finValidite);
        currentAvaOtherActivities.setDateCloture(dateCloture);
        currentAvaOtherActivities.setCompteDebit(compteDebit);
        currentAvaOtherActivities.setActiviteBct(activiteBct);
        currentAvaOtherActivities.setStatutDossier(statutDossier);
        currentAvaOtherActivities.setStatutValidationDossier(statutValidationDossier);
        currentAvaOtherActivities.setNompromoteurProjet(nompromoteurProjet);
        //emailService.SendActivatedEmail(currentAvaOtherActivities.getClient().getFirstName() , currentAvaOtherActivities.getReferenceDossierAVA() ,currentAvaOtherActivities.getClient().getEmail() );
        avaOtherActRepository.save(currentAvaOtherActivities);

        return currentAvaOtherActivities;
    }

    @Override
    public AvaOtherActivities renewalAva(String  currentIdClient,
                                         String avaType,
                                         String référenceDossierAVA ,
                                         String caht,
                                         Date dateValidite,
                                         Date finValidite,
                                         Integer numAutoBct,
                                         Date dateAutorBct,
                                         Float MontantBct,
                                         String idClient,
                                         Date dateDeclarationFiscale,
                                         Float dat,
                                         String observation,
                                         String statutDossier
                                         ) throws  StatutDossierException {
        AvaOtherActivities currentAvaOtherActivities = validateStatutDossier(currentIdClient,idClient,avaType );
        currentAvaOtherActivities.setReferenceDossierAVA(référenceDossierAVA);
        currentAvaOtherActivities.setCaht(caht);
        currentAvaOtherActivities.setDateValidite(dateValidite);
        currentAvaOtherActivities.setFinValidite(finValidite);
        currentAvaOtherActivities.setNumAutorBct(numAutoBct);
        currentAvaOtherActivities.setDataAutoBct(dateAutorBct);
        currentAvaOtherActivities.setMontantBct(MontantBct);
        currentAvaOtherActivities.setIdClient(idClient);
        currentAvaOtherActivities.setDateDeclarationFiscale(dateDeclarationFiscale);
        currentAvaOtherActivities.setDat(dat);
        currentAvaOtherActivities.setObservation(observation);
        currentAvaOtherActivities.setStatutDossier(null);
        currentAvaOtherActivities.setStatutValidationDossier(null);
        avaOtherActRepository.save(currentAvaOtherActivities);
        return currentAvaOtherActivities;
    }

    @Override
    public AvaOtherActivities addBenef(List<Beneficiary> beneficiaries,
                                       String clientid) throws UsernameExistException {
        AvaOtherActivities avaOtherActivities =findByClientId(clientid);
        int j = 0 ;
        boolean verify=false ;
        for (Beneficiary b:beneficiaries  ){
                Beneficiary beneficiary =findBeneficiaryByIdBenef(b.getIdBenef());
                if  (beneficiary!=null && b.getIdBenef().equals(beneficiary.getIdBenef())){
                    j++;
                }
        }
        if (!avaOtherActivities.getStatutDossier().equals("Active")){
            verify=true;
        }
              { if (j > 0){
                  throw new UsernameExistException("bénéficiaire dupliqué");
              }else if (verify) { throw new UsernameExistException("le dossier AVA n’est pas ACTIF");
              }else  {
                  List<Beneficiary> newBenefList = Stream.concat(avaOtherActivities.getBeneficiaries().stream(),
                                  beneficiaries.stream())
                          .collect(Collectors.toList());
                  avaOtherActivities.setBeneficiaries(newBenefList);
                  avaOtherActRepository.save(avaOtherActivities);
                  return avaOtherActivities;
              }
        }


        }

    @Override
    public AvaOtherActivities findbytype(String ava, String idclient) {
        return (AvaOtherActivities) findAvasByIdClientByIdClient(ava , idclient);
    }

    @Override
    public List<Ava> avalistRgChecker() {
        return avaOtherActRepository.findAllByStatutValidationDossier("Awaiting Final Regulatory Approval");
    }

    @Override
    public List<Ava> avalistForRgMaker() {
        return avaOtherActRepository.findAllByStatutValidationDossier("Pending for Regulatory Approval");
    }

    @Override
    public List<Ava> avalistForBranch() {
        return avaOtherActRepository.findAllByStatutValidationDossier(null);
    }

    @Override
    public List<Ava> avalistForAmandment() {
        return avaOtherActRepository.findAllByStatutDossierOrStatutValidationDossier("Active" , "Rejected by Regulatory");
    }

    @Override
    public List<Ava> avalistForRenewel() {
        return avaOtherActRepository.findAllByStatutDossier("Non renouvele");
    }

    @Override
    public List<Ava> avaMonthlyList()  {

        Date date1 = getFirstDateOfMonth(new Date());
        Date date2 = getLAstDateOfMonth(new Date());
     return avaOtherActRepository.findAvasDuMois(date1,date2);
    }

    @Override
    public List<Ava> avaMonthlyCustomList(String date) throws ParseException {
        SimpleDateFormat formatter=new SimpleDateFormat( "yyyy-MM-dd");
        Date dd =formatter.parse(date);
        Date date1 = getFirstDateOfMonth(dd);
        Date date2 = getLAstDateOfMonth(dd);
        return avaOtherActRepository.findAvasDuMois(date1,date2);
    }


    private AvaOtherActivities validateStatutDossier(String currentClientId , String newClientId,String avaType ) throws  StatutDossierException {
        AvaOtherActivities ClientByNewId = findbytype( avaType, newClientId);
        if (StringUtils.isNotBlank(currentClientId)){
            AvaOtherActivities currentclient = findbytype( avaType,currentClientId);

            if (   !ClientByNewId.getStatutDossier().equals("Non renouvele")  ){
                throw new StatutDossierException( "Le renouvellement n'est possible que pour les dossiers AVA dont le statut est “non renouvelé”");
            }

            return currentclient;

        }

            return null;
        }


    private AvaOtherActivities validateClientId(String currentClientId , String newClientId ,String avaType) throws ClientIdExistException {
        AvaOtherActivities ClientByNewId = findbytype( avaType ,newClientId);
        if (StringUtils.isNotBlank(currentClientId)){
            AvaOtherActivities currentclient = findbytype(avaType ,currentClientId);

            if (ClientByNewId!=null && !currentclient.getIdClient().equals(ClientByNewId.getIdClient())){
                throw new ClientIdExistException( "Le client a déjà un compte");
            }

            return currentclient;

        }else {

            if (countAvaByIdClient(newClientId) > 0) {
                throw new ClientIdExistException("Le client a déjà un compte  "+ getFirstDateOfMonth(new Date()));

            }

            return null;
        }
    }
    public int countAvaByIdClient(String clientId ) {

        return  avaOtherActRepository.countAvaByIdClient(clientId);
    }
    private String generateUserId() {
        return RandomStringUtils.randomNumeric(10);
    }
    public Ava  findAvasByIdClientByIdClient(String ava , String idclient ) {
        return  avaOtherActRepository.findAvaByAvaTypeandclientid(ava , idclient);
    }

    public AvaOtherActivities findByClientId(String clientId) {
        return (AvaOtherActivities) avaOtherActRepository.findAvaByIdClient(clientId);
    }
    public Beneficiary findBeneficiaryByIdBenef(String idBenef) {
        return beneficiaryRepository.findBeneficiaryByIdBenef(idBenef);
    }

    public static Date getFirstDateOfMonth(Date date){

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    public static Date getLAstDateOfMonth(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

}
