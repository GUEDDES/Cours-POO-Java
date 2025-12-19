/**
 * Solution Exercice 1 : Hiérarchie de Véhicules
 * 
 * Héritage : Vehicule → VehiculeTerrestre/VehiculeAerien → Voiture/Moto/Avion
 * 
 * @author Cours POO Java
 */
public class Exercice1Solution {
    public static void main(String[] args) {
        testVehicules();
    }
    
    public static void testVehicules() {
        System.out.println("=== TEST HIÉRARCHIE VÉHICULES ===");
        
        // Créer des véhicules
        System.out.println("\n--- Création des véhicules ---");
        Voiture voiture = new Voiture("Peugeot", "308", 2023, 220, 4, 5, "Essence");
        Moto moto = new Moto("Yamaha", "MT-07", 2024, 180, 2, false);
        Avion avion = new Avion("Boeing", "737", 2020, 850, 10000, 180, "Air France");
        
        // POLYMORPHISME : Tableau de véhicules
        Vehicule[] vehicules = {voiture, moto, avion};
        
        System.out.println("\n=== TOUS LES VÉHICULES DÉMARRENT ===");
        for (Vehicule v : vehicules) {
            v.demarrer();  // Appelle la bonne méthode selon le type
        }
        
        System.out.println("\n=== TOUS LES VÉHICULES S'ARRÊTENT ===");
        for (Vehicule v : vehicules) {
            v.arreter();
        }
        
        System.out.println("\n=== VÉHICULES TERRESTRES KLAXONNENT ===");
        for (Vehicule v : vehicules) {
            if (v instanceof VehiculeTerrestre) {
                ((VehiculeTerrestre) v).klaxonner();
            }
        }
        
        System.out.println("\n=== AVION VOLE ===");
        avion.decoller();
        avion.activerPiloteAutomatique();
        avion.atterrir();
        
        System.out.println("\n=== VOITURE SPÉCIFIQUE ===");
        voiture.ouvrirCoffre();
        voiture.fairePleinEssence();
        
        System.out.println("\n=== INFORMATIONS DÉTAILLÉES ===");
        for (Vehicule v : vehicules) {
            v.afficherInfos();
            System.out.println();
        }
        
        System.out.println("\n=== VITESSES EN MPH ===");
        for (Vehicule v : vehicules) {
            System.out.printf("%s %s : %.2f km/h = %.2f mph%n", 
                v.getClass().getSimpleName(), 
                v.getModele(),
                v.getVitesseMax(),
                v.convertirVitesseEnMph());
        }
        
        System.out.println("\n=== STATISTIQUES ===");
        System.out.println("Nombre total de véhicules créés : " + Vehicule.getNombreVehicules());
    }
}

// Classe mère abstraite
abstract class Vehicule {
    protected String marque;
    protected String modele;
    protected int annee;
    protected double vitesseMax;  // en km/h
    private static int nombreVehicules = 0;
    
    public Vehicule(String marque, String modele, int annee, double vitesseMax) {
        this.marque = marque;
        this.modele = modele;
        this.annee = annee;
        this.vitesseMax = vitesseMax;
        nombreVehicules++;
        System.out.println("✅ Véhicule créé : " + marque + " " + modele);
    }
    
    // Méthodes abstraites
    public abstract void demarrer();
    public abstract void arreter();
    
    // Méthodes concrètes
    public void afficherInfos() {
        System.out.println("=== " + getClass().getSimpleName() + " ===");
        System.out.println("Marque       : " + marque);
        System.out.println("Modèle       : " + modele);
        System.out.println("Année        : " + annee);
        System.out.printf("Vitesse max  : %.0f km/h%n", vitesseMax);
    }
    
    public double convertirVitesseEnMph() {
        return vitesseMax * 0.621371;
    }
    
    // Getters
    public String getMarque() { return marque; }
    public String getModele() { return modele; }
    public double getVitesseMax() { return vitesseMax; }
    public static int getNombreVehicules() { return nombreVehicules; }
}

// Classe intermédiaire abstraite
abstract class VehiculeTerrestre extends Vehicule {
    protected int nombreRoues;
    
