package demo.models;

/**
 * Classe représentant un produit
 * Package : demo.models
 */
public class Produit {
    private String reference;
    private String nom;
    private double prix;
    private Categorie categorie;
    
    public Produit(String reference, String nom, double prix, Categorie categorie) {
        this.reference = reference;
        this.nom = nom;
        this.prix = prix;
        this.categorie = categorie;
    }
    
    public void afficher() {
        System.out.printf("[%s] %s - %.2f€ (%s)%n", 
            reference, nom, prix, categorie.getNom());
    }
    
    // Getters
    public String getReference() { return reference; }
    public String getNom() { return nom; }
    public double getPrix() { return prix; }
    public Categorie getCategorie() { return categorie; }
}
