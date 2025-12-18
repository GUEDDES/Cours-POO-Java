# Section 7 : Packages (Paquetages)

## 🎯 Objectifs d'apprentissage

À la fin de cette section, vous serez capable de :
- Comprendre l'utilité des packages
- Organiser votre code en packages
- Importer des classes d'autres packages
- Utiliser les conventions de nommage
- Gérer les conflits de noms

---

## 1. Qu'est-ce qu'un Package ?

### 1.1 Définition

Un **package** est un **espace de noms** qui permet de **regrouper** des classes logiquement liées.

**Analogie** : Un package = un dossier sur votre ordinateur

```
📁 mon-projet/
    📁 com/
        📁 entreprise/
            📁 models/
                📜 Utilisateur.java
                📜 Produit.java
            📁 services/
                📜 UtilisateurService.java
                📜 ProduitService.java
            📁 utils/
                📜 DateUtils.java
                📜 StringUtils.java
```

---

### 1.2 Avantages des Packages

#### 1. Organisation logique
```
Au lieu de :
- Utilisateur.java
- Produit.java
- Commande.java
- UtilisateurService.java
- ProduitService.java
- CommandeService.java
- ...
(tous mélangés)

On a :
models/
  - Utilisateur.java
  - Produit.java
  - Commande.java
services/
  - UtilisateurService.java
  - ProduitService.java
  - CommandeService.java
```

#### 2. Éviter les conflits de noms
```java
// Deux classes "Date" peuvent coexister
java.util.Date          // Date du JDK
java.sql.Date           // Date SQL
com.entreprise.Date     // Votre propre classe Date
```

#### 3. Contrôle d'accès
```java
// Classes visibles uniquement dans le même package
class ClasseInterne { }  // Pas de mot-clé public
```

#### 4. Réutilisabilité
```
Vous pouvez partager un package complet comme bibliothèque
```

---

## 2. Créer et Utiliser des Packages

### 2.1 Déclarer un Package

**Première ligne du fichier Java** :
```java
package com.entreprise.models;

public class Utilisateur {
    private String nom;
    private String email;
    
    public Utilisateur(String nom, String email) {
        this.nom = nom;
        this.email = email;
    }
    
    public String getNom() {
        return nom;
    }
}
```

**Structure de dossiers correspondante** :
```
com/
  entreprise/
    models/
      Utilisateur.java
```

---

### 2.2 Convention de Nommage

**Format** : `domaine.entreprise.module.sous-module`

| Organisation | Package |
|--------------|----------|
| Google | `com.google.android.apps` |
| Apache | `org.apache.commons.lang` |
| GitHub | `com.github.username.project` |
| Université | `edu.universite.departement` |

**Règles** :
- Tout en **minuscules**
- Pas d'espaces, pas de caractères spéciaux
- Utiliser `.` pour séparer les niveaux

**Exemples** :
```java
package com.isitcom.bibliotheque.models;
package com.isitcom.bibliotheque.services;
package com.isitcom.bibliotheque.controllers;
package com.isitcom.bibliotheque.utils;
```

---

## 3. Importer des Classes

### 3.1 Import Spécifique (Recommandé)

```java
package com.entreprise.main;

import com.entreprise.models.Utilisateur;
import com.entreprise.models.Produit;
import java.util.ArrayList;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        ArrayList<Utilisateur> utilisateurs = new ArrayList<>();
        Utilisateur user = new Utilisateur("Alice", "alice@exemple.com");
        utilisateurs.add(user);
    }
}
```

✅ **Avantages** :
- Dépendances **explicites**
- Code plus **lisible**
- Évite les **conflits**

---

### 3.2 Import avec Wildcard (Joker)

```java
package com.entreprise.main;

import com.entreprise.models.*;  // Importe TOUTES les classes du package
import java.util.*;

public class Application {
    public static void main(String[] args) {
        ArrayList<Utilisateur> utilisateurs = new ArrayList<>();
        HashMap<String, Produit> produits = new HashMap<>();
    }
}
```

⚠️ **Inconvénients** :
- Dépendances **cachées**
- Risque de **conflits** entre packages
- Moins **performant** à la compilation

