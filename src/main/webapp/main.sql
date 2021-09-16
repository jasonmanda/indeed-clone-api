-- --------------------------------------------------------
-- Hôte :                        127.0.0.1
-- Version du serveur:           5.7.28 - MySQL Community Server (GPL)
-- SE du serveur:                Win64
-- HeidiSQL Version:             11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Listage des données de la table job.gs_certificat : ~1 rows (environ)
TRUNCATE `gs_certificat`;
/*!40000 ALTER TABLE `gs_certificat` DISABLE KEYS */;
INSERT INTO `gs_certificat` (`id_certificat`, `created_at`, `debut`, `description`, `etat_certificat`, `fin`, `titre_certificat`, `uid_certificat`, `updated_at`, `url_certificat`, `cv_uid`, `id_document`) VALUES
	(1, '2020-04-22 12:09:52', '2010-10-13', 'sslqdkfjlmsd qfmqsdfljqsdfkjkl', 1, '2010-10-13', 'sq fsd f', 'jsdmql slmfk jqdlkfj', '2020-04-22 12:09:52', 'msdfjmslqkfjqlmsdfjlm', '0c9c9f0d-bf81-487b-b2b9-ce6b1f9a717d', 1);
/*!40000 ALTER TABLE `gs_certificat` ENABLE KEYS */;

-- Listage des données de la table job.gs_competence : ~1 rows (environ)
TRUNCATE `gs_competence`;
/*!40000 ALTER TABLE `gs_competence` DISABLE KEYS */;
INSERT INTO `gs_competence` (`id_competence`, `annee`, `created_at`, `echelle`, `etat_competence`, `jour`, `libelle`, `mois`, `semaine`, `updated_at`, `cv_uid`) VALUES
	(1, 1, '2020-04-20 13:15:12', 3, 1, 4, 'Anglais', 4, 4, '2020-04-20 13:16:03', '0c9c9f0d-bf81-487b-b2b9-ce6b1f9a717d');
/*!40000 ALTER TABLE `gs_competence` ENABLE KEYS */;

-- Listage des données de la table job.gs_contact : ~2 rows (environ)
TRUNCATE `gs_contact`;
/*!40000 ALTER TABLE `gs_contact` DISABLE KEYS */;
INSERT INTO `gs_contact` (`id_contact`, `created_at`, `etat_contact`, `facebook`, `linkedin`, `mail`, `numero`, `twitter`, `updated_at`, `website`) VALUES
	(1, '2020-04-20 13:09:18', 1, NULL, NULL, NULL, '33 890 09 98', NULL, '2020-04-20 13:16:03', NULL),
	(2, '2020-04-22 13:03:39', 1, 'https://www.web.facebook.com/lskqdjflqsdkfjl', 'https://www.linkedin.com/in/osdifjqsldfj', 'slqdkfjq@qsdfmsqldf.com', '33 789 98 79', 'https://ww.twitter.com/sdkqflqskdflkqsdf', '2020-04-22 13:03:39', 'https://qsdkjfqsjdfkl.com');
/*!40000 ALTER TABLE `gs_contact` ENABLE KEYS */;

-- Listage des données de la table job.gs_cv : ~2 rows (environ)
TRUNCATE `gs_cv`;
/*!40000 ALTER TABLE `gs_cv` DISABLE KEYS */;
INSERT INTO `gs_cv` (`cv_uid`, `created_at`, `description`, `pret_salary_max`, `pret_salary_min`, `updated_at`, `id_contact`, `id_document`, `id_personne`) VALUES
	('0c9c9f0d-bf81-487b-b2b9-ce6b1f9a717d', '2020-04-18 21:15:30', NULL, 0, 0, '2020-05-04 16:48:59', 1, 4, 1),
	('0c9c9f0d-bf81-487b-b2b9-ce6b1f9a717e', '2020-04-18 21:15:30', NULL, 0, 0, '2020-05-04 16:48:59', 1, 4, 3);
/*!40000 ALTER TABLE `gs_cv` ENABLE KEYS */;

