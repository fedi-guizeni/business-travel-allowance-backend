package com.pfe22.ava.entities.ava.AvaFile;


import com.pfe22.ava.entities.AppUsers.Client;
import com.pfe22.ava.entities.AppUsers.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "ava")
public class Ava implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false , updatable = false)
    private  Long id ;
    private  String ReferenceDossierAVA;
    private String AvaType;
    private String idClient;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id" , referencedColumnName = "id" )
    private Client Client;
    private String NaturePiece;
    private String numCin;
    private String codeAgence;
    private  Date dateCreation;
    @Column(columnDefinition = "date")
    private  Date dateValidite;
    private Date finValidite;
    private Date dateCloture;
    private String compteDebit;
    private String activiteBct;
    private Float caht;
    private Float dat;
    private String observation;
    private String statutDossier;
    private String statutValidationDossier;
    private  Integer numAutorBct;
    private Date dataAutoBct;
    private Float montantBct;
   @OneToOne
   @JoinColumn(name = "agent_id" , referencedColumnName = "id")
    private User Agent;
   @OneToMany(
           targetEntity = Beneficiary.class , cascade = CascadeType.ALL
   )
   @JoinColumn(name = "beneficiaries" , referencedColumnName = "id")
   private List<Beneficiary> beneficiaries;
    @OneToMany(
            targetEntity = HistoriqueAva.class , cascade = CascadeType.ALL
    )
    @JoinColumn(name = "historique" , referencedColumnName = "id")
    private List<HistoriqueAva> historiqueAvas;

    public Ava (){}



    public Ava(Long id,
               String referenceDossierAVA,
               String avaType,
               String idClient,
               String naturePiece,
               String numCin,
               String codeAgence,
               Date dateCreation,
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
               Float montantBct,
               User agent,
               Client client,
               List<Beneficiary> beneficiaries,
               List<HistoriqueAva> historiqueAvas
               ) {
        this.id = id;
        this.ReferenceDossierAVA = referenceDossierAVA;
        this.AvaType = avaType;
        this.idClient = idClient;
        this.NaturePiece = naturePiece;
        this.numCin = numCin;
        this.codeAgence = codeAgence;
        this.dateCreation = dateCreation;
        this.dateValidite = dateValidite;
        this.finValidite = finValidite;
        this.dateCloture = dateCloture;
        this.compteDebit = compteDebit;
        this.activiteBct = activiteBct;
        this.caht = caht;
        this.dat = dat;
        this.observation = observation;
        this.statutDossier = statutDossier;
        this.statutValidationDossier = statutValidationDossier;
        this.numAutorBct=numAutorBct;
        this.dataAutoBct=dataAutoBct;
        this.montantBct=montantBct;
        this.Agent=agent;
        this.Client=client;
        this.beneficiaries=beneficiaries;
        this.historiqueAvas=historiqueAvas;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setReferenceDossierAVA(String referenceDossierAVA) {
        ReferenceDossierAVA = referenceDossierAVA;
    }

    public void setAvaType(String avaType) {
        AvaType = avaType;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    public void setNaturePiece(String naturePiece) {
        NaturePiece = naturePiece;
    }

    public void setNumCin(String numCin) {
        this.numCin = numCin;
    }

    public void setCodeAgence(String codeAgence) {
        this.codeAgence = codeAgence;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public void setDateValidite(Date dateValidite) {
        this.dateValidite = dateValidite;
    }

    public void setFinValidite(Date finValidite) {
        this.finValidite = finValidite;
    }

    public void setDateCloture(Date dateCloture) {
        this.dateCloture = dateCloture;
    }

    public void setCompteDebit(String compteDebit) {
        this.compteDebit = compteDebit;
    }

    public void setActiviteBct(String activiteBct) {
        this.activiteBct = activiteBct;
    }

    public void setCaht(Float caht) {
        this.caht = caht;
    }

    public void setDat(Float dat) {
        this.dat = dat;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public void setStatutDossier(String statutDossier) {
        this.statutDossier = statutDossier;
    }

    public void setStatutValidationDossier(String statutValidationDossier) {
        this.statutValidationDossier = statutValidationDossier;
    }
    public void setNumAutorBct(Integer numAutorBct) {
        this.numAutorBct = numAutorBct;
    }

    public void setDataAutoBct(Date dataAutoBct) {
        this.dataAutoBct = dataAutoBct;
    }

    public void setMontantBct(Float montantBct) {
        this.montantBct = montantBct;
    }

    public void setAgent(User agent) {
        Agent = agent;
    }

    public void setClient(com.pfe22.ava.entities.AppUsers.Client client) {
        Client = client;
    }

    public void setBeneficiaries(List<Beneficiary> beneficiaries) {
        this.beneficiaries = beneficiaries;
    }

    public void setHistoriqueAvas(List<HistoriqueAva> historiqueAvas) {
        this.historiqueAvas = historiqueAvas;
    }

    public Long getId() {
        return id;
    }

    public String getReferenceDossierAVA() {
        return ReferenceDossierAVA;
    }

    public String getAvaType() {
        return AvaType;
    }

    public String getIdClient() {
        return idClient;
    }

    public String getNaturePiece() {
        return NaturePiece;
    }

    public String getNumCin() {
        return numCin;
    }

    public String getCodeAgence() {
        return codeAgence;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public Date getDateValidite() {
        return dateValidite;
    }

    public Date getFinValidite() {
        return finValidite;
    }

    public Date getDateCloture() {
        return dateCloture;
    }

    public String getCompteDebit() {
        return compteDebit;
    }

    public String getActiviteBct() {
        return activiteBct;
    }

    public Float getCaht() {
        return caht;
    }

    public Float getDat() {
        return dat;
    }

    public String getObservation() {
        return observation;
    }

    public String getStatutDossier() {
        return statutDossier;
    }

    public String getStatutValidationDossier() {
        return statutValidationDossier;
    }

    public Integer getNumAutorBct() {
        return numAutorBct;
    }

    public Date getDataAutoBct() {
        return dataAutoBct;
    }

    public Float getMontantBct() {
        return montantBct;
    }

    public User getAgent() {
        return Agent;
    }

    public com.pfe22.ava.entities.AppUsers.Client getClient() {
        return Client;
    }

    public List<Beneficiary> getBeneficiaries() {
        return beneficiaries;
    }

    public List<HistoriqueAva> getHistoriqueAvas() {
        return historiqueAvas;
    }
}
