# Solution : Exercice 1 - Modélisation Bibliothèque

## Énoncé
Modéliser un système de gestion de bibliothèque avec classes, attributs et méthodes.

---

## 📚 Solution Complète

### Diagramme de Classes UML

```
┌─────────────────────────────────┐
│          Livre                  │
├─────────────────────────────────┤
│ - isbn : String                 │
│ - titre : String                │
│ - auteur : String               │
│ - anneePublication : int        │
│ - genre : String                │
│ - disponible : boolean          │
├─────────────────────────────────┤
│ + emprunter() : boolean         │
│ + retourner() : void            │
│ + afficherDetails() : void      │
│ + estDisponible() : boolean     │
└─────────────────────────────────┘
            ▲
            │ (composition)
            │
┌───────────┴─────────────────────┐
│      Bibliotheque               │
├─────────────────────────────────┤
│ - nom : String                  │
│ - adresse : String              │
│ - livres : List<Livre>          │
│ - membres : List<Membre>        │
├─────────────────────────────────┤
│ + ajouterLivre(Livre) : void    │
│ + rechercherLivre(titre) : List │
│ + inscrireMembre(Membre) : void │
│ + afficherCatalogue() : void    │
│ + genererStatistiques() : void  │
└─────────────────────────────────┘
            │
            │ (agrégation)
            ▼
┌─────────────────────────────────┐
│          Membre                 │
├─────────────────────────────────┤
│ - numeroMembre : String         │
│ - nom : String                  │
│ - prenom : String               │
│ - email : String                │
│ - dateInscription : Date        │
│ - livresEmpruntes : List<Livre> │
├─────────────────────────────────┤
│ + emprunterLivre(Livre) : bool  │
│ + retournerLivre(Livre) : void  │
│ + afficherEmprunts() : void     │
│ + getNombreEmprunts() : int     │
└─────────────────────────────────┘
            │
            │ (association)
            ▼
┌─────────────────────────────────┐
│         Emprunt                 │
├─────────────────────────────────┤
│ - id : int                      │
│ - membre : Membre               │
│ - livre : Livre                 │
│ - dateEmprunt : Date            │
│ - dateRetourPrevue : Date       │
│ - dateRetourEffective : Date    │
│ - estEnRetard : boolean         │
├─────────────────────────────────┤
│ + calculerRetard() : int        │
│ + calculerAmende() : double     │
│ + prolonger() : boolean         │
└─────────────────────────────────┘
```

---

## 📋 Description Détaillée des Classes

### 1. Classe `Livre`

```java
public class Livre {
    // Attributs
    private String isbn;              // Identifiant unique (ex: "978-2-1234-5678-9")
    private String titre;             // "Le Seigneur des Anneaux"
    private String auteur;            // "J.R.R. Tolkien"
    private int anneePublication;     // 1954
    private String genre;             // "Fantasy"
    private boolean disponible;       // true si en rayon, false si emprunté
    
    // Constructeur
    public Livre(String isbn, String titre, String auteur, 
                 int anneePublication, String genre) {
        this.isbn = isbn;
        this.titre = titre;
        this.auteur = auteur;
        this.anneePublication = anneePublication;
        this.genre = genre;
        this.disponible = true;  // Par défaut disponible
    }
    
    // Méthodes
    
    /**
     * Emprunte le livre s'il est disponible
     * @return true si emprunt réussi, false sinon
     */
    public boolean emprunter() {
        if (disponible) {
            disponible = false;
            return true;
        }
        return false;
    }
    
    /**
     * Retourne le livre à la bibliothèque
     */
    public void retourner() {
        disponible = true;
    }
    
    /**
     * Vérifie si le livre est disponible
     */
    public boolean estDisponible() {
        return disponible;
    }
    
    /**
     * Affiche les détails du livre
     */
    public void afficherDetails() {
        System.out.println("═════════════════════════════");
        System.out.println("Titre : " + titre);
        System.out.println("Auteur : " + auteur);
        System.out.println("ISBN : " + isbn);
        System.out.println("Année : " + anneePublication);
        System.out.println("Genre : " + genre);
        System.out.println("Statut : " + (disponible ? "✅ Disponible" : "❌ Emprunté"));
        System.out.println("═════════════════════════════");
    }
    
    // Getters
    public String getIsbn() { return isbn; }
    public String getTitre() { return titre; }
    public String getAuteur() { return auteur; }
    public int getAnneePublication() { return anneePublication; }
    public String getGenre() { return genre; }
}
```

