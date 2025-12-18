package demo.models;

/**
 * Classe représentant une catégorie de produits
 * Package : demo.models
 */
public class Categorie {
    private String nom;
    private String description;
    
    public Categorie(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }
    
    public String getNom() {
        return nom;
    }
    
    public String getDescription() {
        return description;
    }
}