✅ **Quand l'utiliser ?** : Prototypage rapide uniquement

---

### 3.3 Utiliser le Nom Complet (Sans Import)

```java
package com.entreprise.main;

public class Application {
    public static void main(String[] args) {
        // Utilisation du nom complet
        com.entreprise.models.Utilisateur user1 = 
            new com.entreprise.models.Utilisateur("Bob", "bob@exemple.com");
        
        java.util.ArrayList<String> liste = new java.util.ArrayList<>();
    }
}
```

⚠️ **Très verbeux** : à utiliser uniquement pour résoudre des conflits

---

### 3.4 Résoudre les Conflits de Noms

```java
import java.util.Date;  // Date du JDK
import java.sql.Date;   // ❌ ERREUR : conflit !

// Solution 1 : Importer une seule classe
import java.util.Date;

public class Test {
    public static void main(String[] args) {
        Date dateUtil = new Date();  // java.util.Date
        java.sql.Date dateSql = new java.sql.Date(System.currentTimeMillis());
    }
}

// Solution 2 : Utiliser les noms complets
public class Test {
    public static void main(String[] args) {
        java.util.Date dateUtil = new java.util.Date();
        java.sql.Date dateSql = new java.sql.Date(System.currentTimeMillis());
    }
}
```

---

## 4. Exemple Complet : Système de Bibliothèque

### 4.1 Structure du Projet

```
com/
  isitcom/
    bibliotheque/
      models/
        Livre.java
        Auteur.java
        Emprunt.java
      services/
        BibliothequeService.java
      utils/
        DateUtils.java
      Main.java
```

---

### 4.2 Fichier : models/Livre.java

```java
package com.isitcom.bibliotheque.models;

public class Livre {
    private String titre;
    private String isbn;
    private Auteur auteur;
    private boolean estDisponible;
    
    public Livre(String titre, String isbn, Auteur auteur) {
        this.titre = titre;
        this.isbn = isbn;
        this.auteur = auteur;
        this.estDisponible = true;
    }
    
    public String getTitre() {
        return titre;
    }
    
    public boolean estDisponible() {
        return estDisponible;
    }
    
    public void emprunter() {
        if (estDisponible) {
            estDisponible = false;
        }
    }
    
    public void retourner() {
        estDisponible = true;
    }
    
    @Override
    public String toString() {
        return titre + " par " + auteur.getNom() + 
               " (" + (estDisponible ? "Disponible" : "Emprunté") + ")";
    }
}
```

---

### 4.3 Fichier : models/Auteur.java

```java
package com.isitcom.bibliotheque.models;

public class Auteur {
    private String nom;
    private String nationalite;
    
    public Auteur(String nom, String nationalite) {
        this.nom = nom;
        this.nationalite = nationalite;
    }
    
    public String getNom() {
        return nom;
    }
    
    public String getNationalite() {
        return nationalite;
    }
}
```

---

### 4.4 Fichier : services/BibliothequeService.java

```java
package com.isitcom.bibliotheque.services;

import com.isitcom.bibliotheque.models.Livre;
import java.util.ArrayList;

public class BibliothequeService {
    private ArrayList<Livre> catalogue;
    
    public BibliothequeService() {
        this.catalogue = new ArrayList<>();
    }
    
    public void ajouterLivre(Livre livre) {
        catalogue.add(livre);
        System.out.println("✅ Livre ajouté : " + livre.getTitre());
    }
    
    public void afficherCatalogue() {
        System.out.println("\n📚 CATALOGUE :");
        for (Livre livre : catalogue) {
            System.out.println("- " + livre);
        }
    }
    
    public Livre rechercherLivre(String titre) {
        for (Livre livre : catalogue) {
            if (livre.getTitre().equalsIgnoreCase(titre)) {
                return livre;
            }
        }
        return null;
    }
}
```

---

### 4.5 Fichier : Main.java

