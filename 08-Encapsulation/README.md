# Section 8 : Encapsulation

## 🎯 Objectifs d'apprentissage

À la fin de cette section, vous serez capable de :
- Comprendre le principe d'encapsulation
- Utiliser les modificateurs d'accès (private, public, protected, default)
- Créer des getters et setters
- Appliquer la validation dans les setters
- Concevoir des classes bien encapsulées

---

## 1. Qu'est-ce que l'Encapsulation ?

### 1.1 Définition

**Encapsulation** = **Masquer** les détails d'implémentation et **protéger** l'intégrité des données.

**Principe** : 
- Les **attributs** sont **privés** (`private`)
- L'accès se fait via des **méthodes publiques** (getters/setters)

---

### 1.2 Problème Sans Encapsulation

```java
class CompteBancaire {
    public double solde;  // ❌ MAUVAIS : public
}

public class Test {
    public static void main(String[] args) {
        CompteBancaire compte = new CompteBancaire();
        compte.solde = 1000;
        
        // ❌ PROBLÈME : modification anarchique
        compte.solde = -5000;  // Solde négatif !
        compte.solde = 999999999;  // Montant irréaliste !
        
        // Aucune validation, aucun contrôle
    }
}
```

---

### 1.3 Solution Avec Encapsulation

```java
class CompteBancaire {
    private double solde;  // ✅ BON : privé
    
    public CompteBancaire(double soldeInitial) {
        if (soldeInitial >= 0) {
            this.solde = soldeInitial;
        } else {
            this.solde = 0;
        }
    }
    
    // Getter : lire le solde
    public double getSolde() {
        return solde;
    }
    
    // Méthodes contrôlées pour modifier
    public void deposer(double montant) {
        if (montant > 0) {
            solde += montant;
            System.out.println("+" + montant + "€ déposés");
        } else {
            System.out.println("❌ Montant invalide");
        }
    }
    
    public void retirer(double montant) {
        if (montant > 0 && montant <= solde) {
            solde -= montant;
            System.out.println("-" + montant + "€ retirés");
        } else {
            System.out.println("❌ Opération impossible");
        }
    }
}

public class Test {
    public static void main(String[] args) {
        CompteBancaire compte = new CompteBancaire(1000);
        
        // ✅ Accès contrôlé
        System.out.println("Solde : " + compte.getSolde() + "€");
        compte.deposer(500);
        compte.retirer(200);
        
        // ❌ Impossible d'accéder directement
        // compte.solde = -5000;  // ERREUR DE COMPILATION
    }
}
```

---

## 2. Modificateurs d'Accès

### 2.1 Les 4 Niveaux

| Modificateur | Classe | Package | Sous-classe | Monde |
|--------------|--------|---------|-------------|-------|
| `private` | ✅ | ❌ | ❌ | ❌ |
| (default) | ✅ | ✅ | ❌ | ❌ |
| `protected` | ✅ | ✅ | ✅ | ❌ |
| `public` | ✅ | ✅ | ✅ | ✅ |

**Ordre de restrictivité** (du plus au moins restrictif) :
```
private < default < protected < public
```

---

### 2.2 Exemples

#### `private` : Uniquement dans la classe

```java
class Personne {
    private String numeroSecu;  // Très sensible
    
    public Personne(String numeroSecu) {
        this.numeroSecu = numeroSecu;
    }
    
    private void afficherNumeroSecu() {
        System.out.println(numeroSecu);  // ✅ OK dans la classe
    }
}

class Test {
    public static void main(String[] args) {
        Personne p = new Personne("123456789");
        // p.numeroSecu;  // ❌ ERREUR
        // p.afficherNumeroSecu();  // ❌ ERREUR
    }
}
```

---

#### `public` : Accessible partout

```java
public class Calculatrice {
    public int additionner(int a, int b) {  // Accessible partout
        return a + b;
    }
}

// Autre fichier, autre package
import com.exemple.Calculatrice;

public class Test {
    public static void main(String[] args) {
        Calculatrice calc = new Calculatrice();
        System.out.println(calc.additionner(5, 3));  // ✅ OK
    }
}
```

