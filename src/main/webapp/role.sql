USE job;
 SET FOREIGN_KEY_CHECKS = 0; 

TRUNCATE gs_contact;
TRUNCATE gs_cv;
TRUNCATE gs_document;
TRUNCATE gs_entreprise;
TRUNCATE gs_image;
TRUNCATE gs_personne;
TRUNCATE gs_role;
TRUNCATE gs_user_role;
TRUNCATE gs_utilisateur;
TRUNCATE gs_ville;
 
INSERT INTO `gs_role` (`id_role`, `created_at`, `etat_role`, `role_name`, `updated_at`) VALUES
	(1, '2020-03-25 18:59:00', 1, 'ROLE_ENTREPRISE', NULL);
INSERT INTO `gs_role` (`id_role`, `created_at`, `etat_role`, `role_name`, `updated_at`) VALUES
	(2, '2020-03-25 18:59:18', 1, 'ROLE_PROFESSIONNEL', NULL);
  