```java
package com.isitcom.bibliotheque;

import com.isitcom.bibliotheque.models.Livre;
import com.isitcom.bibliotheque.models.Auteur;
import com.isitcom.bibliotheque.services.BibliothequeService;

public class Main {
    public static void main(String[] args) {
        // Créer des auteurs
        Auteur orwell = new Auteur("George Orwell", "Britannique");
        Auteur tolkien = new Auteur("J.R.R. Tolkien", "Britannique");
        
        // Créer des livres
        Livre livre1 = new Livre("1984", "978-0451524935", orwell);
        Livre livre2 = new Livre("La Ferme des Animaux", "978-0452284244", orwell);
        Livre livre3 = new Livre("Le Seigneur des Anneaux", "978-0618640157", tolkien);
        
        // Créer le service
        BibliothequeService service = new BibliothequeService();
        
        // Ajouter les livres
        service.ajouterLivre(livre1);
        service.ajouterLivre(livre2);
        service.ajouterLivre(livre3);
        
        // Afficher le catalogue
        service.afficherCatalogue();
        
        // Emprunter un livre
        Livre trouve = service.rechercherLivre("1984");
        if (trouve != null) {
            trouve.emprunter();
            System.out.println("\n📚 Livre emprunté : " + trouve.getTitre());
        }
        
        // Afficher le catalogue mis à jour
        service.afficherCatalogue();
    }
}
```

---

### 4.6 Compilation et Exécution

```bash
# Compilation (depuis la racine du projet)
javac com/isitcom/bibliotheque/models/*.java
javac com/isitcom/bibliotheque/services/*.java
javac com/isitcom/bibliotheque/Main.java

# Exécution
java com.isitcom.bibliotheque.Main
```

**Sortie** :
```
✅ Livre ajouté : 1984
✅ Livre ajouté : La Ferme des Animaux
✅ Livre ajouté : Le Seigneur des Anneaux

📚 CATALOGUE :
- 1984 par George Orwell (Disponible)
- La Ferme des Animaux par George Orwell (Disponible)
- Le Seigneur des Anneaux par J.R.R. Tolkien (Disponible)

📚 Livre emprunté : 1984

📚 CATALOGUE :
- 1984 par George Orwell (Emprunté)
- La Ferme des Animaux par George Orwell (Disponible)
- Le Seigneur des Anneaux par J.R.R. Tolkien (Disponible)
```

---

## 5. Packages Standard de Java

### 5.1 Packages Courants

| Package | Description | Exemples de classes |
|---------|-------------|---------------------|
| `java.lang` | Classes de base (import automatique) | String, Math, System, Integer |
| `java.util` | Structures de données | ArrayList, HashMap, Scanner, Date |
| `java.io` | Entrées/Sorties fichiers | File, FileReader, BufferedReader |
| `java.nio` | Nouvelles I/O (plus performant) | Path, Files |
| `java.net` | Réseau | URL, Socket, HttpURLConnection |
| `java.sql` | Bases de données | Connection, Statement, ResultSet |
| `java.time` | Dates et heures (Java 8+) | LocalDate, LocalTime, Duration |

---

### 5.2 java.lang : Import Automatique

```java
// Pas besoin d'importer java.lang
public class Test {
    public static void main(String[] args) {
        String nom = "Java";     // java.lang.String
        int x = Math.abs(-10);   // java.lang.Math
        System.out.println(nom); // java.lang.System
    }
}
```

---

## 📝 Résumé de la Section

| Concept | Description |
|---------|-------------|
| **Package** | Espace de noms pour regrouper des classes |
| **Déclaration** | `package com.entreprise.module;` (première ligne) |
| **Import spécifique** | `import com.entreprise.models.Utilisateur;` |
| **Import wildcard** | `import com.entreprise.models.*;` (déconseillé) |
| **Convention** | Format : domaine.entreprise.module (minuscules) |

---

## 🎯 Points à Retenir

1. **Package** = organisation logique du code
2. Déclaration package = **première ligne** du fichier
3. Utilisez **import spécifique** (pas wildcard)
4. Convention : **com.entreprise.module** (tout en minuscules)
5. `java.lang` est importé **automatiquement**

---

**Prochaine section** : [Section 8 : Encapsulation →](../08-Encapsulation/README.md)
