# Section 6 : Relations entre Classes

## 🎯 Objectifs d'apprentissage

À la fin de cette section, vous serez capable de :
- Distinguer composition et agrégation
- Implémenter une relation de composition en Java
- Implémenter une relation d'agrégation en Java
- Comprendre les cycles de vie des objets
- Modéliser des systèmes complexes

---

## 1. Introduction aux Relations

### 1.1 Types de Relations

En POO, les objets **collaborent** entre eux. Il existe plusieurs types de relations :

```
┌─────────────────────────────────────────────┐
│         RELATIONS ENTRE CLASSES             │
├─────────────────────────────────────────────┤
│                                             │
│  1. COMPOSITION     (contient, forte)       │
│     Symbole UML : ◆─────                   │
│                                             │
│  2. AGRÉGATION      (a-un, faible)          │
│     Symbole UML : ◇─────                   │
│                                             │
│  3. HÉRITAGE        (est-un)                │
│     Symbole UML : ──▷                       │
│                                             │
│  4. ASSOCIATION     (utilise)               │
│     Symbole UML : ──────                    │
│                                             │
└─────────────────────────────────────────────┘
```

Cette section se concentre sur **Composition** et **Agrégation**.

---

## 2. Composition : Relation "Contient" Forte

### 2.1 Définition

**Composition** = Relation de **possession exclusive**

**Caractéristiques** :
- Les **parties** sont créées **par** le conteneur
- Les parties **meurent avec** le conteneur
- Les parties **ne peuvent pas exister** sans le conteneur
- Cycle de vie **dépendant**

**Exemples du monde réel** :
```
Voiture ◆──── Moteur
Maison  ◆──── Pièce
Corps   ◆──── Cœur
Ordinateur ◆──── Processeur
```

---

### 2.2 Implémentation en Java

#### Exemple 1 : Voiture et Moteur

```java
class Moteur {
    private int puissance;  // en chevaux
    private String type;    // "Essence" ou "Diesel"
    
    public Moteur(int puissance, String type) {
        this.puissance = puissance;
        this.type = type;
    }
    
    public void demarrer() {
        System.out.println("Moteur " + type + " de " + puissance + "CV démarré");
    }
    
    public void arreter() {
        System.out.println("Moteur arrêté");
    }
}

class Voiture {
    private String marque;
    private String modele;
    private Moteur moteur;  // COMPOSITION
    
    // ✅ Le moteur est créé À L'INTÉRIEUR du constructeur
    public Voiture(String marque, String modele, int puissance, String typeMoteur) {
        this.marque = marque;
        this.modele = modele;
        this.moteur = new Moteur(puissance, typeMoteur);  // Création interne
    }
    
    public void demarrer() {
        System.out.println("Démarrage de " + marque + " " + modele);
        moteur.demarrer();
    }
    
    public void arreter() {
        moteur.arreter();
        System.out.println(marque + " " + modele + " arrêtée");
    }
}

// Utilisation
public class TestComposition {
    public static void main(String[] args) {
        Voiture maVoiture = new Voiture("Peugeot", "308", 130, "Essence");
        maVoiture.demarrer();
        maVoiture.arreter();
        
        // ❌ Impossible d'accéder au moteur de l'extérieur
        // Le moteur n'existe QUE dans la voiture
    }
}
```

**Sortie** :
```
Démarrage de Peugeot 308
Moteur Essence de 130CV démarré
Moteur arrêté
Peugeot 308 arrêtée
```

---

#### Exemple 2 : Maison et Pièces

```java
class Piece {
    private String nom;
    private double surface;  // en m²
    
    public Piece(String nom, double surface) {
        this.nom = nom;
        this.surface = surface;
    }
    
    public void afficher() {
        System.out.println(nom + " : " + surface + "m²");
    }
}

class Maison {
    private String adresse;
    private Piece[] pieces;
    
    public Maison(String adresse, int nombrePieces) {
        this.adresse = adresse;
        this.pieces = new Piece[nombrePieces];
    }
    
    // Méthode pour ajouter une pièce
    public void ajouterPiece(int index, String nom, double surface) {
        pieces[index] = new Piece(nom, surface);  // Création interne
    }
    
    public void afficher() {
        System.out.println("Maison située à : " + adresse);
        System.out.println("Pièces :");
        for (Piece p : pieces) {
            if (p != null) {
                p.afficher();
            }
        }
    }
    
    public double surfaceTotale() {
        double total = 0;
        for (Piece p : pieces) {
            if (p != null) {
                total += p.surface;
            }
        }
        return total;
    }
}

// Utilisation
public class TestMaison {
    public static void main(String[] args) {
        Maison maMaison = new Maison("15 rue des Fleurs", 4);
        maMaison.ajouterPiece(0, "Salon", 30);
        maMaison.ajouterPiece(1, "Cuisine", 15);
        maMaison.ajouterPiece(2, "Chambre", 20);
        maMaison.ajouterPiece(3, "Salle de bain", 8);
        
        maMaison.afficher();
        System.out.println("Surface totale : " + maMaison.surfaceTotale() + "m²");
    }
}
```

