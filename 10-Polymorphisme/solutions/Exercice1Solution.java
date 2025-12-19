/**
 * Solution Exercice 1 : Système de Formes Géométriques
 * 
 * Polymorphisme : Classe abstraite + Interfaces
 * 
 * @author Cours POO Java
 */
import java.util.Arrays;
import java.util.Comparator;

public class Exercice1Solution {
    public static void main(String[] args) {
        testFormes();
    }
    
    public static void testFormes() {
        System.out.println("=== SYSTÈME DE FORMES GÉOMÉTRIQUES ===");
        
        // Créer des formes
        System.out.println("\n--- Création des formes ---");
        Forme[] formes = {
            new Cercle(0, 0, "Rouge", 5),
            new Rectangle(10, 10, "Bleu", 8, 4),
            new Triangle(20, 20, "Vert", 3, 4, 5),
            new Carre(30, 30, "Jaune", 6)
        };
        
        System.out.println("\n=== POLYMORPHISME : AFFICHAGE ===");
        for (Forme forme : formes) {
            forme.afficherInfos();
            System.out.println();
        }
        
        System.out.println("\n=== DESSIN DES FORMES ===");
        for (Forme forme : formes) {
            forme.dessiner();
            System.out.println();
        }
        
        System.out.println("\n=== CHANGEMENT DE COULEUR ===");
        formes[0].setCouleur("Violet");
        formes[0].afficherCouleur();
        
        System.out.println("\n=== DÉPLACEMENT ===");
        formes[1].deplacer(50, 50);
        formes[1].afficherInfos();
        
        System.out.println("\n=== CALCUL AIRE TOTALE ===");
        double aireTotale = 0;
        for (Forme forme : formes) {
            aireTotale += forme.calculerAire();
        }
        System.out.printf("Aire totale de toutes les formes : %.2f%n", aireTotale);
        
        System.out.println("\n=== INSTANCEOF ET CASTING ===");
        for (Forme forme : formes) {
            if (forme instanceof Cercle) {
                System.out.println("● C'est un cercle !");
            } else if (forme instanceof Carre) {
                System.out.println("■ C'est un carré !");
            } else if (forme instanceof Rectangle) {
                System.out.println("▭ C'est un rectangle !");
            } else if (forme instanceof Triangle) {
                System.out.println("△ C'est un triangle !");
            }
        }
        
        System.out.println("\n=== REDIMENSIONNEMENT ===");
        if (formes[0] instanceof Redimensionnable) {
            Redimensionnable r = (Redimensionnable) formes[0];
            System.out.println("Avant agrandissement :");
            formes[0].afficherInfos();
            r.agrandir(2.0);
            System.out.println("\nAprès agrandissement (x2) :");
            formes[0].afficherInfos();
        }
        
        System.out.println("\n=== COMPARAISON AIRES ===");
        Forme.comparerAires(formes[0], formes[2]);
        
        System.out.println("\n=== TRI PAR AIRE ===");
        Arrays.sort(formes);
        System.out.println("Formes triées par aire (croissant) :");
        for (int i = 0; i < formes.length; i++) {
            System.out.printf("%d. %s - Aire: %.2f%n", 
                i+1, formes[i].getNom(), formes[i].calculerAire());
        }
    }
}

// Interfaces
interface Dessinable {
    void dessiner();
    void effacer();
    void deplacer(int x, int y);
}

interface Colorable {
    void setCouleur(String couleur);
    String getCouleur();
    void afficherCouleur();
}

interface Redimensionnable {
    void agrandir(double facteur);
    void reduire(double facteur);
}

// Classe abstraite
abstract class Forme implements Dessinable, Colorable, Comparable<Forme> {
    protected int x, y;
    protected String couleur;
    
    public Forme(int x, int y, String couleur) {
        this.x = x;
        this.y = y;
        this.couleur = couleur;
    }
    
    // Méthodes abstraites
    public abstract double calculerAire();
    public abstract double calculerPerimetre();
    public abstract String getNom();
    
    // Méthode concrète
    public void afficherInfos() {
        System.out.println("=== " + getNom() + " ===");
        System.out.println("Couleur      : " + couleur);
        System.out.println("Position     : (" + x + ", " + y + ")");
        System.out.printf("Aire         : %.2f%n", calculerAire());
        System.out.printf("Périmètre    : %.2f%n", calculerPerimetre());
    }
    
    // Implémentation Colorable
    @Override
    public void setCouleur(String couleur) {
        this.couleur = couleur;
        System.out.println("🎨 Couleur changée : " + couleur);
    }
    
    @Override
    public String getCouleur() {
        return couleur;
    }
    
    @Override
    public void afficherCouleur() {
        System.out.println("🎨 Couleur : " + couleur);
    }
    
    // Méthode statique
    public static void comparerAires(Forme f1, Forme f2) {
        double aire1 = f1.calculerAire();
        double aire2 = f2.calculerAire();
        System.out.printf("Comparaison : %s (%.2f) vs %s (%.2f)%n", 
            f1.getNom(), aire1, f2.getNom(), aire2);
        if (aire1 > aire2) {
            System.out.printf("%s a une aire plus grande%n", f1.getNom());
        } else if (aire1 < aire2) {
            System.out.printf("%s a une aire plus grande%n", f2.getNom());
        } else {
            System.out.println("Aires égales");
        }
    }
    
