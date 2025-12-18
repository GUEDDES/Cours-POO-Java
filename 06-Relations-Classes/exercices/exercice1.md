# Exercice 1 : Bibliothèque et Livres

## 🎯 Objectif

Modéliser une bibliothèque avec des relations de **composition** et **agrégation**.

---

## 📋 Énoncé

Vous devez créer un système de gestion de bibliothèque comprenant :

- Des **Livres** (titre, auteur, ISBN, année)
- Des **Emprunteurs** (nom, numéro carte, livres empruntés)
- Une **Bibliothèque** (nom, catalogue de livres)

---

## 📝 Classes à créer

### Classe `Livre`

```java
class Livre {
    private String titre;
    private String auteur;
    private String isbn;
    private int annee;
    private boolean disponible;
    
    // Constructeur
    // Getters/Setters
    // Méthode afficher()
}
```

### Classe `Emprunteur`

```java
class Emprunteur {
    private String nom;
    private String numeroCarte;
    private ArrayList<Livre> livresEmpruntes;  // AGRÉGATION
    
    // L'emprunteur ne crée PAS les livres
    // Il les reçoit en paramètre
    
    public void emprunter(Livre livre)
    public void retourner(Livre livre)
    public void afficherLivresEmpruntes()
}
```

### Classe `Bibliotheque`

```java
class Bibliotheque {
    private String nom;
    private ArrayList<Livre> catalogue;  // AGRÉGATION
    
    public void ajouterLivre(Livre livre)
    public void retirerLivre(Livre livre)
    public ArrayList<Livre> rechercherParAuteur(String auteur)
    public ArrayList<Livre> livresDisponibles()
    public void afficherCatalogue()
}
```

---

## 🔍 Analysez les Relations

### Questions :

1. **Quelle relation** existe entre `Bibliotheque` et `Livre` ?
   - Composition ou Agrégation ?
   - Pourquoi ?

2. **Quelle relation** existe entre `Emprunteur` et `Livre` ?
   - Composition ou Agrégation ?
   - Pourquoi ?

3. **Cycle de vie** :
   - Si la bibliothèque disparaît, les livres disparaissent-ils ?
   - Si un emprunteur disparaît, les livres disparaissent-ils ?

---

## ✅ Programme de test

```java
public class TestBibliotheque {
    public static void main(String[] args) {
        // 1. Créer des livres
        Livre livre1 = new Livre("1984", "George Orwell", "978-0...", 1949);
        Livre livre2 = new Livre("Le Seigneur des Anneaux", "J.R.R. Tolkien", ..., 1954);
        // ...
        
        // 2. Créer une bibliothèque
        Bibliotheque biblio = new Bibliotheque("Bibliothèque Centrale");
        
        // 3. Ajouter les livres
        biblio.ajouterLivre(livre1);
        biblio.ajouterLivre(livre2);
        // ...
        
        // 4. Créer des emprunteurs
        Emprunteur alice = new Emprunteur("Alice", "C001");
        Emprunteur bob = new Emprunteur("Bob", "C002");
        
        // 5. Emprunter des livres
        alice.emprunter(livre1);
        bob.emprunter(livre2);
        
        // 6. Afficher
        biblio.afficherCatalogue();
        alice.afficherLivresEmpruntes();
        
        // 7. Retour
        alice.retourner(livre1);
    }
}
```

---

## 📊 Sortie Attendue

```
🏛️ === BIBLIOTHÈQUE CENTRALE ===
Nombre de livres : 5

Catalogue :
1. [EMPRUNTÉ] 1984 - George Orwell (1949)
2. [EMPRUNTÉ] Le Seigneur des Anneaux - J.R.R. Tolkien (1954)
3. [DISPONIBLE] Harry Potter - J.K. Rowling (1997)
4. [DISPONIBLE] Fondation - Isaac Asimov (1951)
5. [DISPONIBLE] Dune - Frank Herbert (1965)

👤 Emprunteur : Alice (C001)
Livres empruntés : 1
 - 1984 - George Orwell

✅ Alice a rendu : 1984

📊 Livres disponibles : 4/5
```

---

## 🎯 Critères de Validation

- [ ] Classe `Livre` complète
- [ ] Classe `Emprunteur` avec ArrayList
- [ ] Classe `Bibliotheque` avec ArrayList
- [ ] Méthode `emprunter()` qui marque le livre comme indisponible
- [ ] Méthode `retourner()` qui marque le livre comme disponible
- [ ] Méthode `rechercherParAuteur()` fonctionnelle
- [ ] Les livres sont passés en paramètre (agrégation)
- [ ] Programme de test complet
- [ ] Analyse des relations documentée

---

## 🚀 Bonus

1. Ajouter une limite d'emprunt (max 3 livres par emprunteur)
2. Ajouter une date d'emprunt et calculer les retards
3. Créer une classe `Auteur` avec biographie
4. Ajouter des catégories de livres (Roman, Science-Fiction, etc.)

---

**Bon courage ! 📚**
