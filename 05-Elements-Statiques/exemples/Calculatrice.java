/**
 * Exemple : Classe Utilitaire Calculatrice
 * 
 * Objectif : Illustrer l'utilisation de méthodes statiques et constantes
 * Concepts :
 * - Méthodes statiques
 * - Constantes (static final)
 * - Constructeur privé (empêcher l'instanciation)
 * - Classe utilitaire
 * 
 * @author Cours POO Java
 */
public class Calculatrice {
    
    // Constantes mathématiques
    public static final double PI = 3.141592653589793;
    public static final double E = 2.718281828459045;
    public static final double PHI = 1.618033988749895;  // Nombre d'or
    
    // Constructeur privé pour empêcher l'instanciation
    private Calculatrice() {
        throw new AssertionError("Classe utilitaire : ne pas instancier");
    }
    
    // ========== OPÉRATIONS DE BASE ==========
    
    public static double additionner(double a, double b) {
        return a + b;
    }
    
    public static double soustraire(double a, double b) {
        return a - b;
    }
    
    public static double multiplier(double a, double b) {
        return a * b;
    }
    
    public static double diviser(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Division par zéro impossible");
        }
        return a / b;
    }
    
    // ========== PUISSANCES ET RACINES ==========
    
    public static double carre(double n) {
        return n * n;
    }
    
    public static double cube(double n) {
        return n * n * n;
    }
    
    public static double puissance(double base, int exposant) {
        if (exposant == 0) return 1;
        if (exposant < 0) return 1.0 / puissance(base, -exposant);
        
        double resultat = 1;
        for (int i = 0; i < exposant; i++) {
            resultat *= base;
        }
        return resultat;
    }
    
    public static double racineCarree(double n) {
        if (n < 0) {
            throw new IllegalArgumentException("Racine carrée d'un nombre négatif");
        }
        return Math.sqrt(n);
    }
    
    // ========== GÉOMÉTRIE ==========
    
    public static double aireRectangle(double longueur, double largeur) {
        return longueur * largeur;
    }
    
    public static double aireCercle(double rayon) {
        return PI * rayon * rayon;
    }
    
    public static double aireTriangle(double base, double hauteur) {
        return (base * hauteur) / 2;
    }
    
    public static double perimetreCercle(double rayon) {
        return 2 * PI * rayon;
    }
    
    public static double perimetre Rectangle(double longueur, double largeur) {
        return 2 * (longueur + largeur);
    }
    
    // ========== FONCTIONS MATHÉMATIQUES ==========
    
    public static long factorielle(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Factorielle définie pour n >= 0");
        }
        if (n == 0 || n == 1) return 1;
        
        long resultat = 1;
        for (int i = 2; i <= n; i++) {
            resultat *= i;
        }
        return resultat;
    }
    
    public static boolean estPair(int n) {
        return n % 2 == 0;
    }
    
    public static boolean estPremier(int n) {
        if (n < 2) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;
        
        for (int i = 3; i <= Math.sqrt(n); i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }
    
    public static int pgcd(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);
        
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
    
    public static int ppcm(int a, int b) {
        return Math.abs(a * b) / pgcd(a, b);
    }
    
    // ========== STATISTIQUES ==========
    
    public static double moyenne(double... nombres) {
        if (nombres.length == 0) {
            throw new IllegalArgumentException("Au moins un nombre requis");
        }
        
        double somme = 0;
        for (double n : nombres) {
            somme += n;
        }
        return somme / nombres.length;
    }
    
    public static double min(double... nombres) {
        if (nombres.length == 0) {
            throw new IllegalArgumentException("Au moins un nombre requis");
        }
        
        double minimum = nombres[0];
        for (double n : nombres) {
            if (n < minimum) minimum = n;
        }
        return minimum;
    }
    
    public static double max(double... nombres) {
        if (nombres.length == 0) {
            throw new IllegalArgumentException("Au moins un nombre requis");
        }
        
        double maximum = nombres[0];
        for (double n : nombres) {
            if (n > maximum) maximum = n;
        }
        return maximum;
    }
    
    // Programme de test
    public static void main(String[] args) {
        System.out.println("=== TEST CALCULATRICE ===");
        
        // Constantes
        System.out.println("\n--- Constantes ---");
        System.out.println("π = " + Calculatrice.PI);
        System.out.println("e = " + Calculatrice.E);
        System.out.println("φ (nombre d'or) = " + Calculatrice.PHI);
        
        // Opérations de base
        System.out.println("\n--- Opérations de base ---");
        System.out.println("5 + 3 = " + Calculatrice.additionner(5, 3));
        System.out.println("5 - 3 = " + Calculatrice.soustraire(5, 3));
        System.out.println("5 × 3 = " + Calculatrice.multiplier(5, 3));
        System.out.println("10 ÷ 2 = " + Calculatrice.diviser(10, 2));
        
        // Puissances
        System.out.println("\n--- Puissances ---");
        System.out.println("5² = " + Calculatrice.carre(5));
        System.out.println("3³ = " + Calculatrice.cube(3));
        System.out.println("2⁸ = " + Calculatrice.puissance(2, 8));
        System.out.println("√16 = " + Calculatrice.racineCarree(16));
        
        // Géométrie
        System.out.println("\n--- Géométrie ---");
        System.out.printf("Aire cercle (r=5) = %.2f%n", Calculatrice.aireCercle(5));
        System.out.printf("Périmètre cercle (r=5) = %.2f%n", Calculatrice.perimetreCercle(5));
        System.out.println("Aire rectangle (5×3) = " + Calculatrice.aireRectangle(5, 3));
        
        // Fonctions mathématiques
        System.out.println("\n--- Fonctions mathématiques ---");
        System.out.println("5! = " + Calculatrice.factorielle(5));
        System.out.println("10 est pair ? " + Calculatrice.estPair(10));
        System.out.println("17 est premier ? " + Calculatrice.estPremier(17));
        System.out.println("PGCD(48, 18) = " + Calculatrice.pgcd(48, 18));
        System.out.println("PPCM(12, 18) = " + Calculatrice.ppcm(12, 18));
        
        // Statistiques
        System.out.println("\n--- Statistiques ---");
        System.out.println("Moyenne(5, 10, 15, 20) = " + Calculatrice.moyenne(5, 10, 15, 20));
        System.out.println("Min(5, 2, 8, 1, 9) = " + Calculatrice.min(5, 2, 8, 1, 9));
        System.out.println("Max(5, 2, 8, 1, 9) = " + Calculatrice.max(5, 2, 8, 1, 9));
    }
}
