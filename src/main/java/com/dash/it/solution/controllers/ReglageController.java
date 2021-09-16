package com.dash.it.solution.controllers;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.dash.it.solution.FileStorageService;
import com.dash.it.solution.KronosJobApiApplication;
import com.dash.it.solution.viewmodel.BaseQuery;
import com.dash.it.solution.entities.Cv;
import com.dash.it.solution.entities.Document;
import com.dash.it.solution.entities.Image;
import com.dash.it.solution.entities.User;
import com.dash.it.solution.repository.CvRepository;
import com.dash.it.solution.repository.DocumentRepository;
import com.dash.it.solution.repository.ImageRepository;
import com.dash.it.solution.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * The type Reglage controller.
 *
 * @author Jason Mandabrandja
 */
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/reglage")
public class ReglageController {


	private static final Logger logger = LoggerFactory.getLogger(KronosJobApiApplication.class);
    @Autowired
	private FileStorageService fileStorageService;
	@Autowired
	private DocumentRepository documentRepository;
	@Autowired
	private CvRepository cvRepository;
    @Autowired
	private ImageRepository imageRepository;
	@Autowired
	private UserRepository userRepository;

 
	@CrossOrigin(origins = "*")
	@PostMapping(path = "/image/save",produces = "application/json;charset=utf8")
	public BaseQuery<Map<String, Object>> singleUploadImage(@RequestParam("fichier") final MultipartFile fichier,
			@RequestParam final String type) {
		BaseQuery<Map<String, Object>> responseBody = new BaseQuery<Map<String, Object>>();

		Date date = new Date();
		String dir = date.getTime() + "";
		final String fileName = fileStorageService.storeFile(fichier, File.separator + dir);
		final String fileDownloadUri = "api/reglage/download_file/" + dir + "/" + fileName;
		Image img = new Image();
		img.setOriginal_name(fileName);
		img.setPath_image(fileDownloadUri);
		if (type.equals("entreprise")) {
			img.setIs_logo(true);
		} else {
			img.setIs_logo(false);

		}
		img.setIs_temp(true);
		img.setEtat_image(true);
		Map<String, Object> response = new HashMap<>();

		try {
			this.imageRepository.save(img);
			Map<String, Object> v_img = new HashMap<>();
			v_img.put("id_image", img.getId_image());
			v_img.put("original_name", img.getOriginal_name());
			v_img.put("path_image", img.getPath_image());
			v_img.put("is_temp", img.isIs_temp());
			v_img.put("is_logo", img.isIs_logo());
			v_img.put("etat_image", img.isEtat_image());
			v_img.put("id_entreprise", null);
			v_img.put("id_personne", null);
			v_img.put("created_at", img.getCreated_at());
			v_img.put("updated_at", img.getUpdated_at());
			response.put("image", v_img);

			responseBody.setValidate(true);
		} catch (Exception exp) {
			response.put("image", null);

			responseBody.setValidate(false);
			responseBody.setMessage("Impossible d'ajouter l'image cause:");
			responseBody.setErreur_mssg(exp.getMessage());
		}
		// final String fileDownloadUri =
		// ServletUriComponentsBuilder.fromCurrentContextPath()
		// .path("api/reglage/download_file/")
		// .path(fileName)
		// .toUriString();
		// return new UploadFileResponse(fileName, fileDownloadUri,
		// fichier.getContentType(), fichier.getSize());
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
	@PostMapping(path = "/document/save", produces = "application/json;charset=utf8")
	public BaseQuery<Map<String, Object>> singleUploadDocument(@RequestParam("fichier") final MultipartFile fichier,
			@RequestParam final String type, @RequestParam String api_token) {
		BaseQuery<Map<String, Object>> responseBody = new BaseQuery<Map<String, Object>>();
		User user = this.userRepository.getUserByApiToken(api_token);

		Date date = new Date();
		String dir = date.getTime() + "";
		final String fileName = fileStorageService.storeFile(fichier, File.separator + dir);
		final String fileDownloadUri = "api/reglage/download_file/" + dir + "/" + fileName;

		Document document = new Document();
		document.setOriginal_name(fileName);
		document.setPath_document(fileDownloadUri);
		document.setEtat_document(true);
		Map<String, Object> response = new HashMap<>();

		try {
			if(type.equals("cv")){
				Cv oldCv=this.cvRepository.getCvByPersonneId(user.getPersonne().getId_personne());

				Document oldDocument = oldCv.getDocument();
				if (oldDocument != null) {
					oldDocument.setEtat_document(false);
					this.documentRepository.save(oldDocument);
	
				}

				oldCv.setDocument(document);
				
			this.documentRepository.save(document);
			this.cvRepository.save(oldCv);
			}else{
				this.documentRepository.save(document);
			}
	
			

			Map<String, Object> v_document = new HashMap<>();
			v_document.put("id_document", document.getId_document());
			v_document.put("original_name", document.getOriginal_name());
			v_document.put("path_document", document.getPath_document());
			v_document.put("etat_document", document.isEtat_document());
	 
			v_document.put("created_at", document.getCreated_at());
			v_document.put("updated_at", document.getUpdated_at());
			response.put("document", v_document); 

			responseBody.setValidate(true);
		} catch (Exception exp) {
			response.put("document", null);

			responseBody.setValidate(false);
			responseBody.setMessage("Impossible d'ajouter du document cause:");
			responseBody.setErreur_mssg(exp.getMessage());
		}
		// final String fileDownloadUri =
		// ServletUriComponentsBuilder.fromCurrentContextPath()
		// .path("api/reglage/download_file/")
		// .path(fileName)
		// .toUriString();
		// return new UploadFileResponse(fileName, fileDownloadUri,
		// fichier.getContentType(), fichier.getSize());
		responseBody.setData(response);
		return responseBody;
	}
	// @CrossOrigin(origins = "*")
	// @PostMapping("/multi_upload")
	// public List<BaseQuery<Map<String, Image>>> arrayUpload(@RequestParam("files")
	// final MultipartFile[] files,@RequestParam final String type) {
	// return Arrays.asList(files)
	// .stream()
	// .map(file -> singleUpload(file,type))
	// .collect(Collectors.toList());
	// }


	/**
	 * Login user .
	 *
	 * @param user
	 * @return the user
	 */
	@CrossOrigin(origins = "*")
	@PostMapping(path = "/image/delete", produces = "application/json;charset=utf8")
	public BaseQuery<Map<String, Image>> singleDeleteImage(@RequestParam final Long id_image,
			@RequestParam final String api_token) {
		BaseQuery<Map<String, Image>> responseBody = new BaseQuery<Map<String, Image>>();

		Image img = this.imageRepository.getOne((long) id_image);
		img.setEtat_image(false);
		Map<String, Image> response = new HashMap<String, Image>();

		try {
			this.imageRepository.save(img);
			response.put("image", null);

			responseBody.setValidate(true);
		} catch (Exception exp) {
			response.put("image", null);

			responseBody.setValidate(false);
			responseBody.setMessage("Impossible de supprimer l'image cause:");
			responseBody.setErreur_mssg(exp.getMessage());
		}
		// final String fileDownloadUri =
		// ServletUriComponentsBuilder.fromCurrentContextPath()
		// .path("api/reglage/download_file/")
		// .path(fileName)
		// .toUriString();
		// return new UploadFileResponse(fileName, fileDownloadUri,
		// fichier.getContentType(), fichier.getSize());
		responseBody.setData(response);
		return responseBody;
	}

	@CrossOrigin(origins = "*")
	@GetMapping("/download_file/{dir}/{fileName:.+}")
	public ResponseEntity<Resource> downaloadFile(@PathVariable final String dir, @PathVariable final String fileName,
			final HttpServletRequest request) {
		// Load file as Resource
		final Resource resource = fileStorageService.loadFileAsResource(fileName, (File.separator + dir));
		// Try to determine file's content type
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (final IOException ex) {
			logger.info("Could not determine file type.");
		}

		// Fallback to the default content type if type could not be determined
		if (contentType == null) {
			contentType = "application/octet-stream";
		}

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}
 
	@CrossOrigin(origins = "*")
	@PostMapping(path = "/document/delete", produces = "application/json;charset=utf8")
	public BaseQuery<Map<String, Object>> singleDeleteDocument(@RequestParam final Long id_document,
			@RequestParam final String api_token) {

		BaseQuery<Map<String, Object>> responseBody = new BaseQuery<Map<String, Object>>();

		Document document = this.documentRepository.getOne((long) id_document);
		document.setEtat_document(false);
		Map<String, Object> response = new HashMap<String, Object>();
 
	 
		try {

			this.documentRepository.save(document);

			response.put("document", null); 

			responseBody.setValidate(true);

		} catch (Exception exp) {
			response.put("document", null); 

			responseBody.setValidate(false);
			responseBody.setMessage("Impossible de supprimer le document cause:");
			responseBody.setErreur_mssg(exp.getMessage());
		}
		// final String fileDownloadUri =
		// ServletUriComponentsBuilder.fromCurrentContextPath()
		// .path("api/reglage/download_file/")
		// .path(fileName)
		// .toUriString();
		// return new UploadFileResponse(fileName, fileDownloadUri,
		// fichier.getContentType(), fichier.getSize());

		responseBody.setData(response);
		return responseBody;	
	}
}
// @RequestParam("file") MultipartFile file
