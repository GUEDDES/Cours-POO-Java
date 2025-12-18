/**
 * Exemple : Relation de Composition Voiture-Moteur
 * 
 * Objectif : Illustrer une relation de composition (cycle de vie dépendant)
 * Concepts :
 * - Composition (relation forte)
 * - Le moteur est créé À L'INTÉRIEUR de la voiture
 * - Le moteur disparaît avec la voiture
 * 
 * @author Cours POO Java
 */

class Moteur {
    private int puissance;  // en chevaux
    private String type;    // "Essence", "Diesel", "Électrique"
    private boolean enMarche;
    
    public Moteur(int puissance, String type) {
        this.puissance = puissance;
        this.type = type;
        this.enMarche = false;
        System.out.println("⚙️ Moteur créé : " + type + " - " + puissance + "CV");
    }
    
    public void demarrer() {
        if (!enMarche) {
            enMarche = true;
            System.out.println("🔥 Moteur " + type + " démarré");
        } else {
            System.out.println("⚠️ Moteur déjà en marche");
        }
    }
    
    public void arreter() {
        if (enMarche) {
            enMarche = false;
            System.out.println("🛑 Moteur arrêté");
        } else {
            System.out.println("⚠️ Moteur déjà arrêté");
        }
    }
    
    public boolean estEnMarche() {
        return enMarche;
    }
    
    public void afficher() {
        System.out.println("  ⚙️ Moteur : " + type + " - " + puissance + "CV " + 
                         (enMarche ? "(🔥 En marche)" : "(🛑 Arrêté)"));
    }
}

class Voiture {
    private String marque;
    private String modele;
    private Moteur moteur;  // COMPOSITION : le moteur fait partie intégrante de la voiture
    
    // COMPOSITION : Le moteur est créé DANS le constructeur
    public Voiture(String marque, String modele, int puissanceMoteur, String typeMoteur) {
        this.marque = marque;
        this.modele = modele;
        this.moteur = new Moteur(puissanceMoteur, typeMoteur);  // ✅ Création interne
        System.out.println("🚗 Voiture créée : " + marque + " " + modele);
    }
    
    public void demarrer() {
        System.out.println("\n🔑 Démarrage de " + marque + " " + modele);
        moteur.demarrer();
        System.out.println("✅ " + marque + " " + modele + " prête à rouler\n");
    }
    
    public void arreter() {
        System.out.println("\n🛑 Arrêt de " + marque + " " + modele);
        moteur.arreter();
        System.out.println("✅ " + marque + " " + modele + " arrêtée\n");
    }
    
    public void rouler(int vitesse) {
        if (!moteur.estEnMarche()) {
            System.out.println("❌ Impossible de rouler : moteur arrêté");
            return;
        }
        System.out.println("🏎️ " + marque + " " + modele + " roule à " + vitesse + " km/h");
    }
    
    public void afficherInfos() {
        System.out.println("\n========== INFORMATIONS VOITURE ==========");
        System.out.println("🚗 Marque : " + marque);
        System.out.println("📛 Modèle : " + modele);
        moteur.afficher();
        System.out.println("=========================================\n");
    }
}

public class VoitureMoteur {
    public static void main(String[] args) {
        System.out.println("=== COMPOSITION : VOITURE ET MOTEUR ===");
        
        // Créer une voiture (le moteur est créé automatiquement)
        System.out.println("\n--- Création ---");
        Voiture voiture1 = new Voiture("Peugeot", "308", 130, "Essence");
        voiture1.afficherInfos();
        
        // Utiliser la voiture
        System.out.println("--- Utilisation ---");
        voiture1.demarrer();
        voiture1.rouler(90);
        voiture1.rouler(120);
        voiture1.arreter();
        
        voiture1.rouler(50);  // Tentative alors que moteur arrêté
        
        // Créer une autre voiture
        System.out.println("\n--- Autre voiture ---");
        Voiture voiture2 = new Voiture("Tesla", "Model 3", 283, "Électrique");
        voiture2.demarrer();
        voiture2.rouler(150);
        voiture2.afficherInfos();
        
        // ❌ Impossible d'accéder au moteur de l'extérieur
        // voiture1.moteur.demarrer();  // ERREUR : moteur est privé
        
        // ✅ Si la voiture disparaît, le moteur disparaît aussi
        System.out.println("--- Suppression ---");
        voiture1 = null;  // La voiture ET son moteur sont détruits
        System.out.println("⚠️ voiture1 supprimée (moteur détruit aussi)");
    }
}
