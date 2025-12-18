# Section 4 : Classes et Objets en Java

## 🎯 Objectifs d'apprentissage

À la fin de cette section, vous serez capable de :
- Définir une classe en Java
- Créer et manipuler des objets
- Écrire des constructeurs (avec et sans paramètres)
- Utiliser le mot-clé `this`
- Comprendre la surcharge de méthodes
- Maîtriser le Garbage Collector

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

**Utilisation** :
```java
// Avant (sans constructeur)
Smartphone tel1 = new Smartphone();
tel1.marque = "Apple";
tel1.modele = "iPhone 15";
tel1.batterie = 100;

// Maintenant (avec constructeur)
Smartphone tel2 = new Smartphone("Apple", "iPhone 15", 100);
tel2.afficherInfos();  // Apple iPhone 15 - 100%
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

**Sans `this`** :
```java
Smartphone(String marque, String modele, int batterie) {
    marque = marque;  // ❌ Affecte le paramètre à lui-même !
    // L'attribut reste null
}
```

---

### 4.2 Appeler un Autre Constructeur

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

**Utilisation** :
```java
Smartphone tel1 = new Smartphone("Apple", "iPhone", 95);
Smartphone tel2 = new Smartphone("Samsung", "Galaxy");  // Batterie = 100
Smartphone tel3 = new Smartphone();  // Marque = "Inconnu", Batterie = 0
```

---

## 5. Surcharge de Méthodes

### 5.1 Principe

La **surcharge** permet d'avoir **plusieurs méthodes avec le même nom** mais des **signatures différentes** (paramètres différents).

```java
class Calculatrice {
    // Addition de 2 entiers
    int additionner(int a, int b) {
        return a + b;
    }
    
    // Addition de 3 entiers (SURCHARGE)
    int additionner(int a, int b, int c) {
        return a + b + c;
    }
    
    // Addition de 2 doubles (SURCHARGE)
    double additionner(double a, double b) {
        return a + b;
    }
}
```

**Utilisation** :
```java
Calculatrice calc = new Calculatrice();

System.out.println(calc.additionner(5, 3));        // 8
System.out.println(calc.additionner(5, 3, 2));     // 10
System.out.println(calc.additionner(5.5, 3.2));    // 8.7
```

---

### 5.2 Signature d'une Méthode

**Signature = Nom + Types des paramètres (dans l'ordre)**

```java
// ✅ VALIDE : Signatures différentes
void methode(int a)              // Signature 1
void methode(double a)           // Signature 2
void methode(int a, int b)       // Signature 3
void methode(int a, double b)    // Signature 4

// ❌ ERREUR : Même signature
int methode(int a)               // ❌ Même que Signature 1
// Le type de retour ne fait PAS partie de la signature
```

---

## 6. Initialisation par Défaut

### 6.1 Valeurs par Défaut des Attributs

En Java, les attributs sont **automatiquement initialisés** avec des valeurs par défaut :

| Type | Valeur par défaut |
|------|------------------|
| `byte`, `short`, `int`, `long` | `0` |
| `float`, `double` | `0.0` |
| `char` | `'\u0000'` (caractère null) |
| `boolean` | `false` |
| Objets (String, etc.) | `null` |

```java
class Test {
    int nombre;       // 0
    double prix;      // 0.0
    boolean actif;    // false
    String nom;       // null
    
