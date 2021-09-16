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
import com.dash.it.solution.entities.Favoris;
import com.dash.it.solution.entities.Formation;
import com.dash.it.solution.entities.Langue;
import com.dash.it.solution.entities.Offre;
import com.dash.it.solution.entities.Postuler;
import com.dash.it.solution.entities.User;
import com.dash.it.solution.entities.Ville;
import com.dash.it.solution.repository.CertificatRepository;
import com.dash.it.solution.repository.CompetenceRepository;
import com.dash.it.solution.repository.ContactRepository;
import com.dash.it.solution.repository.CvRepository;
import com.dash.it.solution.repository.ExpProRepository;
import com.dash.it.solution.repository.FavorisRepository;
import com.dash.it.solution.repository.FormationRepository;
import com.dash.it.solution.repository.LangueRepository;
import com.dash.it.solution.repository.OffreRepository;
import com.dash.it.solution.repository.PostulerRepository;
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
 * The type Offre controller.
 *
 * @author Jason Mandabrandja
 */
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/offre")
public class OffreController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OffreRepository offreRepository;
    @Autowired
    private CvRepository cvRepository;
    @Autowired
    private PostulerRepository postulerRepository;
    @Autowired
    private FavorisRepository favorisRepository;
    @Autowired
    private VilleRepository villeRepository;

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/save", produces = "application/json;charset=utf8")
    public BaseQuery<Offre> offreSave(@Valid @RequestBody Offre offre, @RequestParam Boolean is_ent,
            @RequestParam String api_token) {
        BaseQuery<Offre> responseBody = new BaseQuery<Offre>();
        Map<String, Object> response = new HashMap<>();

        try {
            Ville ville = null;
            User user = this.userRepository.getUserByApiToken(api_token);
            // Cv cv = null;
            if (is_ent) {
                // cv =
                // this.cvRepository.getCvByPersonneId(user.getPersonne().getId_personne());
                offre.setEntreprise(null);
            } else {
                offre.setPersonne(null);

            }
            ville = this.villeRepository.getVilleByName(offre.getLocalite().getNom_ville());
            if (ville == null) {
                offre.getLocalite().setEtat_ville(true);
                this.villeRepository.save(offre.getLocalite());
            } else {
                offre.setLocalite(ville);

            }
            this.offreRepository.save(offre);

            responseBody.setValidate(true);
        } catch (Exception exp) {
            response.put("offre", null);
            responseBody.setValidate(false);
            responseBody.setMessage("Impossible de faire la mise à jour cause:");
            responseBody.setErreur_mssg(exp.getMessage());
        }
        responseBody.setData(offre);
        return responseBody;
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/list", produces = "application/json;charset=utf8")
    public BaseQuery<List<Offre>> offreList(@RequestParam String api_token, @RequestParam Boolean is_ent) {
        BaseQuery<List<Offre>> responseBody = new BaseQuery<List<Offre>>();
        List<Offre> offres = new ArrayList<>();

        try {
            User user = this.userRepository.getUserByApiToken(api_token);
            // Cv cv = null;
            if (is_ent) {
                offres = this.offreRepository.getActivesOffreForEnt(user.getPersonne().getId_personne(), true);
            } else{
                offres = this.offreRepository.getActivesOffreForPers(user.getPersonne().getId_personne(), true);

            }
            responseBody.setValidate(true);
        } catch (Exception exp) {

            responseBody.setValidate(false);
            responseBody.setMessage("Impossible de charger les informations cause:");
            responseBody.setErreur_mssg(exp.getMessage());
        }
        responseBody.setData(offres);
        return responseBody;
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/get", produces = "application/json;charset=utf8")
    public BaseQuery<Map<String, Object>> offreGet(@RequestParam String api_token, @RequestParam Long id_offre) {
        BaseQuery<Map<String, Object>> responseBody = new BaseQuery<Map<String, Object>>();
        Offre offre = null;
        Map<String, Object> response = new HashMap<>();
        try {
            User user = this.userRepository.getUserByApiToken(api_token);
            // Cv cv = null;
            offre = this.offreRepository.getOne(id_offre);
            if (offre != null) {
                response.put("id_offre", offre.getId_offre());
                response.put("localite", offre.getLocalite());
                response.put("nom_ent", offre.getNom_ent());
                response.put("poste", offre.getPoste());
                response.put("salaire", offre.getSalaire());
                response.put("salaire_min", offre.getSalaire_min());
                response.put("salaire_max", offre.getSalaire_max());
                response.put("salaire_type", offre.getSalaire_type());
                response.put("taille", offre.getTaille());
                response.put("type_contrat", offre.getType_contrat());
                response.put("type_emp", offre.getType_emp());
                response.put("cab_recrutement", offre.isCab_recrutement());
                response.put("cv_require", offre.isCv_require());
                response.put("date_limit", offre.getDate_limit());
                response.put("date_debut", offre.getDate_debut());
                response.put("date_immediat", offre.isDate_immediat());
                response.put("etat_offre", offre.isEtat_offre());
                response.put("no_salaire", offre.isNo_salaire());
                response.put("salaire_model", offre.isSalaire_model());
                response.put("test_compet", offre.isTest_compet());
                response.put("description", offre.getDescription());
                response.put("entreprise", offre.getEntreprise());
                response.put("personne", offre.getPersonne());
                response.put("updated_at", offre.getUpdated_at());
                response.put("created_at", offre.getCreated_at());
                response.put("avantages", offre.getAvantages());
            }
            responseBody.setValidate(true);
        } catch (Exception exp) {

            responseBody.setValidate(false);
            responseBody.setMessage("Impossible de charger les informations cause:");
            responseBody.setErreur_mssg(exp.getMessage());
        }
        responseBody.setData(response);
        return responseBody;
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/delete", produces = "application/json;charset=utf8")
    public BaseQuery<Map<String, Object>> offreDelete(@RequestParam String api_token, @RequestParam Long id_offre) {
        BaseQuery<Map<String, Object>> responseBody = new BaseQuery<Map<String, Object>>();
        Offre offre = null;
        Map<String, Object> response = new HashMap<>();
        try {
            User user = this.userRepository.getUserByApiToken(api_token);
            // Cv cv = null;
            offre = this.offreRepository.getOne(id_offre);
            // if(offre!=null){
            // response.put("id_offre",offre.getId_offre());
            // response.put("localite",offre.getLocalite());
            // response.put("nom_ent",offre.getNom_ent());
            // response.put("poste",offre.getPoste());
            // response.put("salaire",offre.getSalaire());
            // response.put("salaire_min",offre.getSalaire_min());
            // response.put("salaire_max",offre.getSalaire_max());
            // response.put("salaire_type",offre.getSalaire_type());
            // response.put("taille",offre.getTaille());
            // response.put("type_contrat",offre.getType_contrat());
            // response.put("type_emp",offre.getType_emp());
            // response.put("cab_recrutement",offre.isCab_recrutement());
            // response.put("cv_require",offre.isCv_require());
            // response.put("date_limit",offre.getDate_limit());
            // response.put("date_debut",offre.getDate_debut());
            // response.put("date_immediat",offre.isDate_immediat());
            // response.put("etat_offre",offre.isEtat_offre());
            // response.put("no_salaire",offre.isNo_salaire());
            // response.put("salaire_model",offre.isSalaire_model());
            // response.put("test_compet",offre.isTest_compet());
            // response.put("description",offre.getDescription());
            // response.put("entreprise",offre.getEntreprise());
            // response.put("personne",offre.getPersonne());
            // response.put("updated_at",offre.getUpdated_at());
            // response.put("created_at",offre.getCreated_at());
            // response.put("avantages",offre.getAvantages());
            // }
            offre.setEtat_offre(false);
            this.offreRepository.save(offre);
            responseBody.setValidate(true);
        } catch (Exception exp) {

            responseBody.setValidate(false);
            responseBody.setMessage("Impossible de charger les informations cause:");
            responseBody.setErreur_mssg(exp.getMessage());
        }
        responseBody.setData(response);
        return responseBody;
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/postuler/list", produces = "application/json;charset=utf8")
    public BaseQuery<List<Postuler>> offrePostulerList(@RequestParam String api_token,@RequestParam String option, @RequestParam Long id_offre,@RequestParam String cv_uid) {
        BaseQuery<List<Postuler>> responseBody = new BaseQuery<List<Postuler>>();
        // Map<String, Object> response = new HashMap<>();
        List<Postuler> offres = new ArrayList<>();
        try {
            User user = this.userRepository.getUserByApiToken(api_token);
            // Cv cv = null;
            if (option.equals("model_1")) {
                offres = this.postulerRepository.getActivesPostulerForEnt(id_offre);
            } else if (option.equals("model_2")) {
                offres = this.postulerRepository.getActivesPostulerForPers(cv_uid);
            }  
 

            responseBody.setValidate(true);
        } catch (Exception exp) {

            responseBody.setValidate(false);
            responseBody.setMessage("Impossible de charger les informations cause:");
            responseBody.setErreur_mssg(exp.getMessage());
        }
        responseBody.setData(offres);
        return responseBody;
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/postuler/count", produces = "application/json;charset=utf8")
    public BaseQuery<Long> offrePostulerCount(@RequestParam String api_token,@RequestParam String option, @RequestParam Long id_offre,
            @RequestParam String cv_uid) {
        BaseQuery<Long> responseBody = new BaseQuery<Long>();
        // Map<String, Object> response = new HashMap<>();
        long offres = 0L;
        try {
            User user = this.userRepository.getUserByApiToken(api_token);
            // Cv cv = null;
            if (option.equals("model_1")) {
                offres =this.postulerRepository.getActivesPostulerForPersCount(cv_uid, id_offre);
             }else if (option.equals("model_2")) {
                offres =this.postulerRepository.getActivesPostulerForEntCount(id_offre);
             } else {
            }

            responseBody.setValidate(true);
        } catch (Exception exp) {

            responseBody.setValidate(false);
            responseBody.setMessage("Impossible de charger les informations cause:");
            responseBody.setErreur_mssg(exp.getMessage());
        }
        responseBody.setData(offres);
        return responseBody;
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/favoris/count", produces = "application/json;charset=utf8")
    public BaseQuery<Long> offreFavorisCount(@RequestParam String api_token,@RequestParam String option, @RequestParam Long id_offre,
            @RequestParam String cv_uid) {
        BaseQuery<Long> responseBody = new BaseQuery<Long>();
        // Map<String, Object> response = new HashMap<>();
        long offres = 0L;
        try {
            User user = this.userRepository.getUserByApiToken(api_token);
            // Cv cv = null;
            if (option.equals("model_1")) {
                offres =this.favorisRepository.getActivesFavorisForPersCount(cv_uid, id_offre,true);
             } else {
            }

            responseBody.setValidate(true);
        } catch (Exception exp) {

            responseBody.setValidate(false);
            responseBody.setMessage("Impossible de charger les informations cause:");
            responseBody.setErreur_mssg(exp.getMessage());
        }
        responseBody.setData(offres);
        return responseBody;
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/search", produces = "application/json;charset=utf8")
    public BaseQuery<List<Offre>> offreSearch(@RequestParam String api_token, @RequestParam(defaultValue = "") String where,
            @RequestParam(defaultValue = "") String what) {
        BaseQuery<List<Offre>> responseBody = new BaseQuery<List<Offre>>();
        List<Offre> offres = new ArrayList<>();

        try {
            User user = this.userRepository.getUserByApiToken(api_token);
            // Cv cv = null;
            if ((where.equals(null) || where.equals("") || where.equals("null") || where.equals("undefined")) && (what.equals(null) || what.equals("")  || what.equals("null") || what.equals("undefined"))) {
                offres = this.offreRepository.getAllOffres(true);
            } else {
                List<Offre> offres1 = new ArrayList<Offre>();
                List<Offre> offres2 = new ArrayList<Offre>();
                List<Offre> offres3 = new ArrayList<Offre>();

                if ((what.equals(null) || what.equals("null") || what.equals("undefined"))) {
                    offres1 = this.offreRepository.getAllOffres(true);
                    offres2 = this.offreRepository.getAllOffres(true);
                } else {
                    offres1 = this.offreRepository.getOffreByPoste(what, true);
                    offres2 = this.offreRepository.getOffreByDescription(what, true);

                }

                if ((where.equals(null) || where.equals("null") || where.equals("undefined"))) {
                    offres3 = this.offreRepository.getAllOffres(true);
                } else {
                    offres3 = this.offreRepository.getOffreByVille(where, true);
                }
                offres.addAll(offres1);
                for (Offre i : offres2) {
                    boolean enter = false;
                    for (Offre j : offres) {
                        if (j.getId_offre() == i.getId_offre()) {
                            enter = true;
                            break;
                        }
                    }
                    if (!enter) {
                        offres.add(i);
                    }

                }
                for (Offre i : offres3) {
                    boolean enter = false;
                    for (Offre j : offres) {
                        if (j.getId_offre() == i.getId_offre()) {
                            enter = true;
                            break;
                        }
                    }
                    if (!enter) {
                        offres.add(i);
                    }

                }
            }

            responseBody.setValidate(true);
        } catch (Exception exp) {

            responseBody.setValidate(false);
            responseBody.setMessage("Impossible de charger les informations cause:");
            responseBody.setErreur_mssg(exp.getMessage());
        }
        responseBody.setData(offres);
        return responseBody;
    }

    
    @CrossOrigin(origins = "*")
    @PostMapping(path = "/postuler/save", produces = "application/json;charset=utf8")
    public BaseQuery<Map<String, Object>> offrePostulerSave(@RequestParam String cv_uid,@RequestParam long id_offre,@RequestParam String api_token) {
        BaseQuery<Map<String, Object>> responseBody = new BaseQuery<Map<String, Object>>();
        Map<String, Object> response = new HashMap<>();
        Postuler postuler=null;
        try {
       
            User user = this.userRepository.getUserByApiToken(api_token);
            // Cv cv = null;
           Cv cv= this.cvRepository.getOne(cv_uid);
           Offre offre= this.offreRepository.getOne(id_offre);
           postuler=new Postuler();
           postuler.setCv(cv);
           postuler.setOffre(offre);;
            this.postulerRepository.save(postuler);
         

            responseBody.setValidate(true);
        } catch (Exception exp) {
            response.put("postuler", null);
            responseBody.setValidate(false);
            responseBody.setMessage("Impossible de faire la mise à jour cause:");
            responseBody.setErreur_mssg(exp.getMessage());
        }
        responseBody.setData(response);
        return responseBody;
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/favoris/save", produces = "application/json;charset=utf8")
    public BaseQuery<Map<String, Object>> offreFavorisSave(@RequestParam String option,@RequestParam String cv_uid,@RequestParam long id_offre,@RequestParam String api_token) {
        BaseQuery<Map<String, Object>> responseBody = new BaseQuery<Map<String, Object>>();
        Map<String, Object> response = new HashMap<>();
        Favoris favoris=null;
        try {
       
            User user = this.userRepository.getUserByApiToken(api_token);
            // Cv cv = null;
           Cv cv= this.cvRepository.getOne(cv_uid);
           Offre offre= this.offreRepository.getOne(id_offre);
           if(option.equals("add")){
            favoris=new Favoris();
            favoris.setCv(cv);
            favoris.setOffre(offre);
            favoris.setEtat_favoris(true);
            this.favorisRepository.save(favoris);
            
           }else if(option.equals("remove")){
            favoris=this.favorisRepository.getActiveFavorisForPersCount(cv_uid, id_offre, true);
            favoris.setEtat_favoris(false);
            this.favorisRepository.save(favoris);
            }

         

            responseBody.setValidate(true);
        } catch (Exception exp) {
            response.put("postuler", null);
            responseBody.setValidate(false);
            responseBody.setMessage("Impossible de faire la mise à jour cause:");
            responseBody.setErreur_mssg(exp.getMessage());
        }
        responseBody.setData(response);
        return responseBody;
    }
    @CrossOrigin(origins = "*")
    @PostMapping(path = "/postuler/retenue", produces = "application/json;charset=utf8")
    public BaseQuery<Map<String,Boolean>> offrePostulerRetenue(@Valid @RequestBody List<Postuler> postuler,
            @RequestParam String api_token) {
        BaseQuery<Map<String,Boolean>> responseBody = new BaseQuery<Map<String,Boolean>>();
        Map<String,Boolean> response = new HashMap<>();

        try {
            Ville ville = null;
            User user = this.userRepository.getUserByApiToken(api_token);
            for(Postuler p:postuler) {
                p.setRetenue(true);
        
                this.postulerRepository.save(p);

            }
            
            response.put("status", true);
            responseBody.setValidate(true);
        } catch (Exception exp) {
            response.put("status", false);
            responseBody.setValidate(false);
            responseBody.setMessage("Impossible de faire la mise à jour cause:");
            responseBody.setErreur_mssg(exp.getMessage());
        }
        responseBody.setData(response);
        return responseBody;
    }


    
    @CrossOrigin(origins = "*")
    @GetMapping(path = "/favoris", produces = "application/json;charset=utf8")
    public BaseQuery<List<Favoris>> offreFavoris(@RequestParam String api_token) {
        BaseQuery<List<Favoris>> responseBody = new BaseQuery<List<Favoris>>();
        List<Favoris> favoris = new ArrayList<>();

        try {
            User user = this.userRepository.getUserByApiToken(api_token);
            // Cv cv = null;
       
            favoris = this.favorisRepository.getActivesFavorisByIdPers(user.getPersonne().getId_personne(), true);
          
            responseBody.setValidate(true);
        } catch (Exception exp) {

            responseBody.setValidate(false);
            responseBody.setMessage("Impossible de charger les informations cause:");
            responseBody.setErreur_mssg(exp.getMessage());
        }
        responseBody.setData(favoris);
        return responseBody;
    }
    @CrossOrigin(origins = "*")
    @GetMapping(path = "/soumission", produces = "application/json;charset=utf8")
    public BaseQuery<List<Postuler>> offrePostulerList(@RequestParam String api_token) {
        BaseQuery<List<Postuler>> responseBody = new BaseQuery<List<Postuler>>();
        // Map<String, Object> response = new HashMap<>();
        List<Postuler> postuler = new ArrayList<>();
        try {
            User user = this.userRepository.getUserByApiToken(api_token);
            // Cv cv = null;
         
          postuler = this.postulerRepository.getActivesPostulerByIdPers(user.getPersonne().getId_personne());
            

            responseBody.setValidate(true);
        } catch (Exception exp) {

            responseBody.setValidate(false);
            responseBody.setMessage("Impossible de charger les informations cause:");
            responseBody.setErreur_mssg(exp.getMessage());
        }
        responseBody.setData(postuler);
        return responseBody;
    }
}