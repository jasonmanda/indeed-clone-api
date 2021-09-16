package com.dash.it.solution.controllers;

// import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
// import org.springframework.web.servlet.ModelAndView;

import com.dash.it.solution.viewmodel.AuthCredentials;
import com.dash.it.solution.viewmodel.BaseQuery;
import com.dash.it.solution.viewmodel.Resume;
import com.dash.it.solution.viewmodel.UtilisateurViewModel;
import com.dash.it.solution.entities.Certificat;
import com.dash.it.solution.entities.Competence;
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
import com.dash.it.solution.repository.EntrepriseRepository;
import com.dash.it.solution.repository.ExpProRepository;
import com.dash.it.solution.repository.FormationRepository;
import com.dash.it.solution.repository.ImageRepository;
import com.dash.it.solution.repository.LangueRepository;
import com.dash.it.solution.repository.PersonneRepository;
import com.dash.it.solution.repository.RoleRepository;
// import com.dash.it.solution.exception.ResourceNotFoundException;
import com.dash.it.solution.repository.UserRepository;
import com.dash.it.solution.repository.UserRoleRepository;
import com.dash.it.solution.repository.VilleRepository;
import com.dash.it.solution.repository.CertificatRepository;
import com.dash.it.solution.repository.CompetenceRepository;
import com.dash.it.solution.repository.ContactRepository;
import com.dash.it.solution.repository.CvRepository;
import com.dash.it.solution.repository.DocumentRepository;

// import java.util.Date;
import java.util.HashMap;
import java.util.List;
// import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

