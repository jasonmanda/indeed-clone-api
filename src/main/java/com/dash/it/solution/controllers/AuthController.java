package com.dash.it.solution.controllers;

// import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
// import org.springframework.web.servlet.ModelAndView;

import com.dash.it.solution.entities.Certificat;
import com.dash.it.solution.entities.Competence;
import com.dash.it.solution.entities.Contact;
import com.dash.it.solution.entities.Cv;
import com.dash.it.solution.entities.Document;
import com.dash.it.solution.entities.Entreprise;
import com.dash.it.solution.entities.ExpPro;
import com.dash.it.solution.entities.Formation;
import com.dash.it.solution.entities.Grants;
import com.dash.it.solution.entities.Image;
import com.dash.it.solution.entities.Langue;
import com.dash.it.solution.entities.Role;
// import com.dash.it.solution.entities.Personne;
import com.dash.it.solution.entities.User;
import com.dash.it.solution.entities.UserRole;
import com.dash.it.solution.entities.Ville;
import com.dash.it.solution.repository.CertificatRepository;
import com.dash.it.solution.repository.CompetenceRepository;
import com.dash.it.solution.repository.ContactRepository;
import com.dash.it.solution.repository.CvRepository;
import com.dash.it.solution.repository.DocumentRepository;
import com.dash.it.solution.repository.EntrepriseRepository;
import com.dash.it.solution.repository.ExpProRepository;
import com.dash.it.solution.repository.FormationRepository;
import com.dash.it.solution.repository.ImageRepository;
import com.dash.it.solution.repository.LangueRepository;
import com.dash.it.solution.repository.PersonneRepository;
// import com.dash.it.solution.exception.ResourceNotFoundException;
import com.dash.it.solution.repository.UserRepository;
import com.dash.it.solution.repository.UserRoleRepository;
import com.dash.it.solution.repository.VilleRepository;
import com.dash.it.solution.viewmodel.AuthCredentials;
import com.dash.it.solution.viewmodel.BaseQuery;
import com.dash.it.solution.viewmodel.Resume;
import com.dash.it.solution.viewmodel.Subscribe;
import com.dash.it.solution.repository.RoleRepository;

import java.util.Date;
// import java.util.Date;
import java.util.HashMap;
import java.util.List;
// import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

