package com.pfe22.ava.controller;


import com.pfe22.ava.Service.*;
import com.pfe22.ava.entities.AppUsers.Client;
import com.pfe22.ava.entities.AppUsers.HttpResponse;
import com.pfe22.ava.entities.ava.AvaFile.*;
import com.pfe22.ava.exception.AppUsers.*;
import com.pfe22.ava.exception.ExceptionHandling;
import com.pfe22.ava.repository.AvaForeignExporterRepository;
import com.pfe22.ava.repository.BeneficiaryRepository;
import com.pfe22.ava.repository.HistoriqueAvaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.ByteArrayInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

@RestController
@RequestMapping(path = {"/","/ava"})
@CrossOrigin("http://localhost:4200")
public class AvaRessource extends ExceptionHandling {
    private AvaOtherActService avaOtherActService;
    private AvaForeignMarketExporterService avaForeignMarketExporterService;
    private ClientService clientService;
    private BeneficiaryService beneficiaryService;
    private ExportAvaService exportAvaService;
    private BeneficiaryRepository beneficiaryRepository;
    private  AvaForeignExporterRepository avaForeignExporterRepository;
    private HistoriqueAvaRepository historiqueAvaRepository;
    @Autowired
    public AvaRessource(AvaOtherActService avaOtherActService,
    AvaForeignMarketExporterService avaForeignMarketExporterService,
                        ClientService clientService,
                        BeneficiaryService beneficiaryService,
                        AvaForeignExporterRepository avaForeignExporterRepository,
                        ExportAvaService exportAvaService,
                        BeneficiaryRepository beneficiaryRepository,
                        HistoriqueAvaRepository historiqueAvaRepository) {
        this.avaOtherActService = avaOtherActService;
        this.avaForeignMarketExporterService=avaForeignMarketExporterService;
        this.clientService=clientService;
        this.beneficiaryService=beneficiaryService;
        this.exportAvaService=exportAvaService;
        this.beneficiaryRepository=beneficiaryRepository;
        this.avaForeignExporterRepository=avaForeignExporterRepository;
        this.historiqueAvaRepository=historiqueAvaRepository;
    }

    @PostMapping("/clientCheck")
    public ResponseEntity<Client> getClient ( @RequestBody Client clientcheck) throws ClientIdExistException, ClientNotFountException {
        Client client = clientService.checkFetchClient(clientcheck.getUserId()
        );
        return new ResponseEntity<>(client ,HttpStatus.OK);

    }
    @PostMapping("/addfe")
    public ResponseEntity<AvaForeignMarketExporter> addNewAvaForExp( @RequestBody AvaForeignMarketExporter avaForeignMarketExporter) throws ClientIdExistException{
            AvaForeignMarketExporter newAva = avaForeignMarketExporterService.saveAvaForMEx(

           avaForeignMarketExporter.getAvaType(),
           avaForeignMarketExporter.getIdClient(),
           avaForeignMarketExporter.getNaturePiece(),
           avaForeignMarketExporter.getNumCin(),
           avaForeignMarketExporter.getCodeAgence(),
           avaForeignMarketExporter.getDateValidite(),
           avaForeignMarketExporter.getFinValidite(),
           avaForeignMarketExporter.getDateCloture(),
           avaForeignMarketExporter.getCompteDebit(),
           avaForeignMarketExporter.getActiviteBct(),
           avaForeignMarketExporter.getCaht(),
           avaForeignMarketExporter.getDat(),
           avaForeignMarketExporter.getObservation(),
           avaForeignMarketExporter.getStatutDossier(),
           avaForeignMarketExporter.getStatutValidationDossier(),
           avaForeignMarketExporter.getMontantBct(),
           avaForeignMarketExporter.getTitulaireMarche(),
           avaForeignMarketExporter.getMontantContrat(),
           avaForeignMarketExporter.getDevise(),
           avaForeignMarketExporter.getNumAutorBct(),
           avaForeignMarketExporter.getDataAutoBct(),
           avaForeignMarketExporter.getDateContrat(),
                avaForeignMarketExporter.getAgent(),
                    avaForeignMarketExporter.getBeneficiaries()

        );
        return new ResponseEntity<>(newAva ,HttpStatus.OK);
    }

