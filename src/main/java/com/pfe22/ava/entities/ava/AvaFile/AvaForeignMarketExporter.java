package com.pfe22.ava.entities.ava.AvaFile;


import com.pfe22.ava.entities.AppUsers.Client;
import com.pfe22.ava.entities.AppUsers.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "avaForeignMarketExporter ")
public class AvaForeignMarketExporter extends Ava {
    private String titulaireMarche ;
    private Float MontantContrat;
    private String Devise;
    private Date dateContrat;

    public AvaForeignMarketExporter(){}




    public AvaForeignMarketExporter(Long id, String referenceDossier, String avaType, String idClient, String naturePiece, String numCin, String codeAgence, Date dateCreation, Date dateValidite, Date finValidite, Date dateCloture, String compteDebit, String activiteBct, Float caht, Float dat, String observation, String statutDossier, String statutValidationDossier, Integer numAutorBct, Date dataAutoBct, Float montantBct, User agent, Client client, List<Beneficiary> beneficiaries, String titulaireMarche, Float montantContrat, String devise, Date dateContrat ,List<HistoriqueAva> historiqueAvas) {
        super(id, referenceDossier, avaType, idClient, naturePiece, numCin, codeAgence, dateCreation, dateValidite, finValidite, dateCloture, compteDebit, activiteBct, caht, dat, observation, statutDossier, statutValidationDossier, numAutorBct, dataAutoBct, montantBct , agent , client,beneficiaries,historiqueAvas);
        this.titulaireMarche = titulaireMarche;
        this.MontantContrat = montantContrat;
        this.Devise = devise;
        this.dateContrat = dateContrat;
    }

    public void setTitulaireMarche(String titulaireMarche) {
        this.titulaireMarche = titulaireMarche;
    }

    public void setMontantContrat(Float montantContrat) {
        MontantContrat = montantContrat;
    }

    public void setDevise(String devise) {
        Devise = devise;
    }

    public void setDateContrat(Date dateContrat) {
        this.dateContrat = dateContrat;
    }

    public String getTitulaireMarche() {
        return titulaireMarche;
    }

    public Float getMontantContrat() {
        return MontantContrat;
    }

    public String getDevise() {
        return Devise;
    }

    public Date getDateContrat() {
        return dateContrat;
    }
}
