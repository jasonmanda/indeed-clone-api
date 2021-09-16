package com.dash.it.solution.viewmodel;

import com.dash.it.solution.entities.Contact;
import com.dash.it.solution.entities.Entreprise;
import com.dash.it.solution.entities.Grants;
import com.dash.it.solution.entities.Image;

public class Subscribe {
    private Grants grants;
    private AuthCredentials user;
    private Image image;
    private Entreprise entreprise;
 

    public AuthCredentials getUser() {
        return user;
    }

    public void setUser(AuthCredentials user) {
        this.user = user;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Entreprise getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(Entreprise entreprise) {
        this.entreprise = entreprise;
    }
 

    public Grants getGrants() {
        return grants;
    }

    public void setGrants(Grants grants) {
        this.grants = grants;
    }

    
 
}