    @PostMapping("/add")
    //@PreAuthorize("hasAnyAuthority('CREATE')")
    public ResponseEntity<AvaOtherActivities> addNewAvaAutre(@RequestBody AvaOtherActivities avaOtherActivities
                                                             ) throws ClientIdExistException {

        AvaOtherActivities newAva = avaOtherActService.saveAvaOtherAct(

        avaOtherActivities.getAvaType(),
        avaOtherActivities.getIdClient(),
        avaOtherActivities.getNaturePiece(),
        avaOtherActivities.getNumCin(),
        avaOtherActivities.getCodeAgence(),
        avaOtherActivities.getDateValidite(),
        avaOtherActivities.getFinValidite(),
        avaOtherActivities.getDateCloture(),
        avaOtherActivities.getCompteDebit(),
        avaOtherActivities.getActiviteBct(),
        avaOtherActivities.getCaht(),
        avaOtherActivities.getDat(),
        avaOtherActivities.getObservation(),
        avaOtherActivities.getStatutDossier(),
        avaOtherActivities.getStatutValidationDossier(),
        avaOtherActivities.getNumAutorBct(),
        avaOtherActivities.getDataAutoBct(),
        avaOtherActivities.getMontantBct(),
        avaOtherActivities.getNompromoteurProjet(),
        avaOtherActivities.getDateDeclarationFiscale(),
        avaOtherActivities.getReferenceFiscale(),
                avaOtherActivities.getAgent(),
                avaOtherActivities.getBeneficiaries(),
                avaOtherActivities.getHistoriqueAvas()




        );
        return  new ResponseEntity<>(newAva , HttpStatus.OK) ;

    }
    @PostMapping("/add_benef")
    public ResponseEntity<AvaOtherActivities> addBenef( @RequestBody AvaOtherActivities avaOtherActivities

            ) throws UsernameExistException {
        AvaOtherActivities addbenef = avaOtherActService.addBenef(
                avaOtherActivities.getBeneficiaries(),
                avaOtherActivities.getIdClient(),
                avaOtherActivities.getAvaType()


        );
        return new ResponseEntity<>(addbenef ,HttpStatus.OK);
    }
    @PostMapping("/add_benef-exp")
    public ResponseEntity<AvaForeignMarketExporter> addBenef( @RequestBody AvaForeignMarketExporter avaForeignMarketExporter


    ) throws UsernameExistException {
        AvaForeignMarketExporter addbenef = avaForeignMarketExporterService.addBenef(
                avaForeignMarketExporter.getBeneficiaries(),
                avaForeignMarketExporter.getIdClient(),
                avaForeignMarketExporter.getAvaType()



        );
        return new ResponseEntity<>(addbenef ,HttpStatus.OK);
    }
    @DeleteMapping("/delete-benef/{id}")
    // @PreAuthorize("hasAnyAuthority('DELETE')")
    public ResponseEntity<HttpResponse> deleteUser(@PathVariable("id")Long id){
        beneficiaryService.deleteBenef(id);
        return response(HttpStatus.OK , "beneficiary deleted Successfully");

    }

    @PostMapping("/update-benef")
//    @PreAuthorize("hasAnyAuthority('UPDATE')")
    public ResponseEntity<Beneficiary> updateBenef(@RequestParam("currentidBenef") String currentidBenef ,
                                                   @RequestParam("idBenef") String idBenef ,
                                                   @RequestParam("benefCardId") Integer benefCardId ,
                                                   @RequestParam("benefCardType") String benefCardType,
                                                   @RequestParam("nomPrenomBenef") String nomPrenomBenef ,
                                                   @RequestParam("adressBenef") String adressBenef ,
                                                   @RequestParam("qualite") String qualite ,
                                                   @RequestParam("tel") Integer tel ,
                                                   @RequestParam("statutBenef") String statutBenef
                                                      )  {


        Beneficiary updateBenef = beneficiaryService.update(
                currentidBenef,
                idBenef,
                benefCardId,
                benefCardType,
                nomPrenomBenef,
                adressBenef,
                qualite,
                tel,
                statutBenef
        );

        return  new ResponseEntity<>(updateBenef , HttpStatus.OK) ;

    }