-- Listage des données de la table job.gs_document : ~4 rows (environ)
TRUNCATE `gs_document`;
/*!40000 ALTER TABLE `gs_document` DISABLE KEYS */;
INSERT INTO `gs_document` (`id_document`, `created_at`, `etat_document`, `original_name`, `path_document`, `updated_at`) VALUES
	(1, '2020-04-22 12:09:19', 1, 'Projet JEE Miage.pdf', 'api/reglage/download_file/1587557358415/Projet JEE Miage.pdf', '2020-04-22 12:09:19'),
	(2, '2020-04-22 12:14:15', 1, 'actes_version_finale_definitivement_validee.pdf', 'api/reglage/download_file/1587557654779/actes_version_finale_definitivement_validee.pdf', '2020-04-22 12:14:15'),
	(3, '2020-04-22 12:21:27', 1, 'B374.pdf', 'api/reglage/download_file/1587558087437/B374.pdf', '2020-04-22 12:21:27'),
	(4, '2020-05-04 16:48:55', 1, 'cleavage.jpg', 'api/reglage/download_file/1588610932606/cleavage.jpg', '2020-05-04 16:48:55');
/*!40000 ALTER TABLE `gs_document` ENABLE KEYS */;

-- Listage des données de la table job.gs_entreprise : ~1 rows (environ)
TRUNCATE `gs_entreprise`;
/*!40000 ALTER TABLE `gs_entreprise` DISABLE KEYS */;
INSERT INTO `gs_entreprise` (`id_entreprise`, `cab_recrutement`, `capital`, `created_at`, `description`, `etat_entreprise`, `nom`, `taille`, `updated_at`, `id_contact`, `id_personne`, `id_ville`, `domaine`) VALUES
	(1, 1, 37000, '2020-04-22 13:03:39', 'dqsf qsdmfjqsklmdfjmslqdkf skmqd jfl', 1, 'jkkn j   kl', '1-49', '2020-04-22 13:03:39', 2, 2, 3, NULL);
/*!40000 ALTER TABLE `gs_entreprise` ENABLE KEYS */;

-- Listage des données de la table job.gs_exp_pro : ~2 rows (environ)
TRUNCATE `gs_exp_pro`;
/*!40000 ALTER TABLE `gs_exp_pro` DISABLE KEYS */;
INSERT INTO `gs_exp_pro` (`id_exp_pro`, `created_at`, `debut`, `description`, `etat_exp_pro`, `fin`, `nom_soc`, `poste`, `updated_at`, `cv_uid`, `id_ville`) VALUES
	(1, '2020-04-22 12:39:45', '2019-10-23', 'jqsldkfjlsqdfjmsldf', 1, '2019-10-23', 'ksdj fsqdfkjsdflkjsdqfkl', 'ksld qksdmfqmsfljn,n,n', '2020-04-22 12:39:53', '0c9c9f0d-bf81-487b-b2b9-ce6b1f9a717d', 1),
	(2, '2020-04-22 12:39:45', '2019-10-23', 'jqsldkfjlsqdfjmsldf', 1, '2019-10-23', 'ksdj fsqdfkjsdflkjsdqfkl', 'ksld qksdmfqmsfljn,n,n', '2020-04-22 12:39:53', '0c9c9f0d-bf81-487b-b2b9-ce6b1f9a717e', 1);
/*!40000 ALTER TABLE `gs_exp_pro` ENABLE KEYS */;

