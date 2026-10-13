#notes atelier 4
##A.etape2
| Critère | Constructeur | Attribut (@Autowired) |
| Champ `final` possible ? | Oui | Non |
| Dépendance visible dans la signature de la classe ? | Oui | Non (cachée) |
| Utilisable avec `new` hors conteneur sans dépendance null ? | Oui (la dépendance est obligatoire) | Non (le champ reste `null`) |
| Signalé par SonarQube for IDE ? | Non | Oui |
donc la conclusion est que  on utilise l'injection par constructeur `@RequiredArgsConstructor` + champ `final`)
car l'objet ne peut jamais exister dans un état incomplet.

##B.services:

| Service | Dépendances injectées | Mode | Justification |
|---|---|---|---|
| AgenceServiceImpl | IAgenceRepository | Constructeur | Champ final, service sans état, dépendance typée par interface |
| EmployeServiceImpl | IEmployeRepository | Constructeur | Même raison |
| VehiculeServiceImpl | IVehiculeRepository | Constructeur | Même raison |
| EquipementServiceImpl | IEquipementRepository | Constructeur | Même raison |
| ClientServiceImpl | IClientRepository | Constructeur | Même raison |
| ReservationServiceImpl | IReservationRepository | Constructeur | Même raison |
| ContratServiceImpl | IContratRepository | Constructeur | Même raison |
| PaiementServiceImpl | IPaiementRepository | Constructeur | Lecture seule (findById, findAll), les paiements passent par le Contrat |
| MaintenanceServiceImpl | IMaintenanceRepository | Constructeur | Même raison |


##C.message d'eurreur
mesage1 :  
Causes probables : `@Service` oublié sur `ContratServiceImpl`, ou classe placée en dehors du
package scanné.
Correction : ajouter `@Service` et vérifier le package.


message2: 

corrections : `@Primary` sur la classe du bean à utiliser par défaut . aussi `@Qualifier("emailNotificateur")` sur le **paramètre du constructeur** de
`NotificationController`

message3: 
 corection: Avec linjection par constructeur, chaque bean a besoin de l'autre déjà créé pour être
construit,aucun des deux ne peut être créé en premier, le cycle est impossible à contourner.


##D.Anomalies SonarQube for IDE
|  | Règle | Explication | Correction |

| 1 | S6813 | J'avais mis `@Autowired` sur un champ. Sonar le signale car la dépendance est cachée et le champ ne peut pas être `final`. | J'ai utilisé `@RequiredArgsConstructor` avec un champ `final`. |
| 2 | S112 | J'ai remplacé `ResourceNotFoundException` par `RuntimeException` dans `findById`. Sonar dit qu'il ne faut pas lever une exception générique, car on ne sait pas quel est le problème. | J'ai remis `ResourceNotFoundException("Contrat", id)`, qui est claire et propre à notre métier. |
| 3 | S1128 | Il y avait des imports inutilisés (`RequiredArgsConstructor`, `Service`). | Je les ai supprimés. |
| 4 | Nommage | Le nom `iVehiculeService`  ne respecte pas la convention Java . | Renommé en `IVehiculeService` |

## E. Questions de compréhension

1. **Où est le `new` de `ContratServiceImpl` et qui l'exécute ?**
   Il n'est écrit nulle part dans mon code. C'est le conteneur Spring qui l'exécute au démarrage,
   grâce à `@Service` et au scan. Il donne au constructeur le proxy de `IContratRepository`.

2. **Pourquoi un contrôleur dépend-il de `IContratService` et non de `ContratServiceImpl` ?**
   Chaque couche dépend de l'abstraction de la couche du dessous. Le couplage est faible : on peut
   changer l'implémentation sans modifier le contrôleur, et fournir une dépendance de remplacement
   pour les tests.

3. **Pourquoi un service singleton doit-il rester sans état ?**
   Il est partagé par tous les threads. Exemple de bug : un attribut `contratCourant` modifiable.
   Deux requêtes en même temps s'écrasent, et l'une travaille sur le contrat de l'autre.

4. **Pourquoi `update` charge-t-il l'entité avant `save` ?**
   Si on passe directement l'objet reçu, on écrase toutes les colonnes, même celles non envoyées, et
   avec `orphanRemoval` on peut vider la collection de paiements. On charge donc l'existant, on
   recopie les attributs modifiables, puis on sauvegarde. Cela vérifie aussi que l'entité existe.

5. **Différences**
    - `@Component` se met sur une classe qu'on écrit nous-mêmes (détectée par le scan). `@Bean` se met
      sur une méthode d'une classe `@Configuration`, pour une classe qu'on ne peut pas annoter
      (bibliothèque, JDK) ou une construction particulière.
    - `@Primary` se met sur la classe (candidat par défaut). `@Qualifier("nom")` se met à
      l'injection (choix explicite).
    - `IPaiementService` n'a ni création, ni modification, ni suppression, car un paiement
      appartient à un contrat : ces opérations passent par le Contrat.









