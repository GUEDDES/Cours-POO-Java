# Section 2 : Le Paradigme Orienté Objet

## 🎯 Objectifs d'apprentissage

À la fin de cette section, vous serez capable de :
- Comprendre la différence entre programmation procédurale et orientée objet
- Identifier les concepts fondamentaux : objet, classe, attribut, méthode
- Modéliser des problèmes réels en utilisant des objets
- Distinguer les attributs des méthodes

---

## 1. Limitations de la Programmation Procédurale

### 1.1 Approche Procédurale Classique

En programmation procédurale, on décompose un problème en **fonctions** qui manipulent des **données**.

**Exemple : Gestion d'un compte bancaire en C**
```c
// Données globales
struct Compte {
    int numero;
    char titulaire[50];
    double solde;
};

// Fonctions qui manipulent ces données
void deposer(struct Compte* c, double montant) {
    c->solde += montant;
}

void retirer(struct Compte* c, double montant) {
    if (c->solde >= montant) {
        c->solde -= montant;
    }
}

void afficher(struct Compte* c) {
    printf("Compte %d : %.2f\n", c->numero, c->solde);
}
```

---

### 1.2 Problèmes de Cette Approche

#### 🚫 **Problème 1 : Séparation données/traitements**
```
Données (struct)     <--->     Fonctions
     ↓                              ↓
Dispersées                    Dispersées
Difficile de savoir              Difficile de savoir
quelles fonctions                quelles données sont
utilisent quelles données       affectées
```

#### 🚫 **Problème 2 : Pas de protection des données**
```c
// N'importe qui peut modifier directement le solde !
struct Compte c1;
c1.solde = -1000;  // ❌ Solde négatif : incohérent !
```

#### 🚫 **Problème 3 : Code difficile à réutiliser**
```c
// Pour ajouter un nouveau type de compte (compte épargne),
// il faut dupliquer toutes les fonctions :
void deposer_compte_courant(...);
void deposer_compte_epargne(...);
void retirer_compte_courant(...);
void retirer_compte_epargne(...);
// etc.
```

#### 🚫 **Problème 4 : Maintenance complexe**
```
Modifier une structure = Modifier toutes les fonctions associées
```

---

## 2. La Révolution Orientée Objet

### 2.1 Principe Fondamental

> 💡 **Idée clé** : Regrouper les **données** et les **traitements** dans une seule entité appelée **OBJET**.

```
Approche Procédurale :          Approche Objet :

  Données                       ┌───────────────────┐
     +                          │     OBJET      │
  Fonctions                     │───────────────────│
     ↓                          │   Données    │
  Séparés                        │      +       │
                                │  Traitements  │
                                └───────────────────┘
                                  Encapsulés
```

---

### 2.2 Qu'est-ce qu'un Objet ?

**Définition** :  
Un **objet** est une entité qui regroupe :
1. **Un état** (ses données = attributs)
2. **Un comportement** (ses actions = méthodes)
3. **Une identité** (son nom unique en mémoire)

---

### 2.3 Exemples d'Objets du Monde Réel

#### Exemple 1 : Un Smartphone 📱

```
┌────────────────────────────┐
│   OBJET : monSmartphone     │
├────────────────────────────┤
│ 📋 ÉTAT (Attributs)       │
│ - marque : "Samsung"       │
│ - modele : "Galaxy S24"    │
│ - batterie : 85%           │
│ - estAllume : true         │
├────────────────────────────┤
│ ⚡ COMPORTEMENT (Méthodes) │
│ - allumer()                │
│ - eteindre()               │
│ - appeler(numero)          │
│ - recharger()              │
└────────────────────────────┘
```

---

#### Exemple 2 : Un Livre 📖

```
┌───────────────────────────────┐
│   OBJET : monLivre              │
├───────────────────────────────┤
│ 📋 ÉTAT (Attributs)            │
│ - titre : "Clean Code"        │
│ - auteur : "Robert C. Martin" │
│ - nbPages : 464               │
│ - estEmprunte : false         │
├───────────────────────────────┤
│ ⚡ COMPORTEMENT (Méthodes)      │
│ - emprunter()                 │
│ - retourner()                 │
│ - afficherInfos()             │
└───────────────────────────────┘
```

