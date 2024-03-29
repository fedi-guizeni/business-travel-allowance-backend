package com.pfe22.ava.Service.implementation;

import com.pfe22.ava.Service.EmailService;
import com.pfe22.ava.entities.AppUsers.Client;
import com.pfe22.ava.entities.AppUsers.User;
import com.pfe22.ava.entities.ava.AvaFile.Ava;
import com.pfe22.ava.entities.ava.AvaFile.AvaForeignMarketExporter;
import com.pfe22.ava.Service.AvaForeignMarketExporterService;
import com.pfe22.ava.entities.ava.AvaFile.Beneficiary;
import com.pfe22.ava.entities.ava.AvaFile.HistoriqueAva;
import com.pfe22.ava.exception.AppUsers.ClientIdExistException;
import com.pfe22.ava.exception.AppUsers.StatutDossierException;
import com.pfe22.ava.exception.AppUsers.UsernameExistException;
import com.pfe22.ava.repository.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
public class AvaForeignMarketExporterImplementation implements AvaForeignMarketExporterService {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private AvaForeignExporterRepository avaForeignExporterRepository ;
    private ClientRepository clientRepository;
    private UserRepository userRepository;
    private HistoriqueAvaRepository historiqueAvaRepository;
    private BeneficiaryRepository beneficiaryRepository;
    private EmailService emailService;


    @Autowired
    public AvaForeignMarketExporterImplementation(AvaForeignExporterRepository avaForeignExporterRepository,
                                                  ClientRepository clientRepository,
                                                  BeneficiaryRepository beneficiaryRepository,
                                                  UserRepository userRepository,
                                                  HistoriqueAvaRepository historiqueAvaRepository,
                                                  EmailService emailService
                                                  ) {
        this.avaForeignExporterRepository = avaForeignExporterRepository;
        this.clientRepository = clientRepository;
        this.beneficiaryRepository = beneficiaryRepository ;
        this.userRepository =userRepository;
        this.historiqueAvaRepository=historiqueAvaRepository;
        this.emailService = emailService;
    }

    @Override
    public AvaForeignMarketExporter saveAvaForMEx(
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
            Float montantBct,
            String titulaireMarche,
            Float montantContrat,
            String devise,
            Integer numAutorBct,
            Date dataAutoBct,
            Date dateContrat
                                                     , User agent,
            List<Beneficiary> beneficiaries) throws ClientIdExistException {
        validateClientId(StringUtils.EMPTY,idClient ,avaType);
        AvaForeignMarketExporter avaForeignMarketExporter = new AvaForeignMarketExporter();
        avaForeignMarketExporter.setReferenceDossierAVA(generateAvaRef());
        avaForeignMarketExporter.setAvaType(avaType);
        avaForeignMarketExporter.setIdClient(idClient);
        avaForeignMarketExporter.setNaturePiece(naturePiece);
        avaForeignMarketExporter.setNumCin(numCin);
        avaForeignMarketExporter.setCodeAgence(codeAgence);
        avaForeignMarketExporter.setDateCreation(new Date());
        avaForeignMarketExporter.setDateValidite(dateValidite);
        avaForeignMarketExporter.setFinValidite(finValidite);
        avaForeignMarketExporter.setDateCloture(dateCloture);
        avaForeignMarketExporter.setCompteDebit(compteDebit);
        avaForeignMarketExporter.setActiviteBct(activiteBct);
        avaForeignMarketExporter.setCaht(caht);
        avaForeignMarketExporter.setDat(dat);
        avaForeignMarketExporter.setObservation(observation);
        avaForeignMarketExporter.setStatutDossier("null");
        avaForeignMarketExporter.setStatutValidationDossier("null");
        avaForeignMarketExporter.setMontantBct(montantBct);
        avaForeignMarketExporter.setTitulaireMarche(titulaireMarche);
        avaForeignMarketExporter.setMontantContrat(montantContrat);
        avaForeignMarketExporter.setDevise(devise);
        avaForeignMarketExporter.setNumAutorBct(numAutorBct);
        avaForeignMarketExporter.setDataAutoBct(dataAutoBct);
        avaForeignMarketExporter.setDateContrat(dateContrat);
        avaForeignMarketExporter.setAgent(agent);
        avaForeignMarketExporter.setClient(getClient(idClient));
        avaForeignMarketExporter.getBeneficiaries();
        HistoriqueAva historiqueAva = new HistoriqueAva();
        historiqueAva.setDateOperation(new Date());
        historiqueAva.setAgent(agent);
        historiqueAva.setEtatDossier("creation");
        List<HistoriqueAva> newHistorique = new ArrayList<>();
        newHistorique.add(historiqueAva);

        avaForeignMarketExporter.setHistoriqueAvas(newHistorique);
        avaForeignExporterRepository.save(avaForeignMarketExporter);
            return avaForeignMarketExporter;
    }

