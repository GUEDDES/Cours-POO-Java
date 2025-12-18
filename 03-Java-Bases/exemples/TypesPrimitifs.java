/**
 * Exemples d'utilisation des types primitifs Java
 */
public class TypesPrimitifs {
    public static void main(String[] args) {
        // Types entiers
        byte age = 25;
        short annee = 2024;
        int population = 8000000;
        long distanceTerre = 149600000L;  // L pour indiquer un long
        
        System.out.println("=== TYPES ENTIERS ===");
        System.out.println("Age (byte) : " + age);
        System.out.println("Année (short) : " + annee);
        System.out.println("Population (int) : " + population);
        System.out.println("Distance Terre-Soleil (long) : " + distanceTerre + " km");
        
        // Types décimaux
        float prix = 19.99f;  // f pour indiquer un float
        double pi = 3.141592653589793;
        
        System.out.println("\n=== TYPES DÉCIMAUX ===");
        System.out.println("Prix (float) : " + prix + "€");
        System.out.println("Pi (double) : " + pi);
        
        // Type caractère
        char lettre = 'A';
        char symbole = '€';
        
        System.out.println("\n=== TYPE CARACTÈRE ===");
        System.out.println("Lettre : " + lettre);
        System.out.println("Symbole : " + symbole);
        
        // Type booléen
        boolean estMajeur = true;
        boolean aPermis = false;
        
        System.out.println("\n=== TYPE BOOLÉEN ===");
        System.out.println("Est majeur ? " + estMajeur);
        System.out.println("A le permis ? " + aPermis);
        
        // Constantes
        final double TVA = 0.20;
        final int MAX_TENTATIVES = 3;
        
        System.out.println("\n=== CONSTANTES ===");
        System.out.println("Taux TVA : " + (TVA * 100) + "%");
        System.out.println("Max tentatives : " + MAX_TENTATIVES);
    }
}
