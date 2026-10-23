# Section 4 : Classes et Objets en Java

## 🎯 Objectifs d'apprentissage

À la fin de cette section, vous serez capable de :
- Définir une classe en Java
- Créer et manipuler des objets
- Écrire des constructeurs (avec et sans paramètres)
- Utiliser le mot-clé `this` pour lever l'ambiguïté et chaîner les constructeurs
- Comprendre la surcharge de méthodes
- Maîtriser le Garbage Collector
- Comprendre le contrat fondamental de la classe `Object` (`equals` et `hashCode`)

---

## 1. Définir une Classe Simple

### 1.1 Syntaxe de Base

```java
class NomClasse {
    // Attributs (variables d'instance)
    type attribut1;
    type attribut2;
    
    // Méthodes
    typeRetour methode1() {
        // code
    }
}
```

---

### 1.2 Exemple : Classe Smartphone

```java
class Smartphone {
    // Attributs
    String marque;
    String modele;
    int batterie;  // Pourcentage de batterie (0-100)
    boolean estAllume;
    
    // Méthode pour allumer le téléphone
    void allumer() {
        if (!estAllume) {
            estAllume = true;
            System.out.println(marque + " " + modele + " est maintenant allumé");
        } else {
            System.out.println("Le téléphone est déjà allumé");
        }
    }
    
    // Méthode pour éteindre
    void eteindre() {
        if (estAllume) {
            estAllume = false;
            System.out.println("Téléphone éteint");
        }
    }
    
    // Méthode pour recharger
    void recharger(int pourcentage) {
        batterie += pourcentage;
        if (batterie > 100) {
            batterie = 100;
        }
        System.out.println("Batterie : " + batterie + "%");
    }
    
    // Méthode pour afficher les informations
    void afficherInfos() {
        System.out.println("Marque : " + marque);
        System.out.println("Modèle : " + modele);
        System.out.println("Batterie : " + batterie + "%");
        System.out.println("État : " + (estAllume ? "Allumé" : "Éteint"));
    }
}
```

---

## 2. Créer et Utiliser des Objets

### 2.1 Création d'un Objet

```java
public class TestSmartphone {
    public static void main(String[] args) {
        // 1. Déclaration
        Smartphone monTelephone;
        
        // 2. Instanciation (création en mémoire)
        monTelephone = new Smartphone();
        
        // Alternative : déclaration + instanciation
        Smartphone tel2 = new Smartphone();
    }
}
```

**Schéma mémoire** :
```
monTelephone ──────┐
                   │
                   ▼
            ┌──────────────────┐
            │  Objet Smartphone│
            ├──────────────────┤
            │ marque: null     │
            │ modele: null     │
            │ batterie: 0      │
            │ estAllume: false │
            └──────────────────┘
```

---

### 2.2 Accéder aux Attributs et Méthodes

```java
public class TestSmartphone {
    public static void main(String[] args) {
        // Créer un objet
        Smartphone monTel = new Smartphone();
        
        // Affecter des valeurs aux attributs
        monTel.marque = "Samsung";
        monTel.modele = "Galaxy S24";
        monTel.batterie = 85;
        monTel.estAllume = false;
        
        // Appeler des méthodes
        monTel.afficherInfos();
        monTel.allumer();
        monTel.recharger(10);
    }
}
```

**Sortie** :
```
Marque : Samsung
Modèle : Galaxy S24
Batterie : 85%
État : Éteint
Samsung Galaxy S24 est maintenant allumé
Batterie : 95%
```

---

## 3. Les Constructeurs

### 3.1 Qu'est-ce qu'un Constructeur ?

Un **constructeur** est une méthode spéciale qui **initialise** un objet lors de sa création.

**Caractéristiques** :
- Même nom que la classe
- Pas de type de retour (même pas `void`)
- Appelé automatiquement avec `new`

---

### 3.2 Constructeur par Défaut

Si vous ne définissez aucun constructeur, Java crée automatiquement un **constructeur par défaut** (vide).

```java
class Smartphone {
    String marque;
    String modele;
    int batterie;
    
    // Constructeur par défaut (implicite si non défini)
    // Smartphone() { }
}

// Utilisation
Smartphone tel = new Smartphone();  // Appelle le constructeur par défaut
```

---

### 3.3 Constructeur avec Paramètres

```java
class Smartphone {
    String marque;
    String modele;
    int batterie;
    boolean estAllume;
    
    // Constructeur avec paramètres
    Smartphone(String m, String mod, int bat) {
        marque = m;
        modele = mod;
        batterie = bat;
        estAllume = false;  // Valeur par défaut
    }
    
    void afficherInfos() {
        System.out.println(marque + " " + modele + " - " + batterie + "%");
    }
}
```

---

## 4. Le Mot-Clé `this`

### 4.1 Lever l'Ambiguïté