    @Override
    public Iterable<Ava> getAllAvaForMEx() {
        return avaForeignExporterRepository.findAll();
    }

    @Override
    public AvaForeignMarketExporter getAvaForMexById(Long id) {
        return avaForeignExporterRepository.findAvaById(id);
    }

    @Override
    public AvaForeignMarketExporter updateAva(String currentIdClient,
                                              String idClient,
                                              Float newCaht,
                                              Integer newnumAutorBct,
                                              Date NewdataAutoBct,
                                              Float newMontantBct,
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
                                              Float MontantContrat,
                                              String Devise,
                                              Date dateContrat,
                                              String agenId) throws ClientIdExistException, MessagingException {
        AvaForeignMarketExporter avaForeignMarketExporter=validateClientId(currentIdClient,idClient,avaType  );
        avaForeignMarketExporter.setIdClient(idClient);
        avaForeignMarketExporter.setCaht(newCaht);
        avaForeignMarketExporter.setNumAutorBct(newnumAutorBct);
        avaForeignMarketExporter.setDataAutoBct(NewdataAutoBct);
        avaForeignMarketExporter.setMontantBct(newMontantBct);
        avaForeignMarketExporter.setObservation(newObservation);
        avaForeignMarketExporter.setReferenceDossierAVA(référenceDossierAVA);
        avaForeignMarketExporter.setAvaType(avaType);
        avaForeignMarketExporter.setNaturePiece(naturePiece);
        avaForeignMarketExporter.setNumCin(numCin);
        avaForeignMarketExporter.setCodeAgence(codeAgence);
        avaForeignMarketExporter.setDateValidite(dateValidite);
        avaForeignMarketExporter.setFinValidite(finValidite);
        avaForeignMarketExporter.setDateCloture(dateCloture);
        avaForeignMarketExporter.setCompteDebit(compteDebit);
        avaForeignMarketExporter.setActiviteBct(activiteBct);
        avaForeignMarketExporter.setStatutDossier("null");
        avaForeignMarketExporter.setStatutValidationDossier("null");
        avaForeignMarketExporter.setTitulaireMarche(titulaireMarche);
        avaForeignMarketExporter.setMontantContrat(MontantContrat);
        avaForeignMarketExporter.setDevise(Devise);
        avaForeignMarketExporter.setDateContrat(dateContrat);
        HistoriqueAva historiqueAva = new HistoriqueAva();
        historiqueAva.setDateOperation(new Date());
        historiqueAva.setAgent(findAgentbyId(agenId));
        historiqueAva.setEtatDossier("Modification");
        List<HistoriqueAva> newHistorique = new ArrayList<>();
        newHistorique.add(historiqueAva);
        List<HistoriqueAva> newHistoriqueList = Stream.concat(avaForeignMarketExporter.getHistoriqueAvas().stream(),
                        newHistorique.stream())
                .collect(Collectors.toList());
        avaForeignMarketExporter.setHistoriqueAvas(newHistoriqueList);
        avaForeignExporterRepository.save(avaForeignMarketExporter);
        return avaForeignMarketExporter;
    }

