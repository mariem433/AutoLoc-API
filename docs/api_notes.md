1. Quel composant reçoit toutes les requêtes, et comment retrouve-t-il la méthode du contrôleur ?

C’est le DispatcherServlet, le point d’entrée unique de Spring MVC. Il demande au HandlerMapping de trouver la bonne méthode en comparant le verbe HTTP et l’URL avec les annotations (@GetMapping, @PostMapping...). Ensuite il appelle la méthode, et Jackson transforme le résultat en JSON.

2. Pourquoi dépendre de IContratService et pas de ContratRepository ou ContratServiceImpl ?

Chaque couche a son rôle : le contrôleur reçoit la requête, le service porte la logique métier, le repository accède aux données. Si le contrôleur appelait le repository, on contournerait la logique métier. Et dépendre de l’interface plutôt que de l’implémentation permet de changer ou de remplacer le service sans toucher au contrôleur.

3. Pourquoi POST donne 201, DELETE 204, et une ressource absente 404 et non 500 ?

Le 201 dit qu’une ressource a été créée. Le 204 dit que la suppression a réussi et qu’il n’y a rien à renvoyer. Une ressource absente est une erreur du client (il a demandé un id qui n’existe pas), donc 404. Le 500 est réservé aux plantages du serveur. Notre ResourceNotFoundException est transformée en 404 par le GlobalExceptionHandler.

4. Pourquoi PaiementController n’a que des GET ? Quelle réponse pour un POST /api/paiements ?

Un paiement n’existe pas sans son contrat : c’est le Contrat qui porte ses paiements (cascade ALL). On les crée donc dans le JSON du contrat, pas séparément. Un POST /api/paiements donne 405 Method Not Allowed, car cette méthode n’existe pas pour cette route.

5. Risques d’exposer les entités JPA, et pourquoi @JsonManagedReference sur Contrat.paiements mais @JsonIgnore sur Agence.vehicules ?

Risques : l’API est liée à la structure de la base (renommer un champ casse les clients), on peut exposer des champs internes, et les associations bidirectionnelles créent des boucles infinies (StackOverflowError). Les DTO règlent ça.