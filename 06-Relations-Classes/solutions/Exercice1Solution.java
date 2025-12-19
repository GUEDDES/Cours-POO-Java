/**
 * Solution Exercice 1 : Bibliothèque et Livres
 * 
 * Relations : Agrégation (Bibliothèque ◇→ Livre, Emprunteur ◇→ Livre)
 * 
 * @author Cours POO Java
 */
import java.util.ArrayList;

public class Exercice1Solution {
    public static void main(String[] args) {
        System.out.println("=== SYSTÈME DE BIBLIOTHÈQUE ===");
        
        // 1. Créer des livres INDÉPENDAMMENT
        System.out.println("\n--- Création des livres ---");
        Livre livre1 = new Livre("1984", "George Orwell", "978-0451524935", 1949);
        Livre livre2 = new Livre("Le Seigneur des Anneaux", "J.R.R. Tolkien", "978-0618640157", 1954);
        Livre livre3 = new Livre("Harry Potter", "J.K. Rowling", "978-0439708180", 1997);
        Livre livre4 = new Livre("Fondation", "Isaac Asimov", "978-0553293357", 1951);
        Livre livre5 = new Livre("Dune", "Frank Herbert", "978-0441172719", 1965);
        
        // 2. Créer une bibliothèque
        System.out.println("\n--- Création de la bibliothèque ---");
        Bibliotheque biblio = new Bibliotheque("Bibliothèque Centrale");
        
        // 3. Ajouter les livres (AGRÉGATION)
        System.out.println("\n--- Ajout des livres ---");
        biblio.ajouterLivre(livre1);
        biblio.ajouterLivre(livre2);
        biblio.ajouterLivre(livre3);
        biblio.ajouterLivre(livre4);
        biblio.ajouterLivre(livre5);
        
        // 4. Créer des emprunteurs
        System.out.println("\n--- Création des emprunteurs ---");
        Emprunteur alice = new Emprunteur("Alice Dupont", "C001");
        Emprunteur bob = new Emprunteur("Bob Martin", "C002");
        
        // 5. Emprunter des livres
        System.out.println("\n--- Emprunts ---");
        alice.emprunter(livre1);
        alice.emprunter(livre3);
        bob.emprunter(livre2);
        
        // 6. Afficher le catalogue
        biblio.afficherCatalogue();
        
        // 7. Afficher les livres empruntés
        System.out.println();
        alice.afficherLivresEmpruntes();
        bob.afficherLivresEmpruntes();
        
        // 8. Rechercher par auteur
        System.out.println("\n--- Recherche par auteur ---");
        ArrayList<Livre> livresOrwell = biblio.rechercherParAuteur("George Orwell");
        System.out.println("Livres de George Orwell : " + livresOrwell.size());
        for (Livre l : livresOrwell) {
            l.afficher();
        }
        
        // 9. Retourner un livre
        System.out.println("\n--- Retour ---");
        alice.retourner(livre1);
        
        // 10. Afficher les livres disponibles
        System.out.println("\n--- Livres disponibles ---");
        ArrayList<Livre> disponibles = biblio.livresDisponibles();
        System.out.println("Nombre : " + disponibles.size());
        for (Livre l : disponibles) {
            System.out.println("  ✓ " + l.getTitre());
        }
        
        // 11. Statistiques
        biblio.afficherStatistiques();
        
        // Test limite emprunts
        System.out.println("\n--- Test limite emprunts ---");
        alice.emprunter(livre4);
        alice.emprunter(livre5);  // Limite atteinte
    }
}

class Livre {
    private String titre;
    private String auteur;
    private String isbn;
    private int annee;
    private boolean disponible;
    
    public Livre(String titre, String auteur, String isbn, int annee) {
        this.titre = titre;
        this.auteur = auteur;
        this.isbn = isbn;
        this.annee = annee;
        this.disponible = true;
        System.out.println("📖 Livre créé : " + titre);
    }
    
    public String getTitre() { return titre; }
    public String getAuteur() { return auteur; }
    public String getIsbn() { return isbn; }
    public int getAnnee() { return annee; }
    public boolean isDisponible() { return disponible; }
    
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
    
    public void afficher() {
        System.out.printf("  [%s] %s - %s (%d)%n", 
            disponible ? "DISPONIBLE" : "EMPRUNTÉ", 
            titre, auteur, annee);
    }
}

