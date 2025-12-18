/**
 * Exemple 1 : Classe Voiture
 * 
 * Objectif : Illustrer la création d'une classe avec constructeurs, attributs et méthodes
 * Concepts : 
 * - Attributs d'instance
 * - Constructeurs (par défaut et avec paramètres)
 * - Méthodes d'instance
 * - Méthode toString()
 * 
 * @author Cours POO Java
 */
public class Voiture {
    // Attributs d'instance
    private String marque;
    private String modele;
    private int annee;
    private double kilometrage;
    private boolean estDemarree;
    
    // Constructeur par défaut
    public Voiture() {
        this.marque = "Inconnue";
        this.modele = "Inconnu";
        this.annee = 2024;
        this.kilometrage = 0.0;
        this.estDemarree = false;
    }
    
    // Constructeur avec paramètres
    public Voiture(String marque, String modele, int annee) {
        this.marque = marque;
        this.modele = modele;
        this.annee = annee;
        this.kilometrage = 0.0;
        this.estDemarree = false;
    }
    
    // Méthode pour démarrer la voiture
    public void demarrer() {
        if (!estDemarree) {
            estDemarree = true;
            System.out.println("🚗 " + marque + " " + modele + " a démarré");
        } else {
            System.out.println("⚠️ La voiture est déjà démarrée");
        }
    }
    
    // Méthode pour arrêter la voiture
    public void arreter() {
        if (estDemarree) {
            estDemarree = false;
            System.out.println("🛑 " + marque + " " + modele + " s'est arrêtée");
        } else {
            System.out.println("⚠️ La voiture est déjà arrêtée");
        }
    }
    
    // Méthode pour rouler
    public void rouler(double distance) {
        if (!estDemarree) {
            System.out.println("❌ Impossible de rouler : la voiture n'est pas démarrée");
            return;
        }
        
        if (distance <= 0) {
            System.out.println("❌ Distance invalide");
            return;
        }
        
        kilometrage += distance;
        System.out.printf("✅ Vous avez parcouru %.1f km (Total : %.1f km)%n", distance, kilometrage);
    }
    
    // Méthode pour afficher les informations
    public void afficherInfos() {
        System.out.println("\n========== INFORMATIONS VOITURE ==========");
        System.out.println("Marque      : " + marque);
        System.out.println("Modèle      : " + modele);
        System.out.println("Année       : " + annee);
        System.out.printf("Kilométrage : %.1f km%n", kilometrage);
        System.out.println("État        : " + (estDemarree ? "✅ Démarrée" : "🛑 Arrêtée"));
        System.out.println("==========================================\n");
    }
    
    // Méthode toString()
    @Override
    public String toString() {
        return marque + " " + modele + " (" + annee + ") - " + kilometrage + " km";
    }
    
    // Programme de test
    public static void main(String[] args) {
        System.out.println("=== TEST CLASSE VOITURE ===");
        
        // Créer une voiture avec le constructeur par défaut
        Voiture voiture1 = new Voiture();
        voiture1.afficherInfos();
        
        // Créer une voiture avec le constructeur paramétré
        Voiture voiture2 = new Voiture("Peugeot", "308", 2023);
        voiture2.afficherInfos();
        
        // Tester les méthodes
        System.out.println("--- Scénario d'utilisation ---");
        voiture2.demarrer();
        voiture2.rouler(50.5);
        voiture2.rouler(30.0);
        voiture2.arreter();
        voiture2.rouler(10.0);  // Tentative de rouler alors qu'elle est arrêtée
        
        // Afficher les informations finales
        voiture2.afficherInfos();
        
        // Utiliser toString()
        System.out.println("Représentation : " + voiture2);
    }
}
