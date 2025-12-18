package demo.utils;

/**
 * Classe utilitaire pour le formatage
 * Package : demo.utils
 */
public class FormatUtils {
    
    // Constructeur privé pour empêcher l'instanciation
    private FormatUtils() {
        throw new AssertionError("Classe utilitaire : ne pas instancier");
    }
    
    /**
     * Formate un prix avec symbole euro
     */
    public static String formatPrix(double prix) {
        return String.format("%.2f€", prix);
    }
    
    /**
     * Arrondit un nombre à n décimales
     */
    public static double arrondir(double nombre, int decimales) {
        double facteur = Math.pow(10, decimales);
        return Math.round(nombre * facteur) / facteur;
    }
    
    /**
     * Met en majuscules la première lettre
     */
    public static String capitaliser(String texte) {
        if (texte == null || texte.isEmpty()) {
            return texte;
        }
        return texte.substring(0, 1).toUpperCase() + texte.substring(1).toLowerCase();
    }
}