/**
 * The type Auth controller.
 *
 * @author Jason Mandabrandja
 */
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EntrepriseRepository entrepriseRepository;
    @Autowired
    private VilleRepository villeRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private PersonneRepository personneRepository;
    @Autowired
    private CvRepository cvRepository;
    @Autowired
    private ExpProRepository expProRepository;
    @Autowired
    private FormationRepository formationRepository;
    @Autowired
    private CompetenceRepository competenceRepository;
    @Autowired
    private LangueRepository langueRepository;
    @Autowired
    private CertificatRepository certificatRepository;
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;

    /**
     * Login user .
     *
     * @param user
     * @return the user
     */
    @CrossOrigin(origins = "*")
    @PostMapping(path = "/login", consumes = "application/json;charset=utf8", produces = "application/json;charset=utf8")
    public BaseQuery<Map<String, Object>> login(@Valid @RequestBody AuthCredentials user) {
        BaseQuery<Map<String, Object>> responseBody = new BaseQuery<Map<String, Object>>();
        // @Valid @RequestBody User user
        // @RequestParam String id ?id={id}
        /// {etat_utilisateur}/{remember} @PathVariable boolean
        // etat_utilisateur,@PathVariable boolean remember,
        Map<String, Object> response = new HashMap<>();
        try {
            User current_user = this.userRepository.login(user.getEmail(), user.getPassword(), true);
            Entreprise ent= new Entreprise();
            if (!(current_user == null)) {


                UUID api_token = UUID.randomUUID();
                current_user.setApi_token(api_token.toString());
                this.userRepository.save(current_user);
                current_user.setPassword(null);

                response.put("utilisateur", current_user);
                Image img=null;
                Grants grants = new Grants();
                    Resume resume = new Resume();
                    Contact contact =null;

                
                UserRole userRole = this.userRoleRepository.getUserActiveRole(current_user.getId_utilisateur(), true);
                grants.setRole(userRole.getRole());
                if (userRole.getRole().getRole_name().equals("ROLE_PROFESSIONNEL")) {
                    img = this.imageRepository.getActivePersonneImage(current_user.getPersonne().getId_personne(), true,false,false);
                    ent.setPersonne(current_user.getPersonne());
                    Cv cv=this.cvRepository.getCvByPersonneId(current_user.getPersonne().getId_personne());
                    resume.setCv(cv);
                    List<ExpPro> exp_pro=this.expProRepository.getActivesExPro(cv.getCv_uid(), true);
                    resume.setExp_pro(exp_pro);                    
                    List<Formation> formation=this.formationRepository.getActivesFormation(cv.getCv_uid(), true);
                    resume.setFormation(formation);                    
                    List<Competence> competence=this.competenceRepository.getActivesCompetence(cv.getCv_uid(), true);
                    resume.setCompetence(competence);
                    List<Langue> langue=this.langueRepository.getActivesLangue(cv.getCv_uid(), true);
                    resume.setLangue(langue);
                    List<Certificat> certificat=this.certificatRepository.getActivesCertificat(cv.getCv_uid(), true);
                    resume.setCertificat(certificat);
                    response.put("contact",null);
                } else if (userRole.getRole().getRole_name().equals("ROLE_ENTREPRISE")) {
                    ent=  this.entrepriseRepository.getEntrepriseByIdPersonne(current_user.getPersonne().getId_personne());
                    img = this.imageRepository.getActiveEntrepriseImage( ent.getId_entreprise(), true,true,false);
                    response.put("contact",ent.getContact());
                }
                
                response.put("personne", ent);
                response.put("document",resume);
                response.put("contact",contact);
                response.put("grants", grants);
                responseBody.setValidate(true);

            } else {
                response.put("utilisateur", null);
                response.put("personne", null);
                response.put("image", null);
                response.put("document", null); 
                response.put("contact", null); 
                response.put("grants", null);
                responseBody.setMessage("Impossible de se connecter cause:");
                responseBody.setErreur_mssg("Les données de connexions sont invalides ou le compte n'existe pas");
                responseBody.setValidate(false);

            }

        } catch (Exception exp) {
            response.put("utilisateur", null);
            response.put("personne", null);
            response.put("document", null);  
            response.put("image", null);
            response.put("grants", null);
            responseBody.setValidate(false);
            responseBody.setMessage("Impossible de se connecter cause:");
            responseBody.setErreur_mssg(exp.getMessage());
        }

        responseBody.setData(response);
        return responseBody;
    }

    /**
     * Login user .
     *
     * @param user
     * @return the user
     */
    @CrossOrigin(origins = "*")
    @GetMapping(path = "/subscribe/email/exists", produces = "application/json;charset=utf8")
    public BaseQuery<Map<String, Object>> emailExists(@RequestParam String email, @RequestParam String type,
            @RequestParam Long id) {
        BaseQuery<Map<String, Object>> responseBody = new BaseQuery<Map<String, Object>>();
    
 
        // @Valid @RequestBody User user
        // @RequestParam String id ?id={id}
        /// {etat_utilisateur}/{remember} @PathVariable boolean
        // etat_utilisateur,@PathVariable boolean remember,
        Map<String, Object> response = new HashMap<>();
        try {
            if (type.equals("add")) {
                User current_user = this.userRepository.uniqueEmailAdd(email);
                if (current_user == null) {
                    response.put("exists", false);
                    responseBody.setValidate(true);

                } else {
                    response.put("exists", true);
                    responseBody.setValidate(false);
                    responseBody.setMessage("Impossible de vérifier l'email cause:");
                    responseBody.setErreur_mssg("L'email existe déjà");
                }

            } else {
                User current_user = this.userRepository.uniqueEmailEdit(email, id);
                if (current_user == null) {
                    response.put("exists", false);
                    responseBody.setValidate(true);

                } else {
                    response.put("exists", true);
                    responseBody.setValidate(false);
                    responseBody.setMessage("Impossible de vérifier l'email cause:");
                    responseBody.setErreur_mssg("L'email est déjà pris par un autre compte");
                }
            }

        } catch (Exception exp) {

            response.put("exists", false);
            responseBody.setValidate(false);
            responseBody.setMessage("Impossible de vérifier l'email cause:");
            responseBody.setErreur_mssg(exp.getMessage());
        }

        responseBody.setData(response);
        return responseBody;
    }

    /**
     * Login user .
     *
     * @param user
     * @return the user
     */
    @CrossOrigin(origins = "*")
    @GetMapping(path = "/subscribe/nom_ent/exists", produces = "application/json;charset=utf8")
    public BaseQuery<Map<String, Boolean>> nomEntExists(@RequestParam String nom_ent, @RequestParam String type,
            @RequestParam Long id) {
        BaseQuery<Map<String, Boolean>> responseBody = new BaseQuery<Map<String, Boolean>>();
      
        // @Valid @RequestBody User user
        // @RequestParam String id ?id={id}
        /// {etat_utilisateur}/{remember} @PathVariable boolean
        // etat_utilisateur,@PathVariable boolean remember,
        Map<String, Boolean> response = new HashMap<>();
        try {
            if (type.equals("add")) {
                Entreprise current_nom_ent = this.entrepriseRepository.uniqueNomEntAdd(nom_ent);
                if (current_nom_ent == null) {
                    response.put("exists", false);
                    responseBody.setValidate(true);

                } else {
                    response.put("exists", true);
                    responseBody.setValidate(false);
                    responseBody.setMessage("Impossible de vérifier le nom de l'entreprise cause:");
                    responseBody.setErreur_mssg("Le nom de l'entreprise existe déjà");
                }

            } else {
                Entreprise current_nom_ent = this.entrepriseRepository.uniqueNomEntEdit(nom_ent, id);
                if (current_nom_ent == null) {
                    response.put("exists", false);
                    responseBody.setValidate(true);

                } else {
                    response.put("exists", true);
                    responseBody.setValidate(false);
                    responseBody.setMessage("Impossible de vérifier le nom de l'entreprise cause:");
                    responseBody.setErreur_mssg("Le nom de l'entreprise est déjà pris par un autre compte");
                }
            }

        } catch (Exception exp) {

            response.put("exists", false);
            responseBody.setValidate(false);
            responseBody.setMessage("Impossible de vérifier l'email cause:");
            responseBody.setErreur_mssg(exp.getMessage());
        }

        responseBody.setData(response);
        return responseBody;
    }

    /**
     * Subscribe user .
     *
     * @param user
     * @return the user
     */
    @CrossOrigin(origins = "*")
    @PostMapping(path = "/subscribe", consumes = "application/json;charset=utf8", produces = "application/json;charset=utf8")
    public BaseQuery<Map<String, Object>> subscribe(@RequestParam String type,
            @Valid @RequestBody Subscribe subscribe) {
        BaseQuery<Map<String, Object>> responseBody = new BaseQuery<Map<String, Object>>();
        // @Valid @RequestBody User user
        // @RequestParam String id ?id={id}
        /// {etat_utilisateur}/{remember} @PathVariable boolean
        // etat_utilisateur,@PathVariable boolean remember,
        Map<String, Object> response = new HashMap<>();
        try {
            Ville ville = null;

            if (type.equals("entreprise")) {
                ville = this.villeRepository.getVilleByName(subscribe.getEntreprise().getVille().getNom_ville());
                if (ville == null) {
                    ville=new Ville();

                    ville.setNom_ville(subscribe.getEntreprise().getVille().getNom_ville());
                    ville.setEtat_ville(true);
                    this.villeRepository.save(ville);
                    subscribe.getEntreprise().setVille(ville);
                }else{
                    subscribe.getEntreprise().setVille(ville);

                }
            } else {
                ville = this.villeRepository
                        .getVilleByName(subscribe.getEntreprise().getPersonne().getVille().getNom_ville());
                if (ville == null) {
                    ville=new Ville();

                    ville.setNom_ville(subscribe.getEntreprise().getPersonne().getVille().getNom_ville());
                    ville.setEtat_ville(true);
                    this.villeRepository.save(ville);
                    subscribe.getEntreprise().getPersonne().setVille(ville);   
                 }else{
                    subscribe.getEntreprise().getPersonne().setVille(ville); 
                }
            }
            Role role = this.roleRepository.getRoleByName(subscribe.getGrants().getRole().getRole_name());
            if (role == null) {
                throw new Exception("Erreur impossible role indéfinie");
            } else {
                subscribe.getGrants().setRole(role);

            }
            User current_user = new User();
            current_user.setEmail(subscribe.getUser().getEmail());
            current_user.setPassword(subscribe.getUser().getPassword());
            current_user.setEtat_utilisateur(true);
            UserRole userRole = new UserRole();
            userRole.setRole(subscribe.getGrants().getRole());
            userRole.setUser(current_user);

            UUID api_token = UUID.randomUUID();
            current_user.setApi_token(api_token.toString());
            Image img = null;
            if (subscribe.getImage() == null) {

                if (type.equals("entreprise")) {
                    this.personneRepository.save(subscribe.getEntreprise().getPersonne());
                    subscribe.getEntreprise().setEtat_entreprise(true);
                    subscribe.getEntreprise().getContact().setEtat_contact(true);
                    this.contactRepository.save(subscribe.getEntreprise().getContact());
                    this.entrepriseRepository.save(subscribe.getEntreprise());
 
                } else {
                    this.personneRepository.save(subscribe.getEntreprise().getPersonne());
                    Cv oldCv=new Cv();
                    oldCv.setPersonne(subscribe.getEntreprise().getPersonne());
                        boolean enter=false;
                    do{
                        try{
                        UUID uuid=UUID.randomUUID();
                            oldCv.setCv_uid(uuid.toString());
                            this.cvRepository.save(oldCv);
                            enter=true;
                        }catch(Exception exp){
                            enter=false;
                        }
                    }while(!enter);
                }

            } else {

                img = new Image();

                img.setOriginal_name(subscribe.getImage().getOriginal_name());
                img.setPath_image(subscribe.getImage().getPath_image());
                img.setIs_temp(false);

                if (type.equals("entreprise")) {
                    img.setIs_logo(true);
                    this.personneRepository.save(subscribe.getEntreprise().getPersonne());
                    subscribe.getEntreprise().setEtat_entreprise(true);
                    subscribe.getEntreprise().getContact().setEtat_contact(true);
                    this.contactRepository.save(subscribe.getEntreprise().getContact());

                    this.entrepriseRepository.save(subscribe.getEntreprise());
                    img.setEntreprise(subscribe.getEntreprise());
                    

                } else {
                    img.setIs_logo(false);
                    this.personneRepository.save(subscribe.getEntreprise().getPersonne());
                    img.setPersonne(subscribe.getEntreprise().getPersonne());
                    Cv oldCv=new Cv();
                        oldCv.setPersonne(subscribe.getEntreprise().getPersonne());
                            boolean enter=false;
                        do{
                            try{
                            UUID uuid=UUID.randomUUID();
                                oldCv.setCv_uid(uuid.toString());
                                this.cvRepository.save(oldCv);
                                enter=true;
                            }catch(Exception exp){
                                enter=false;
                            }
                        }while(!enter);
                    
            

                }
                subscribe.getImage().setEtat_image(false);
                subscribe.setImage(this.imageRepository.save(subscribe.getImage()));
                img.setEtat_image(true);
                img = this.imageRepository.save(img);
            }
            
            current_user.setPersonne(subscribe.getEntreprise().getPersonne());
            this.userRepository.save(current_user);
            userRole.setEtat_user_role(true);
            this.userRoleRepository.save(userRole);
            current_user.setPassword(null);

            response.put("personne", subscribe.getEntreprise());
            response.put("utilisateur", current_user);
            response.put("image", img);
            response.put("contact", subscribe.getEntreprise().getContact());
            response.put("grants", subscribe.getGrants());

            responseBody.setValidate(true);

        } catch (Exception exp) {
            response.put("utilisateur", null);
            response.put("personne", null);
            response.put("image", null);
            response.put("contact", null);
            response.put("grants", null);
            responseBody.setValidate(false);
            responseBody.setMessage("Impossible d'inscrire cause:");
            responseBody.setErreur_mssg(exp.getMessage());
        }

        responseBody.setData(response);
        return responseBody;
    }

}
