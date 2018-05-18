# Application GSB_RH

Autheur : __Anthony Nizac__

[Projet Github de l'application](https://github.com/Gotox29/Java "Projet Github")


Application de gestion de personnel pour le contexte GSB.
A terme l'application devra permettre :
  * [X] Ajouter un employé
  * [X] Consulter une liste d'employé
    * [X] Modifier un employé
    * [ ] Programmer date départ d'un employé !! Si la date est dépassé il passera dans un onglet ancien amployé
  * [X] Connexion de l'utilisateur

Installation de l'application GSBRH_OBS
---------------------------------------

* Télécharger les dossiers src, bin et lib ainsi que le script GSBRH.sql

* Placer le tout dans un dossier GSBRH_OBS dans votre workspace(pour eclypse java)

* Ouvrir java et créer dans ce même workspace un projet nommer GSBRH_OBS (en environement javaSE-1.8)

* Importer la base de données GSBRH.sql dans votre serveur SGBDR

* Ouvrir le fichier GSBRH_OBS/src/bzh.gsbrh.modeles/ConnexionBDD.java

* Modifier les lignes 9, 10 et 11 pour les faire correspondre avec votre serveur et la base de données importé

