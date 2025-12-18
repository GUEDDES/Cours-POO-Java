# Solution Exercice 1 : Système de Réservation Hôtelière

## Question 1 : Identifier les Classes (3 points)

### Classes Identifiées

1. **Hôtel**
2. **Chambre**
3. **Client**
4. **Réservation**
5. **Réceptionniste**

**Justification** :  
📌 **Hôtel** : Entité principale qui contient les chambres  
📌 **Chambre** : Représente les différents types de chambres  
📌 **Client** : Personne qui réserve  
📌 **Réservation** : Lien entre client et chambre  
📌 **Réceptionniste** : Acteur qui gère les réservations

---

## Question 2 : Attributs par Classe (6 points)

### Classe : Hôtel

```
- nom : String
- adresse : String
- nombreEtoiles : int
- chambres : List<Chambre>
- reservations : List<Réservation>
```

---

### Classe : Chambre

```
- numero : int
- typeChambre : String ("simple", "double", "suite")
- prixParNuit : double
- statut : String ("disponible", "occupée")
- nombreLits : int
- superficie : double
```

---

### Classe : Client

```
- nom : String
- prenom : String
- numeroTelephone : String
- email : String
- numeroIdentite : String
```

---

### Classe : Réservation

```
- numeroReservation : String
- client : Client
- chambre : Chambre
- dateArrivee : Date
- dateDepart : Date
- statut : String ("confirmée", "annulée")
- montantTotal : double
```

---

### Classe : Réceptionniste

```
- nom : String
- prenom : String
- employeId : String
- hotel : Hôtel
```

---

## Question 3 : Méthodes par Classe (6 points)

### Classe : Hôtel

```
+ ajouterChambre(chambre : Chambre) : void
+ supprimerChambre(numero : int) : void
+ afficherChambresDisponibles() : List<Chambre>
+ calculerTauxOccupation() : double
+ genererRapportFinancier() : void
```

---

### Classe : Chambre

```
+ afficherDetails() : void
+ changerStatut(nouveauStatut : String) : void
+ estDisponible() : boolean
+ calculerPrixSejour(nbNuits : int) : double
```

---

### Classe : Client

```
+ afficherInfos() : void
+ modifierNumeroTelephone(nouveauNumero : String) : void
+ validerEmail() : boolean
```

---

### Classe : Réservation

```
+ creerReservation(client : Client, chambre : Chambre, dateArrivee : Date, dateDepart : Date) : void
+ annulerReservation() : void
+ calculerDuree() : int
+ calculerMontantTotal() : double
+ afficherRecapitulatif() : void
```

---

### Classe : Réceptionniste

```
+ enregistrerReservation(reservation : Réservation) : void
+ libererChambre(numeroChambre : int) : void
+ consulterChambresDisponibles() : List<Chambre>
+ afficherPlanningJour(date : Date) : void
```

---

## Question 4 : Diagramme de Classes (5 points)

```
┌────────────────────────────────────────┐
│                Hôtel                    │
├────────────────────────────────────────┤
│ - nom : String                       │
│ - adresse : String                   │
│ - nombreEtoiles : int                │
│ - chambres : List<Chambre>           │
│ - reservations : List<Réservation>   │
├────────────────────────────────────────┤
│ + ajouterChambre(Chambre) : void     │
│ + afficherChambresDisponibles()      │
│ + calculerTauxOccupation() : double  │
│ + genererRapportFinancier() : void   │
└────────────────────────────────────────┘
                │
                │ 1      *
                ▼
┌────────────────────────────────────────┐
│               Chambre                   │
├────────────────────────────────────────┤
│ - numero : int                       │
│ - typeChambre : String               │
│ - prixParNuit : double               │
│ - statut : String                    │
├────────────────────────────────────────┤
│ + estDisponible() : boolean          │
│ + changerStatut(String) : void       │
│ + calculerPrixSejour(int) : double   │
└────────────────────────────────────────┘

┌────────────────────────────────────────┐
│              Réservation                │
├────────────────────────────────────────┤
│ - numeroReservation : String         │
│ - client : Client                    │
│ - chambre : Chambre                  │
│ - dateArrivee : Date                 │
│ - dateDepart : Date                  │
│ - statut : String                    │
├────────────────────────────────────────┤
│ + creerReservation(...) : void        │
│ + annulerReservation() : void        │
│ + calculerMontantTotal() : double    │
└────────────────────────────────────────┘
        │                    │
        │ *                * │
        ▼                    ▼
┌───────────────┐    ┌───────────────┐
│    Client    │    │   Chambre   │
└───────────────┘    └───────────────┘
```

**Relations** :
- Hôtel **contient** plusieurs Chambres (1 → *)
- Réservation **lie** un Client à une Chambre

---

## Question 5 : Scénarios d'Utilisation (Bonus)

### Scénario 1 : Réservation d'une Chambre

```
1. Le client "Alice Dupont" contacte l'hôtel
2. Le réceptionniste consulte les chambres disponibles
   -> hotel.afficherChambresDisponibles()
3. Une chambre double (n°205, 80€/nuit) est disponible
4. Le réceptionniste crée une réservation
   -> reservation = new Réservation()
   -> reservation.creerReservation(alice, chambre205, "2025-01-10", "2025-01-15")
5. Le statut de la chambre 205 passe à "occupée"
   -> chambre205.changerStatut("occupée")
6. Le montant total est calculé : 5 nuits * 80€ = 400€
   -> reservation.calculerMontantTotal()
7. La réservation est enregistrée dans l'hôtel
   -> hotel.ajouterReservation(reservation)
```

---

### Scénario 2 : Libération d'une Chambre

```
1. Le client "Bob Martin" quitte l'hôtel (chambre n°102)
2. Le réceptionniste libère la chambre
   -> receptionniste.libererChambre(102)
3. La chambre 102 passe en statut "disponible"
   -> chambre102.changerStatut("disponible")
4. La chambre redevient visible dans la liste des chambres disponibles
   -> hotel.afficherChambresDisponibles() inclut chambre102
5. Le système met à jour le taux d'occupation
   -> hotel.calculerTauxOccupation()
```

---

## 🎯 Points Clés de la Solution

1. **Classes bien définies** : Chaque classe a une responsabilité claire
2. **Attributs pertinents** : Informations nécessaires sans redondance
3. **Méthodes logiques** : Actions cohérentes avec la responsabilité de la classe
4. **Relations explicites** : Hôtel contient Chambres, Réservation lie Client et Chambre
5. **Scénarios réalistes** : Flux d'utilisation concrets

---

**Note** : /20 (+2 bonus) = /22 maximum  
**Difficulté** : ⭐⭐⭐☆☆