---

#### `protected` : Classe + Package + Sous-classes

```java
package com.exemple;

public class Animal {
    protected String espece;  // Accessible aux sous-classes
    
    protected void seDeplacer() {
        System.out.println("L'animal se déplace");
    }
}

// Autre package
package com.exemple.animaux;
import com.exemple.Animal;

public class Chien extends Animal {
    public void aboyer() {
        System.out.println(espece + " aboie");  // ✅ OK (héritage)
        seDeplacer();  // ✅ OK
    }
}
```

---

## 3. Getters et Setters

### 3.1 Convention de Nommage

```java
class Produit {
    private String nom;
    private double prix;
    private boolean enStock;
    
    // Getter : getNom() pour un attribut "nom"
    public String getNom() {
        return nom;
    }
    
    // Setter : setNom(valeur) pour un attribut "nom"
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public double getPrix() {
        return prix;
    }
    
    public void setPrix(double prix) {
        this.prix = prix;
    }
    
    // Pour les booléens : is... au lieu de get...
    public boolean isEnStock() {
        return enStock;
    }
    
    public void setEnStock(boolean enStock) {
        this.enStock = enStock;
    }
}
```

**Règles** :
- Getter = `get` + Nom (première lettre en majuscule)
- Setter = `set` + Nom
- Pour `boolean` : `is` + Nom (au lieu de `get`)

---

### 3.2 Validation dans les Setters

```java
class Produit {
    private String nom;
    private double prix;
    private int quantite;
    
    public void setNom(String nom) {
        if (nom != null && !nom.trim().isEmpty()) {
            this.nom = nom;
        } else {
            throw new IllegalArgumentException("Nom invalide");
        }
    }
    
    public void setPrix(double prix) {
        if (prix >= 0) {
            this.prix = prix;
        } else {
            throw new IllegalArgumentException("Prix ne peut pas être négatif");
        }
    }
    
    public void setQuantite(int quantite) {
        if (quantite >= 0 && quantite <= 10000) {
            this.quantite = quantite;
        } else {
            throw new IllegalArgumentException("Quantité invalide (0-10000)");
        }
    }
}
```

---

### 3.3 Getters/Setters : Pas Toujours Nécessaires

```java
class Personne {
    private String nom;
    private final String numeroSecu;  // Immuable
    
    public Personne(String nom, String numeroSecu) {
        this.nom = nom;
        this.numeroSecu = numeroSecu;
    }
    
    // ✅ Getter pour nom
    public String getNom() {
        return nom;
    }
    
    // ✅ Setter pour nom
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    // ✅ Getter pour numeroSecu
    public String getNumeroSecu() {
        return numeroSecu;
    }
    
    // ❌ PAS de setter pour numeroSecu (immuable)
}
```

---

## 4. Exemple Complet : Classe Produit E-commerce