    public VehiculeTerrestre(String marque, String modele, int annee, double vitesseMax, int nombreRoues) {
        super(marque, modele, annee, vitesseMax);
        this.nombreRoues = nombreRoues;
    }
    
    public abstract void klaxonner();
    
    @Override
    public void afficherInfos() {
        super.afficherInfos();
        System.out.println("Nombre roues : " + nombreRoues);
    }
}

abstract class VehiculeAerien extends Vehicule {
    protected double altitudeMax;  // en mètres
    
    public VehiculeAerien(String marque, String modele, int annee, double vitesseMax, double altitudeMax) {
        super(marque, modele, annee, vitesseMax);
        this.altitudeMax = altitudeMax;
    }
    
    public abstract void decoller();
    public abstract void atterrir();
    
    @Override
    public void afficherInfos() {
        super.afficherInfos();
        System.out.printf("Altitude max : %.0f m%n", altitudeMax);
    }
}

// Classes concrètes
class Voiture extends VehiculeTerrestre {
    private int nombrePortes;
    private String typeCarburant;
    
    public Voiture(String marque, String modele, int annee, double vitesseMax, 
                   int nombreRoues, int nombrePortes, String typeCarburant) {
        super(marque, modele, annee, vitesseMax, nombreRoues);
        this.nombrePortes = nombrePortes;
        this.typeCarburant = typeCarburant;
    }
    
    @Override
    public void demarrer() {
        System.out.println("🚗 La voiture " + marque + " " + modele + " démarre (moteur " + typeCarburant + ")");
    }
    
    @Override
    public void arreter() {
        System.out.println("🛑 La voiture s'arrête");
    }
    
    @Override
    public void klaxonner() {
        System.out.println("📢 Beep beep!");
    }
    
    public void ouvrirCoffre() {
        System.out.println("📦 Coffre ouvert");
    }
    
    public void fairePleinEssence() {
        System.out.println("⛽ Plein de " + typeCarburant + " effectué");
    }
    
    @Override
    public void afficherInfos() {
        super.afficherInfos();
        System.out.println("Portes       : " + nombrePortes);
        System.out.println("Carburant    : " + typeCarburant);
    }
}

class Moto extends VehiculeTerrestre {
    private boolean avecSidecar;
    
    public Moto(String marque, String modele, int annee, double vitesseMax, 
                int nombreRoues, boolean avecSidecar) {
        super(marque, modele, annee, vitesseMax, nombreRoues);
        this.avecSidecar = avecSidecar;
    }
    
    @Override
    public void demarrer() {
        System.out.println("🏍️ La moto " + marque + " " + modele + " vrombit");
    }
    
    @Override
    public void arreter() {
        System.out.println("🛑 La moto s'arrête");
    }
    
    @Override
    public void klaxonner() {
        System.out.println("📢 Tut tut!");
    }
    
    @Override
    public void afficherInfos() {
        super.afficherInfos();
        System.out.println("Sidecar      : " + (avecSidecar ? "Oui" : "Non"));
    }
}

class Avion extends VehiculeAerien {
    private int nombrePassagers;
    private String compagnie;
    
    public Avion(String marque, String modele, int annee, double vitesseMax, 
                 double altitudeMax, int nombrePassagers, String compagnie) {
        super(marque, modele, annee, vitesseMax, altitudeMax);
        this.nombrePassagers = nombrePassagers;
        this.compagnie = compagnie;
    }
    
    @Override
    public void demarrer() {
        System.out.println("✈️ L'avion " + marque + " " + modele + " allume ses réacteurs");
    }
    
    @Override
    public void arreter() {
        System.out.println("🛑 Réacteurs coupés");
    }
    
    @Override
    public void decoller() {
        System.out.println("🛫 Décollage... altitude max : " + (int)altitudeMax + "m");
    }
    
    @Override
    public void atterrir() {
        System.out.println("🛬 Atterrissage en cours...");
        System.out.println("✅ Atterrissage réussi");
    }
    
    public void activerPiloteAutomatique() {
        System.out.println("✈️ Pilote automatique activé");
    }
    
    @Override
    public void afficherInfos() {
        super.afficherInfos();
        System.out.println("Passagers    : " + nombrePassagers);
        System.out.println("Compagnie    : " + compagnie);
    }
}
