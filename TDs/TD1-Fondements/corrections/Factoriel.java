import java.util.Scanner;

public class Factoriel {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Saisie du nombre
        System.out.print("Entrez un nombre entier positif : ");
        int n = sc.nextInt();
        
        // Verification que le nombre est positif
        if (n < 0) {
            System.out.println("Erreur : le factoriel n'est" +
                " pas defini pour les nombres negatifs.");
        } else {
            // Calcul du factoriel
            long factoriel = 1;
            for (int i = 1; i <= n; i++) {
                factoriel *= i;
            }
            
            // Affichage du resultat
            System.out.println("Le factoriel de " + n +
                " est : " + factoriel);
        }
        
        sc.close();
    }
}
