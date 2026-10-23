# Exercice 2 : Gestion d'un Catalogue d'Articles et Contrat d'Égalité (Avancé)

## 🎯 Objectifs

- Mettre en pratique le chaînage de constructeurs avec `this(...)`.
- Appliquer la validation d'état lors de l'instanciation.
- Redéfinir rigoureusement le contrat `equals(Object)` et `hashCode()`.
- Manipuler un tableau d'objets pour vérifier l'unicité logique.

---

## 📋 Énoncé

Dans une application de gestion commerciale, on modélise les articles vendus.

### 1. Classe `Article`

Créez la classe `Article` comportant les attributs suivants :
- `String reference` : référence alphanumérique unique de l'article (ex: `"ART-001"`).
- `String designation` : nom ou description courte de l'article.
- `double prix` : prix unitaire hors taxe.

#### Travail à réaliser :
1. Écrire le constructeur principal à trois paramètres : `Article(String reference, String designation, double prix)`. Si la référence est `null` ou vide, ou si le prix est strictement négatif, initialiser avec des valeurs par défaut sûres.
2. Écrire un second constructeur : `Article(String reference, String designation)` qui initialise le prix à `0.0` en appelant le premier constructeur avec `this(...)`.
3. Redéfinir `toString()` pour retourner une chaîne sous le format : `[ART-001] Ordinateur Portable - 1250.0 DT`.
4. Deux articles sont considérés **égaux** si et seulement s'ils possèdent la même référence (insensible à la casse). Redéfinissez `equals(Object obj)` et `hashCode()` pour respecter ce contrat.

---

### 2. Classe `Catalogue`

Créez la classe `Catalogue` pour gérer une collection d'articles dans un tableau :
- Attributs : `Article[] articles` et `int nombreArticles`.
- Constructeur prenant la capacité maximale du catalogue.
- Méthode `boolean ajouterArticle(Article a)` : ajoute l'article seulement s'il n'est pas déjà présent dans le catalogue (utiliser `equals()` pour la détection des doublons).
- Méthode `Article rechercherArticle(String reference)` : renvoie l'article correspondant ou `null` s'il n'existe pas.
- Méthode `void afficherCatalogue()` : liste tous les articles présents.

---

### 3. Classe de Test

Créez une classe `TestCatalogue` pour valider le bon fonctionnement :
1. Instancier plusieurs articles (dont deux ayant la même référence `"ART-100"` mais des désignations et des casses de référence différentes comme `"art-100"`).
2. Tester l'ajout au catalogue et observer le rejet du doublon.
3. Vérifier la conformité de `equals()` et `hashCode()`.
