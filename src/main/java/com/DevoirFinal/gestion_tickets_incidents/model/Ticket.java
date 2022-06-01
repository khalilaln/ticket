package com.DevoirFinal.gestion_tickets_incidents.model;

import javax.persistence.*;

@Entity
public class Ticket {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue()
    private Long id;

    private String name;
    private String description;

    @Column( nullable = true)
    private String image;

    private String urgence;
    private String environnement;
    private String logiciel;

    private String etat;

    @ManyToOne
    @JoinColumn(nullable = true)
    private User developeur;

    @ManyToOne
    private User client;

    public Ticket() {
    }

    public Ticket(Long id, String name, String description, String image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
    }



    public Ticket(String name, String description, String image, String urgence, String environnement, String logiciel,   User client) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.urgence = urgence;
        this.environnement = environnement;
        this.logiciel = logiciel;
        this.client = client;
        this.etat="ouverture";
    }

    public Ticket(Long id, String name, String description, String image, String urgence, String environnement, String logiciel, String etat, User developeur, User client) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.urgence = urgence;
        this.environnement = environnement;
        this.logiciel = logiciel;
        this.etat = etat;
        this.developeur = developeur;
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrgence() {
        return urgence;
    }

    public void setUrgence(String urgence) {
        this.urgence = urgence;
    }

    public String getEnvironnement() {
        return environnement;
    }

    public void setEnvironnement(String environnement) {
        this.environnement = environnement;
    }

    public String getLogiciel() {
        return logiciel;
    }

    public void setLogiciel(String logiciel) {
        this.logiciel = logiciel;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public User getDevelopeur() {
        return developeur;
    }

    public void setDevelopeur(User developeur) {
        this.developeur = developeur;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }
}