    // Implémentation Comparable
    @Override
    public int compareTo(Forme autre) {
        return Double.compare(this.calculerAire(), autre.calculerAire());
    }
}

// Classes concrètes
class Cercle extends Forme implements Redimensionnable {
    private double rayon;
    
    public Cercle(int x, int y, String couleur, double rayon) {
        super(x, y, couleur);
        this.rayon = rayon;
    }
    
    @Override
    public double calculerAire() {
        return Math.PI * rayon * rayon;
    }
    
    @Override
    public double calculerPerimetre() {
        return 2 * Math.PI * rayon;
    }
    
    @Override
    public String getNom() {
        return "Cercle";
    }
    
    @Override
    public void dessiner() {
        System.out.println("● Dessin d'un cercle de rayon " + rayon);
        System.out.println("    ***    ");
        System.out.println("  *     *  ");
        System.out.println(" *       * ");
        System.out.println("  *     *  ");
        System.out.println("    ***    ");
    }
    
    @Override
    public void effacer() {
        System.out.println("○ Cercle effacé");
    }
    
    @Override
    public void deplacer(int newX, int newY) {
        System.out.printf("Cercle déplacé de (%d,%d) vers (%d,%d)%n", x, y, newX, newY);
        this.x = newX;
        this.y = newY;
    }
    
    @Override
    public void agrandir(double facteur) {
        rayon *= facteur;
        System.out.printf("➕ Cercle agrandi (x%.1f) - Nouveau rayon : %.2f%n", facteur, rayon);
    }
    
    @Override
    public void reduire(double facteur) {
        rayon /= facteur;
        System.out.printf("➖ Cercle réduit (/%.1f) - Nouveau rayon : %.2f%n", facteur, rayon);
    }
}

class Rectangle extends Forme implements Redimensionnable {
    protected double longueur;
    protected double largeur;
    
    public Rectangle(int x, int y, String couleur, double longueur, double largeur) {
        super(x, y, couleur);
        this.longueur = longueur;
        this.largeur = largeur;
    }
    
    @Override
    public double calculerAire() {
        return longueur * largeur;
    }
    
    @Override
    public double calculerPerimetre() {
        return 2 * (longueur + largeur);
    }
    
    @Override
    public String getNom() {
        return "Rectangle";
    }
    
    @Override
    public void dessiner() {
        System.out.printf("▭ Dessin d'un rectangle %.1f x %.1f%n", longueur, largeur);
        System.out.println("**********");
        System.out.println("*        *");
        System.out.println("*        *");
        System.out.println("**********");
    }
    
    @Override
    public void effacer() {
        System.out.println("▭ Rectangle effacé");
    }
    
    @Override
    public void deplacer(int newX, int newY) {
        System.out.printf("Rectangle déplacé de (%d,%d) vers (%d,%d)%n", x, y, newX, newY);
        this.x = newX;
        this.y = newY;
    }
    
    @Override
    public void agrandir(double facteur) {
        longueur *= facteur;
        largeur *= facteur;
        System.out.printf("➕ Rectangle agrandi (x%.1f)%n", facteur);
    }
    
    @Override
    public void reduire(double facteur) {
        longueur /= facteur;
        largeur /= facteur;
        System.out.printf("➖ Rectangle réduit (/%.1f)%n", facteur);
    }
}

class Triangle extends Forme {
    private double cote1, cote2, cote3;
    
    public Triangle(int x, int y, String couleur, double cote1, double cote2, double cote3) {
        super(x, y, couleur);
        this.cote1 = cote1;
        this.cote2 = cote2;
        this.cote3 = cote3;
    }
    
    @Override
    public double calculerAire() {
        // Formule de Héron
        double s = (cote1 + cote2 + cote3) / 2.0;
        return Math.sqrt(s * (s - cote1) * (s - cote2) * (s - cote3));
    }
    
    @Override
    public double calculerPerimetre() {
        return cote1 + cote2 + cote3;
    }
    
    @Override
    public String getNom() {
        return "Triangle";
    }
    
    @Override
    public void dessiner() {
        System.out.printf("△ Dessin d'un triangle (%.1f, %.1f, %.1f)%n", cote1, cote2, cote3);
        System.out.println("    /\\    ");
        System.out.println("   /  \\   ");
        System.out.println("  /    \\  ");
        System.out.println(" /______\\ ");
    }
    
    @Override
    public void effacer() {
        System.out.println("△ Triangle effacé");
    }
    
    @Override
    public void deplacer(int newX, int newY) {
        System.out.printf("Triangle déplacé de (%d,%d) vers (%d,%d)%n", x, y, newX, newY);
        this.x = newX;
        this.y = newY;
    }
}

// BONUS : Carré hérite de Rectangle
class Carre extends Rectangle {
    public Carre(int x, int y, String couleur, double cote) {
        super(x, y, couleur, cote, cote);
    }
    
    @Override
    public String getNom() {
        return "Carré";
    }
    
    @Override
    public void dessiner() {
        System.out.printf("■ Dessin d'un carré de côté %.1f%n", longueur);
        System.out.println("*******");
        System.out.println("*     *");
        System.out.println("*     *");
        System.out.println("*******");
    }
}
