/**
 * Exemple : Classe de configuration avec éléments statiques
 * 
 * Démontre l'utilisation de :
 * - Attributs statiques
 * - Constantes (static final)
 * - Méthodes statiques
 * - Bloc d'initialisation statique
 */

public class ConfigurationApp {
    
    // ========== CONSTANTES (static final) ==========
    public static final String NOM_APPLICATION = "MonAppli";
    public static final String VERSION = "2.1.0";
    public static final int PORT_DEFAUT = 8080;
    public static final String URL_BASE = "https://api.example.com";
    
    // ========== ATTRIBUTS STATIQUES ==========
    private static int nombreInstances = 0;
    private static boolean modeDebug = false;
    private static String environnement;
    
    // ========== BLOC D'INITIALISATION STATIQUE ==========
    static {
        System.out.println("⚙️  Initialisation de " + NOM_APPLICATION + " v" + VERSION);
        
        // Déterminer l'environnement
        String env = System.getProperty("env");
        if (env != null) {
            environnement = env;
        } else {
            environnement = "development";
        }
        
        System.out.println("🌍 Environnement : " + environnement);
        
        // Activer debug en développement
        if (environnement.equals("development")) {
            modeDebug = true;
            System.out.println("🔧 Mode debug activé");
        }
    }
    
    // ========== ATTRIBUTS D'INSTANCE ==========
    private int id;
    private String nom;
    
    // ========== CONSTRUCTEUR ==========
    public ConfigurationApp(String nom) {
        this.id = ++nombreInstances;
        this.nom = nom;
        logDebug("Instance créée : " + nom + " (ID: " + id + ")");
    }
    
    // ========== MÉTHODES STATIQUES ==========
    
    /**
     * Active ou désactive le mode debug
     */
    public static void setModeDebug(boolean debug) {
        modeDebug = debug;
        System.out.println("🔧 Mode debug : " + (debug ? "ON" : "OFF"));
    }
    
    /**
     * Retourne le nombre total d'instances créées
     */
    public static int getNombreInstances() {
        return nombreInstances;
    }
    
    /**
     * Affiche un message de debug (seulement si mode debug activé)
     */
    public static void logDebug(String message) {
        if (modeDebug) {
            System.out.println("[DEBUG] " + message);
        }
    }
    
    /**
     * Affiche les informations de configuration
     */
    public static void afficherConfiguration() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("📑 CONFIGURATION");
        System.out.println("=".repeat(50));
        System.out.println("Application : " + NOM_APPLICATION);
        System.out.println("Version : " + VERSION);
        System.out.println("URL API : " + URL_BASE);
        System.out.println("Port : " + PORT_DEFAUT);
        System.out.println("Environnement : " + environnement);
        System.out.println("Mode debug : " + (modeDebug ? "activé" : "désactivé"));
        System.out.println("Instances créées : " + nombreInstances);
        System.out.println("=".repeat(50));
    }
    
    /**
     * Construit une URL complète
     */
    public static String construireURL(String endpoint) {
        return URL_BASE + endpoint;
    }
    
    // ========== MÉTHODES D'INSTANCE ==========
    
    public void afficher() {
        System.out.println("Instance #" + id + " : " + nom);
    }
    
    // ========== PROGRAMME PRINCIPAL ==========
    
    public static void main(String[] args) {
        // Le bloc statique s'exécute ici automatiquement
        
        // Afficher la configuration
        ConfigurationApp.afficherConfiguration();
        
        // Créer des instances
        System.out.println("\n--- Création d'instances ---");
        ConfigurationApp app1 = new ConfigurationApp("Module Utilisateur");
        ConfigurationApp app2 = new ConfigurationApp("Module Produits");
        ConfigurationApp app3 = new ConfigurationApp("Module Commandes");
        
        // Afficher les instances
        System.out.println("\n--- Instances créées ---");
        app1.afficher();
        app2.afficher();
        app3.afficher();
        
        // Utiliser méthodes statiques
        System.out.println("\n--- Utilisation de méthodes statiques ---");
        System.out.println("Nombre total d'instances : " + ConfigurationApp.getNombreInstances());
        
        String urlUsers = ConfigurationApp.construireURL("/users");
        System.out.println("URL utilisateurs : " + urlUsers);
        
        // Désactiver le debug
        ConfigurationApp.setModeDebug(false);
        ConfigurationApp.logDebug("Ce message ne s'affiche pas");
        
        // Réactiver le debug
        ConfigurationApp.setModeDebug(true);
        ConfigurationApp.logDebug("Ce message s'affiche");
        
        // Afficher configuration finale
        ConfigurationApp.afficherConfiguration();
    }
}

/*
SORTIE ATTENDUE :

⚙️  Initialisation de MonAppli v2.1.0
🌍 Environnement : development
🔧 Mode debug activé

==================================================
📑 CONFIGURATION
==================================================
Application : MonAppli
Version : 2.1.0
URL API : https://api.example.com
Port : 8080
Environnement : development
Mode debug : activé
Instances créées : 0
==================================================

--- Création d'instances ---
[DEBUG] Instance créée : Module Utilisateur (ID: 1)
[DEBUG] Instance créée : Module Produits (ID: 2)
[DEBUG] Instance créée : Module Commandes (ID: 3)

--- Instances créées ---
Instance #1 : Module Utilisateur
Instance #2 : Module Produits
Instance #3 : Module Commandes

--- Utilisation de méthodes statiques ---
Nombre total d'instances : 3
URL utilisateurs : https://api.example.com/users
🔧 Mode debug : OFF
🔧 Mode debug : ON
[DEBUG] Ce message s'affiche

==================================================
📑 CONFIGURATION
==================================================
Application : MonAppli
Version : 2.1.0
URL API : https://api.example.com
Port : 8080
Environnement : development
Mode debug : activé
Instances créées : 3
==================================================
*/