-- Listage des données de la table job.gs_favoris : ~7 rows (environ)
TRUNCATE `gs_favoris`;
/*!40000 ALTER TABLE `gs_favoris` DISABLE KEYS */;
INSERT INTO `gs_favoris` (`id_favoris`, `created_at`, `etat_favoris`, `updated_at`, `cv_uid`, `id_offre`) VALUES
	(1, '2020-05-04 17:51:35', 0, '2020-05-04 17:51:58', '0c9c9f0d-bf81-487b-b2b9-ce6b1f9a717d', 1),
	(2, '2020-05-04 17:52:10', 0, '2020-05-04 17:52:18', '0c9c9f0d-bf81-487b-b2b9-ce6b1f9a717d', 1),
	(3, '2020-05-04 17:52:20', 0, '2020-05-05 10:56:45', '0c9c9f0d-bf81-487b-b2b9-ce6b1f9a717d', 1),
	(4, '2020-05-05 10:56:50', 0, '2020-05-05 13:03:54', '0c9c9f0d-bf81-487b-b2b9-ce6b1f9a717d', 1),
	(5, '2020-05-05 13:03:57', 0, '2020-05-05 13:04:00', '0c9c9f0d-bf81-487b-b2b9-ce6b1f9a717d', 1),
	(6, '2020-05-05 14:16:10', 0, '2020-05-05 14:36:31', '0c9c9f0d-bf81-487b-b2b9-ce6b1f9a717d', 1),
	(7, '2020-05-05 19:05:14', 1, '2020-05-05 19:05:14', '0c9c9f0d-bf81-487b-b2b9-ce6b1f9a717e', 1);
/*!40000 ALTER TABLE `gs_favoris` ENABLE KEYS */;

-- Listage des données de la table job.gs_formation : ~2 rows (environ)
TRUNCATE `gs_formation`;
/*!40000 ALTER TABLE `gs_formation` DISABLE KEYS */;
INSERT INTO `gs_formation` (`id_formation`, `created_at`, `debut`, `description`, `etat_formation`, `fin`, `niveau_etude`, `titre_formation`, `updated_at`, `cv_uid`, `id_document`) VALUES
	(1, '2020-04-22 12:21:40', '2011-11-10', 'sdqmkfj mqskld fjqmskldf jmqsdjfm', 1, '2011-11-10', 'bepc', 'kds qfdk kqsldfksqdfkj qdklfj', '2020-04-22 12:21:40', '0c9c9f0d-bf81-487b-b2b9-ce6b1f9a717d', 3),
	(2, '2020-04-22 12:21:40', '2011-11-10', 'sdqmkfj mqskld fjqmskldf jmqsdjfm', 1, '2011-11-10', 'bepc', 'kds qfdk kqsldfksqdfkj qdklfj', '2020-04-22 12:21:40', '0c9c9f0d-bf81-487b-b2b9-ce6b1f9a717e', 3);
/*!40000 ALTER TABLE `gs_formation` ENABLE KEYS */;

-- Listage des données de la table job.gs_image : ~4 rows (environ)
TRUNCATE `gs_image`;
/*!40000 ALTER TABLE `gs_image` DISABLE KEYS */;
INSERT INTO `gs_image` (`id_image`, `created_at`, `etat_image`, `is_logo`, `is_temp`, `original_name`, `path_image`, `updated_at`, `id_entreprise`, `id_personne`) VALUES
	(1, '2020-04-18 21:14:50', 0, 0, 1, 'IMG_20200331_122143.jpg', 'api/reglage/download_file/1587244486760/IMG_20200331_122143.jpg', '2020-04-18 21:15:30', NULL, NULL),
	(2, '2020-04-18 21:15:31', 1, 0, 0, 'IMG_20200331_122143.jpg', 'api/reglage/download_file/1587244486760/IMG_20200331_122143.jpg', '2020-04-18 21:15:31', NULL, 1),
	(3, '2020-04-22 13:03:22', 0, 1, 1, 'th.jpg', 'api/reglage/download_file/1587560601761/th.jpg', '2020-04-22 13:03:39', NULL, NULL),
	(4, '2020-04-22 13:03:40', 1, 1, 0, 'th.jpg', 'api/reglage/download_file/1587560601761/th.jpg', '2020-04-22 13:03:40', 1, NULL);
/*!40000 ALTER TABLE `gs_image` ENABLE KEYS */;

