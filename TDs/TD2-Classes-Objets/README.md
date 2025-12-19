# TD 2 : Objets & Classes

## 📋 Informations

- **Titre** : TD 2 - Objets & Classes
- **Niveau** : 2ème année Licence Informatique
- **Année Universitaire** : 2025-2026
- **Enseignants** : BEN ABDELJELIL MOUNA & Abdelweheb GUEDDES
- **Institution** : Institut Supérieur d'Informatique et des Technologies de Communication, Hammam Sousse

---

## 🎯 Objectifs

Ce TD vise à maîtriser les concepts fondamentaux de la POO :
- Création de classes avec attributs privés
- Constructeurs (défaut, paramétrés, copie)
- Méthodes d'instance
- Encapsulation (getters/setters)
- Manipulation de tableaux d'objets
- Redéfinition de `toString()`

---

## 📝 Exercices

### Exercice 1 : Classe Complexe

**Objectif** : Créer une classe Java pour manipuler des nombres complexes de la forme *z = a + ib*

**Attributs privés** :
- `double re` : partie réelle
- `double im` : partie imaginaire

**Constructeurs** :
- Constructeur par défaut (0 + 0i)
- Constructeur avec paramètres (re, im)
- Constructeur de copie

**Méthodes** :
- `void afficher()` : affiche le nombre
- `Complexe addition(Complexe c)` : retourne la somme
- `Complexe soustraction(Complexe c)` : retourne la différence
- `Complexe multiplication(Complexe c)` : retourne le produit
- `double module()` : retourne √(a² + b²)
- `String toString()` : représentation textuelle

**Formules** :
- Multiplication : `(a + bi)(c + di) = (ac - bd) + (ad + bc)i`
- Module : `|z| = √(a² + b²)`

📄 **[Voir l'énoncé complet](enonce.md#exercice-1)**  
💻 **[Voir la correction](corrections/Complexe.java)**

---

### Exercice 2 : Classe Etudiant

**Objectif** : Manipuler des objets de type Étudiant avec encapsulation

**Attributs privés** :
- `int matricule` : numéro d'inscription
- `String nom` : nom de l'étudiant
- `String prenom` : prénom de l'étudiant
- `double moyenne` : moyenne générale

**Méthodes** :
- `void saisir()` : saisie au clavier
- `void afficher()` : affichage des informations
- `boolean admis()` : retourne true si moyenne ≥ 10
- `String mention()` : retourne la mention
- `String toString()` : représentation formatée

**Programme principal** :
1. Déclarer et saisir n étudiants
2. Afficher la liste complète
3. Afficher uniquement les admis
4. Afficher le meilleur étudiant

**Barème des mentions** :

| Moyenne | Mention |
|---------|----------|
| < 10 | Aucune |
| 10 - 11.99 | Passable |
| 12 - 13.99 | Assez bien |
| 14 - 15.99 | Bien |
| ≥ 16 | Très bien |

📄 **[Voir l'énoncé complet](enonce.md#exercice-2)**  
💻 **[Voir la correction](corrections/Etudiant.java)**

---

## 🔑 Concepts POO illustrés

### Exercice 1 - Classe Complexe

- ✅ Encapsulation des données (attributs privés)
- ✅ Surcharge de constructeurs
- ✅ Méthodes d'instance retournant des objets
- ✅ Redéfinition de `toString()`

### Exercice 2 - Classe Etudiant

- ✅ Gestion d'un tableau d'objets
- ✅ Méthodes de saisie et d'affichage
- ✅ Méthodes booléennes pour les tests
- ✅ Recherche du maximum dans un tableau d'objets

---

## ❗ Bonnes pratiques

### Points d'attention

- ⚠️ Toujours fermer les `Scanner` avec `close()`
- ⚠️ Utiliser `sc.nextLine()` après `nextInt()` pour consommer le retour à la ligne
- ⚠️ Privilégier les attributs privés avec des getters/setters
- ⚠️ Documenter les formules mathématiques complexes

### Recommandations

- ✅ Tester avec des valeurs limites (0, négatifs)
- ✅ Vérifier les cas particuliers (aucun étudiant admis)
- ✅ Ajouter la validation des entrées utilisateur

---

## 📚 Ressources

- 📄 [Énoncés complets](enonce.md)
- 📊 [Corrections (PDF Beamer)](TD2-Classes-Objets-Corrections.pdf)
- 💻 [Code source des corrections](corrections/)

---

## 🔗 Navigation

- ⬅️ [TD 1 : Fondements de POO](../TD1-Fondements/)
- ➡️ [TD 3 : Révision & Synthèse](../TD3-Revision-Synthese/)
- 🏠 [Retour au dépôt principal](../../)
