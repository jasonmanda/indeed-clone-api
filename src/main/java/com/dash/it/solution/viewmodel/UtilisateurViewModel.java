package com.dash.it.solution.viewmodel;

import com.dash.it.solution.entities.Contact;
import com.dash.it.solution.entities.Entreprise;
import com.dash.it.solution.entities.Grants;
import com.dash.it.solution.entities.Image;
import com.dash.it.solution.entities.User;

public class UtilisateurViewModel {
    private Entreprise entreprise;
    private User utilisateur;
    private Grants grants;
    private Image image;
    private Contact contact;
    private String oldPwd;
    private String newPwd;

    public Entreprise getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(Entreprise entreprise) {
        this.entreprise = entreprise;
    }

    public String getOldPwd() {
        return oldPwd;
    }

    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }

    public Grants getGrants() {
        return grants;
    }

    public void setGrants(Grants grants) {
        this.grants = grants;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public User getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(User utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
    
}