---

## 3. Classes et Objets

### 3.1 Qu'est-ce qu'une Classe ?

> Une **classe** est un **modèle** (un plan, un moule) qui définit la structure et le comportement d'objets similaires.

**Analogie** :
```
Classe          ≈  Plan d'architecte d'une maison
Objet           ≈  Une maison construite selon ce plan

Classe          ≈  Recette de cuisine
Objet           ≈  Un plat réalisé avec cette recette

Classe          ≈  Moule à gâteau
Objet           ≘  Un gâteau créé avec ce moule
```

---

### 3.2 Relation Classe / Objets

```
┌──────────────────────────────┐
│     CLASSE : Smartphone        │
├──────────────────────────────┤
│ Attributs :                   │
│ - marque                      │
│ - modele                      │
│ - batterie                    │
│ - estAllume                   │
├──────────────────────────────┤
│ Méthodes :                     │
│ - allumer()                   │
│ - eteindre()                  │
│ - appeler(numero)             │
│ - recharger()                 │
└──────────────────────────────┘
                  ↓
         Permet de créer 
                  ↓
    ┌──────────────────────────────┐
    │ OBJET 1 : smartphone1      │
    │ - marque: "Apple"          │
    │ - modele: "iPhone 15"      │
    │ - batterie: 95%            │
    │ - estAllume: true          │
    └──────────────────────────────┘
    
    ┌──────────────────────────────┐
    │ OBJET 2 : smartphone2      │
    │ - marque: "Samsung"        │
    │ - modele: "Galaxy S24"     │
    │ - batterie: 20%            │
    │ - estAllume: false         │
    └──────────────────────────────┘
```

**Points clés** :
- 📜 Une **classe** = 1 définition
- 📦 Des **objets** = Multiples instances
- Chaque objet a ses **propres valeurs** d'attributs
- Tous les objets partagent les **mêmes méthodes**

---

## 4. Attributs : L'État d'un Objet

### 4.1 Définition

Les **attributs** (ou **propriétés**, **champs**) représentent les **caractéristiques** d'un objet.

**Caractéristiques** :
- Chaque attribut a un **type** (int, String, double, etc.)
- Chaque objet a ses **propres valeurs**
- Les attributs définissent **l'état** de l'objet

---

### 4.2 Exemples d'Attributs

#### Classe Voiture 🚗
```
Attributs possibles :
- marque        (String)
- modele        (String)
- annee         (int)
- kilometrage   (double)
- couleur       (String)
- estDemarree   (boolean)
- vitesse       (double)
```

#### Classe CompteBancaire 💳
```
Attributs possibles :
- numeroCompte  (String)
- titulaire     (String)
- solde         (double)
- dateOuverture (Date)
- estActif      (boolean)
```

---

## 5. Méthodes : Le Comportement d'un Objet

### 5.1 Définition

Les **méthodes** représentent les **actions** qu'un objet peut effectuer.

**Caractéristiques** :
- Une méthode peut **lire** ou **modifier** les attributs
- Une méthode peut avoir des **paramètres** en entrée
- Une méthode peut **retourner** un résultat
- Toutes les instances partagent les **mêmes méthodes**

---

### 5.2 Exemples de Méthodes

#### Classe Voiture 🚗
```
Méthodes possibles :
- demarrer()                  → change estDemarree à true
- arreter()                   → change estDemarree à false
- accelerer(double vitesse)   → augmente la vitesse
- freiner()                   → diminue la vitesse
- afficherInfos()             → affiche marque, modèle, etc.
```

#### Classe CompteBancaire 💳
```
Méthodes possibles :
- deposer(double montant)     → augmente le solde
- retirer(double montant)     → diminue le solde
- consulterSolde()            → retourne le solde
- cloturer()                  → change estActif à false
```

---

## 6. Modéliser un Problème en Objets

### 6.1 Méthode en 4 Étapes

#### Étape 1 : Identifier les Objets (Noms)

Cherchez les **noms** dans le cahier des charges.