```java
class Produit {
    // Attributs privés
    private String nom;
    private String description;
    private double prixHT;
    private static final double TAUX_TVA = 0.20;  // 20%
    private int stock;
    private boolean actif;
    
    // Constructeur
    public Produit(String nom, double prixHT, int stock) {
        setNom(nom);
        setPrixHT(prixHT);
        setStock(stock);
        this.description = "";
        this.actif = true;
    }
    
    // Getters
    public String getNom() {
        return nom;
    }
    
    public String getDescription() {
        return description;
    }
    
    public double getPrixHT() {
        return prixHT;
    }
    
    public double getPrixTTC() {
        return prixHT * (1 + TAUX_TVA);
    }
    
    public int getStock() {
        return stock;
    }
    
    public boolean isActif() {
        return actif;
    }
    
    public boolean isDisponible() {
        return actif && stock > 0;
    }
    
    // Setters avec validation
    public void setNom(String nom) {
        if (nom == null || nom.trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom ne peut pas être vide");
        }
        this.nom = nom.trim();
    }
    
    public void setDescription(String description) {
        this.description = (description != null) ? description : "";
    }
    
    public void setPrixHT(double prixHT) {
        if (prixHT < 0) {
            throw new IllegalArgumentException("Le prix ne peut pas être négatif");
        }
        this.prixHT = prixHT;
    }
    
    public void setStock(int stock) {
        if (stock < 0) {
            throw new IllegalArgumentException("Le stock ne peut pas être négatif");
        }
        this.stock = stock;
    }
    
    public void setActif(boolean actif) {
        this.actif = actif;
    }
    
    // Méthodes métier
    public boolean commander(int quantite) {
        if (quantite <= 0) {
            System.out.println("❌ Quantité invalide");
            return false;
        }
        if (!isDisponible()) {
            System.out.println("❌ Produit indisponible");
            return false;
        }
        if (quantite > stock) {
            System.out.println("❌ Stock insuffisant");
            return false;
        }
        
        stock -= quantite;
        System.out.println("✅ Commande de " + quantite + " " + nom + " validée");
        return true;
    }
    
    public void reapprovisionner(int quantite) {
        if (quantite > 0) {
            stock += quantite;
            System.out.println("✅ Stock augmenté de " + quantite);
        }
    }
    
    public void afficher() {
        System.out.println("\n=== " + nom + " ===");
        System.out.println("Description : " + description);
        System.out.printf("Prix HT : %.2f€%n", prixHT);
        System.out.printf("Prix TTC : %.2f€%n", getPrixTTC());
        System.out.println("Stock : " + stock);
        System.out.println("Statut : " + (isDisponible() ? "✅ Disponible" : "❌ Indisponible"));
    }
}

// Test
public class TestProduit {
    public static void main(String[] args) {
        Produit produit = new Produit("Laptop Dell XPS 15", 1200.0, 10);
        produit.setDescription("Ordinateur portable haute performance");
        
        produit.afficher();
        
        produit.commander(3);
        produit.afficher();
        
        produit.reapprovisionner(5);
        produit.afficher();
        
        // Tentative de modification invalide
        try {
            produit.setPrixHT(-100);  // Exception
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Erreur : " + e.getMessage());
        }
    }
}
```

---

## 5. Avantages de l'Encapsulation

### 5.1 Validation Centralisée

```java
class CompteBancaire {
    private double solde;
    
    public void setSolde(double solde) {
        if (solde >= 0) {
            this.solde = solde;
        } else {
            throw new IllegalArgumentException("Solde invalide");
        }
    }
}
```

---

### 5.2 Flexibilité : Changement d'Implémentation

```java
class Produit {
    private double prixBase;
    private double remise;  // En pourcentage
    
    public double getPrix() {
        return prixBase * (1 - remise / 100);
    }
}
```

---

### 5.3 Logs et Traçabilité

```java
class CompteBancaire {
    private double solde;
    
    public void setSolde(double nouveauSolde) {
        double ancienSolde = this.solde;
        this.solde = nouveauSolde;
        System.out.println("[LOG] Solde modifié : " + ancienSolde + " → " + nouveauSolde);
    }
}
```

---

## 📝 Résumé de la Section

| Concept | Description |
|---------|-------------|
| **Encapsulation** | Masquer les détails et protéger les données |
| **Attributs privés** | Pas d'accès direct de l'extérieur |
| **Getters/Setters** | Méthodes publiques pour accéder aux attributs |
| **Validation** | Contrôle dans les setters |

---

## 🎯 Points à Retenir

1. **Attributs** toujours **privés** (`private`)
2. **Accès** via **getters/setters**
3. **Validation** dans les setters
4. Pas de setter pour les attributs **immuables**
5. L'encapsulation permet la **maintenance** et la **flexibilité**

---

**Prochaine section** : [Section 9 : Héritage →](../09-Heritage/README.md)