    @Override
    public AvaForeignMarketExporter ValidateAva(String currentIdClient,
                                                String idClient,
                                                Float newCaht,
                                                Integer newnumAutorBct,
                                                Date NewdataAutoBct,
                                                Float newMontantBct,
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
                                                Float MontantContrat,
                                                String Devise,
                                                Date dateContrat,
                                                String agentId) throws ClientIdExistException, MessagingException {
        AvaForeignMarketExporter avaForeignMarketExporter=validateClientId(currentIdClient,idClient,avaType  );
        avaForeignMarketExporter.setIdClient(idClient);
        avaForeignMarketExporter.setCaht(newCaht);
        avaForeignMarketExporter.setNumAutorBct(newnumAutorBct);
        avaForeignMarketExporter.setDataAutoBct(NewdataAutoBct);
        avaForeignMarketExporter.setMontantBct(newMontantBct);
        avaForeignMarketExporter.setObservation(newObservation);
        avaForeignMarketExporter.setReferenceDossierAVA(référenceDossierAVA);
        avaForeignMarketExporter.setAvaType(avaType);
        avaForeignMarketExporter.setNaturePiece(naturePiece);
        avaForeignMarketExporter.setNumCin(numCin);
        avaForeignMarketExporter.setCodeAgence(codeAgence);
        avaForeignMarketExporter.setDateValidite(dateValidite);
        avaForeignMarketExporter.setFinValidite(finValidite);
        avaForeignMarketExporter.setDateCloture(dateCloture);
        avaForeignMarketExporter.setCompteDebit(compteDebit);
        avaForeignMarketExporter.setActiviteBct(activiteBct);
        avaForeignMarketExporter.setStatutDossier(statutDossier);
        avaForeignMarketExporter.setStatutValidationDossier(statutValidationDossier);
        avaForeignMarketExporter.setTitulaireMarche(titulaireMarche);
        avaForeignMarketExporter.setMontantContrat(MontantContrat);
        avaForeignMarketExporter.setDevise(Devise);
        avaForeignMarketExporter.setDateContrat(dateContrat);
        HistoriqueAva historiqueAva = new HistoriqueAva();
        historiqueAva.setDateOperation(new Date());
        historiqueAva.setAgent(findAgentbyId(agentId));
        historiqueAva.setEtatDossier(statutValidationDossier);
        List<HistoriqueAva> newHistorique = new ArrayList<>();
        newHistorique.add(historiqueAva);
        List<HistoriqueAva> newHistoriqueList = Stream.concat(avaForeignMarketExporter.getHistoriqueAvas().stream(),
                        newHistorique.stream())
                .collect(Collectors.toList());
        avaForeignMarketExporter.setHistoriqueAvas(newHistoriqueList);
        avaForeignExporterRepository.save(avaForeignMarketExporter);
        if (statutDossier.equals("Active")){
            emailService.SendActivatedEmail(
                    avaForeignMarketExporter.getClient().getFirstName() ,
                    avaForeignMarketExporter.getClient().getLastName(),
                    avaForeignMarketExporter.getAvaType(),
                    avaForeignMarketExporter.getReferenceDossierAVA(),
                    avaForeignMarketExporter.getClient().getEmail() );

        }
        return avaForeignMarketExporter;



    }

    @Override
    public AvaForeignMarketExporter renewalAva(String currentIdClient,
                                               String avaType,
                                               String idClient,
                                               Float newCaht,
                                               Integer newnumAutorBct,
                                               Date NewdataAutoBct,
                                               Float newMontantBct,

                                               Date dateValidite,
                                               Date finValidite,

                                               Float dat,
                                               String statutDossier,
                                               String statutValidationDossier,
                                               String agentId
                                        ) throws StatutDossierException {
        AvaForeignMarketExporter avaForeignMarketExporter=validateStatutDossier(currentIdClient,idClient,avaType);
        avaForeignMarketExporter.setIdClient(idClient);
        avaForeignMarketExporter.setCaht(newCaht);
        avaForeignMarketExporter.setNumAutorBct(newnumAutorBct);
        avaForeignMarketExporter.setDataAutoBct(NewdataAutoBct);
        avaForeignMarketExporter.setMontantBct(newMontantBct);
        avaForeignMarketExporter.setDateValidite(dateValidite);
        avaForeignMarketExporter.setFinValidite(finValidite);
        avaForeignMarketExporter.setDat(dat);
        avaForeignMarketExporter.setStatutDossier("null");
        avaForeignMarketExporter.setStatutValidationDossier("null");
        HistoriqueAva historiqueAva = new HistoriqueAva();
        historiqueAva.setDateOperation(new Date());
        historiqueAva.setAgent(findAgentbyId(agentId));
        historiqueAva.setEtatDossier("renouvellement");
        List<HistoriqueAva> newHistorique = new ArrayList<>();
        newHistorique.add(historiqueAva);
        List<HistoriqueAva> newHistoriqueList = Stream.concat(avaForeignMarketExporter.getHistoriqueAvas().stream(),
                        newHistorique.stream())
                .collect(Collectors.toList());
        avaForeignMarketExporter.setHistoriqueAvas(newHistoriqueList);
        avaForeignExporterRepository.save(avaForeignMarketExporter);
        return avaForeignMarketExporter;
    }

