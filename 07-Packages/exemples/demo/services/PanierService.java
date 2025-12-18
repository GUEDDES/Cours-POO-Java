package demo.services;

import demo.models.Produit;
import demo.models.Categorie;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service de gestion du panier d'achat
 * Package : demo.services
 */
public class PanierService {
    private Map<Produit, Integer> items;
    
    public PanierService() {
        this.items = new HashMap<>();
    }
    
    public void ajouterProduit(Produit produit, int quantite) {
        items.put(produit, items.getOrDefault(produit, 0) + quantite);
        System.out.println("✅ " + quantite + "x " + produit.getNom() + " ajouté(s)");
    }
    
    public void afficherPanier() {
        System.out.println("\n🛍️  PANIER :");
        if (items.isEmpty()) {
            System.out.println("  Panier vide");
            return;
        }
        
        double total = 0;
        for (Map.Entry<Produit, Integer> entry : items.entrySet()) {
            Produit p = entry.getKey();
            int qte = entry.getValue();
            double sousTotal = p.getPrix() * qte;
            System.out.printf("  %dx %s - %.2f€%n", qte, p.getNom(), sousTotal);
            total += sousTotal;
        }
        System.out.println("\n  TOTAL : " + String.format("%.2f€", total));
    }
    
    public double getTotal() {
        return items.entrySet().stream()
            .mapToDouble(e -> e.getKey().getPrix() * e.getValue())
            .sum();
    }
    
    public List<Produit> getProduitsPar Categorie(Categorie categorie) {
        List<Produit> resultat = new ArrayList<>();
        for (Produit p : items.keySet()) {
            if (p.getCategorie().equals(categorie)) {
                resultat.add(p);
            }
        }
        return resultat;
    }
}
