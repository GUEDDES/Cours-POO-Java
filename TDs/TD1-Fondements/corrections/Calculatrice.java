import java.util.Scanner;

public class Calculatrice {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Saisie des deux nombres
        System.out.print("Entrez le premier nombre : ");
        int a = sc.nextInt();
        System.out.print("Entrez le deuxieme nombre : ");
        int b = sc.nextInt();
        
        // Saisie du type d'operation
        System.out.println("\nChoisissez l'operation :");
        System.out.println("1 - Addition");
        System.out.println("2 - Soustraction");
        System.out.println("3 - Multiplication");
        System.out.println("4 - Division");
        System.out.print("Votre choix : ");
        int operation = sc.nextInt();
        
        // Calcul et affichage selon l'operation
        switch (operation) {
            case 1:
                System.out.println("Somme : " + a + " + " + b +
                    " = " + (a + b));
                break;
            case 2:
                System.out.println("Difference : " + a + " - " +
                    b + " = " + (a - b));
                break;
            case 3:
                System.out.println("Produit : " + a + " x " + b +
                    " = " + (a * b));
                break;
            case 4:
                if (b != 0) {
                    System.out.println("Quotient : " + a + " / " +
                        b + " = " + ((double) a / b));
                } else {
                    System.out.println("Erreur : Division par zero.");
                }
                break;
            default:
                System.out.println("Operation invalide.");
        }
        
        sc.close();
    }
}