    void afficher() {
        System.out.println("nombre = " + nombre);  // 0
        System.out.println("prix = " + prix);      // 0.0
        System.out.println("actif = " + actif);    // false
        System.out.println("nom = " + nom);        // null
    }
}
```

⚠️ **Attention** : Les **variables locales** (dans les méthodes) ne sont PAS initialisées automatiquement !

```java
void methode() {
    int x;
    System.out.println(x);  // ❌ ERREUR : x non initialisé
}
```

---

## 7. Gestion de la Mémoire : Garbage Collector

### 7.1 Principe

En Java, vous n'avez **pas besoin de libérer manuellement la mémoire**. Le **Garbage Collector** (GC) s'en charge automatiquement.

**Fonctionnement** :
```
1. Un objet est créé avec new
2. Il est utilisé
3. Quand plus aucune référence ne pointe vers lui → GC le supprime
```

---

### 7.2 Exemple

```java
public class TestGC {
    public static void main(String[] args) {
        // Créer un objet
        Smartphone tel1 = new Smartphone("Apple", "iPhone", 100);
        
        // Créer un deuxième objet
        Smartphone tel2 = new Smartphone("Samsung", "Galaxy", 85);
        
        // Réaffecter tel1
        tel1 = tel2;  // L'ancien objet "iPhone" n'est plus référencé
        
        // ✅ Le Garbage Collector va automatiquement libérer
        // la mémoire de l'objet "iPhone"
        
        // Supprimer toutes les références
        tel1 = null;
        tel2 = null;  // Les 2 objets seront collectés par le GC
    }
}
```

**Schéma** :
```
Avant tel1 = tel2 :
tel1 ──→ [iPhone]   tel2 ──→ [Galaxy]

Après tel1 = tel2 :
tel1 ──→ [Galaxy]   tel2 ──→ [Galaxy]
         ↑──────────────────┘
         
[iPhone]  ← Plus aucune référence → GC le supprime
```

---

### 7.3 Forcer le Garbage Collector (déconseillé)

```java
System.gc();  // Suggère au GC de se lancer (pas garanti)
```

⚠️ **Ne PAS utiliser en production** : Le GC sait mieux que vous quand se lancer !

---

## 8. La Classe Object

### 8.1 Hiérarchie des Classes

En Java, **toutes les classes héritent implicitement de `Object`**.

```
        Object (classe mère de toutes les classes)
          ↑
          |
    Smartphone, Livre, CompteBancaire, etc.
```

---

### 8.2 Méthodes Importantes de Object

#### toString()

Retourne une représentation textuelle de l'objet.

```java
class Smartphone {
    String marque;
    String modele;
    
    Smartphone(String marque, String modele) {
        this.marque = marque;
        this.modele = modele;
    }
    
    // Redéfinition de toString()
    @Override
    public String toString() {
        return marque + " " + modele;
    }
}
```

**Utilisation** :
```java
Smartphone tel = new Smartphone("Apple", "iPhone 15");

// Sans toString() redéfini
System.out.println(tel);  // Smartphone@15db9742 (adresse mémoire)

// Avec toString() redéfini
System.out.println(tel);  // Apple iPhone 15
```

---

#### equals(Object obj)

Compare deux objets pour vérifier s'ils sont "égaux".

```java
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
        return marque.equals(autre.marque) && modele.equals(autre.modele);
    }
}
```

**Utilisation** :
```java
Smartphone tel1 = new Smartphone("Apple", "iPhone");
Smartphone tel2 = new Smartphone("Apple", "iPhone");

System.out.println(tel1 == tel2);        // false (références différentes)
System.out.println(tel1.equals(tel2));   // true (contenu identique)
```

---

## 📝 Résumé de la Section

| Concept | Points clés |
|---------|-------------|
| **Classe** | Modèle définissant attributs + méthodes |
| **Objet** | Instance concrète créée avec `new` |
| **Constructeur** | Méthode spéciale pour initialiser un objet |
| **`this`** | Référence à l'objet courant |
| **Surcharge** | Plusieurs méthodes avec le même nom mais signatures différentes |
| **Garbage Collector** | Libère automatiquement la mémoire |
| **Object** | Classe mère de toutes les classes Java |

---

## 🎯 Points à Retenir

1. **Constructeur** = même nom que la classe, pas de type de retour
2. Utilisez **`this`** pour lever l'ambiguïté paramètres/attributs
3. La **surcharge** permet de créer plusieurs versions d'une méthode
4. Les **attributs** sont initialisés automatiquement, pas les **variables locales**
5. Le **Garbage Collector** gère la mémoire automatiquement

---

**Prochaine section** : [Section 5 : Éléments Statiques →](../05-Elements-Statiques/README.md)
