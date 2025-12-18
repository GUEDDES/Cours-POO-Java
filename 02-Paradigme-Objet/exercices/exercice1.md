# Exercice 1 : Modéliser un Système de Réservation Hôtelière

## 🎯 Objectif
Pratiquer l'identification des classes, attributs et méthodes à partir d'un cahier des charges.

---

## 📋 Cahier des Charges

Vous devez concevoir un système de réservation pour un hôtel.

**Description du système** :

> "Un hôtel propose plusieurs types de chambres : simples, doubles et suites. Chaque chambre a un numéro unique, un prix par nuit et un statut (disponible ou occupée).
>
> Un client peut réserver une chambre en fournissant son nom, son numéro de téléphone et les dates de séjour (arrivée et départ). Une réservation a un numéro unique et peut être annulée.
>
> Le réceptionniste peut consulter la liste des chambres disponibles, enregistrer une réservation et libérer une chambre après le départ du client.
>
> L'hôtel peut afficher le taux d'occupation (pourcentage de chambres occupées) et générer un rapport financier mensuel."

---

## 📊 Questions

### Question 1 : Identifier les Classes (3 points)

Lisez attentivement le cahier des charges et listez **toutes les classes** à modéliser.

**Astuce** : Cherchez les noms importants.

---

### Question 2 : Attributs par Classe (6 points)

Pour **chaque classe** identifiée, listez ses attributs avec leur type.

**Format attendu** :
```
Classe : NomClasse
- attribut1 : type
- attribut2 : type
- ...
```

**Astuce** : Cherchez les caractéristiques et adjectifs.

---

### Question 3 : Méthodes par Classe (6 points)

Pour **chaque classe**, listez les méthodes principales.

**Format attendu** :
```
Classe : NomClasse
- methode1(param1, param2) : typeRetour
- methode2() : typeRetour
- ...
```

**Astuce** : Cherchez les actions et verbes.

---

### Question 4 : Diagramme de Classes (5 points)

Dessinez un diagramme de classes **simple en ASCII** pour représenter votre modélisation.

**Format attendu** :
```
┌───────────────────┐
│    NomClasse     │
├───────────────────┤
│ - attribut1     │
│ - attribut2     │
├───────────────────┤
│ + methode1()    │
│ + methode2()    │
└───────────────────┘
```

---

### Question 5 : Scénarios d'Utilisation (Bonus +2 points)

Décrivez **2 scénarios concrets** montrant l'interaction entre les objets.

**Exemple de format** :
```
Scénario 1 : Réservation d'une chambre
1. Le client "Alice" contacte l'hôtel
2. Le réceptionniste consulte les chambres disponibles
3. Une chambre double (n°205) est disponible
4. Le réceptionniste crée une réservation pour Alice
5. La chambre 205 passe en statut "occupée"
```

---

## 📝 Critères d'Évaluation

| Critère | Points |
|---------|--------|
| Classes identifiées (complètes) | 3 |
| Attributs pertinents avec types | 6 |
| Méthodes pertinentes avec signatures | 6 |
| Diagramme de classes clair | 5 |
| **Bonus** : Scénarios d'utilisation | +2 |
| **TOTAL** | /20 (+2) |

---

## 💡 Conseils

1. **Relisez plusieurs fois** le cahier des charges
2. **Surlignez** les noms (classes), adjectifs (attributs), verbes (méthodes)
3. **Ne sur-compliquez pas** : restez simple et clair
4. **Pensez réutilisabilité** : évitez les duplications

---

**Durée estimée** : 1h30  
**Difficulté** : ⭐⭐⭐☆☆

[Voir la solution →](../solutions/exercice1_solution.md)