**Justification des attributs** :
- `isbn` : Identifiant unique international
- `disponible` : État essentiel pour la gestion des emprunts
- `genre` : Facilite la recherche et le classement

---

### 2. Classe `Membre`

```java
import java.util.*;

public class Membre {
    // Attributs
    private String numeroMembre;           // "M001", "M002"...
    private String nom;
    private String prenom;
    private String email;
    private Date dateInscription;
    private List<Livre> livresEmpruntes;   // Livres actuellement empruntés
    private static final int MAX_EMPRUNTS = 5;  // Limite d'emprunts
    
    // Constructeur
    public Membre(String nom, String prenom, String email) {
        this.numeroMembre = genererNumeroMembre();
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.dateInscription = new Date();
        this.livresEmpruntes = new ArrayList<>();
    }
    
    /**
     * Génère un numéro de membre unique
     */
    private String genererNumeroMembre() {
        return "M" + System.currentTimeMillis() % 100000;
    }
    
    /**
     * Emprunte un livre
     * @param livre Le livre à emprunter
     * @return true si succès, false si échec
     */
    public boolean emprunterLivre(Livre livre) {
        // Vérifier la limite d'emprunts
        if (livresEmpruntes.size() >= MAX_EMPRUNTS) {
            System.out.println("❌ Limite d'emprunts atteinte (" + MAX_EMPRUNTS + ")");
            return false;
        }
        
        // Vérifier si le livre est disponible
        if (!livre.estDisponible()) {
            System.out.println("❌ Le livre '" + livre.getTitre() + "' n'est pas disponible");
            return false;
        }
        
        // Emprunter
        if (livre.emprunter()) {
            livresEmpruntes.add(livre);
            System.out.println("✅ Livre emprunté : " + livre.getTitre());
            return true;
        }
        
        return false;
    }
    
    /**
     * Retourne un livre
     */
    public void retournerLivre(Livre livre) {
        if (livresEmpruntes.remove(livre)) {
            livre.retourner();
            System.out.println("✅ Livre retourné : " + livre.getTitre());
        } else {
            System.out.println("❌ Ce livre n'est pas dans vos emprunts");
        }
    }
    
    /**
     * Affiche tous les livres empruntés
     */
    public void afficherEmprunts() {
        System.out.println("\n📚 Livres empruntés par " + prenom + " " + nom + " :");
        if (livresEmpruntes.isEmpty()) {
            System.out.println("  Aucun livre emprunté");
        } else {
            for (int i = 0; i < livresEmpruntes.size(); i++) {
                System.out.println("  " + (i + 1) + ". " + livresEmpruntes.get(i).getTitre());
            }
        }
    }
    
    public int getNombreEmprunts() {
        return livresEmpruntes.size();
    }
    
    // Getters
    public String getNumeroMembre() { return numeroMembre; }
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
}
```

**Justification** :
- `MAX_EMPRUNTS` : Règle métier typique des bibliothèques
- `livresEmpruntes` : Liste pour gérer plusieurs emprunts simultanés

---

### 3. Classe `Bibliotheque`

