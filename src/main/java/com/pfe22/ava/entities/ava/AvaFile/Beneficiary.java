package com.pfe22.ava.entities.ava.AvaFile;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "beneficiaries")
public class Beneficiary implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false , updatable = false)
    private  Long id ;
    private String idBenef;
    private Integer benefCardId;
    private String benefCardType;
    private String nomPrenomBenef;
    private  String adressBenef;
    private String qualite;
    private Integer tel;
    private  String statutBenef;
    private Date dateCreation;




    public Beneficiary(){}
    public Beneficiary(
                       String idBenef,
                       Integer benefCardId,
                       String benefCardType,
                       String nomPrenomBenef,
                       String adressBenef,
                       String qualite,
                       Integer tel,
                       String statutBenef,
                       Date dateCreation
                       ) {

        this.idBenef = idBenef;
        this.benefCardId = benefCardId;
        this.benefCardType = benefCardType;
        this.nomPrenomBenef = nomPrenomBenef;
        this.adressBenef = adressBenef;
        this.qualite = qualite;
        this.tel = tel;
        this.statutBenef = statutBenef;
        this.dateCreation=dateCreation;

    }

    public void setIdBenef(String idBenef) {
        this.idBenef = idBenef;
    }

    public void setBenefCardId(Integer benefCardId) {
        this.benefCardId = benefCardId;
    }

    public void setBenefCardType(String benefCardType) {
        this.benefCardType = benefCardType;
    }

    public void setNomPrenomBenef(String nomPrenomBenef) {
        this.nomPrenomBenef = nomPrenomBenef;
    }

    public void setAdressBenef(String adressBenef) {
        this.adressBenef = adressBenef;
    }

    public void setQualite(String qualite) {
        this.qualite = qualite;
    }

    public void setTel(Integer tel) {
        this.tel = tel;
    }

    public void setStatutBenef(String statutBenef) {
        this.statutBenef = statutBenef;
    }

    public void setDateCreation(Date dateCreation) {this.dateCreation = dateCreation;}

    public String getIdBenef() {
        return idBenef;
    }

    public Integer getBenefCardId() {
        return benefCardId;
    }

    public String getBenefCardType() {
        return benefCardType;
    }

    public String getNomPrenomBenef() {
        return nomPrenomBenef;
    }

    public String getAdressBenef() {
        return adressBenef;
    }

    public String getQualite() {
        return qualite;
    }

    public Integer getTel() {
        return tel;
    }

    public String getStatutBenef() {
        return statutBenef;
    }

    public Date getDateCreation() {return dateCreation;}
}