-- Listage des données de la table job.gs_langue : ~4 rows (environ)
TRUNCATE `gs_langue`;
/*!40000 ALTER TABLE `gs_langue` DISABLE KEYS */;
INSERT INTO `gs_langue` (`id_langue`, `created_at`, `echelle`, `etat_langue`, `libelle`, `updated_at`, `cv_uid`) VALUES
	(1, '2020-04-20 13:09:18', 'debutant', 1, 'jsd', '2020-04-20 13:16:03', '0c9c9f0d-bf81-487b-b2b9-ce6b1f9a717d'),
	(2, '2020-04-20 13:12:45', 'debutant', 1, 'jkj', '2020-04-20 13:16:03', '0c9c9f0d-bf81-487b-b2b9-ce6b1f9a717d'),
	(3, '2020-04-20 13:12:45', 'debutant', 1, 'jkj', '2020-04-20 13:16:03', '0c9c9f0d-bf81-487b-b2b9-ce6b1f9a717e'),
	(4, '2020-04-20 13:09:18', 'debutant', 1, 'jsd', '2020-04-20 13:16:03', '0c9c9f0d-bf81-487b-b2b9-ce6b1f9a717e');
/*!40000 ALTER TABLE `gs_langue` ENABLE KEYS */;

-- Listage des données de la table job.gs_offre : ~1 rows (environ)
TRUNCATE `gs_offre`;
/*!40000 ALTER TABLE `gs_offre` DISABLE KEYS */;
INSERT INTO `gs_offre` (`id_offre`, `avantages`, `cab_recrutement`, `created_at`, `cv_require`, `date_debut`, `date_immediat`, `date_limit`, `description`, `etat_offre`, `no_salaire`, `nom_ent`, `poste`, `salaire`, `salaire_max`, `salaire_min`, `salaire_model`, `salaire_type`, `taille`, `test_compet`, `type_contrat`, `type_emp`, `updated_at`, `id_entreprise`, `id_ville`, `id_personne`) VALUES
	(1, '["remote_working","horaire_flex","part_transp","titre_restau_panier","13mois","13mois+","vdf","rtt","epargne_sal","autre"]', 1, '2020-04-30 10:26:35', 1, '1970-01-01 00:00:00', 1, '2020-03-31 23:00:00', 'DQSFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF', 1, 1, 'fdg dfgsdfgsdgsdfgdf', 'dqfsdf', 50000, 0, 0, 0, 'par heure', '250-499', 0, 'CDI', 'Temps partiel', '2020-05-05 16:40:26', 1, 6, NULL);
/*!40000 ALTER TABLE `gs_offre` ENABLE KEYS */;

-- Listage des données de la table job.gs_personne : ~3 rows (environ)
TRUNCATE `gs_personne`;
/*!40000 ALTER TABLE `gs_personne` DISABLE KEYS */;
INSERT INTO `gs_personne` (`id_personne`, `created_at`, `nom_personne`, `prenom_personne`, `updated_at`, `id_ville`) VALUES
	(1, '2020-04-18 21:15:30', 'dxcdsdsd', 'dgdfgdf', '2020-04-22 12:39:53', 2),
	(2, '2020-04-22 13:03:39', 'kk msdjkf qdfkjm', 'kjfdsklfjqmsdfjk', '2020-04-22 13:03:39', NULL),
	(3, '2020-04-18 21:15:30', 'Mandabrandja', 'Jason', '2020-04-22 12:39:53', 4);
/*!40000 ALTER TABLE `gs_personne` ENABLE KEYS */;

-- Listage des données de la table job.gs_postuler : ~2 rows (environ)
TRUNCATE `gs_postuler`;
/*!40000 ALTER TABLE `gs_postuler` DISABLE KEYS */;
INSERT INTO `gs_postuler` (`id_postuler`, `created_at`, `retenue`, `updated_at`, `cv_uid`, `id_offre`, `message`, `id_entreprise`, `id_personne`) VALUES
	(1, '2020-05-04 17:06:26', 0, '2020-05-04 17:06:26', '0c9c9f0d-bf81-487b-b2b9-ce6b1f9a717d', 1, NULL, NULL, NULL),
	(2, '2020-05-05 19:05:05', 0, '2020-05-05 19:05:05', '0c9c9f0d-bf81-487b-b2b9-ce6b1f9a717e', 1, NULL, NULL, NULL);