```java
import java.util.*;

public class Bibliotheque {
    // Attributs
    private String nom;
    private String adresse;
    private List<Livre> livres;
    private List<Membre> membres;
    
    // Constructeur
    public Bibliotheque(String nom, String adresse) {
        this.nom = nom;
        this.adresse = adresse;
        this.livres = new ArrayList<>();
        this.membres = new ArrayList<>();
    }
    
    /**
     * Ajoute un livre au catalogue
     */
    public void ajouterLivre(Livre livre) {
        livres.add(livre);
        System.out.println("✅ Livre ajouté au catalogue : " + livre.getTitre());
    }
    
    /**
     * Recherche des livres par titre (partiel)
     */
    public List<Livre> rechercherLivre(String titrePartiel) {
        List<Livre> resultats = new ArrayList<>();
        String recherche = titrePartiel.toLowerCase();
        
        for (Livre livre : livres) {
            if (livre.getTitre().toLowerCase().contains(recherche)) {
                resultats.add(livre);
            }
        }
        
        return resultats;
    }
    
    /**
     * Recherche des livres par auteur
     */
    public List<Livre> rechercherParAuteur(String auteur) {
        List<Livre> resultats = new ArrayList<>();
        String recherche = auteur.toLowerCase();
        
        for (Livre livre : livres) {
            if (livre.getAuteur().toLowerCase().contains(recherche)) {
                resultats.add(livre);
            }
        }
        
        return resultats;
    }
    
    /**
     * Inscrit un nouveau membre
     */
    public void inscrireMembre(Membre membre) {
        membres.add(membre);
        System.out.println("✅ Membre inscrit : " + membre.getPrenom() + " " + membre.getNom());
        System.out.println("   Numéro de membre : " + membre.getNumeroMembre());
    }
    
    /**
     * Affiche le catalogue complet
     */
    public void afficherCatalogue() {
        System.out.println("\n" + "═".repeat(50));
        System.out.println("📚 CATALOGUE DE " + nom.toUpperCase());
        System.out.println("═".repeat(50));
        
        if (livres.isEmpty()) {
            System.out.println("  Aucun livre dans le catalogue");
        } else {
            for (int i = 0; i < livres.size(); i++) {
                Livre livre = livres.get(i);
                System.out.printf("%d. %s - %s (%d) %s\n", 
                    i + 1,
                    livre.getTitre(),
                    livre.getAuteur(),
                    livre.getAnneePublication(),
                    livre.estDisponible() ? "✅" : "❌"
                );
            }
        }
        System.out.println("═".repeat(50));
    }
    
    /**
     * Génère des statistiques
     */
    public void genererStatistiques() {
        int totalLivres = livres.size();
        int livresDisponibles = 0;
        int livresEmpruntes = 0;
        
        for (Livre livre : livres) {
            if (livre.estDisponible()) {
                livresDisponibles++;
            } else {
                livresEmpruntes++;
            }
        }
        
        System.out.println("\n" + "═".repeat(50));
        System.out.println("📊 STATISTIQUES DE LA BIBLIOTHÈQUE");
        System.out.println("═".repeat(50));
        System.out.println("Nombre total de livres : " + totalLivres);
        System.out.println("Livres disponibles : " + livresDisponibles + " (" + 
                          (totalLivres > 0 ? (livresDisponibles * 100 / totalLivres) : 0) + "%)");
        System.out.println("Livres empruntés : " + livresEmpruntes + " (" + 
                          (totalLivres > 0 ? (livresEmpruntes * 100 / totalLivres) : 0) + "%)");
        System.out.println("Nombre de membres : " + membres.size());
        System.out.println("═".repeat(50));
    }
}
```

---

## 🧪 Programme de Test

