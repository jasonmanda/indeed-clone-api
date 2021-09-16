package com.dash.it.solution.viewmodel;

import java.util.List;

import com.dash.it.solution.entities.Competence;
import com.dash.it.solution.entities.ExpPro;
import com.dash.it.solution.entities.Langue;
import com.dash.it.solution.entities.Personne;
import com.dash.it.solution.entities.Certificat;
import com.dash.it.solution.entities.Contact;
import com.dash.it.solution.entities.Cv;
import com.dash.it.solution.entities.Formation;

public class Resume {
 
    private List<ExpPro> exp_pro;
    private List<Formation> formation;
    private List<Competence> competence;
    private List<Langue> langue;
    private List<Certificat> certificat;
    private Cv cv;
 

    public List<ExpPro> getExp_pro() {
        return exp_pro;
    }

    public void setExp_pro(List<ExpPro> exp_pro) {
        this.exp_pro = exp_pro;
    }

    public List<Formation> getFormation() {
        return formation;
    }

    public void setFormation(List<Formation> formation) {
        this.formation = formation;
    }


    public List<Competence> getCompetence() {
        return competence;
    }

    public void setCompetence(List<Competence> competence) {
        this.competence = competence;
    }

    public List<Langue> getLangue() {
        return langue;
    }

    public void setLangue(List<Langue> langue) {
        this.langue = langue;
    }

    public List<Certificat> getCertificat() {
        return certificat;
    }

    public void setCertificat(List<Certificat> certificat) {
        this.certificat = certificat;
    }

    public Cv getCv() {
        return cv;
    }

    public void setCv(Cv cv) {
        this.cv = cv;
    }


}