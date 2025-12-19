# TD 1 : Fondements de POO

## 📋 Informations

- **Titre** : TD 1 - Fondements de POO
- **Niveau** : 2ème année Licence Informatique
- **Année Universitaire** : 2025-2026
- **Enseignants** : BEN ABDELJELIL MOUNA & Abdelweheb GUEDDES
- **Institution** : Institut Supérieur d'Informatique et des Technologies de Communication, Hammam Sousse

---

## 🎯 Objectifs

Ce TD vise à renforcer les bases de la programmation Java en manipulant :
- Les structures de contrôle (`for`, `if-else`, `switch`, `do-while`)
- La saisie utilisateur avec `Scanner`
- Les types primitifs et les chaînes de caractères
- La validation des entrées utilisateur

---

## 📝 Exercices

### Exercice 1 : Calcul du factoriel

**Objectif** : Écrire un programme Java permettant de saisir un nombre entier à partir du clavier et de calculer et d'afficher le factoriel de ce nombre.

**Rappel** :
- Le factoriel de *n* est noté *n!*
- Formule : *n! = n × (n-1) × (n-2) × ... × 2 × 1*
- Par convention : *0! = 1*
- Exemple : *5! = 5 × 4 × 3 × 2 × 1 = 120*

📄 **[Voir l'énoncé complet](enonce.md#exercice-1)**  
💻 **[Voir la correction](corrections/Factoriel.java)**

---

### Exercice 2 : Calculatrice simple

**Objectif** : Écrire un programme Java permettant de saisir deux nombres entiers à partir du clavier, puis de calculer et d'afficher leur somme, produit, différence et quotient selon une valeur saisie au clavier qui représente le type de l'opération.

**Opérations à implémenter** :
- Addition (somme)
- Soustraction (différence)
- Multiplication (produit)
- Division (quotient)

📄 **[Voir l'énoncé complet](enonce.md#exercice-2)**  
💻 **[Voir la correction](corrections/Calculatrice.java)**

---

### Exercice 3 : Recherche de caractère

**Objectif** : Écrire un programme Java permettant de rechercher un caractère X dans une chaîne de caractères CH et de calculer et afficher le nombre d'occurrence de ce caractère dans CH.

**Concepts utilisés** :
- Parcours d'une chaîne de caractères
- Méthode `charAt(i)` pour accéder au caractère à la position i
- Méthode `length()` pour obtenir la longueur
- Compteur pour les occurrences

📄 **[Voir l'énoncé complet](enonce.md#exercice-3)**  
💻 **[Voir la correction](corrections/RechercheCaractere.java)**

---

### Exercice 4 : Jeu du nombre secret

**Objectif** : Écrire un programme Java permettant de saisir un nombre entier à partir du clavier et de le comparer avec un nombre nommé secret.

**Règles** :
- Si le nombre saisi est supérieur au nombre secret, afficher "nombre saisi supérieur au nombre secret"
- Si le nombre saisi est inférieur au nombre secret, afficher "nombre saisi inférieur au nombre secret"
- Si le nombre saisi est égal au nombre secret, afficher "nombre saisi égal au nombre secret"

📄 **[Voir l'énoncé complet](enonce.md#exercice-4)**  
💻 **[Voir la correction](corrections/NombreSecret.java)**  
🎁 **[Bonus : Version avec plusieurs tentatives](corrections/NombreSecretAvance.java)**

---

## 🔑 Points clés

### Structures de contrôle
- Boucle `for` (exercices 1 et 3)
- Structure conditionnelle `if-else` (exercices 1 et 4)
- Structure `switch` (exercice 2)
- Boucle `do-while` (bonus exercice 4)

### Manipulation de données
- Saisie avec `Scanner`
- Types primitifs : `int`, `long`, `char`, `double`
- Manipulation de chaînes : `String`, `charAt()`, `length()`
- Cast de types : `(double)`

### Bonnes pratiques
- Toujours valider les entrées utilisateur
- Gérer les cas d'erreur (division par zéro, nombres négatifs)
- Fermer les ressources avec `close()`
- Choisir le bon type de données (`long` pour factoriel)

---

## 📚 Ressources

- 📄 [Énoncés complets (PDF)](TD1-Fondements-Enonces.pdf)
- 📊 [Corrections (Beamer)](TD1-Fondements-Corrections.pdf)
- 💻 [Code source des corrections](corrections/)

---

## 🔗 Navigation

- ⬅️ [Retour au dépôt principal](../../)
- ➡️ [TD 2 : Objets & Classes](../TD2-Classes-Objets/)
