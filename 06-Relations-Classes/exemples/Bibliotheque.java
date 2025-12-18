/**
 * Exemple : Système de Bibliothèque
 * Démontre : Composition (Livre/Bibliothèque), Agrégation (Auteur/Livre)
 */

import java.util.ArrayList;
import java.util.List;

// Classe Auteur (existe indépendamment)
class Auteur {
    private String nom;
    private String nationalite;
    private int anneeNaissance;
    
    public Auteur(String nom, String nationalite, int anneeNaissance) {
        this.nom = nom;
        this.nationalite = nationalite;
        this.anneeNaissance = anneeNaissance;
    }
    
    public String getNom() {
        return nom;
    }
    
    public void afficher() {
        System.out.println("👤 " + nom + " (" + nationalite + ", " + anneeNaissance + ")");
    }
}

// Classe Livre (appartient à une bibliothèque)
class Livre {
    private String titre;
    private Auteur auteur;  // AGRÉGATION : l'auteur existe indépendamment
    private String isbn;
    private int anneePublication;
    private boolean disponible;
    
    public Livre(String titre, Auteur auteur, String isbn, int anneePublication) {
        this.titre = titre;
        this.auteur = auteur;
        this.isbn = isbn;
        this.anneePublication = anneePublication;
        this.disponible = true;
    }
    
    public void emprunter() {
        if (disponible) {
            disponible = false;
            System.out.println("✅ Livre emprunté : " + titre);
        } else {
            System.out.println("❌ Livre déjà emprunté : " + titre);
        }
    }
    
    public void retourner() {
        if (!disponible) {
            disponible = true;
            System.out.println("✅ Livre retourné : " + titre);
        } else {
            System.out.println("⚠️ Livre déjà disponible : " + titre);
        }
    }
    
    public String getTitre() {
        return titre;
    }
    
    public String getIsbn() {
        return isbn;
    }
    
    public boolean estDisponible() {
        return disponible;
    }
    
    public void afficher() {
        System.out.println("\n📕 " + titre);
        System.out.print("   Auteur : ");
        auteur.afficher();
        System.out.println("   ISBN : " + isbn);
        System.out.println("   Année : " + anneePublication);
        System.out.println("   Statut : " + (disponible ? "✅ Disponible" : "❌ Emprunté"));
    }
}

// Classe Bibliothèque (COMPOSITION avec Livre)
class Bibliotheque {
    private String nom;
    private List<Livre> livres;  // COMPOSITION : les livres appartiennent à la bibliothèque
    
    public Bibliotheque(String nom) {
        this.nom = nom;
        this.livres = new ArrayList<>();
        System.out.println("🏛️ Bibliothèque créée : " + nom);
    }
    
    // Ajouter un livre (COMPOSITION : le livre est créé pour la bibliothèque)
    public void ajouterLivre(String titre, Auteur auteur, String isbn, int annee) {
        Livre livre = new Livre(titre, auteur, isbn, annee);
        livres.add(livre);
        System.out.println("➕ Livre ajouté : " + titre);
    }
    
    // Supprimer un livre
    public boolean supprimerLivre(String isbn) {
        for (int i = 0; i < livres.size(); i++) {
            if (livres.get(i).getIsbn().equals(isbn)) {
                String titre = livres.get(i).getTitre();
                livres.remove(i);
                System.out.println("➖ Livre supprimé : " + titre);
                return true;
            }
        }
        System.out.println("❌ Livre non trouvé (ISBN: " + isbn + ")");
        return false;
    }
    
    // Rechercher un livre par titre
    public Livre rechercherParTitre(String titre) {
        for (Livre livre : livres) {
            if (livre.getTitre().toLowerCase().contains(titre.toLowerCase())) {
                return livre;
            }
        }
        return null;
    }
    