class Emprunteur {
    private String nom;
    private String numeroCarte;
    private ArrayList<Livre> livresEmpruntes;
    private static final int MAX_EMPRUNTS = 3;
    
    public Emprunteur(String nom, String numeroCarte) {
        this.nom = nom;
        this.numeroCarte = numeroCarte;
        this.livresEmpruntes = new ArrayList<>();
        System.out.println("👤 Emprunteur créé : " + nom + " (" + numeroCarte + ")");
    }
    
    public String getNom() { return nom; }
    public String getNumeroCarte() { return numeroCarte; }
    
    public void emprunter(Livre livre) {
        if (livresEmpruntes.size() >= MAX_EMPRUNTS) {
            System.out.println("❌ " + nom + " : Limite d'emprunts atteinte (" + MAX_EMPRUNTS + " max)");
            return;
        }
        
        if (!livre.isDisponible()) {
            System.out.println("❌ " + nom + " : Livre indisponible : " + livre.getTitre());
            return;
        }
        
        livresEmpruntes.add(livre);
        livre.setDisponible(false);
        System.out.println("✅ " + nom + " a emprunté : " + livre.getTitre());
    }
    
    public void retourner(Livre livre) {
        if (livresEmpruntes.remove(livre)) {
            livre.setDisponible(true);
            System.out.println("✅ " + nom + " a rendu : " + livre.getTitre());
        } else {
            System.out.println("❌ " + nom + " n'a pas emprunté ce livre");
        }
    }
    
    public void afficherLivresEmpruntes() {
        System.out.println("\n👤 Emprunteur : " + nom + " (" + numeroCarte + ")");
        System.out.println("Livres empruntés : " + livresEmpruntes.size() + "/" + MAX_EMPRUNTS);
        if (livresEmpruntes.isEmpty()) {
            System.out.println("  (Aucun livre emprunté)");
        } else {
            for (Livre l : livresEmpruntes) {
                System.out.println("  - " + l.getTitre() + " (" + l.getAuteur() + ")");
            }
        }
    }
}

class Bibliotheque {
    private String nom;
    private ArrayList<Livre> catalogue;
    
    public Bibliotheque(String nom) {
        this.nom = nom;
        this.catalogue = new ArrayList<>();
        System.out.println("🏛️ Bibliothèque créée : " + nom);
    }
    
    public void ajouterLivre(Livre livre) {
        catalogue.add(livre);
        System.out.println("✅ Livre ajouté au catalogue : " + livre.getTitre());
    }
    
    public void retirerLivre(Livre livre) {
        if (catalogue.remove(livre)) {
            System.out.println("❌ Livre retiré du catalogue : " + livre.getTitre());
        } else {
            System.out.println("⚠️ Livre non trouvé dans le catalogue");
        }
    }
    
    public ArrayList<Livre> rechercherParAuteur(String auteur) {
        ArrayList<Livre> resultat = new ArrayList<>();
        for (Livre livre : catalogue) {
            if (livre.getAuteur().equalsIgnoreCase(auteur)) {
                resultat.add(livre);
            }
        }
        return resultat;
    }
    
    public ArrayList<Livre> livresDisponibles() {
        ArrayList<Livre> disponibles = new ArrayList<>();
        for (Livre livre : catalogue) {
            if (livre.isDisponible()) {
                disponibles.add(livre);
            }
        }
        return disponibles;
    }
    
    public void afficherCatalogue() {
        System.out.println("\n🏛️ === " + nom.toUpperCase() + " ===");
        System.out.println("Nombre de livres : " + catalogue.size());
        System.out.println("\nCatalogue :");
        for (int i = 0; i < catalogue.size(); i++) {
            System.out.print((i + 1) + ". ");
            catalogue.get(i).afficher();
        }
    }
    
    public void afficherStatistiques() {
        int disponibles = livresDisponibles().size();
        int empruntes = catalogue.size() - disponibles;
        
        System.out.println("\n📊 === STATISTIQUES ===");
        System.out.println("Total livres    : " + catalogue.size());
        System.out.println("Disponibles     : " + disponibles);
        System.out.println("Empruntés       : " + empruntes);
        System.out.printf("Taux d'emprunt  : %.1f%%%n", (empruntes * 100.0 / catalogue.size()));
    }
}