```java
public class TestBibliotheque {
    public static void main(String[] args) {
        // Créer une bibliothèque
        Bibliotheque biblio = new Bibliotheque(
            "Bibliothèque Municipale de Paris",
            "15 rue des Livres, 75001 Paris"
        );
        
        System.out.println("🏛️  Bienvenue à la " + biblio.getNom() + "\n");
        
        // Ajouter des livres
        Livre livre1 = new Livre(
            "978-2-07-061298-1",
            "Le Seigneur des Anneaux",
            "J.R.R. Tolkien",
            1954,
            "Fantasy"
        );
        
        Livre livre2 = new Livre(
            "978-2-07-061299-8",
            "Harry Potter à l'école des sorciers",
            "J.K. Rowling",
            1997,
            "Fantasy"
        );
        
        Livre livre3 = new Livre(
            "978-2-07-036822-8",
            "1984",
            "George Orwell",
            1949,
            "Science-Fiction"
        );
        
        Livre livre4 = new Livre(
            "978-2-07-037621-6",
            "Le Petit Prince",
            "Antoine de Saint-Exupéry",
            1943,
            "Conte"
        );
        
        biblio.ajouterLivre(livre1);
        biblio.ajouterLivre(livre2);
        biblio.ajouterLivre(livre3);
        biblio.ajouterLivre(livre4);
        
        // Afficher le catalogue
        biblio.afficherCatalogue();
        
        // Inscrire des membres
        Membre alice = new Membre("Dupont", "Alice", "alice@email.com");
        Membre bob = new Membre("Martin", "Bob", "bob@email.com");
        
        biblio.inscrireMembre(alice);
        biblio.inscrireMembre(bob);
        
        // Alice emprunte des livres
        System.out.println("\n--- Alice emprunte des livres ---");
        alice.emprunterLivre(livre1);
        alice.emprunterLivre(livre3);
        
        // Bob essaie d'emprunter un livre déjà emprunté
        System.out.println("\n--- Bob essaie d'emprunter le même livre ---");
        bob.emprunterLivre(livre1);  // Échec
        
        // Bob emprunte un livre disponible
        bob.emprunterLivre(livre2);
        
        // Afficher les emprunts
        alice.afficherEmprunts();
        bob.afficherEmprunts();
        
        // Statistiques
        biblio.genererStatistiques();
        
        // Alice retourne un livre
        System.out.println("\n--- Alice retourne '1984' ---");
        alice.retournerLivre(livre3);
        
        // Statistiques après retour
        biblio.genererStatistiques();
        
        // Recherche de livres
        System.out.println("\n--- Recherche : 'Harry' ---");
        List<Livre> resultats = biblio.rechercherLivre("Harry");
        for (Livre livre : resultats) {
            livre.afficherDetails();
        }
    }
}
```

---

## 📊 Sortie Attendue

```
🏛️  Bienvenue à la Bibliothèque Municipale de Paris

✅ Livre ajouté au catalogue : Le Seigneur des Anneaux
✅ Livre ajouté au catalogue : Harry Potter à l'école des sorciers
✅ Livre ajouté au catalogue : 1984
✅ Livre ajouté au catalogue : Le Petit Prince

══════════════════════════════════════════════════
📚 CATALOGUE DE BIBLIOTHÈQUE MUNICIPALE DE PARIS
══════════════════════════════════════════════════
1. Le Seigneur des Anneaux - J.R.R. Tolkien (1954) ✅
2. Harry Potter à l'école des sorciers - J.K. Rowling (1997) ✅
3. 1984 - George Orwell (1949) ✅
4. Le Petit Prince - Antoine de Saint-Exupéry (1943) ✅
══════════════════════════════════════════════════

✅ Membre inscrit : Alice Dupont
   Numéro de membre : M12345
✅ Membre inscrit : Bob Martin
   Numéro de membre : M12346

--- Alice emprunte des livres ---
✅ Livre emprunté : Le Seigneur des Anneaux
✅ Livre emprunté : 1984

--- Bob essaie d'emprunter le même livre ---
❌ Le livre 'Le Seigneur des Anneaux' n'est pas disponible
✅ Livre emprunté : Harry Potter à l'école des sorciers

📚 Livres empruntés par Alice Dupont :
  1. Le Seigneur des Anneaux
  2. 1984

📚 Livres empruntés par Bob Martin :
  1. Harry Potter à l'école des sorciers

══════════════════════════════════════════════════
📊 STATISTIQUES DE LA BIBLIOTHÈQUE
══════════════════════════════════════════════════
Nombre total de livres : 4
Livres disponibles : 1 (25%)
Livres empruntés : 3 (75%)
Nombre de membres : 2
══════════════════════════════════════════════════

--- Alice retourne '1984' ---
✅ Livre retourné : 1984

══════════════════════════════════════════════════
📊 STATISTIQUES DE LA BIBLIOTHÈQUE
══════════════════════════════════════════════════
Nombre total de livres : 4
Livres disponibles : 2 (50%)
Livres empruntés : 2 (50%)
Nombre de membres : 2
══════════════════════════════════════════════════
```

---

## 🎯 Améliorations Possibles

1. **Classe Emprunt** : Gérer les dates et les amendes
2. **Base de données** : Persistance des données
3. **Interface graphique** : Application Desktop/Web
4. **Système de réservation** : Réserver un livre emprunté
5. **Catégories** : Organiser par genres, rayons

---

**Modélisation terminée ! ✅**