/**
 * The type User controller.
 *
 * @author Jason Mandabrandja
 */
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private EntrepriseRepository entrepriseRepository;

  @Autowired
  private ImageRepository imageRepository;
  @Autowired
  private RoleRepository roleRepository;
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
  private VilleRepository villeRepository;
  @Autowired
  private UserRoleRepository userRoleRepository;

  /**
   * Subscribe user .
   *
   * @param user
   * @return the user
   */
  @CrossOrigin(origins = "*")
  @GetMapping(path = "/user/update_info", produces = "application/json;charset=utf8")
  public BaseQuery<Map<String, Object>> updateInfo(@RequestParam String api_token) {
    BaseQuery<Map<String, Object>> responseBody = new BaseQuery<Map<String, Object>>();
    // @Valid @RequestBody User user
    // @RequestParam String id ?id={id}
    /// {etat_utilisateur}/{remember} @PathVariable boolean
    // etat_utilisateur,@PathVariable boolean remember,
    Map<String, Object> response = new HashMap<>();
    try {

      User user = this.userRepository.getUserByApiToken(api_token);

      UserRole userRole = this.userRoleRepository.getUserActiveRole(user.getId_utilisateur(), true);
      Role role = userRole.getRole();

      Document document = null;

      if (user == null)
        throw new Exception("Impossible car l'utilisateur n'existe pas");
      user.setPassword(null);
      response.put("utilisateur", user);
      Image img = null;
      Resume resume = new Resume();

      if (role.getRole_name().equals("ROLE_PROFESSIONNEL")) {

        img = this.imageRepository.getActivePersonneImage(user.getPersonne().getId_personne(), true, false, false);
        Cv cv = this.cvRepository.getCvByPersonneId(user.getPersonne().getId_personne());
        resume.setCv(cv);
        List<ExpPro> exp_pro = this.expProRepository.getActivesExPro(cv.getCv_uid(), true);
        resume.setExp_pro(exp_pro);
        List<Formation> formation = this.formationRepository.getActivesFormation(cv.getCv_uid(), true);
        resume.setFormation(formation);
        List<Competence> competence = this.competenceRepository.getActivesCompetence(cv.getCv_uid(), true);
        resume.setCompetence(competence);
        List<Langue> langue = this.langueRepository.getActivesLangue(cv.getCv_uid(), true);
        resume.setLangue(langue);
        List<Certificat> certificat = this.certificatRepository.getActivesCertificat(cv.getCv_uid(), true);
        resume.setCertificat(certificat);
      } else if (role.getRole_name().equals("ROLE_ENTREPRISE")) {

        Entreprise ent = this.entrepriseRepository.getEntrepriseByIdPersonne(user.getPersonne().getId_personne());
        img = this.imageRepository.getActiveEntrepriseImage(ent.getId_entreprise(), true, true, false);

      } else {
        throw new Exception("Impossbile type de compte non définie");
      }

      response.put("image", img);

      Map<String, Object> v_grants = new HashMap<>();
      v_grants.put("role", role);
      response.put("grants", v_grants);
      Entreprise ent = null;

      if (role.getRole_name().equals("ROLE_PROFESSIONNEL")) {

        ent = new Entreprise();
        ent.setPersonne(user.getPersonne());
        response.put("contact", null);

      } else {

        ent = this.entrepriseRepository.getEntrepriseByIdPersonne(user.getPersonne().getId_personne());
        response.put("contact", ent.getContact());
      }
      response.put("personne", ent);
      response.put("document", resume);
      responseBody.setValidate(true);

    } catch (Exception exp) {
      response.put("utilisateur", null);
      response.put("personne", null);
      response.put("document", null);
      response.put("contact", null);
      response.put("image", null);
      response.put("grants", null);
      responseBody.setValidate(false);
      responseBody.setMessage("Impossible de faire la mise à jour cause:");
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
  @PostMapping(path = "/user/update", produces = "application/json;charset=utf8")
  public BaseQuery<Map<String, Object>> userUpdate(@Valid @RequestBody UtilisateurViewModel utilisateurViewModel,
      @RequestParam String api_token, @RequestParam String type, @RequestParam String option) {
    BaseQuery<Map<String, Object>> responseBody = new BaseQuery<Map<String, Object>>();
    // @Valid @RequestBody User user
    // @RequestParam String id ?id={id}
    /// {etat_utilisateur}/{remember} @PathVariable boolean
    // etat_utilisateur,@PathVariable boolean remember,
    Map<String, Object> response = new HashMap<>();
    try {

      User user = this.userRepository.getUserByApiToken(api_token);
      if (type.equals("entreprise")) {
        if (option.equals("user_password")) {
          if (user.getEmail().equals(utilisateurViewModel.getUtilisateur().getEmail())
              && user.getPassword().equals(utilisateurViewModel.getOldPwd())) {
            user.setEmail(utilisateurViewModel.getUtilisateur().getEmail());
            user.setPassword(utilisateurViewModel.getNewPwd());
            this.userRepository.save(user);

          } else {
            throw new Exception("Erreur mot de passe ne corrrespond pas");
          }
        } else if (option.equals("user_grants_image")) {
          Role role = this.roleRepository.getRoleByName(utilisateurViewModel.getGrants().getRole().getRole_name());
          if (role == null) {
            throw new Exception("Erreur impossible role indéfinie");
          } else {
            utilisateurViewModel.getGrants().setRole(role);

          }

          UserRole userRole = new UserRole();
          for(UserRole i:this.userRoleRepository.getAllRoleUser(user.getId_utilisateur(),true)){
            i.setEtat_user_role(false);
            this.userRoleRepository.save(i);
          }
          userRole.setRole(utilisateurViewModel.getGrants().getRole());
          userRole.setUser(user);
          userRole.setEtat_user_role(true);
          this.userRoleRepository.save(userRole);

          Image img = null;
          
          this.entrepriseRepository.save(utilisateurViewModel.getEntreprise());

          if (utilisateurViewModel.getImage() != null) {

            img = new Image();

            img.setOriginal_name(utilisateurViewModel.getImage().getOriginal_name());
            img.setPath_image(utilisateurViewModel.getImage().getPath_image());
            img.setIs_temp(false);

            img.setIs_logo(true);

            img.setEntreprise(utilisateurViewModel.getEntreprise());

            utilisateurViewModel.getImage().setEtat_image(false);
            utilisateurViewModel.setImage(this.imageRepository.save(utilisateurViewModel.getImage()));
            img.setEtat_image(true);
            img = this.imageRepository.save(img);
          }
        }else if(option.equals("user_info")){
          Ville ville = null;
          ville = this.villeRepository.getVilleByName(utilisateurViewModel.getEntreprise().getVille().getNom_ville());
          if (ville == null) {
                ville=new Ville();

              ville.setNom_ville(utilisateurViewModel.getEntreprise().getVille().getNom_ville());
              ville.setEtat_ville(true);
              this.villeRepository.save(ville);
              utilisateurViewModel.getEntreprise().setVille(ville);
          }else{
              utilisateurViewModel.getEntreprise().setVille(ville);

          }

          this.personneRepository.save(utilisateurViewModel.getEntreprise().getPersonne());
      
          this.contactRepository.save(utilisateurViewModel.getEntreprise().getContact());
        }
      } else {
        if (option.equals("user_password")) {
          if (user.getEmail().equals(utilisateurViewModel.getUtilisateur().getEmail())
              && user.getPassword().equals(utilisateurViewModel.getOldPwd())) {
            user.setEmail(utilisateurViewModel.getUtilisateur().getEmail());
            user.setPassword(utilisateurViewModel.getNewPwd());
            this.userRepository.save(user);

          } else {
            throw new Exception("Erreur mot de passe ne corrrespond pas");
          }
        } else if (option.equals("user_grants_image")) {
          Role role = this.roleRepository.getRoleByName(utilisateurViewModel.getGrants().getRole().getRole_name());
          if (role == null) {
            throw new Exception("Erreur impossible role indéfinie");
          } else {
            utilisateurViewModel.getGrants().setRole(role);

          }

          UserRole userRole = new UserRole();
          for(UserRole i:this.userRoleRepository.getAllRoleUser(user.getId_utilisateur(),true)){
            i.setEtat_user_role(false);
            this.userRoleRepository.save(i);
          }
          userRole.setRole(utilisateurViewModel.getGrants().getRole());
          userRole.setUser(user);
          userRole.setEtat_user_role(true);
          this.userRoleRepository.save(userRole);

          Image img = null;

          if (utilisateurViewModel.getImage() != null) {

            img = new Image();

            img.setOriginal_name(utilisateurViewModel.getImage().getOriginal_name());
            img.setPath_image(utilisateurViewModel.getImage().getPath_image());
            img.setIs_temp(false);

            img.setIs_logo(false);

            img.setPersonne(utilisateurViewModel.getEntreprise().getPersonne());

            utilisateurViewModel.getImage().setEtat_image(false);
            utilisateurViewModel.setImage(this.imageRepository.save(utilisateurViewModel.getImage()));
            img.setEtat_image(true);
            img = this.imageRepository.save(img);
          }
        }else if(option.equals("user_info")){
          Ville ville = null;
                  ville = this.villeRepository
                  .getVilleByName(utilisateurViewModel.getEntreprise().getPersonne().getVille().getNom_ville());
          if (ville == null) {
               ville=new Ville();

              ville.setNom_ville(utilisateurViewModel.getEntreprise().getPersonne().getVille().getNom_ville());
              ville.setEtat_ville(true);
              this.villeRepository.save(ville);
              utilisateurViewModel.getEntreprise().getPersonne().setVille(ville);
            }else{

              utilisateurViewModel.getEntreprise().getPersonne().setVille(ville); 
          }
          this.personneRepository.save(utilisateurViewModel.getEntreprise().getPersonne());
        }

      }
 


      responseBody = this.updateInfo(api_token);

    } catch (Exception exp) {
      response.put("utilisateur", null);
      response.put("personne", null);
      response.put("document", null);
      response.put("contact", null);
      response.put("image", null);
      response.put("grants", null);
      responseBody.setValidate(false);
      responseBody.setMessage("Impossible de faire la mise à jour cause:");
      responseBody.setErreur_mssg(exp.getMessage());
      responseBody.setData(response);
    }

    return responseBody;
  }

}
