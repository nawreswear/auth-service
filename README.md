🔐 Microservice 1 : Service d’Authentification (Auth Service)

- Description générale

Le microservice d’authentification (Auth Service) est le point d’entrée sécurisé de la plateforme.
Il est responsable de la gestion des identités, de l’authentification des utilisateurs et de la sécurisation des échanges entre les différents microservices via des jetons JWT.

Ce microservice garantit que seuls les utilisateurs autorisés peuvent accéder aux fonctionnalités de la plateforme, en fonction de leurs rôles et permissions.

 - Objectifs principaux

Assurer une authentification sécurisée des utilisateurs

Gérer l’inscription (signup) et la connexion (login)

Générer et valider des jetons JWT

Centraliser la gestion des rôles et des autorisations

Fournir une base de sécurité commune à tous les microservices

- Fonctionnalités clés

Création de comptes utilisateurs

Vérification de l’unicité des identifiants (email)

Chiffrement sécurisé des mots de passe (BCrypt)

Authentification par email et mot de passe

Génération de JWT (access token)

Validation et décodage des tokens

Gestion des rôles (ADMIN, EMPLOYÉ, MANAGER, CLIENT, etc.)

Gestion des erreurs de sécurité (401, 403, 409)

Journalisation des tentatives d’authentification

- Sécurité

Architecture stateless

Utilisation de Spring Security

Authentification basée sur JWT

Mots de passe chiffrés avec BCrypt

Protection contre les accès non autorisés

Intégration avec une Gateway (Spring Cloud Gateway)

- Endpoints principaux
Méthode	Endpoint	Description
POST	/api/v1/auth/signup	Inscription d’un nouvel utilisateur
POST	/api/v1/auth/login	Authentification et génération du JWT
GET	/api/v1/auth/validate	Validation d’un token JWT
GET	/api/v1/auth/me	Informations sur l’utilisateur connecté
- Interactions avec les autres microservices

Fournit des JWT utilisés par les autres microservices

La Gateway valide le token auprès de l’Auth Service

Les microservices métiers se basent sur les rôles contenus dans le JWT

Assure une sécurité centralisée et cohérente

- Technologies utilisées

Java 17 / 21

Spring Boot 3

Spring Security 6

JWT

PostgreSQL

JPA / Hibernate

Maven

Lombok

- Avantages

Sécurité centralisée

Scalabilité élevée

Facilité d’intégration avec de nouveaux services

Architecture moderne et conforme aux bonnes pratiques

Réduction des risques liés aux accès non autorisés

- Résumé

Le microservice d’authentification constitue le socle de sécurité de la plateforme.
Il garantit l’identité des utilisateurs, protège les ressources et permet une communication sécurisée entre tous les composants du système.