Le mot-clé `this` fait référence à **l'objet courant**.

```java
class Smartphone {
    String marque;
    String modele;
    int batterie;
    
    // Constructeur avec paramètres ayant les mêmes noms
    Smartphone(String marque, String modele, int batterie) {
        this.marque = marque;      // this.marque = attribut, marque = paramètre
        this.modele = modele;
        this.batterie = batterie;
    }
}
```

---

### 4.2 Appeler un Autre Constructeur (`this(...)`)

```java
class Smartphone {
    String marque;
    String modele;
    int batterie;
    
    // Constructeur complet
    Smartphone(String marque, String modele, int batterie) {
        this.marque = marque;
        this.modele = modele;
        this.batterie = batterie;
    }
    
    // Constructeur avec batterie par défaut
    Smartphone(String marque, String modele) {
        this(marque, modele, 100);  // Appelle le constructeur complet
    }
    
    // Constructeur par défaut
    Smartphone() {
        this("Inconnu", "Inconnu", 0);
    }
}
```

---

## 5. Surcharge de Méthodes

### 5.1 Principe

La **surcharge** permet d'avoir **plusieurs méthodes avec le même nom** mais des **signatures différentes** (paramètres différents).

```java
class Calculatrice {
    int additionner(int a, int b) {
        return a + b;
    }
    
    int additionner(int a, int b, int c) {
        return a + b + c;
    }
    
    double additionner(double a, double b) {
        return a + b;
    }
}
```

---

### 5.2 Signature d'une Méthode

**Signature = Nom + Types des paramètres (dans l'ordre)**

Le type de retour et les modificateurs ne font **PAS** partie de la signature.

---

## 6. Initialisation par Défaut

En Java, les attributs sont **automatiquement initialisés** avec des valeurs par défaut (`0`, `0.0`, `false`, `null`). Les **variables locales** doivent obligatoirement être initialisées avant lecture.

---

## 7. Gestion de la Mémoire : Garbage Collector

En Java, le ramasse-miettes (**Garbage Collector**) désalloue automatiquement la mémoire des objets non référencés.

---

## 8. La Classe Object et son Contrat

Toutes les classes en Java héritent implicitement de la classe racine `java.lang.Object`.

### 8.1 Méthode `toString()`
Retourne une chaîne décrivant l'objet textuellement.

### 8.2 Le Contrat `equals(Object)` et `hashCode()`

Pour comparer le **contenu logique** de deux objets et non leur adresse en mémoire :

```java
import java.util.Objects;

class Smartphone {
    String marque;
    String modele;
    
    Smartphone(String marque, String modele) {
        this.marque = marque;
        this.modele = modele;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Smartphone autre = (Smartphone) obj;
        return Objects.equals(marque, autre.marque) && Objects.equals(modele, autre.modele);
    }

    @Override
    public int hashCode() {
        return Objects.hash(marque, modele);
    }
}
```

> ⚠️ **Règle d'or du contrat Java** : Si deux objets sont égaux selon `equals()`, ils **DOIVENT impérativement renvoyer le même entier via `hashCode()`**. Sans cela, l'insertion dans des tables de hachage (`HashSet`, `HashMap`) échoue silencieusement.

---

## 9. Pièges Fréquents & Anti-Patterns

| Piège fréquent | Erreur constatée | Solution rigoureuse |
| :--- | :--- | :--- |
| **Comparaison par `==`** | `s1 == s2` compare les pointeurs mémoire, pas les valeurs. | Toujours utiliser `s1.equals(s2)`. |
| **Oubli de `hashCode()`** | Redéfinir `equals()` seul. | Toujours redéfinir `hashCode()` en tandem avec `equals()`. |
| **Variables locales non initialisées** | `int x; System.out.println(x);` (erreur compilation). | Affecter une valeur explicite avant la première lecture. |
| **Oubli du mot-clé `this`** | `marque = marque;` n'affecte pas l'attribut d'instance. | Utiliser `this.marque = marque;`. |

> 💡 **Remarque didactique** : Pour faciliter l'initiation aux objets, les attributs de cette section ont une visibilité par défaut. Dès la **[Section 8 : Encapsulation](../08-Encapsulation/)**, vous découvrirez comment protéger vos données avec `private` et des accesseurs validés.

---

## 📝 Résumé de la Section

| Concept | Points clés |
|---------|-------------|
| **Classe** | Modèle définissant attributs + méthodes |
| **Objet** | Instance concrète créée avec `new` |
| **Constructeur** | Initialise un nouvel objet lors de sa création |
| **`this`** | Référence à l'instance courante |
| **Surcharge** | Même nom, listes de paramètres différentes |
| **Object** | Mère universelle : respecter `equals()` et `hashCode()` |

---

**Prochaine section** : [Section 5 : Éléments Statiques →](../05-Elements-Statiques/README.md)
