# Exercice 1 : Classe Étudiant

## 🎯 Objectif

Créer une classe `Etudiant` complète avec constructeurs, attributs, méthodes et validation des données.

---

## 📋 Énoncé

Vous devez développer une classe `Etudiant` pour un système de gestion universitaire.

### Attributs requis

- `matricule` (String) - Identifiant unique, format "E" suivi de 5 chiffres (ex: E00123)
- `nom` (String) - Nom de famille
- `prenom` (String) - Prénom
- `dateNaissance` (String) - Format "JJ/MM/AAAA"
- `moyenne` (double) - Moyenne générale (0-20)
- `nbAbsences` (int) - Nombre d'absences

---

## 📝 Fonctionnalités à implémenter

### 1. Constructeurs

```java
// Constructeur complet
public Etudiant(String matricule, String nom, String prenom, String dateNaissance)

// Le constructeur doit :
// - Valider le format du matricule (Ex+5 chiffres)
// - Vérifier que nom et prénom ne sont pas vides
// - Initialiser moyenne à 0.0 et nbAbsences à 0
```

### 2. Getters et Setters

Créer les getters pour tous les attributs.

Créer les setters UNIQUEMENT pour :
- `moyenne` : validation (0 ≤ moyenne ≤ 20)
- `nbAbsences` : validation (≥ 0)

**Pas de setter pour** : matricule, nom, prénom, dateNaissance (immutables)

### 3. Méthodes métier

```java
// Enregistrer une absence
public void ajouterAbsence()

// Vérifier si l'étudiant est admis (moyenne >= 10)
public boolean estAdmis()

// Vérifier si l'étudiant risque l'exclusion (absences > 10)
public boolean risqueExclusion()

// Calculer l'âge approximatif (année actuelle - année de naissance)
public int calculerAge()

// Afficher les informations complètes
public void afficher()

// Méthode toString() pour représentation textuelle
@Override
public String toString()
```

---

## 💡 Indications

### Validation du matricule

```java
// Utiliser une expression régulière ou vérification manuelle
// Format : E + exactement 5 chiffres
// Valide : E00001, E12345
// Invalide : E123, E1234567, 00001, e00001
```

### Calcul de l'âge

```java
// Extraire l'année de la date de naissance (4 derniers caractères)
// Âge approximatif = 2025 - année de naissance
```

### Affichage formaté

```java
// Utiliser System.out.printf() pour un affichage propre
// Exemple :
// ========== FICHE ÉTUDIANT ==========
// Matricule    : E00123
// Nom          : DUPONT Alice
// Date naissance : 15/03/2003
// Âge          : 22 ans
// Moyenne      : 14.50/20
// Absences     : 3
// Statut       : ✅ Admis
// ====================================
```

---

## ✅ Programme de test

Créer une classe `TestEtudiant` avec un `main()` qui :

1. Crée 3 étudiants avec des données différentes
2. Définit leurs moyennes
3. Ajoute quelques absences
4. Affiche leurs informations
5. Teste les différentes méthodes

### Exemple de sortie attendue

```
=== CRÉATION DES ÉTUDIANTS ===
✅ Étudiant créé : E00001
✅ Étudiant créé : E00002
✅ Étudiant créé : E00003

=== INFORMATIONS ===

========== FICHE ÉTUDIANT ==========
Matricule      : E00001
Nom            : DUPONT Alice
Date naissance : 15/03/2003
Âge            : 22 ans
Moyenne        : 15.50/20
Absences       : 2
Statut         : ✅ Admis
====================================

========== FICHE ÉTUDIANT ==========
Matricule      : E00002
Nom            : MARTIN Bob
Date naissance : 22/08/2004
Âge            : 21 ans
Moyenne        : 9.00/20
Absences       : 12
Statut         : ❌ Non admis
⚠️  RISQUE D'EXCLUSION (12 absences)
====================================
```

---

## 🎯 Critères de validation

- [ ] Classe `Etudiant` créée avec tous les attributs privés
- [ ] Constructeur avec validation du matricule
- [ ] Getters pour tous les attributs
- [ ] Setters uniquement pour moyenne et nbAbsences (avec validation)
- [ ] Méthode `ajouterAbsence()` fonctionnelle
- [ ] Méthode `estAdmis()` retourne true si moyenne ≥ 10
- [ ] Méthode `risqueExclusion()` retourne true si absences > 10
- [ ] Méthode `calculerAge()` calcule correctement
- [ ] Méthode `afficher()` affiche toutes les informations
- [ ] Méthode `toString()` implémentée
- [ ] Programme de test complet et fonctionnel
- [ ] Gestion des cas d'erreur (matricule invalide, moyenne hors limites)

---

## 🚀 Bonus (optionnel)

1. Ajouter un compteur statique pour le nombre total d'étudiants créés
2. Créer une méthode `comparerMoyenne(Etudiant autre)` qui compare deux étudiants
3. Ajouter une méthode `obtenirMention()` qui retourne :
   - "Très Bien" si moyenne ≥ 16
   - "Bien" si moyenne ≥ 14
   - "Assez Bien" si moyenne ≥ 12
   - "Passable" si moyenne ≥ 10
   - "Échec" si moyenne < 10

---

**Bon courage ! 🎓**
