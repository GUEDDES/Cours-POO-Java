import java.util.Scanner;

public class Complexe {
    // Attributs prives
    private double re; // partie reelle
    private double im; // partie imaginaire
    
    // Constructeur par defaut
    public Complexe() {
        this.re = 0;
        this.im = 0;
    }
    
    // Constructeur avec parametres
    public Complexe(double re, double im) {
        this.re = re;
        this.im = im;
    }
    
    // Constructeur de copie
    public Complexe(Complexe c) {
        this.re = c.re;
        this.im = c.im;
    }
    
    // Methode afficher
    public void afficher() {
        if (im >= 0) {
            System.out.println("(" + re + " + " + im + "i)");
        } else {
            System.out.println("(" + re + " - " + Math.abs(im) + "i)");
        }
    }
    
    // Methode toString
    @Override
    public String toString() {
        if (im >= 0) {
            return "(" + re + " + " + im + "i)";
        } else {
            return "(" + re + " - " + Math.abs(im) + "i)";
        }
    }
    
    // Addition de deux complexes
    public Complexe addition(Complexe c) {
        return new Complexe(this.re + c.re, this.im + c.im);
    }
    
    // Soustraction de deux complexes
    public Complexe soustraction(Complexe c) {
        return new Complexe(this.re - c.re, this.im - c.im);
    }
    
    // Multiplication : (a+bi)*(c+di) = (ac-bd)+(ad+bc)i
    public Complexe multiplication(Complexe c) {
        double nouvelleRe = this.re * c.re - this.im * c.im;
        double nouvelleIm = this.re * c.im + this.im * c.re;
        return new Complexe(nouvelleRe, nouvelleIm);
    }
    
    // Calcul du module
    public double module() {
        return Math.sqrt(re * re + im * im);
    }
    
    // Programme principal
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Saisie du premier nombre complexe
        System.out.println("Premier nombre complexe :");
        System.out.print("Partie reelle : ");
        double re1 = sc.nextDouble();
        System.out.print("Partie imaginaire : ");
        double im1 = sc.nextDouble();
        Complexe c1 = new Complexe(re1, im1);
        
        // Saisie du deuxieme nombre complexe
        System.out.println("\nDeuxieme nombre complexe :");
        System.out.print("Partie reelle : ");
        double re2 = sc.nextDouble();
        System.out.print("Partie imaginaire : ");
        double im2 = sc.nextDouble();
        Complexe c2 = new Complexe(re2, im2);
        
        // Affichage des resultats
        System.out.println("\n--- Resultats ---");
        System.out.print("C1 = "); c1.afficher();
        System.out.print("C2 = "); c2.afficher();
        
        Complexe somme = c1.addition(c2);
        System.out.print("\nSomme : "); somme.afficher();
        
        Complexe difference = c1.soustraction(c2);
        System.out.print("Difference : "); difference.afficher();
        
        Complexe produit = c1.multiplication(c2);
        System.out.print("Produit : "); produit.afficher();
        
        System.out.println("\nModule de C1 : " + c1.module());
        System.out.println("Module de C2 : " + c2.module());
        
        sc.close();
    }
}
