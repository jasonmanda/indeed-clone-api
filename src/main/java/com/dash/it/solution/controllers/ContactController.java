package com.dash.it.solution.controllers;

// import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
// import org.springframework.web.servlet.ModelAndView;

import com.dash.it.solution.viewmodel.AuthCredentials;
import com.dash.it.solution.viewmodel.BaseQuery;
import com.dash.it.solution.entities.Contact;
import com.dash.it.solution.entities.Grants;
import com.dash.it.solution.entities.Image;
import com.dash.it.solution.viewmodel.KeyValue;
// import com.dash.it.solution.entities.Personne;
import com.dash.it.solution.entities.User;
import com.dash.it.solution.entities.UserRole;
import com.dash.it.solution.repository.ContactRepository;
// import com.dash.it.solution.exception.ResourceNotFoundException;
import com.dash.it.solution.repository.UserRepository;
import com.dash.it.solution.repository.UserRoleRepository;

import java.util.ArrayList;
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
@RequestMapping("/api/contact")
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

 
    // @Valid @RequestBody Key user
    /**
     * Login user .
     *
     * @param user
     * @return the user
     */
    @CrossOrigin(origins = "*")
    @PostMapping(path = "/exists/multi", produces = "application/json;charset=utf8")
    public List<BaseQuery<Map<String, Boolean>>> contactExistsMulti(
        @RequestParam String type,
        @RequestParam Long id,
        @Valid @RequestBody List<KeyValue<String,String>> val
    ){
        List<BaseQuery<Map<String, Boolean>>> temp=new ArrayList<BaseQuery<Map<String, Boolean>>>();
            for(KeyValue<String,String> temps :val){
                temp.add(this.contactExists(type, temps.getKey(), temps.getValue(), id));

            }

        return temp;
    } 
 
    // @Valid @RequestBody Key user
    /**
     * Login user .
     *
     * @param user
     * @return the user
     */
     @CrossOrigin(origins = "*")
     @GetMapping(path = "/exists", produces = "application/json;charset=utf8")
    public BaseQuery<Map<String, Boolean>> contactExists(@RequestParam String type,
            @RequestParam String option,
            @RequestParam String link,
            @RequestParam Long id) {
        BaseQuery<Map<String, Boolean>> responseBody = new BaseQuery<Map<String, Boolean>>();
  
        // @Valid @RequestBody User user
        // @RequestParam String id ?id={id}
        /// {etat_utilisateur}/{remember} @PathVariable boolean
        // etat_utilisateur,@PathVariable boolean remember,
        Map<String, Boolean> response = new HashMap<>();
        try {
            if (type.equals("add") ) {
                if(option.equals("facebook")){
                    Contact current_contact = this.contactRepository.uniqueFacebookAdd(link);
                    if (current_contact == null) {
                        response.put("exists", false);
                        responseBody.setValidate(true);
                    } else {
                        response.put("exists", true);
                        responseBody.setValidate(false);
                        responseBody.setMessage("Impossible de vérifier le compte facebook cause:");
                        responseBody.setErreur_mssg("Le compte facebook existe déjà");
                    }
                    
                }else if(option.equals("linkedin")){
                    Contact current_contact = this.contactRepository.uniqueLinkedinAdd(link);
                    if (current_contact == null) {
                        response.put("exists", false);
                        responseBody.setValidate(true);
                    } else {
                        response.put("exists", true);
                        responseBody.setValidate(false);
                        responseBody.setMessage("Impossible de vérifier le compte linkedin cause:");
                        responseBody.setErreur_mssg("Le compte linkedin existe déjà");
                    }
                    
                }else if(option.equals("mail")){
                    Contact current_contact = this.contactRepository.uniqueMailAdd(link);
                    if (current_contact == null) {
                        response.put("exists", false);
                        responseBody.setValidate(true);
                    } else {
                        response.put("exists", true);
                        responseBody.setValidate(false);
                        responseBody.setMessage("Impossible de vérifier le compte email cause:");
                        responseBody.setErreur_mssg("Le compte email existe déjà");
                    }
                    
                }else if(option.equals("numero")){
                    Contact current_contact = this.contactRepository.uniqueNumeroAdd(link);
                    if (current_contact == null) {
                        response.put("exists", false);
                        responseBody.setValidate(true);
                    } else {
                        response.put("exists", true);
                        responseBody.setValidate(false);
                        responseBody.setMessage("Impossible de vérifier le compte numero cause:");
                        responseBody.setErreur_mssg("Le compte numero existe déjà");
                    }
                    
                }else if(option.equals("twitter")){
                    Contact current_contact = this.contactRepository.uniqueTwitterAdd(link);
                    if (current_contact == null) {
                        response.put("exists", false);
                        responseBody.setValidate(true);
                    } else {
                        response.put("exists", true);
                        responseBody.setValidate(false);
                        responseBody.setMessage("Impossible de vérifier le compte twitter cause:");
                        responseBody.setErreur_mssg("Le compte twitter existe déjà");
                    }
                    
                }else if(option.equals("website")){
                    Contact current_contact = this.contactRepository.uniqueWebsiteAdd(link);
                    if (current_contact == null) {
                        response.put("exists", false);
                        responseBody.setValidate(true);
                    } else {
                        response.put("exists", true);
                        responseBody.setValidate(false);
                        responseBody.setMessage("Impossible de vérifier le compte site web cause:");
                        responseBody.setErreur_mssg("Le compte site web existe déjà");
                    }
                    
                }
          
             

            } else {
                if(option.equals("facebook")){
                    Contact current_contact = this.contactRepository.uniqueFacebookEdit(link,id);
                    if (current_contact == null) {
                        response.put("exists", false);
                        responseBody.setValidate(true);
                    } else {
                        response.put("exists", true);
                        responseBody.setValidate(false);
                        responseBody.setMessage("Impossible de vérifier le compte facebook cause:");
                        responseBody.setErreur_mssg("Le compte facebook existe déjà");
                    }
                    
                }else if(option.equals("linkedin")){
                    Contact current_contact = this.contactRepository.uniqueLinkedinEdit(link,id);
                    if (current_contact == null) {
                        response.put("exists", false);
                        responseBody.setValidate(true);
                    } else {
                        response.put("exists", true);
                        responseBody.setValidate(false);
                        responseBody.setMessage("Impossible de vérifier le compte linkedin cause:");
                        responseBody.setErreur_mssg("Le compte linkedin existe déjà");
                    }
                    
                }else if(option.equals("mail")){
                    Contact current_contact = this.contactRepository.uniqueMailEdit(link,id);
                    if (current_contact == null) {
                        response.put("exists", false);
                        responseBody.setValidate(true);
                    } else {
                        response.put("exists", true);
                        responseBody.setValidate(false);
                        responseBody.setMessage("Impossible de vérifier le compte email cause:");
                        responseBody.setErreur_mssg("Le compte email existe déjà");
                    }
                    
                }else if(option.equals("numero")){
                    Contact current_contact = this.contactRepository.uniqueNumeroEdit(link,id);
                    if (current_contact == null) {
                        response.put("exists", false);
                        responseBody.setValidate(true);
                    } else {
                        response.put("exists", true);
                        responseBody.setValidate(false);
                        responseBody.setMessage("Impossible de vérifier le compte numero cause:");
                        responseBody.setErreur_mssg("Le compte numero existe déjà");
                    }
                    
                }else if(option.equals("twitter")){
                    Contact current_contact = this.contactRepository.uniqueTwitterEdit(link,id);
                    if (current_contact == null) {
                        response.put("exists", false);
                        responseBody.setValidate(true);
                    } else {
                        response.put("exists", true);
                        responseBody.setValidate(false);
                        responseBody.setMessage("Impossible de vérifier le compte twitter cause:");
                        responseBody.setErreur_mssg("Le compte twitter existe déjà");
                    }
                    
                }else if(option.equals("website")){
                    Contact current_contact = this.contactRepository.uniqueWebsiteEdit(link,id);
                    if (current_contact == null) {
                        response.put("exists", false);
                        responseBody.setValidate(true);
                    } else {
                        response.put("exists", true);
                        responseBody.setValidate(false);
                        responseBody.setMessage("Impossible de vérifier le compte site web cause:");
                        responseBody.setErreur_mssg("Le compte site web existe déjà");
                    }
                    
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
}
