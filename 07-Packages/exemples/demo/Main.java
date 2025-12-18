package demo;

import demo.models.Produit;
import demo.models.Categorie;
import demo.services.PanierService;
import demo.utils.FormatUtils;

import java.util.List;

/**
 * Exemple complet d'utilisation des packages
 * 
 * Démontre :
 * - Organisation en packages
 * - Imports spécifiques
 * - Communication entre packages
 */
public class Main {
    
    public static void main(String[] args) {
        System.out.println("🛍️  SYSTÈME DE E-COMMERCE");
        System.out.println("=".repeat(50));
        
        // Créer des catégories
        Categorie electronique = new Categorie("Electronique", "Appareils électroniques");
        Categorie informatique = new Categorie("Informatique", "Matériel informatique");
        
        // Créer des produits
        Produit laptop = new Produit(
            "LAP001",
            "Laptop Dell XPS 15",
            1299.99,
            informatique
        );
        
        Produit smartphone = new Produit(
            "PHONE001",
            "iPhone 15 Pro",
            1199.99,
            electronique
        );
        
        Produit ecouteurs = new Produit(
            "AUDIO001",
            "AirPods Pro 2",
            279.99,
            electronique
        );
        
        // Afficher les produits
        System.out.println("\n📚 CATALOGUE :");
        laptop.afficher();
        smartphone.afficher();
        ecouteurs.afficher();
        
        // Créer un panier
        PanierService panier = new PanierService();
        
        // Ajouter des produits
        System.out.println("\n🛍️  AJOUT AU PANIER :");
        panier.ajouterProduit(laptop, 1);
        panier.ajouterProduit(smartphone, 2);
        panier.ajouterProduit(ecouteurs, 3);
        
        // Afficher le panier
        System.out.println("\n" + "=".repeat(50));
        panier.afficherPanier();
        System.out.println("=".repeat(50));
        
        // Utiliser les utilitaires de formatage
        System.out.println("\n💰 FORMATAGE :");
        double total = panier.getTotal();
        System.out.println("Total brut : " + FormatUtils.formatPrix(total));
        System.out.println("Total arrondi : " + FormatUtils.arrondir(total, 2) + "€");
        
        // Afficher produits par catégorie
        System.out.println("\n📊 PRODUITS PAR CATÉGORIE :");
        List<Produit> produitsElectro = panier.getProduitsPar Categorie(electronique);
        System.out.println("Catégorie " + electronique.getNom() + " : " + produitsElectro.size() + " produits");
    }
}