/*!40000 ALTER TABLE `gs_postuler` ENABLE KEYS */;

-- Listage des données de la table job.gs_role : ~2 rows (environ)
TRUNCATE `gs_role`;
/*!40000 ALTER TABLE `gs_role` DISABLE KEYS */;
INSERT INTO `gs_role` (`id_role`, `created_at`, `etat_role`, `role_name`, `updated_at`) VALUES
	(1, '2020-03-25 18:59:00', 1, 'ROLE_ENTREPRISE', NULL),
	(2, '2020-03-25 18:59:18', 1, 'ROLE_PROFESSIONNEL', NULL);
/*!40000 ALTER TABLE `gs_role` ENABLE KEYS */;

-- Listage des données de la table job.gs_user_role : ~3 rows (environ)
TRUNCATE `gs_user_role`;
/*!40000 ALTER TABLE `gs_user_role` DISABLE KEYS */;
INSERT INTO `gs_user_role` (`id_user_role`, `created_at`, `etat_user_role`, `updated_at`, `id_role`, `id_utilisateur`) VALUES
	(1, '2020-04-18 21:15:31', 1, '2020-04-18 21:15:31', 2, 1),
	(2, '2020-04-22 13:03:40', 1, '2020-04-22 13:03:40', 1, 2),
	(3, '2020-04-18 21:15:31', 1, '2020-04-18 21:15:31', 2, 3);
/*!40000 ALTER TABLE `gs_user_role` ENABLE KEYS */;

-- Listage des données de la table job.gs_utilisateur : ~3 rows (environ)
TRUNCATE `gs_utilisateur`;
/*!40000 ALTER TABLE `gs_utilisateur` DISABLE KEYS */;
INSERT INTO `gs_utilisateur` (`id_utilisateur`, `api_token`, `created_at`, `email`, `etat_utilisateur`, `password`, `updated_at`, `id_personne`) VALUES
	(1, 'd452ac93-822c-4f45-bd99-ae427c18403a', '2020-04-18 21:15:31', 'dashing.jr@gmail.com', 1, 'bonjourlemonde', '2020-05-11 19:59:27', 1),
	(2, '9ffcc631-fd65-4848-959f-cb6c577e49c6', '2020-04-22 13:03:40', 'dashing.jr@gmail.coms', 1, 'bonjourlemonde', '2020-05-05 19:06:22', 2),
	(3, '38b5e1b3-85dd-46b5-9915-596e67d3c5cc', '2020-04-18 21:15:31', 'dashing.jr@gmail.comss', 1, 'bonjourlemonde', '2020-05-05 19:04:28', 3);
/*!40000 ALTER TABLE `gs_utilisateur` ENABLE KEYS */;

-- Listage des données de la table job.gs_ville : ~6 rows (environ)
TRUNCATE `gs_ville`;
/*!40000 ALTER TABLE `gs_ville` DISABLE KEYS */;
INSERT INTO `gs_ville` (`id_ville`, `created_at`, `etat_ville`, `nom_ville`, `updated_at`) VALUES
	(1, '2020-04-18 21:15:30', 1, 'Sébikhotane', '2020-04-22 12:39:45'),
	(2, '2020-04-22 12:39:53', 1, 'Bakel', '2020-04-22 12:39:53'),
	(3, '2020-04-22 13:03:39', 1, 'Bambey', '2020-04-22 13:03:39'),
	(4, '2020-04-28 17:13:52', 1, 'Dakar', '2020-04-28 17:13:52'),
	(5, '2020-04-30 09:51:04', 1, 'Dahra', '2020-04-30 09:51:04'),
	(6, '2020-04-30 10:26:35', 1, 'Dagana', '2020-04-30 10:26:35');
/*!40000 ALTER TABLE `gs_ville` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