    @PostMapping("/updateOther")
//    @PreAuthorize("hasAnyAuthority('UPDATE')")
    public ResponseEntity<AvaOtherActivities> updateAvaOther(@RequestParam("currentIdClient") String currentIdClient ,
                                                             @RequestParam("idClient") String idClient ,
                                                             @RequestParam("caht") Float caht,
                                                             @RequestParam("numAutorBct") Integer numAutorBct ,
                                                             @RequestParam("dataAutoBct") String dataAutoBct ,
                                                             @RequestParam("montantBct") Float montantBct ,
                                                             @RequestParam("dateDeclarationFiscale") String dateDeclarationFiscale ,
                                                             @RequestParam("referenceFiscale") String referenceFiscale ,
                                                             @RequestParam("observation") String observation ,
                                                             @RequestParam("referenceDossierAVA") String referenceDossierAVA,
                                                             @RequestParam("avaType") String avaType ,
                                                             @RequestParam("naturePiece") String naturePiece ,
                                                             @RequestParam("numCin") String numCin ,
                                                             @RequestParam("codeAgence") String codeAgence ,
                                                             @RequestParam("dateValidite") String dateValidite ,
                                                             @RequestParam("finValidite") String finValidite ,
                                                             @RequestParam("dateCloture") String dateCloture ,
                                                             @RequestParam("compteDebit") String compteDebit ,
                                                             @RequestParam("activiteBct") String activiteBct ,
                                                             @RequestParam("statutDossier") String statutDossier ,
                                                             @RequestParam("statutValidationDossier") String statutValidationDossier ,
                                                             @RequestParam("nompromoteurProjet") String nompromoteurProjet,
                                                             @RequestParam("agentId") String agentId ) throws ClientIdExistException, ParseException, MessagingException {
        SimpleDateFormat formatter=new SimpleDateFormat( "yyyy-MM-dd");

        AvaOtherActivities updateUser = avaOtherActService.updateAva(currentIdClient ,idClient,
                caht ,
                numAutorBct ,
                formatter.parse(dataAutoBct) ,
                montantBct ,
                formatter.parse(dateDeclarationFiscale)  ,
                referenceFiscale  ,
                observation,
                referenceDossierAVA,
                avaType,
                naturePiece,
                numCin,
                codeAgence,
                formatter.parse(dateValidite),
                formatter.parse(finValidite),
                formatter.parse("0000-00-00"),
                compteDebit,
                activiteBct,
                statutDossier,
                statutValidationDossier,
                nompromoteurProjet,
                agentId
        );

        return  new ResponseEntity<>(updateUser , HttpStatus.OK) ;

    }
    @PostMapping("/validateOther")
//    @PreAuthorize("hasAnyAuthority('UPDATE')")
    public ResponseEntity<AvaOtherActivities> validateAvaOther(@RequestParam("currentIdClient") String currentIdClient ,
                                                             @RequestParam("idClient") String idClient ,
                                                             @RequestParam("caht") Float caht,
                                                             @RequestParam("numAutorBct") Integer numAutorBct ,
                                                             @RequestParam("dataAutoBct") String dataAutoBct ,
                                                             @RequestParam("montantBct") Float montantBct ,
                                                             @RequestParam("dateDeclarationFiscale") String dateDeclarationFiscale ,
                                                             @RequestParam("referenceFiscale") String referenceFiscale ,
                                                             @RequestParam("observation") String observation ,
                                                             @RequestParam("referenceDossierAVA") String referenceDossierAVA,
                                                             @RequestParam("avaType") String avaType ,
                                                             @RequestParam("naturePiece") String naturePiece ,
                                                             @RequestParam("numCin") String numCin ,
                                                             @RequestParam("codeAgence") String codeAgence ,
                                                             @RequestParam("dateValidite") String dateValidite ,
                                                             @RequestParam("finValidite") String finValidite ,
                                                             @RequestParam("dateCloture") String dateCloture ,
                                                             @RequestParam("compteDebit") String compteDebit ,
                                                             @RequestParam("activiteBct") String activiteBct ,
                                                             @RequestParam("statutDossier") String statutDossier ,
                                                             @RequestParam("statutValidationDossier") String statutValidationDossier ,
                                                             @RequestParam("nompromoteurProjet") String nompromoteurProjet,
                                                             @RequestParam("agentId") String agentId ) throws ClientIdExistException, ParseException, MessagingException {
        SimpleDateFormat formatter=new SimpleDateFormat( "yyyy-MM-dd");

        AvaOtherActivities updateUser = avaOtherActService.validateAva(currentIdClient ,idClient,
                caht ,
                numAutorBct ,
                formatter.parse(dataAutoBct) ,
                montantBct ,
                formatter.parse(dateDeclarationFiscale)  ,
                referenceFiscale  ,
                observation,
                referenceDossierAVA,
                avaType,
                naturePiece,
                numCin,
                codeAgence,
                formatter.parse(dateValidite),
                formatter.parse(finValidite),
                formatter.parse("0000-00-00"),
                compteDebit,
                activiteBct,
                statutDossier,
                statutValidationDossier,
                nompromoteurProjet,
                agentId
                );

        return  new ResponseEntity<>(updateUser , HttpStatus.OK) ;

    }
    @PostMapping("/updateFmEx")
//    @PreAuthorize("hasAnyAuthority('UPDATE')")
    public ResponseEntity<AvaForeignMarketExporter> vupdateAvaFmEx(@RequestParam("currentIdClient") String currentIdClient ,
                                                             @RequestParam("idClient") String idClient ,
                                                             @RequestParam("caht") Float caht,
                                                             @RequestParam("numAutorBct") Integer numAutorBct ,
                                                             @RequestParam("dataAutoBct") String dataAutoBct ,
                                                             @RequestParam("montantBct") Float montantBct ,
                                                             @RequestParam("observation") String observation ,
                                                             @RequestParam("referenceDossierAVA") String referenceDossierAVA,
                                                             @RequestParam("avaType") String avaType ,
                                                             @RequestParam("naturePiece") String naturePiece ,
                                                             @RequestParam("numCin") String numCin ,
                                                             @RequestParam("codeAgence") String codeAgence ,
                                                             @RequestParam("dateValidite") String dateValidite ,
                                                             @RequestParam("finValidite") String finValidite ,
                                                             @RequestParam("dateCloture") String dateCloture ,
                                                             @RequestParam("compteDebit") String compteDebit ,
                                                             @RequestParam("activiteBct") String activiteBct ,
                                                             @RequestParam("statutDossier") String statutDossier ,
                                                             @RequestParam("statutValidationDossier") String statutValidationDossier ,
                                                             @RequestParam("titulaireMarche") String titulaireMarche ,
                                                                  @RequestParam("montantContrat") Float montantContrat ,
                                                                  @RequestParam("devise") String devise ,
                                                                  @RequestParam("dateContrat") String dateContrat,
                                                                  @RequestParam("agentId") String agentId) throws ClientIdExistException, ParseException, MessagingException {
        SimpleDateFormat formatter=new SimpleDateFormat( "yyyy-MM-dd");

        AvaForeignMarketExporter updateAva = avaForeignMarketExporterService.updateAva(currentIdClient ,idClient,
                caht ,
                numAutorBct ,
                formatter.parse(dataAutoBct) ,
                montantBct ,
                observation,
                referenceDossierAVA,
                avaType,
                naturePiece,
                numCin,
                codeAgence,
                formatter.parse(dateValidite) ,
                formatter.parse(finValidite),
                formatter.parse("0000-00-00"),
                compteDebit,
                activiteBct,
                statutDossier,
                statutValidationDossier,
                titulaireMarche,
                montantContrat,
                devise,
                formatter.parse(dateContrat),
                agentId



        );

        return  new ResponseEntity<>(updateAva , HttpStatus.OK) ;

    }
    @PostMapping("/ValidateFmEx")
//    @PreAuthorize("hasAnyAuthority('UPDATE')")
    public ResponseEntity<AvaForeignMarketExporter> validateAvaFmEx(@RequestParam("currentIdClient") String currentIdClient ,
                                                                  @RequestParam("idClient") String idClient ,
                                                                  @RequestParam("caht") Float caht,
                                                                  @RequestParam("numAutorBct") Integer numAutorBct ,
                                                                  @RequestParam("dataAutoBct") String dataAutoBct ,
                                                                  @RequestParam("montantBct") Float montantBct ,
                                                                  @RequestParam("observation") String observation ,
                                                                  @RequestParam("referenceDossierAVA") String referenceDossierAVA,
                                                                  @RequestParam("avaType") String avaType ,
                                                                  @RequestParam("naturePiece") String naturePiece ,
                                                                  @RequestParam("numCin") String numCin ,
                                                                  @RequestParam("codeAgence") String codeAgence ,
                                                                  @RequestParam("dateValidite") String dateValidite ,
                                                                  @RequestParam("finValidite") String finValidite ,
                                                                  @RequestParam("dateCloture") String dateCloture ,
                                                                  @RequestParam("compteDebit") String compteDebit ,
                                                                  @RequestParam("activiteBct") String activiteBct ,
                                                                  @RequestParam("statutDossier") String statutDossier ,
                                                                  @RequestParam("statutValidationDossier") String statutValidationDossier ,
                                                                  @RequestParam("titulaireMarche") String titulaireMarche ,
                                                                  @RequestParam("montantContrat") Float montantContrat ,
                                                                  @RequestParam("devise") String devise ,
                                                                  @RequestParam("dateContrat") String dateContrat,
                                                                  @RequestParam("agentId") String agentId) throws ClientIdExistException, ParseException, MessagingException {
        SimpleDateFormat formatter=new SimpleDateFormat( "yyyy-MM-dd");

        AvaForeignMarketExporter updateAva = avaForeignMarketExporterService.ValidateAva(currentIdClient ,idClient,
                caht ,
                numAutorBct ,
                formatter.parse(dataAutoBct) ,
                montantBct ,
                observation,
                referenceDossierAVA,
                avaType,
                naturePiece,
                numCin,
                codeAgence,
                formatter.parse(dateValidite) ,
                formatter.parse(finValidite),
                formatter.parse("0000-00-00"),
                compteDebit,
                activiteBct,
                statutDossier,
                statutValidationDossier,
                titulaireMarche,
                montantContrat,
                devise,
                formatter.parse(dateContrat),
                agentId



        );

        return  new ResponseEntity<>(updateAva , HttpStatus.OK) ;

    }
    @PostMapping("/RenewalFmEx")
//    @PreAuthorize("hasAnyAuthority('UPDATE')")
    public ResponseEntity<AvaForeignMarketExporter> RenewalAvaFmEx(@RequestParam("currentIdClient") String currentIdClient ,
                                                                  @RequestParam("idClient") String idClient ,
                                                                  @RequestParam("caht") Float caht,
                                                                   @RequestParam("avaType") String avaType,
                                                                  @RequestParam("numAutorBct") Integer numAutorBct ,
                                                                  @RequestParam("dataAutoBct") String dataAutoBct ,
                                                                  @RequestParam("montantBct") Float montantBct ,
                                                                  @RequestParam("dateValidite") String dateValidite ,
                                                                  @RequestParam("finValidite") String finValidite ,
                                                                   @RequestParam("dat") Float dat ,
                                                                  @RequestParam("statutDossier") String statutDossier ,
                                                                  @RequestParam("statutValidationDossier") String statutValidationDossier,
                                                                   @RequestParam("agentId") String agentId

                                                                  ) throws ClientIdExistException, ParseException, StatutDossierException {
        SimpleDateFormat formatter=new SimpleDateFormat( "yyyy-MM-dd");

        AvaForeignMarketExporter updateAva = avaForeignMarketExporterService.renewalAva(currentIdClient ,avaType
                ,idClient,
                caht ,
                numAutorBct ,
                formatter.parse(dataAutoBct) ,
                montantBct ,
                formatter.parse(dateValidite) ,
                formatter.parse(finValidite),
                dat,
                statutDossier,
                statutValidationDossier,
                agentId

        );

        return  new ResponseEntity<>(updateAva , HttpStatus.OK) ;

    }

