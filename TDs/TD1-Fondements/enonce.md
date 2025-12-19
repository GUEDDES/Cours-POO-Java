# TD 1 : Fondements de POO - Énoncés

## Exercice 1 : Calcul du factoriel

### Objectif

Écrire un programme Java permettant de saisir un nombre entier à partir du clavier et de calculer et d'afficher le factoriel de ce nombre.

### Rappel

- Le factoriel de *n* est noté *n!*
- Formule : *n! = n × (n-1) × (n-2) × ... × 2 × 1*
- Par convention : *0! = 1*
- Exemple : *5! = 5 × 4 × 3 × 2 × 1 = 120*

### Consignes

1. Demander à l'utilisateur de saisir un nombre entier
2. Vérifier que le nombre est positif ou nul
3. Calculer le factoriel
4. Afficher le résultat

### Points d'attention

- Utiliser le type `long` pour éviter les débordements
- Gérer le cas des nombres négatifs
- Tester avec : 0, 1, 5, 10, 20

---

## Exercice 2 : Calculatrice simple

### Objectif

Écrire un programme Java permettant de saisir deux nombres entiers à partir du clavier, puis de calculer et d'afficher leur somme, produit, différence et quotient selon une valeur saisie au clavier qui représente le type de l'opération.

### Opérations à implémenter

1. **Addition** : afficher `a + b = résultat`
2. **Soustraction** : afficher `a - b = résultat`
3. **Multiplication** : afficher `a × b = résultat`
4. **Division** : afficher `a ÷ b = résultat` (en décimal)

### Consignes

1. Saisir deux nombres entiers `a` et `b`
2. Afficher un menu avec les 4 opérations
3. Saisir le choix de l'utilisateur (1-4)
4. Effectuer l'opération correspondante
5. Afficher le résultat

### Points d'attention

- Utiliser une structure `switch` pour gérer les différents cas
- Gérer la division par zéro
- Utiliser le cast `(double)` pour la division
- Gérer les choix invalides

---

## Exercice 3 : Recherche de caractère

### Objectif

Écrire un programme Java permettant de rechercher un caractère X dans une chaîne de caractères CH et de calculer et afficher le nombre d'occurrence de ce caractère dans CH.

### Consignes

1. Saisir une chaîne de caractères `CH`
2. Saisir un caractère `X` à rechercher
3. Parcourir la chaîne pour compter les occurrences de `X`
4. Afficher le nombre total d'occurrences

### Méthodes utiles

- `String.length()` : retourne la longueur de la chaîne
- `String.charAt(int index)` : retourne le caractère à la position `index`

### Points d'attention

- Initialiser le compteur à 0
- Parcourir tous les caractères de la chaîne
- Tester avec des caractères présents et absents

---

## Exercice 4 : Jeu du nombre secret

### Objectif

Écrire un programme Java permettant de saisir un nombre entier à partir du clavier et de le comparer avec un nombre nommé secret.

### Règles

- Si le nombre saisi est **supérieur** au nombre secret, afficher "nombre saisi supérieur au nombre secret"
- Si le nombre saisi est **inférieur** au nombre secret, afficher "nombre saisi inférieur au nombre secret"  
- Si le nombre saisi est **égal** au nombre secret, afficher "nombre saisi égal au nombre secret. Bravo !"

### Consignes

1. Déclarer une constante `SECRET` avec la valeur de votre choix (ex: 42)
2. Demander à l'utilisateur de deviner le nombre
3. Comparer la saisie avec le nombre secret
4. Afficher le message approprié

### Amélioration (Bonus)

Modifier le programme pour permettre plusieurs tentatives jusqu'à ce que l'utilisateur trouve le nombre secret :
- Utiliser une boucle `do-while`
- Compter le nombre de tentatives
- Afficher "Bravo ! Trouvé en X tentative(s) !" à la fin

### Points d'attention

- Utiliser le mot-clé `final` pour déclarer la constante
- Tester les trois cas possibles
- Pour le bonus : penser à incrémenter le compteur de tentatives

---

## 🎯 Conseils généraux

1. **Testez systématiquement** vos programmes avec des valeurs limites
2. **Commentez votre code** pour expliquer les parties importantes
3. **Validez les entrées** utilisateur avant de les utiliser
4. **Fermez les ressources** (`Scanner`) avec `close()`
5. **Utilisez des noms de variables explicites**