    @Override
    public AvaForeignMarketExporter addBenef(List<Beneficiary> beneficiaries,
                                             String clientid,
                                             String typeAva
                                             ) throws UsernameExistException {
        AvaForeignMarketExporter avaForeignMarketExporter = (AvaForeignMarketExporter) findAvasByIdClientByIdClient(typeAva,clientid);
        int j = 0 ;
        boolean verify=false ;
        for (Beneficiary b:beneficiaries  ){
            Beneficiary beneficiary =findBeneficiaryByIdBenef(b.getIdBenef());
            if  (beneficiary!=null && b.getIdBenef().equals(beneficiary.getIdBenef())){
                j++;
            }
        }
        if (!avaForeignMarketExporter.getStatutDossier().equals("Active")){
            verify=true;
        }
        { if (j > 0){
            throw new UsernameExistException("bénéficiaire dupliqué");
        }else if (verify) { throw new UsernameExistException("le dossier AVA n’est pas ACTIF");
        }else  {
            List<Beneficiary> newBenefList = Stream.concat(avaForeignMarketExporter.getBeneficiaries().stream(),
                            beneficiaries.stream())
                    .collect(Collectors.toList());
            avaForeignMarketExporter.setBeneficiaries(newBenefList);
            avaForeignExporterRepository.save(avaForeignMarketExporter);
            return avaForeignMarketExporter;
        }
        }
    }

    @Override
    public AvaForeignMarketExporter findbytype(String ava, String idclient) {
        return (AvaForeignMarketExporter) findAvasByIdClientByIdClient(ava , idclient);
    }

    private AvaForeignMarketExporter validateClientId(String currentClientId , String newClientId , String avaType   )
            throws ClientIdExistException {
        AvaForeignMarketExporter ClientById = findbytype( avaType, newClientId  );


        if (StringUtils.isNotBlank(currentClientId)){

            AvaForeignMarketExporter currentAva = findbytype(avaType ,currentClientId  );

            if (ClientById!=null && !currentAva.getIdClient().equals(ClientById.getIdClient()) ){

                throw new ClientIdExistException( "Le client a déjà un compteee");
            }


            return currentAva;

        }else {

                    if ( ClientById != null    ){
                        logger.info("Le client a déjà un compte : "+countAvaByIdClient(newClientId));
                        throw new ClientIdExistException("Le client a déjà un compte : "+countAvaByIdClient(newClientId));
                      }
                    if ( findAvasByIdClientByIdClient("autre-activités",newClientId) != null    ){
                        logger.info("Le client a déjà un compte : "+countAvaByIdClient(newClientId));
                        throw new ClientIdExistException("Le client a déjà un compte : "+countAvaByIdClient(newClientId));
                    }

                  

            return null;
        }

    }
    public AvaForeignMarketExporter  findByClientId(String clientId ) {
        return (AvaForeignMarketExporter) avaForeignExporterRepository.findAvaByIdClient(clientId);
    }
//    public AvaForeignMarketExporter  findByClientIdAndType(String clientId , String avaType ) {
//        return (AvaForeignMarketExporter) avaForeignExporterRepository.findAvaByIdClientAndAvaType(clientId ,avaType);
//    }

    public Ava  findAvasByIdClientByIdClient(String ava , String idclient ) {
        return  avaForeignExporterRepository.findAvaByAvaTypeandclientid(ava , idclient);
    }
    public int countAvaByIdClient(String clientId ) {

        return  avaForeignExporterRepository.countAvaByIdClient(clientId);
    }
    public Client getClient(String clientId){
        return clientRepository.findClientByUserId(clientId);
    }

    private AvaForeignMarketExporter validateStatutDossier(String currentClientId , String newClientId , String avaType ) throws  StatutDossierException {
        AvaForeignMarketExporter ClientByNewId = findbytype( avaType, newClientId);
        if (StringUtils.isNotBlank(currentClientId)){
            AvaForeignMarketExporter currentclient = findbytype(avaType,currentClientId);

            if (   !ClientByNewId.getStatutDossier().equals("Non renouvele")  ){
                throw new StatutDossierException( "Le renouvellement n'est possible que pour les dossiers AVA dont le statut est “non renouvelé”");
            }

            return currentclient;

        }

        return null;
    }
    private User findAgentbyId(String id){
        return userRepository.findUserByUserId(id);
    }

    private String generateAvaRef() {
        return RandomStringUtils.randomNumeric(10);
    }
    public Beneficiary findBeneficiaryByIdBenef(String idBenef) {
        return beneficiaryRepository.findBeneficiaryByIdBenef(idBenef);
    }
}