---

### 2.3 Cycle de Vie en Composition

```java
public class TestCycleVie {
    public static void main(String[] args) {
        Voiture v = new Voiture("Renault", "Clio", 90, "Essence");
        // ✅ Création de la voiture → création automatique du moteur
        
        v = null;
        // ✅ La voiture disparaît → le moteur disparaît aussi
        // Le Garbage Collector supprime les deux
    }
}
```

---

## 3. Agrégation : Relation "A-Un" Faible

### 3.1 Définition

**Agrégation** = Relation de **partage** ou **utilisation**

**Caractéristiques** :
- Les **parties existent avant** le conteneur
- Les parties **survivent** après destruction du conteneur
- Les parties **peuvent être partagées**
- Cycle de vie **indépendant**

**Exemples du monde réel** :
```
Université ◇──── Étudiant
Bibliothèque ◇──── Livre
Équipe ◇──── Joueur
Playlist ◇──── Chanson
```

---

### 3.2 Implémentation en Java

#### Exemple 1 : Université et Étudiants

```java
class Etudiant {
    private String nom;
    private String matricule;
    private double moyenne;
    
    public Etudiant(String nom, String matricule) {
        this.nom = nom;
        this.matricule = matricule;
        this.moyenne = 0.0;
    }
    
    public String getNom() {
        return nom;
    }
    
    public void setMoyenne(double moyenne) {
        this.moyenne = moyenne;
    }
    
    public void afficher() {
        System.out.println(nom + " (" + matricule + ") - Moyenne : " + moyenne);
    }
}

class Universite {
    private String nom;
    private ArrayList<Etudiant> etudiants;
    
    public Universite(String nom) {
        this.nom = nom;
        this.etudiants = new ArrayList<>();
    }
    
    // ✅ L'étudiant est PASSÉ EN PARAMÈTRE (pas créé à l'intérieur)
    public void inscrire(Etudiant etudiant) {
        etudiants.add(etudiant);
        System.out.println(etudiant.getNom() + " inscrit à " + nom);
    }
    
    public void desinscrire(Etudiant etudiant) {
        etudiants.remove(etudiant);
        System.out.println(etudiant.getNom() + " désinscrit de " + nom);
    }
    
    public void afficherEtudiants() {
        System.out.println("\nÉtudiants de " + nom + " :");
        for (Etudiant e : etudiants) {
            e.afficher();
        }
    }
}

// Utilisation
public class TestAgregation {
    public static void main(String[] args) {
        // Créer des étudiants INDÉPENDAMMENT
        Etudiant alice = new Etudiant("Alice", "E001");
        Etudiant bob = new Etudiant("Bob", "E002");
        Etudiant charlie = new Etudiant("Charlie", "E003");
        
        alice.setMoyenne(15.5);
        bob.setMoyenne(14.0);
        charlie.setMoyenne(16.2);
        
        // Créer une université
        Universite univ = new Universite("Université de Paris");
        
        // Inscrire les étudiants
        univ.inscrire(alice);
        univ.inscrire(bob);
        univ.inscrire(charlie);
        
        univ.afficherEtudiants();
        
        // Désinscrire Bob
        univ.desinscrire(bob);
        
        // ✅ Bob existe toujours, même désinscrit
        System.out.println("\nBob après désinscription :");
        bob.afficher();
        
        // ✅ Même si l'université disparaît, les étudiants existent
        univ = null;
        System.out.println("\nAlice après suppression de l'université :");
        alice.afficher();
    }
}
```

---

#### Exemple 2 : Bibliothèque et Livres

```java
import java.util.ArrayList;

class Livre {
    private String titre;
    private String auteur;
    private String isbn;
    
    public Livre(String titre, String auteur, String isbn) {
        this.titre = titre;
        this.auteur = auteur;
        this.isbn = isbn;
    }
    
    public String getTitre() {
        return titre;
    }
    
    public void afficher() {
        System.out.println(titre + " - " + auteur + " (ISBN: " + isbn + ")");
    }
}

class Bibliotheque {
    private String nom;
    private ArrayList<Livre> livres;
    
    public Bibliotheque(String nom) {
        this.nom = nom;
        this.livres = new ArrayList<>();
    }
    
    public void ajouterLivre(Livre livre) {
        livres.add(livre);
        System.out.println("✅ Livre ajouté : " + livre.getTitre());
    }
    
    public void retirerLivre(Livre livre) {
        if (livres.remove(livre)) {
            System.out.println("❌ Livre retiré : " + livre.getTitre());
        }
    }
    
    public void afficherCatalogue() {
        System.out.println("\n📚 Catalogue de " + nom + " :");
        for (Livre livre : livres) {
            livre.afficher();
        }
    }
}

// Utilisation
public class TestBibliotheque {
    public static void main(String[] args) {
        // Créer des livres
        Livre livre1 = new Livre("1984", "George Orwell", "978-0451524935");
        Livre livre2 = new Livre("Le Seigneur des Anneaux", "J.R.R. Tolkien", "978-0618640157");
        Livre livre3 = new Livre("Harry Potter", "J.K. Rowling", "978-0439708180");
        
        // Créer une bibliothèque
        Bibliotheque biblio = new Bibliotheque("Bibliothèque Municipale");
        
        // Ajouter des livres
        biblio.ajouterLivre(livre1);
        biblio.ajouterLivre(livre2);
        biblio.ajouterLivre(livre3);
        
        biblio.afficherCatalogue();
        
        // Retirer un livre
        biblio.retirerLivre(livre2);
        
        // ✅ Le livre existe toujours, il peut être ajouté ailleurs
        Bibliotheque autreBiblio = new Bibliotheque("Bibliothèque Universitaire");
        autreBiblio.ajouterLivre(livre2);
    }
}
```

