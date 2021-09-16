package com.dash.it.solution.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.dash.it.solution.entities.Certificat;
import com.dash.it.solution.entities.Competence;
import com.dash.it.solution.entities.Contact;
import com.dash.it.solution.entities.Cv;
import com.dash.it.solution.entities.ExpPro;
import com.dash.it.solution.entities.Formation;
import com.dash.it.solution.entities.Langue;
import com.dash.it.solution.entities.User;
import com.dash.it.solution.entities.Ville;
import com.dash.it.solution.repository.CertificatRepository;
import com.dash.it.solution.repository.CompetenceRepository;
import com.dash.it.solution.repository.ContactRepository;
import com.dash.it.solution.repository.CvRepository;
import com.dash.it.solution.repository.ExpProRepository;
import com.dash.it.solution.repository.FormationRepository;
import com.dash.it.solution.repository.LangueRepository;
import com.dash.it.solution.repository.PersonneRepository;
import com.dash.it.solution.repository.UserRepository;
import com.dash.it.solution.repository.VilleRepository;
import com.dash.it.solution.viewmodel.BaseQuery;
import com.dash.it.solution.viewmodel.Resume;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Cv controller.
 *
 * @author Jason Mandabrandja
 */
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/resume")
public class CvController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CvRepository cvRepository;
    @Autowired
    private ExpProRepository expProRepository;
    @Autowired
    private FormationRepository formationRepository;
    @Autowired
    private PersonneRepository personneRepository;
    @Autowired
    private VilleRepository villeRepository;
    @Autowired
    private LangueRepository langueRepository;
    @Autowired
    private CertificatRepository certificatRepository;
    @Autowired
    private CompetenceRepository competenceRepository;
    @Autowired
    private ContactRepository contactRepository;

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/save", produces = "application/json;charset=utf8")
    public BaseQuery<Map<String, Object>> saveCv(@Valid @RequestBody Resume resume, @RequestParam String api_token) {
        BaseQuery<Map<String, Object>> responseBody = new BaseQuery<Map<String, Object>>();
        Map<String, Object> response = new HashMap<>();

        try {
            User user = this.userRepository.getUserByApiToken(api_token);
            Cv cv = this.cvRepository.getCvByPersonneId(user.getPersonne().getId_personne());

            cv.setDescription(resume.getCv().getDescription());
            cv.setPersonne(user.getPersonne());
            cv.setPret_salary_max(resume.getCv().getPret_salary_max());
            cv.setPret_salary_min(resume.getCv().getPret_salary_min());
            this.cvRepository.save(cv);
            List<ExpPro> real_exp_pro = new ArrayList<ExpPro>();
            for (ExpPro exp_pro : resume.getExp_pro()) {
                exp_pro.setCv(cv);
                Ville tempVille=this.villeRepository.getVilleByName(exp_pro.getVille().getNom_ville());
                if(tempVille==null){
                    exp_pro.getVille().setEtat_ville(true);
                    this.villeRepository.save(exp_pro.getVille());
                }else{
                    exp_pro.setVille(tempVille);
                }
                this.expProRepository.save(exp_pro);
           
                if (exp_pro.isEtat_exp_pro()) {
                    real_exp_pro.add(exp_pro);
                }
            }
            List<Formation> real_formation = new ArrayList<Formation>();
            for (Formation formation : resume.getFormation()) {
                formation.setCv(cv);
                this.formationRepository.save(formation);
                if (formation.isEtat_formation()) {
                    real_formation.add(formation);
                }
            }
            Ville ville = null;
            user.getPersonne().setNom_personne(resume.getCv().getPersonne().getNom_personne());
            user.getPersonne().setPrenom_personne(resume.getCv().getPersonne().getPrenom_personne());
            ville = this.villeRepository.getVilleByName(resume.getCv().getPersonne().getVille().getNom_ville());
            if (ville == null) {
                String nom_ville = resume.getCv().getPersonne().getVille().getNom_ville();
                resume.getCv().getPersonne().setVille(new Ville());
                resume.getCv().getPersonne().getVille().setNom_ville(nom_ville);
                resume.getCv().getPersonne().getVille().setEtat_ville(true);
                ville = this.villeRepository.save(resume.getCv().getPersonne().getVille());

            }
            user.getPersonne().setVille(ville);
            this.personneRepository.save(user.getPersonne());

            List<Competence> real_competence = new ArrayList<Competence>();
            for (Competence competence : resume.getCompetence()) {
                competence.setCv(cv);
                this.competenceRepository.save(competence);

                if (competence.isEtat_competence()) {
                    real_competence.add(competence);
                }
            }
            List<Langue> real_langue = new ArrayList<Langue>();
            for (Langue langue : resume.getLangue()) {
                langue.setCv(cv);
                this.langueRepository.save(langue);
                if (langue.isEtat_langue()) {
                    real_langue.add(langue);
                }
            }
            List<Certificat> real_certificat = new ArrayList<Certificat>();
            for (Certificat certificat : resume.getCertificat()) {
                certificat.setCv(cv);
                this.certificatRepository.save(certificat);

                if (certificat.isEtat_certificat()) {
                    real_certificat.add(certificat);
                }
            }
            if (resume.getCv().getContact() != null) {
                if (!resume.getCv().getContact().isEtat_contact()) {
                    resume.getCv().getContact().setEtat_contact(true);
                }
                this.contactRepository.save(resume.getCv().getContact());
            }
            cv.setContact(resume.getCv().getContact());
            cv.setPersonne(user.getPersonne());
            this.cvRepository.save(cv);
            response.put("cv", cv);
            response.put("exp_pro", real_exp_pro);
            response.put("formation", real_formation);
            response.put("competence", real_competence);
            response.put("langue", real_langue);
            response.put("certificat", real_certificat);
            responseBody.setValidate(true);
        } catch (Exception exp) {
            response.put("cv", null);
            response.put("exp_pro", null);
            response.put("formation", null);
            response.put("competence", null);
            response.put("langue", null);
            response.put("certificat", null);
            responseBody.setValidate(false);
            responseBody.setMessage("Impossible de faire la mise à jour cause:");
            responseBody.setErreur_mssg(exp.getMessage());
        }
        responseBody.setData(response);
        return responseBody;
    }
    
    @CrossOrigin(origins = "*")
    @GetMapping(path = "/get", produces = "application/json;charset=utf8")
    public BaseQuery<Map<String, Object>> getResume(@RequestParam String api_token, @RequestParam String cv_uid) {
        BaseQuery<Map<String, Object>> responseBody = new BaseQuery<Map<String, Object>>();
        Map<String, Object> response = new HashMap<>();

        try {
            User user = this.userRepository.getUserByApiToken(api_token);
            // Cv cv = null;
            Cv cv= this.cvRepository.getOne(cv_uid);
            List<ExpPro> real_exp_pro =  this.expProRepository.getActivesExPro(cv_uid,true);
            List<Formation> real_formation=  this.formationRepository.getActivesFormation(cv_uid,true);
            List<Competence> real_competence =  this.competenceRepository.getActivesCompetence(cv_uid,true);
            List<Langue> real_langue =  this.langueRepository.getActivesLangue(cv_uid,true);
            List<Certificat> real_certificat =  this.certificatRepository.getActivesCertificat(cv_uid,true);
            response.put("cv", cv);
            response.put("exp_pro", real_exp_pro);
            response.put("formation", real_formation);
            response.put("competence", real_competence);
            response.put("langue", real_langue);
            response.put("certificat", real_certificat);
            responseBody.setValidate(true);
        } catch (Exception exp) {
            response.put("cv", null);
            response.put("exp_pro", null);
            response.put("formation", null);
            response.put("competence", null);
            response.put("langue", null);
            response.put("certificat", null);
            responseBody.setValidate(false);
            responseBody.setMessage("Impossible de charger les informations cause:");
            responseBody.setErreur_mssg(exp.getMessage());
        }
        responseBody.setData(response);
        return responseBody;
    }

}