    @PostMapping("renouveleAA")
    public ResponseEntity<AvaOtherActivities> renouveleAvaOther(@RequestParam("currentIdClient") String currentIdClient ,
                                                                @RequestParam("referenceDossierAVA") String referenceDossierAVA,
                                                                @RequestParam("caht") Float caht,
                                                                @RequestParam("avaType") String avaType,
                                                                @RequestParam("dateValidite") String dateValidite ,
                                                                @RequestParam("finValidite") String finValidite ,
                                                                @RequestParam("numAutorBct") Integer numAutorBct ,
                                                                @RequestParam("dataAutoBct") String dataAutoBct ,
                                                                @RequestParam("montantBct") Float montantBct ,
                                                                @RequestParam("idClient") String idClient ,
                                                                @RequestParam("dateDeclarationFiscale") String dateDeclarationFiscale ,
                                                                @RequestParam("dat") Float dat ,
                                                                @RequestParam("observation") String observation ,
                                                                @RequestParam("statutDossier") String statutDossier,
                                                                @RequestParam("agentId") String agentId



                                                            ) throws ClientIdExistException, ParseException, StatutDossierException {
        SimpleDateFormat formatter=new SimpleDateFormat( "yyyy-MM-dd");

        AvaOtherActivities renouveleAva = avaOtherActService.renewalAva(currentIdClient ,avaType,
                referenceDossierAVA,
                caht ,
                formatter.parse(dateValidite) ,
                formatter.parse(finValidite),
                numAutorBct  ,
                formatter.parse(dataAutoBct)  ,
                montantBct ,
                idClient,
                formatter.parse(dateDeclarationFiscale)  ,
                dat,
                observation,
                statutDossier,
                agentId




        );

        return  new ResponseEntity<>(renouveleAva , HttpStatus.OK) ;

    }
    @GetMapping("/list")
    public  ResponseEntity<List<Ava>> getAllUsers() {
        List<Ava> ava = (List<Ava>) avaOtherActService.getAllAvaOtherAct();
        return  new ResponseEntity(ava , HttpStatus.OK) ;


    }
    @GetMapping("/listRgChecker")
    public  ResponseEntity<List<Ava>> getAllAvas() {
        List<Ava> ava = (List<Ava>) avaOtherActService.avalistRgChecker();
        return  new ResponseEntity(ava , HttpStatus.OK) ;
    }
    @GetMapping("/listRgMaker")
    public  ResponseEntity<List<Ava>> getAllAvas2() {
        List<Ava> ava = (List<Ava>) avaOtherActService.avalistForRgMaker();
        return  new ResponseEntity(ava , HttpStatus.OK) ;
    }

