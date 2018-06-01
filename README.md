# Application GSBRH

Autheur : __Anthony Nizac__

[Projet Github de l'application](https://github.com/Gotox29/Java "Projet Github")


Application de gestion de personnel pour le contexte GSB.
A terme l'application devra permettre :
  * [X] Ajouter un employé
  * [X] Consulter une liste d'employé
    * [X] Modifier un employé
    * [X] Programmer date départ d'un employé !! Si la date est dépassé il passera dans un onglet ancien employé
  * [X] Connexion de l'utilisateur

Installation de l'application GSBRH
---------------------------------------

* Télécharger le repertoire GSBRH ainsi que le script slam_gsb.sql

* Placer le repertoire GSBRH dans votre workspace(pour eclypse java)

* Ouvrir java et créer dans ce même workspace un projet nommer GSBRH (en environement javaSE-1.8)

* Importer la base de données slam_gsb.sql dans votre serveur de base de donnée

* Ouvrir le fichier GSBRH/src/bzh.gsbrh.modeles/ConnexionBDD.java

* Modifier les lignes 26, 31 et 36 afin de les faire correspondre avec votre serveur et la base de données précédemment importé