---

## 4. Composition vs Agrégation : Tableau Comparatif

| Critère | Composition ◆ | Agrégation ◇ |
|---------|---------------|---------------|
| **Relation** | "Contient" (forte) | "A-un" (faible) |
| **Création** | À l'intérieur du conteneur | Avant le conteneur |
| **Cycle de vie** | Dépendant | Indépendant |
| **Destruction** | Parties détruites avec le tout | Parties survivent |
| **Partage** | ❌ Exclusif | ✅ Possible |
| **Exemple** | Voiture → Moteur | Université → Étudiant |

---

## 5. Exercice Guidé : Système d'Entreprise

### 5.1 Énoncé

Modélisez une entreprise avec :
- Une **Entreprise** qui a plusieurs **Départements** (composition)
- Chaque **Département** emploie des **Employés** (agrégation)

---

### 5.2 Solution

```java
class Employe {
    private String nom;
    private String poste;
    private double salaire;
    
    public Employe(String nom, String poste, double salaire) {
        this.nom = nom;
        this.poste = poste;
        this.salaire = salaire;
    }
    
    public String getNom() {
        return nom;
    }
    
    public void afficher() {
        System.out.println("  - " + nom + " (" + poste + ") : " + salaire + "€");
    }
}

class Departement {
    private String nom;
    private ArrayList<Employe> employes;
    
    public Departement(String nom) {
        this.nom = nom;
        this.employes = new ArrayList<>();
    }
    
    public void embaucher(Employe employe) {
        employes.add(employe);
    }
    
    public void afficher() {
        System.out.println("\nDépartement : " + nom);
        for (Employe e : employes) {
            e.afficher();
        }
    }
}

class Entreprise {
    private String nom;
    private ArrayList<Departement> departements;
    
    public Entreprise(String nom) {
        this.nom = nom;
        this.departements = new ArrayList<>();
    }
    
    // COMPOSITION : créer un département
    public void creerDepartement(String nomDept) {
        Departement dept = new Departement(nomDept);
        departements.add(dept);
    }
    
    public Departement getDepartement(int index) {
        return departements.get(index);
    }
    
    public void afficher() {
        System.out.println("========== " + nom + " ==========");
        for (Departement d : departements) {
            d.afficher();
        }
    }
}

// Utilisation
public class TestEntreprise {
    public static void main(String[] args) {
        // Créer des employés
        Employe e1 = new Employe("Alice", "Développeuse", 45000);
        Employe e2 = new Employe("Bob", "Designer", 40000);
        Employe e3 = new Employe("Charlie", "Manager", 55000);
        
        // Créer une entreprise
        Entreprise entreprise = new Entreprise("TechCorp");
        
        // Créer des départements (COMPOSITION)
        entreprise.creerDepartement("Informatique");
        entreprise.creerDepartement("Design");
        
        // Embaucher des employés (AGRÉGATION)
        entreprise.getDepartement(0).embaucher(e1);
        entreprise.getDepartement(0).embaucher(e3);
        entreprise.getDepartement(1).embaucher(e2);
        
        entreprise.afficher();
    }
}
```

---

## 📝 Résumé de la Section

| Concept | Description |
|---------|-------------|
| **Composition** | Relation forte : les parties sont créées par et meurent avec le conteneur |
| **Agrégation** | Relation faible : les parties existent indépendamment |
| **Cycle de vie** | Composition = dépendant, Agrégation = indépendant |

---

## 🎯 Points à Retenir

1. **Composition** : "Le moteur FAIT PARTIE de la voiture"
2. **Agrégation** : "L'étudiant APPARTIENT À l'université (mais peut exister sans)"
3. Composition → `new` **dans** le constructeur
4. Agrégation → objet **passé en paramètre**
5. Choix dépend du **contexte métier**

---

**Prochaine section** : [Section 7 : Packages (Paquetages) →](../07-Packages/README.md)
