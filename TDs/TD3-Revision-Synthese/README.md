# TD 3 : Révision & Synthèse

## 📋 Informations

- **Titre** : TD 3 - Révision & Synthèse (Héritage, classe abstraite, interface, static, final, constructeurs)
- **Niveau** : 2ème année Licence Informatique
- **Année Universitaire** : 2025-2026
- **Enseignants** : BEN ABDELJELIL MOUNA & Abdelweheb GUEDDES
- **Institution** : Institut Supérieur d'Informatique et des Technologies de Communication, Hammam Sousse

---

## 🎯 Objectifs

Ce TD synthétise les concepts avancés de la POO :
- **Héritage** : extension de classes, appel `super()`
- **Classe abstraite** : méthodes abstraites et concrètes
- **Interface** : contrats d'implémentation
- **Modificateurs** : `static`, `final`, `protected`
- **Polymorphisme** : manipulation de tableaux d'objets hétérogènes

---

## 📝 Exercices

### Exercice 1 : Questions de cours

**Questions théoriques** :

1. **Différence entre attribut `static` et attribut d'instance**
2. **Définition d'un objet en Java et lien avec la classe**
3. **Différences entre classe abstraite et interface + syntaxe**
4. **Constructeur sans paramètre vs avec paramètre**
5. **Rôle essentiel de la JVM**

📄 **[Voir les réponses](corrections/Exercice1-Reponses.md)**

---

### Exercice 2 : Système de gestion de transport

**Objectif** : Modéliser un système de gestion de véhicules avec héritage, interfaces et polymorphisme.

#### 1. Classe abstraite `Vehicule`

**Attributs protégés** :
- `String marque`
- `String modele`
- `int annee`

**Méthodes** :
- Constructeur paramétré
- Méthode abstraite `void decrire()`
- Méthode `String getType()`

#### 2. Interfaces

**Interface `Conduisible`** :
```java
public interface Conduisible {
    void demarrer();
}
```

**Interface `Flottable`** :
```java
public interface Flottable {
    void naviguer();
}
```

#### 3. Classes concrètes

- **`Voiture extends Vehicule implements Conduisible`**
  - Redéfinit `decrire()` et `getType()`
  - Implémente `demarrer()`

- **`Bateau extends Vehicule implements Flottable`**
  - Redéfinit `decrire()` et `getType()`
  - Implémente `naviguer()`

#### 4. Classe `GestionTransport`

**Attributs** :
- `Vehicule[] vehicules` : tableau de véhicules
- `int nbVehicules` : compteur

**Méthodes** :
- `void ajouterVehicule(Vehicule vehicule)`
- `void decrireTousLesVehicules()`

#### 5. Classe `VoitureElectrique extends Voiture`

**Attribut supplémentaire** :
- `double capaciteBatterie` (en kWh)

**Méthode spécifique** :
- `double calculerAutonomie(double consommation)`

📄 **[Voir l'énoncé complet](enonce.md#exercice-2)**  
💻 **[Voir les corrections](corrections/)**

---

### Exercice 3 : Classe `Bibliotheque` avec attribut `static`

**Objectif** : Comprendre l'utilisation des attributs et méthodes `static`

**Classe `Bibliotheque`** :
- Attribut `static int nombreLivres`
- Méthode `void ajouterLivre()`
- Méthode `static void retirerLivre()`
- Méthode `final int getNombreLivres()`

📄 **[Voir l'énoncé complet](enonce.md#exercice-3)**  
💻 **[Voir la correction](corrections/Bibliotheque.java)**

---

## 🔑 Concepts clés illustrés

### Héritage
- ✅ Extension de classe abstraite
- ✅ Appel du constructeur parent avec `super()`
- ✅ Redéfinition de méthodes avec `@Override`
- ✅ Attributs `protected` accessibles aux sous-classes

### Interfaces
- ✅ Implémentation de contrats
- ✅ Héritage multiple via interfaces
- ✅ Typage polymorphe

### Polymorphisme
- ✅ Tableau de type parent contenant des sous-classes
- ✅ Appel dynamique de méthodes redéfinies
- ✅ Opérateur `instanceof` pour vérifier le type

### Modificateurs
- ✅ `static` : partagé par toutes les instances
- ✅ `final` : méthode non redéfinissable
- ✅ `abstract` : méthode sans corps, classe non instanciable

---

## 💡 Points importants

### Différences classe abstraite vs interface

| Aspect | Classe abstraite | Interface |
|--------|------------------|------------|
| **Méthodes** | Concrètes et abstraites | Abstraites (+ défaut Java 8+) |
| **Attributs** | Oui (tous types) | Constantes uniquement |
| **Constructeur** | Oui | Non |
| **Héritage** | Simple (1 seule) | Multiple (plusieurs) |
| **Usage** | Relation "est-un" forte | Contrat de capacité |

### Utilisation de `static`

```java
// Attribut statique : partagé par toutes les instances
private static int compteur = 0;

// Méthode statique : appelée sans instance
public static void afficherCompteur() {
    System.out.println(compteur);
}

// Appel
MaClasse.afficherCompteur(); // Sans créer d'objet
```

---

## 📚 Ressources

- 📄 [Énoncés complets](enonce.md)
- 📊 [Corrections (PDF)](TD3-Revision-Synthese-Corrections.pdf)
- 💻 [Code source des corrections](corrections/)

---

## 🔗 Navigation

- ⬅️ [TD 2 : Objets & Classes](../TD2-Classes-Objets/)
- 🏠 [Retour au dépôt principal](../../)
