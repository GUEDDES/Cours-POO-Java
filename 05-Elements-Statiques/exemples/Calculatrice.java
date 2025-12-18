/**
 * Exemple : Calculatrice Statique
 * Démontre : Méthodes statiques, constantes, classe utilitaire
 */

class Calculatrice {
    // Constantes mathématiques
    public static final double PI = 3.14159265359;
    public static final double E = 2.71828182846;
    
    // Constructeur privé (empêche l'instanciation)
    private Calculatrice() {
        throw new AssertionError("Classe utilitaire : ne pas instancier");
    }
    
    // Opérations de base
    public static double addition(double a, double b) {
        return a + b;
    }
    
    public static double soustraction(double a, double b) {
        return a - b;
    }
    
    public static double multiplication(double a, double b) {
        return a * b;
    }
    
    public static double division(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Division par zéro impossible");
        }
        return a / b;
    }
    
    // Puissance
    public static double puissance(double base, int exposant) {
        double resultat = 1;
        for (int i = 0; i < Math.abs(exposant); i++) {
            resultat *= base;
        }
        return exposant >= 0 ? resultat : 1 / resultat;
    }
    
    // Racine carrée
    public static double racineCarree(double nombre) {
        if (nombre < 0) {
            throw new IllegalArgumentException("Racine carrée de nombre négatif");
        }
        return Math.sqrt(nombre);
    }
    
    // Factorielle
    public static long factorielle(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Factorielle de nombre négatif");
        }
        if (n == 0 || n == 1) {
            return 1;
        }
        long resultat = 1;
        for (int i = 2; i <= n; i++) {
            resultat *= i;
        }
        return resultat;
    }
    
    // Aire du cercle
    public static double aireCercle(double rayon) {
        if (rayon < 0) {
            throw new IllegalArgumentException("Rayon négatif");
        }
        return PI * rayon * rayon;
    }
    
    // Périmètre du cercle
    public static double perimetreCercle(double rayon) {
        if (rayon < 0) {
            throw new IllegalArgumentException("Rayon négatif");
        }
        return 2 * PI * rayon;
    }
    
    // Aire du rectangle
    public static double aireRectangle(double longueur, double largeur) {
        if (longueur < 0 || largeur < 0) {
            throw new IllegalArgumentException("Dimensions négatives");
        }
        return longueur * largeur;
    }
    
    // Aire du triangle
    public static double aireTriangle(double base, double hauteur) {
        if (base < 0 || hauteur < 0) {
            throw new IllegalArgumentException("Dimensions négatives");
        }
        return (base * hauteur) / 2;
    }
    
    // Moyenne de plusieurs nombres
    public static double moyenne(double... nombres) {
        if (nombres.length == 0) {
            throw new IllegalArgumentException("Aucun nombre fourni");
        }
        double somme = 0;
        for (double nombre : nombres) {
            somme += nombre;
        }
        return somme / nombres.length;
    }
    
    // Maximum de plusieurs nombres
    public static double max(double... nombres) {
        if (nombres.length == 0) {
            throw new IllegalArgumentException("Aucun nombre fourni");
        }
        double maximum = nombres[0];
        for (double nombre : nombres) {
            if (nombre > maximum) {
                maximum = nombre;
            }
        }
        return maximum;
    }
    
    // Minimum de plusieurs nombres
    public static double min(double... nombres) {
        if (nombres.length == 0) {
            throw new IllegalArgumentException("Aucun nombre fourni");
        }
        double minimum = nombres[0];
        for (double nombre : nombres) {
            if (nombre < minimum) {
                minimum = nombre;
            }
        }
        return minimum;
    }
}

// Classe de test
public class TestCalculatrice {
    public static void main(String[] args) {
        System.out.println("\n🧮 === CALCULATRICE STATIQUE ===\n");
        
        // Opérations de base
        System.out.println("--- Opérations de Base ---");
        System.out.println("10 + 5 = " + Calculatrice.addition(10, 5));
        System.out.println("10 - 5 = " + Calculatrice.soustraction(10, 5));
        System.out.println("10 * 5 = " + Calculatrice.multiplication(10, 5));
        System.out.println("10 / 5 = " + Calculatrice.division(10, 5));
        
        // Puissance et racine
        System.out.println("\n--- Puissance et Racine ---");
        System.out.println("2^8 = " + Calculatrice.puissance(2, 8));
        System.out.println("2^-3 = " + Calculatrice.puissance(2, -3));
        System.out.println("√16 = " + Calculatrice.racineCarree(16));
        
        // Factorielle
        System.out.println("\n--- Factorielle ---");
        System.out.println("5! = " + Calculatrice.factorielle(5));
        System.out.println("10! = " + Calculatrice.factorielle(10));
        
        // Géométrie
        System.out.println("\n--- Géométrie ---");
        System.out.printf("Aire cercle (r=5) = %.2f\n", Calculatrice.aireCercle(5));
        System.out.printf("Périmètre cercle (r=5) = %.2f\n", Calculatrice.perimetreCercle(5));
        System.out.println("Aire rectangle (10x5) = " + Calculatrice.aireRectangle(10, 5));
        System.out.println("Aire triangle (b=10, h=6) = " + Calculatrice.aireTriangle(10, 6));
        
        // Statistiques
        System.out.println("\n--- Statistiques ---");
        System.out.println("Moyenne (10, 20, 30, 40, 50) = " + 
                           Calculatrice.moyenne(10, 20, 30, 40, 50));
        System.out.println("Max (5, 12, 3, 18, 9) = " + 
                           Calculatrice.max(5, 12, 3, 18, 9));
        System.out.println("Min (5, 12, 3, 18, 9) = " + 
                           Calculatrice.min(5, 12, 3, 18, 9));
        
        // Constantes
        System.out.println("\n--- Constantes ---");
        System.out.println("PI = " + Calculatrice.PI);
        System.out.println("E = " + Calculatrice.E);
        
        // Test d'erreur
        System.out.println("\n--- Test d'Erreur ---");
        try {
            Calculatrice.division(10, 0);
        } catch (ArithmeticException e) {
            System.out.println("❌ Exception capturée : " + e.getMessage());
        }
        
        // Tentative d'instanciation (va échouer)
        System.out.println("\n--- Tentative d'Instanciation ---");
        try {
            // Calculatrice calc = new Calculatrice();  // Décommenter pour voir l'erreur
            System.out.println("✅ Classe utilitaire : instanciation bloquée");
        } catch (AssertionError e) {
            System.out.println("❌ " + e.getMessage());
        }
    }
}