    @GetMapping("/listBranch")
    public  ResponseEntity<List<Ava>> getAllAvas3() {
        List<Ava> ava = (List<Ava>) avaOtherActService.avalistForBranch();
        return  new ResponseEntity(ava , HttpStatus.OK) ;
    }
    @GetMapping("/listAmendement")
    public  ResponseEntity<List<Ava>> getAllAvas5() {
        List<Ava> ava = (List<Ava>) avaOtherActService.avalistForAmandment();
        return  new ResponseEntity(ava , HttpStatus.OK) ;
    }
    @GetMapping("/listRenewel")
    public  ResponseEntity<List<Ava>> getAllAvas6() {
        List<Ava> ava = (List<Ava>) avaOtherActService.avalistForRenewel();
        return  new ResponseEntity(ava , HttpStatus.OK) ;
    }
    @GetMapping("/listMois")
    public  ResponseEntity<List<Ava>> getAllAvas7()  {
        List<Ava> ava = (List<Ava>) avaOtherActService.avaMonthlyList();
        return  new ResponseEntity(ava , HttpStatus.OK) ;
    }

    @PostMapping("/listMoisCustum")
    public  ResponseEntity<List<Ava>> getAllAvas8(  @RequestParam("dateList") String dateList) throws ParseException {
        List<Ava> ava = (List<Ava>) avaOtherActService.avaMonthlyCustomList(dateList);
        return  new ResponseEntity(ava , HttpStatus.OK) ;
    }




