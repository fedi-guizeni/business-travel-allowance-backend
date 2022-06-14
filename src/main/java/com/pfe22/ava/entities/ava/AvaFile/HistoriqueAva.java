package com.pfe22.ava.entities.ava.AvaFile;


import com.pfe22.ava.entities.AppUsers.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class HistoriqueAva implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false , updatable = false)
    private  Long id ;
    @OneToOne
    @JoinColumn(name = "agent_id" , referencedColumnName = "id")
    private User agent;
    private String etatDossier;
    private Date dateOperation;


    public HistoriqueAva() {}
    public HistoriqueAva(Long id, User agent,String etatDossier,Date dateOperation) {
        this.id = id;
        this.agent = agent;
        this.etatDossier = etatDossier;
        this.dateOperation=dateOperation;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public void setAgent(User agent) {
        this.agent = agent;
    }

    public void setEtatDossier(String etatDossier) {
        this.etatDossier = etatDossier;
    }

    public void setDateOperation(Date dateOperation) {
        this.dateOperation = dateOperation;
    }

    public Long getId() {
        return id;
    }

    public User getAgent() {
        return agent;
    }

    public String getEtatDossier() {
        return etatDossier;
    }

    public Date getDateOperation() {
        return dateOperation;
    }
}
