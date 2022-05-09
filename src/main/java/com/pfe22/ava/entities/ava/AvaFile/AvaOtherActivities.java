package com.pfe22.ava.entities.ava.AvaFile;


import com.pfe22.ava.entities.AppUsers.Client;
import com.pfe22.ava.entities.AppUsers.User;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "avaOtherActivities ")
public class AvaOtherActivities extends Ava{
    private String NompromoteurProjet;
    private Date dateDeclarationFiscale;
    private String referenceFiscale;

    public AvaOtherActivities(){}




    public AvaOtherActivities(Long id, String referenceDossierAVA, String avaType, String idClient, String naturePiece, String numCin, String codeAgence, Date dateCreation, Date dateValidite, Date finValidite, Date dateCloture, String compteDebit, String activiteBct, String caht, Float dat, String observation, String statutDossier, String statutValidationDossier, Integer numAutorBct, Date dataAutoBct, Float montantBct, User agent , Client client , List<Beneficiary> beneficiaries, String nompromoteurProjet, Date dateDeclarationFiscale, String referenceFiscale) {
        super(id, referenceDossierAVA, avaType, idClient, naturePiece, numCin, codeAgence, dateCreation, dateValidite, finValidite, dateCloture, compteDebit, activiteBct, caht, dat, observation, statutDossier, statutValidationDossier, numAutorBct, dataAutoBct, montantBct , agent,client ,beneficiaries);
        this.NompromoteurProjet = nompromoteurProjet;
        this.dateDeclarationFiscale = dateDeclarationFiscale;
        this.referenceFiscale = referenceFiscale;
    }



    public void setNompromoteurProjet(String nompromoteurProjet) {
        NompromoteurProjet = nompromoteurProjet;
    }

    public void setDateDeclarationFiscale(Date dateDeclarationFiscale) {
        this.dateDeclarationFiscale = dateDeclarationFiscale;
    }

    public void setReferenceFiscale(String referenceFiscale) {
        this.referenceFiscale = referenceFiscale;
    }

    public String getNompromoteurProjet() {
        return NompromoteurProjet;
    }

    public Date getDateDeclarationFiscale() {
        return dateDeclarationFiscale;
    }

    public String getReferenceFiscale() {
        return referenceFiscale;
    }
}
