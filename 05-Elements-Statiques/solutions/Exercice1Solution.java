/**
 * Solution Exercice 1 : Classe Utilitaire MathPlus
 * 
 * @author Cours POO Java
 */
import java.util.Arrays;
import java.math.BigInteger;

public class Exercice1Solution {
    public static void main(String[] args) {
        testMathPlus();
    }
    
    public static void testMathPlus() {
        System.out.println("=== TEST CLASSE MATHPLUS ===");
        
        // Constantes
        System.out.println("\n--- Constantes ---");
        System.out.println("Nombre d'or (φ) : " + MathPlus.NOMBRE_OR);
        System.out.println("Racine de 2 : " + MathPlus.RACINE_2);
        System.out.println("Racine de 3 : " + MathPlus.RACINE_3);
        
        // Fibonacci
        System.out.println("\n--- Fibonacci ---");
        for (int i = 0; i <= 10; i++) {
            System.out.println("F(" + i + ") = " + MathPlus.fibonacci(i));
        }
        
        // Nombres premiers
        System.out.println("\n--- Nombres premiers ---");
        int[] testsPremiers = {2, 7, 15, 17, 20, 29, 100};
        for (int n : testsPremiers) {
            System.out.println(n + " est premier ? " + MathPlus.estPremier(n));
        }
        
        System.out.println("\nProchains premiers :");
        System.out.println("Après 10 : " + MathPlus.prochainPremier(10));
        System.out.println("Après 20 : " + MathPlus.prochainPremier(20));
        System.out.println("Après 100 : " + MathPlus.prochainPremier(100));
        
        // Combinatoire
        System.out.println("\n--- Combinatoire ---");
        System.out.println("C(5, 2) = " + MathPlus.coefficientBinomial(5, 2));
        System.out.println("C(10, 3) = " + MathPlus.coefficientBinomial(10, 3));
        System.out.println("C(20, 5) = " + MathPlus.coefficientBinomial(20, 5));
        
        // Statistiques
        System.out.println("\n--- Statistiques ---");
        double[] nombres = {2, 4, 6, 8, 10};
        System.out.println("Tableau : " + Arrays.toString(nombres));
        System.out.printf("Médiane : %.2f%n", MathPlus.mediane(nombres));
        System.out.printf("Écart-type : %.2f%n", MathPlus.ecartType(nombres));
        System.out.printf("Variance : %.2f%n", MathPlus.variance(nombres));
        
        double[] nombres2 = {1, 3, 5, 7};
        System.out.println("\nTableau : " + Arrays.toString(nombres2));
        System.out.printf("Médiane : %.2f%n", MathPlus.mediane(nombres2));
        System.out.printf("Écart-type : %.2f%n", MathPlus.ecartType(nombres2));
        
        // Géométrie
        System.out.println("\n--- Géométrie ---");
        System.out.printf("Distance (0,0) → (3,4) : %.2f%n", MathPlus.distance(0, 0, 3, 4));
        System.out.printf("Distance (1,1) → (4,5) : %.2f%n", MathPlus.distance(1, 1, 4, 5));
        System.out.printf("Aire trapèze (3, 7, 4) : %.2f%n", MathPlus.aireTrapeze(3, 7, 4));
        System.out.printf("Aire trapèze (5, 10, 6) : %.2f%n", MathPlus.aireTrapeze(5, 10, 6));
        
        // Bonus
        System.out.println("\n--- BONUS ---");
        System.out.println("20! = " + MathPlus.factorielleGrande(20));
        System.out.println("\nPremiers 15 nombres premiers :");
        int[] premiers = MathPlus.suitePremiers(15);
        System.out.println(Arrays.toString(premiers));
        
        // Test erreur
        System.out.println("\n--- Tests validation ---");
        try {
            MathPlus.fibonacci(-1);
        } catch (IllegalArgumentException e) {
            System.out.println("✓ Exception capturée : " + e.getMessage());
        }
    }
}

class MathPlus {
    // Constantes
    public static final double NOMBRE_OR = 1.618033988749895;
    public static final double RACINE_2 = 1.41421356237;
    public static final double RACINE_3 = 1.73205080757;
    
    // Constructeur privé
    private MathPlus() {
        throw new AssertionError("Classe utilitaire : ne pas instancier");
    }
    
    // === FIBONACCI ===
    public static long fibonacci(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n doit être >= 0");
        }
        if (n == 0) return 0;
        if (n == 1) return 1;
        
        long a = 0, b = 1;
        for (int i = 2; i <= n; i++) {
            long temp = a + b;
            a = b;
            b = temp;
        }
        return b;
    }
    
    // === NOMBRES PREMIERS ===
    public static boolean estPremier(int n) {
        if (n < 2) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;
        
        for (int i = 3; i <= Math.sqrt(n); i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }
    
    public static int prochainPremier(int n) {
        int candidat = n + 1;
        while (!estPremier(candidat)) {
            candidat++;
        }
        return candidat;
    }
    
    // === COMBINATOIRE ===
    public static long coefficientBinomial(int n, int k) {
        if (k < 0 || k > n) {
            throw new IllegalArgumentException("k doit être entre 0 et n");
        }
        if (k == 0 || k == n) return 1;
        if (k > n - k) k = n - k;  // Optimisation
        
        long resultat = 1;
        for (int i = 0; i < k; i++) {
            resultat = resultat * (n - i) / (i + 1);
        }
        return resultat;
    }
    
    // === STATISTIQUES ===
    public static double mediane(double[] nombres) {
        if (nombres == null || nombres.length == 0) {
            throw new IllegalArgumentException("Tableau vide");
        }
        
        double[] copie = Arrays.copyOf(nombres, nombres.length);
        Arrays.sort(copie);
        
        int milieu = copie.length / 2;
        if (copie.length % 2 == 0) {
            return (copie[milieu - 1] + copie[milieu]) / 2.0;
        } else {
            return copie[milieu];
        }
    }
    
    public static double ecartType(double[] nombres) {
        return Math.sqrt(variance(nombres));
    }
    
    public static double variance(double[] nombres) {
        if (nombres == null || nombres.length == 0) {
            throw new IllegalArgumentException("Tableau vide");
        }
        
        double moyenne = 0;
        for (double n : nombres) {
            moyenne += n;
        }
        moyenne /= nombres.length;
        
        double sommeCarres = 0;
        for (double n : nombres) {
            double ecart = n - moyenne;
            sommeCarres += ecart * ecart;
        }
        
        return sommeCarres / nombres.length;
    }
    
    // === GÉOMÉTRIE ===
    public static double distance(double x1, double y1, double x2, double y2) {
        double dx = x2 - x1;
        double dy = y2 - y1;
        return Math.sqrt(dx * dx + dy * dy);
    }
    
    public static double aireTrapeze(double petiteBase, double grandeBase, double hauteur) {
        if (petiteBase < 0 || grandeBase < 0 || hauteur < 0) {
            throw new IllegalArgumentException("Les dimensions doivent être positives");
        }
        return (petiteBase + grandeBase) * hauteur / 2.0;
    }
    
    // === BONUS ===
    public static BigInteger factorielleGrande(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n doit être >= 0");
        }
        
        BigInteger resultat = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            resultat = resultat.multiply(BigInteger.valueOf(i));
        }
        return resultat;
    }
    
    public static int[] suitePremiers(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n doit être > 0");
        }
        
        int[] premiers = new int[n];
        int compteur = 0;
        int candidat = 2;
        
        while (compteur < n) {
            if (estPremier(candidat)) {
                premiers[compteur] = candidat;
                compteur++;
            }
            candidat++;
        }
        
        return premiers;
    }
}