**Exemple** :  
> "Un 🏛️ **étudiant** s'inscrit à un 📚 **cours** dispensé par un 👨‍🏫 **professeur** dans une 🏢 **salle**."

**Objets identifiés** : Étudiant, Cours, Professeur, Salle

---

#### Étape 2 : Identifier les Attributs (Adjectifs/Caractéristiques)

Cherchez les **adjectifs** ou **caractéristiques**.

**Exemple** :  
> "L'étudiant a un **nom**, un **numéro d'étudiant** et une **moyenne**. Le cours a un **titre** et un **nombre d'heures**."

```
Classe Étudiant :
- nom
- numeroEtudiant
- moyenne

Classe Cours :
- titre
- nbHeures
```

---

#### Étape 3 : Identifier les Méthodes (Verbes/Actions)

Cherchez les **verbes** ou **actions**.

**Exemple** :  
> "L'étudiant peut **s'inscrire** à un cours et **consulter** ses notes. Le professeur peut **attribuer** une note."

```
Classe Étudiant :
- sInscrire(cours)
- consulterNotes()

Classe Professeur :
- attribuerNote(etudiant, note)
```

---

#### Étape 4 : Dessiner le Diagramme de Classes

```
┌───────────────────────┐
│      Étudiant          │
├───────────────────────┤
│ - nom               │
│ - numeroEtudiant    │
│ - moyenne           │
├───────────────────────┤
│ + sInscrire(cours)  │
│ + consulterNotes() │
└───────────────────────┘
```

---

### 6.2 Exemple Complet : Système de Bibliothèque 📚

**Cahier des charges** :  
> "Une bibliothèque contient des livres. Chaque livre a un titre, un auteur, un ISBN et un statut (disponible ou emprunté). Un utilisateur peut emprunter un livre s'il est disponible et le retourner. La bibliothèque peut afficher la liste de tous les livres."

---

**Analyse** :

**Étape 1 : Objets** (noms)  
- Bibliothèque
- Livre
- Utilisateur

**Étape 2 : Attributs** (adjectifs)  
```
Livre :
- titre
- auteur
- isbn
- estDisponible

Utilisateur :
- nom
- numeroMembre

Bibliothèque :
- nomBibliothèque
- listeLivres
```

**Étape 3 : Méthodes** (verbes)  
```
Livre :
- emprunter()
- retourner()
- afficherInfos()

Utilisateur :
- emprunterLivre(livre)
- retournerLivre(livre)

Bibliothèque :
- ajouterLivre(livre)
- afficherTousLesLivres()
- rechercherLivre(titre)
```

**Étape 4 : Modélisation**
```
┌────────────────────────┐
│        Livre           │
├────────────────────────┤
│ - titre              │
│ - auteur             │
│ - isbn               │
│ - estDisponible      │
├────────────────────────┤
│ + emprunter()        │
│ + retourner()        │
│ + afficherInfos()   │
└────────────────────────┘

┌────────────────────────────┐
│      Utilisateur        │
├────────────────────────────┤
│ - nom                  │
│ - numeroMembre         │
├────────────────────────────┤
│ + emprunterLivre(livre)│
│ + retournerLivre(livre)│
└────────────────────────────┘
```

---

## 📝 Résumé de la Section

| Concept | Définition |
|---------|-------------|
| **Objet** | Entité regroupant état (attributs) + comportement (méthodes) |
| **Classe** | Modèle (plan) pour créer des objets |
| **Attribut** | Variable décrivant une caractéristique de l'objet |
| **Méthode** | Fonction définissant une action que l'objet peut faire |
| **Instance** | Objet concret créé à partir d'une classe |

---

## 🎯 Points à Retenir

1. La POO **regroupe données et traitements** dans des objets
2. Une **classe** = modèle, des **objets** = instances concrètes
3. **Attributs** = CE QU'EST l'objet (état)
4. **Méthodes** = CE QUE FAIT l'objet (comportement)
5. **Modélisation** : Noms → Objets, Adjectifs → Attributs, Verbes → Méthodes

---

**Prochaine section** : [Section 3 : Java - Les Fondamentaux →](../03-Java-Bases/README.md)