    @GetMapping("/listT12")
    public  ResponseEntity<List<Ava>> getAllUsersTypeFE() {
        List<Ava> ava = (List<Ava>) avaForeignMarketExporterService.getAllAvaForMEx();
        return  new ResponseEntity(ava , HttpStatus.OK) ;


    }


    @GetMapping("/findT/{id}")
    // @PreAuthorize("hasAnyAuthority('DELETE')")
    public ResponseEntity<HttpResponse> findAva(@PathVariable("id")Long id){
       Ava ava = avaForeignMarketExporterService.getAvaForMexById(id);
        return  new ResponseEntity(ava , HttpStatus.OK) ;

    }
    @GetMapping("/findgroupby")
    // @PreAuthorize("hasAnyAuthority('DELETE')")
    public ResponseEntity<HttpResponse> findAvagroupby(){
        List<OutputStat> ava = avaForeignExporterRepository.findcountgroupbytype();
        return  new ResponseEntity(ava , HttpStatus.OK) ;

    }
    @GetMapping("/findgroupby2")
    // @PreAuthorize("hasAnyAuthority('DELETE')")
    public ResponseEntity<HttpResponse> findAvagroupby2(){
        List<OutputStat> ava = avaForeignExporterRepository.findcountgroupbytype2();
        return  new ResponseEntity(ava , HttpStatus.OK) ;

    }
    @GetMapping("/findgroupby3")
    // @PreAuthorize("hasAnyAuthority('DELETE')")
    public ResponseEntity<HttpResponse> findAvagroupby3(){
        List<OutputStat> ava = avaForeignExporterRepository.findcountgroupbytype3();
        return  new ResponseEntity(ava , HttpStatus.OK) ;

    }
    @GetMapping("/findgroupby4")
    // @PreAuthorize("hasAnyAuthority('DELETE')")
    public ResponseEntity<HttpResponse> findAvagroupby4(){
        List<OutputStat> ava = avaForeignExporterRepository.findcountgroupbytype4();
        return  new ResponseEntity(ava , HttpStatus.OK) ;

    }
    @GetMapping("/findgroupby5")
    // @PreAuthorize("hasAnyAuthority('DELETE')")
    public ResponseEntity<HttpResponse> findAvagroupby5(){
        ArrayList<OutputListStat> ListAva =  new ArrayList<OutputListStat>();
          OutputListStat l1 = new OutputListStat();
          OutputListStat l2 = new OutputListStat();
          OutputListStat l3 = new OutputListStat();
        List<OutputStat> ava = avaForeignExporterRepository.findcountgroupbytype5("marche-a-etranger");
        List<OutputStat> ava2 = avaForeignExporterRepository.findcountgroupbytype5("exportateur");
        List<OutputStat> ava3 = avaForeignExporterRepository.findcountgroupbytype5("autre-activités");
          l1.setName("marche-a-etranger");
          l1.setSeries(ava);
          l2.setName("exportateur");
          l2.setSeries(ava2);
          l3.setName("autre-activités");
          l3.setSeries(ava3);
        ListAva.add(l1);
        ListAva.add(l2);
        ListAva.add(l3);


        return  new ResponseEntity(ListAva , HttpStatus.OK) ;

    }
    @GetMapping("/findgroupby6")
    // @PreAuthorize("hasAnyAuthority('DELETE')")
    public ResponseEntity<HttpResponse> findAvagroupby6(){
        List<OutputStat> ava = avaForeignExporterRepository.findcountgroupbytype6();
        return  new ResponseEntity(ava , HttpStatus.OK) ;

    }
    @GetMapping("/findgroupby7")
    // @PreAuthorize("hasAnyAuthority('DELETE')")
    public ResponseEntity<HttpResponse> findAvagroupby7(){
        List<OutputStat> ava = avaForeignExporterRepository.findcountgroupbytype7();
        return  new ResponseEntity(ava , HttpStatus.OK) ;

    }
    @GetMapping("/findgroupby8")
    // @PreAuthorize("hasAnyAuthority('DELETE')")
    public ResponseEntity<HttpResponse> findAvagroupby8(){
        ArrayList<OutputDateListStats> ListAva =  new ArrayList<OutputDateListStats>();
        List<OutputDateStat> ava = avaForeignExporterRepository.findcountgroupbytype8();
        OutputDateListStats l1 = new OutputDateListStats();
        l1.setName("A.V.A");
        l1.setSeries(ava);
        ListAva.add(l1);
        return  new ResponseEntity(ListAva , HttpStatus.OK) ;

    }
//    @GetMapping("/findT/type")
//    // @PreAuthorize("hasAnyAuthority('DELETE')")
//    public ResponseEntity<HttpResponse> findAvaby(@RequestParam("avaType") String avaType ,
//                                                  @RequestParam("currentIdClient") String  currentIdClient){
//        Ava ava = avaForeignMarketExporterService.findbytype(avaType,currentIdClient);
//        return  new ResponseEntity(ava , HttpStatus.OK) ;
//
//    }
@GetMapping("/findHistoriqueAva")
// @PreAuthorize("hasAnyAuthority('DELETE')")
public ResponseEntity<HttpResponse> findAvaHistorique(@RequestParam("refDossier") Long refDossier){
        Ava ava = avaForeignExporterRepository.findAvaById(refDossier);
    List<HistoriqueAva> historiqueAvas = (List<HistoriqueAva>) historiqueAvaRepository.findAll();
    List<HistoriqueAva> customList = new ArrayList<>();
//    for (HistoriqueAva historiqueAva:historiqueAvas
//         ) {
//        if (historiqueAva.getAva().getReferenceDossierAVA().equals( ava.getReferenceDossierAVA())){
//            customList.add(historiqueAva);
//        }
//
//    }


    return  new ResponseEntity(historiqueAvas , HttpStatus.OK) ;

}
@PostMapping("/export2/pdf")
public ResponseEntity<InputStreamResource> exportPdfAva(@RequestParam("dateList") String dateList) throws ParseException   {
    List<Ava> avas = (List<Ava>) avaOtherActService.avaMonthlyCustomList(dateList);
    ByteArrayInputStream bais = exportAvaService.AvasPDFReportCustomMonth(avas,dateList) ;
    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Disposition",
            "inline ; filename=ava.pdf");
    return   ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
            .body(new InputStreamResource(bais));

}

    @GetMapping("/export/pdf")
    public ResponseEntity<InputStreamResource> exportPdfAvaCustomMonth()  {
        List<Ava> avas = (List<Ava>) avaOtherActService.avaMonthlyList();
        ByteArrayInputStream bais = exportAvaService.AvasPDFReport(avas) ;
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition",
                "inline ; filename=ava.pdf");
        return    ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bais));

    }
    @GetMapping("/exportBenef/pdf")
    public ResponseEntity<InputStreamResource> exportPdfBenef() {
        List<Ava> beneficiaries = (List<Ava>) avaForeignExporterRepository.findAll();
        ByteArrayInputStream bais = exportAvaService.BenefPDFReport(beneficiaries) ;
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition",
                "inline ; filename=ava.pdf");
        return    ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bais));

    }
    @PostMapping("/exportBenef2/pdf")
    public ResponseEntity<InputStreamResource> exportPdfBenefCustomMonth(@RequestParam("dateList") String dateList) throws ParseException {
        List<Ava> beneficiaries = (List<Ava>) avaForeignExporterRepository.findAll();
        ByteArrayInputStream bais = exportAvaService.BenefPDFReportCustomMonth(beneficiaries,dateList) ;
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition",
                "inline ; filename=ava.pdf");
        return    ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bais));

    }

    @GetMapping("/find/{id}")
    // @PreAuthorize("hasAnyAuthority('DELETE')")
    public ResponseEntity<HttpResponse> findAvaT(@PathVariable("id")Long id){
        Ava ava = avaOtherActService.getAvaOtherActById(id);
        return  new ResponseEntity(ava , HttpStatus.OK) ;


    }
    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String msg) {

        return new  ResponseEntity<>(new HttpResponse(httpStatus.value() ,
                httpStatus,httpStatus.getReasonPhrase().toUpperCase(),
                msg.toUpperCase() ),httpStatus);
    }


}