    // Afficher tous les livres disponibles
    public void afficherLivresDisponibles() {
        System.out.println("\n📚 === LIVRES DISPONIBLES ===");
        boolean aucunDisponible = true;
        for (Livre livre : livres) {
            if (livre.estDisponible()) {
                livre.afficher();
                aucunDisponible = false;
            }
        }
        if (aucunDisponible) {
            System.out.println("⚠️ Aucun livre disponible");
        }
    }
    
    // Afficher tous les livres
    public void afficherTousLesLivres() {
        System.out.println("\n📚 === CATALOGUE COMPLET : " + nom + " ===");
        if (livres.isEmpty()) {
            System.out.println("⚠️ Bibliothèque vide");
            return;
        }
        for (Livre livre : livres) {
            livre.afficher();
        }
    }
    
    // Statistiques
    public void afficherStatistiques() {
        int total = livres.size();
        int disponibles = 0;
        for (Livre livre : livres) {
            if (livre.estDisponible()) {
                disponibles++;
            }
        }
        int empruntes = total - disponibles;
        
        System.out.println("\n📊 === STATISTIQUES : " + nom + " ===");
        System.out.println("Total de livres : " + total);
        System.out.println("Disponibles : " + disponibles);
        System.out.println("Empruntés : " + empruntes);
        if (total > 0) {
            System.out.printf("Taux d'emprunt : %.1f%%\n", (empruntes * 100.0 / total));
        }
    }
}

// Classe de test
public class TestBibliotheque {
    public static void main(String[] args) {
        System.out.println("\n🏛️ === SYSTÈME DE BIBLIOTHÈQUE ===\n");
        
        // Créer des auteurs (AGRÉGATION : existent indépendamment)
        Auteur victor = new Auteur("Victor Hugo", "Française", 1802);
        Auteur moliere = new Auteur("Molière", "Française", 1622);
        Auteur camus = new Auteur("Albert Camus", "Française", 1913);
        
        // Créer une bibliothèque
        Bibliotheque biblio = new Bibliotheque("Bibliothèque Municipale");
        
        System.out.println("\n--- Ajout de Livres ---");
        // Ajouter des livres (COMPOSITION : appartiennent à la bibliothèque)
        biblio.ajouterLivre("Les Misérables", victor, "978-2-07-036584-5", 1862);
        biblio.ajouterLivre("Notre-Dame de Paris", victor, "978-2-253-09621-9", 1831);
        biblio.ajouterLivre("Le Tartuffe", moliere, "978-2-253-03798-4", 1664);
        biblio.ajouterLivre("L'Étranger", camus, "978-2-07-036002-4", 1942);
        
        // Afficher le catalogue
        biblio.afficherTousLesLivres();
        
        // Emprunter des livres
        System.out.println("\n--- Emprunts ---");
        Livre livre1 = biblio.rechercherParTitre("Misérables");
        if (livre1 != null) {
            livre1.emprunter();
        }
        
        Livre livre2 = biblio.rechercherParTitre("Étranger");
        if (livre2 != null) {
            livre2.emprunter();
        }
        
        // Afficher livres disponibles
        biblio.afficherLivresDisponibles();
        
        // Retourner un livre
        System.out.println("\n--- Retour ---");
        if (livre1 != null) {
            livre1.retourner();
        }
        
        // Statistiques
        biblio.afficherStatistiques();
        
        // Supprimer un livre
        System.out.println("\n--- Suppression ---");
        biblio.supprimerLivre("978-2-253-03798-4");
        
        // Statistiques finales
        biblio.afficherStatistiques();
        
        // Démonstration AGRÉGATION vs COMPOSITION
        System.out.println("\n--- Démonstration Relations ---");
        System.out.println("ℹ️ AGRÉGATION : Les auteurs existent indépendamment");
        System.out.print("Victor Hugo existe toujours : ");
        victor.afficher();
        
        System.out.println("\nℹ️ COMPOSITION : Si la bibliothèque est détruite, les livres le sont aussi");
        System.out.println("(Les objets Livre sont liés au cycle de vie de Bibliotheque)");
    }
}
